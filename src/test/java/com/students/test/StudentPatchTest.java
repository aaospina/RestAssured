package com.students.test;

import com.student.base.TestBase;
import com.student.model.Student;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class StudentPatchTest extends TestBase {

    @Test
    public void updateStudent(){

        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Java");
        courses.add("C++");
        courses.add("Python");

        Student student = new Student();
        student.setFirstName("Orlando");
        student.setLastName("Tellez");
        student.setEmail("abrahammmmmmaaaaaaaaaa@gmail.com");
        student.setProgramme("Natural Science");
        student.setCourses(courses);

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(student)
                .patch("/101")
                .then()
                .statusCode(200);

    }
}
