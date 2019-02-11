package com.students.test;

import com.student.base.TestBase;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentDeleteTest extends TestBase {

    @Test
    public void deleteStudent(){
        given()
                .when()
                .delete("/101")
                .then()
                .statusCode(204);
    }
}
