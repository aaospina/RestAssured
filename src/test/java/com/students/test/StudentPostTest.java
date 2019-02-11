package com.students.test;

import com.student.base.TestBase;
import com.student.model.Student;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class StudentPostTest extends TestBase {

    @Test
    public void createNewStudent(){

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
                .when()
                .body(student)
                .post()
                .then()
                .statusCode(201);

    }

}
