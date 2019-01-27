package com.student.base;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {

    @BeforeClass
    public static void init(){
        RestAssured.baseURI="http://localhost";
        RestAssured.port=8086;
        RestAssured.basePath="/student";
    }
}
