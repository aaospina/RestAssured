package com.students.loggingexamples;

import com.student.base.TestBase;
import com.student.model.Student;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class LoggingResponseValues extends TestBase {


    /**
     * this test will print out the response headers
     */
    @Test
    public void test001(){
        System.out.println("--------------------------Printing reponse headers--------------------------------");
        given()
                .param("programme", "Computer Science")
                .param("limit", 1)
                .when()
                .get("/list")
                .then()
                .log()
                .headers()
                .statusCode(200);
    }

    /**
     * this test will print the response Status Line
     */
    @Test
    public void test002(){
        System.out.println("--------------------------Printing status line--------------------------------");
        given()
                .param("programme", "Computer Science")
                .param("limit", 1)
                .when()
                .get("/list")
                .then()
                .log()
                .status()
                .statusCode(200);
    }

    /**
     * this test will print the response body
     */
    @Test
    public void test003(){
        System.out.println("--------------------------Printing response body--------------------------------");
        given()
                .param("programme", "Computer Science")
                .param("limit", 1)
                .when()
                .get("/list")
                .then()
                .log()
                .body()
                .statusCode(200);
    }

    /**
     * this test will print the response in case of an error
     */
    @Test
    public void test004(){
        System.out.println("--------------------------Printing response body in case of an error--------------------------------");
        given()
                .param("programme", "Computer Science")
                .param("limit", -1)
                .when()
                .get("/list")
                .then()
                .log()
                .ifError();
    }

}
