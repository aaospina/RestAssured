package com.authspringsecurity.examples;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.filter.session.SessionFilter;
import org.junit.BeforeClass;
import org.junit.Test;

public class FormAuthWithCsrf {

    public static SessionFilter filter;

    @BeforeClass
    public static void init(){

        filter = new SessionFilter();

        RestAssured.baseURI = "http://localhost:8086";

        RestAssured.given().auth().form("user", "user", new FormAuthConfig("/login", "uname", "pwd").withAutoDetectionOfCsrf())
                .filter(filter)
                .get();

        System.err.println(filter.getSessionId());
    }

    @Test
    public void getAllStudents(){
        RestAssured.given()
                .sessionId(filter.getSessionId())
                .get("/student/list")
                .then()
                .log()
                .all();
    }
}
