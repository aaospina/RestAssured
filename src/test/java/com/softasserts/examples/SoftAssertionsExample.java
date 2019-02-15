package com.softasserts.examples;

import io.restassured.RestAssured;
import org.junit.Test;

import static org.hamcrest.Matchers.*; //Added manually

public class SoftAssertionsExample {

    @Test
    public void hardAssert(){
        RestAssured.given()
                .when()
                .get("http://localhost:8086/student/list")
                .then()
                .body("[0].firstName", equalTo("Vernon"))
                .body("[0].lastName", equalTo("Harper"))
                .body("[0].email", equalTo("egestas.rhoncus.Proin@massaQuisqueporttitor.org"))
                .body("[0].programme", equalTo("Financial Analysis"));
    }

    @Test
    public void softAssert(){    //In soft assertions you can see in the terminal if you have multiples fails in the assertions
        RestAssured.given()
                .when()
                .get("http://localhost:8086/student/list")
                .then()
                .body("[0].firstName", equalTo("Vernonv"),
                        "[0].lastName", equalTo("Harper"),
                        "[0].email", equalTo("egestas.rhoncus.Proin@massaQuisqueporttitor.orge"),
                        "[0].programme", equalTo("Financial Analysis")
                );
    }
}
