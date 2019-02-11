package com.students.test;

import com.student.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentGetTest extends TestBase {

    @Test
    public void getAllStudentsInformation(){
        /**
         * given()
         *set cookies, add auth, adding parameters, setting headers info
         *.when()
         * GET, POST, PUT, DELETE, ect
         * .then()
         * Validate status code, extract response, extract headers, cookies, extract de response body
         */
        Response response = given()
                .when()
                .get("/list");

        System.out.println(response.body().asString());  //with asString, We see the response as a string
        System.out.println(response.body().prettyPrint());  //with prettyPrint, we see thw response as a format json

        //Validate the status code

        given()
                .when()
                .get("/list")
                .then()
                .statusCode(200);

    }

    @Test
    public void getStudentInfo(){
        Response response = given()
                .when()
                .get("/1");

        //System.out.println(response.body().prettyPrint());

        given()
                .when()
                .get("/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void getStudentFromFA(){
        Response response = given()
                .when()
                .get("/list?programme=Financial Analysis&limit=2");

        //System.out.println(response.body().prettyPeek());

        Response response2 = given()
                .param("programme", "Financial Analysis")
            //.param("programme", "Financial Analysis")
              //  .param("limit", 2)
                .when()
                .get("/list");

        System.out.println(response2);
    }
}
