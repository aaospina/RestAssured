package com.students.loggingexamples;

import com.student.base.TestBase;
import com.student.model.Student;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class LoggingRequestValues extends TestBase {

    /**
     * this test will print out all the request headers
     */
    @Test
    public void test001(){
        System.out.println("--------------------------Printing request headers--------------------------------");
        given()
                .log()
                .headers()
                .when()
                .get("/1")
                .then()
                .statusCode(200);
    }

    /**
     * this test will print out the request parameters
     */
    @Test
    public void test002(){
        System.out.println("--------------------------Printing request parameters--------------------------------");
        given()
                .param("programme", "Computer Science")
                .param("limit", 1)
                .log()
                .params()
                .when()
                .get("/list")
                .then()
                .statusCode(200);
    }

    @Test
    public void test003(){
        System.out.println("--------------------------Printing request body--------------------------------");
        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Java");
        courses.add("C++");

        Student student = new Student();
        student.setFirstName("Abraham");
        student.setLastName("Ospina");
        student.setEmail("abraham@gmail.com");
        student.setProgramme("Computer Science");
        student.setCourses(courses);

        given()
                .contentType(ContentType.JSON)
                .log()
                .body()
                .when()
                .body(student)
                .post()
                .then()
                .statusCode(201);
    }

    @Test
    public void test004(){
        System.out.println("--------------------------Print out all the details--------------------------------");
        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Java");
        courses.add("C++");

        Student student = new Student();
        student.setFirstName("Abraham");
        student.setLastName("Ospina");
        student.setEmail("abraham@gmail.com");
        student.setProgramme("Computer Science");
        student.setCourses(courses);

        given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .body(student)
                .post()
                .then()
                .statusCode(201);
    }

    /**
     * This test will print Request details if validation fails
     */


    @Test
    public void test005(){
        System.out.println("--------------------------Printing all the Request datails if validation fails--------------------------------");
        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Java");
        courses.add("C++");

        Student student = new Student();
        student.setFirstName("Abraham");
        student.setLastName("Ospina");
        student.setEmail("abraham@gmail.com");
        student.setProgramme("Computer Science");
        student.setCourses(courses);

        given()
                .contentType(ContentType.JSON)
                .log()
                .ifValidationFails()
                .when()
                .body(student)
                .post()
                .then()
                .statusCode(201);
    }
}
