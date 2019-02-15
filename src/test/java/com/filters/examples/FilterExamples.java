package com.filters.examples;

import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.io.output.WriterOutputStream;  //necessary to the filters
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.PrintStream;
import java.io.StringWriter;

public class FilterExamples {

    public static StringWriter requestWriter;
    public static PrintStream requestCapture;

    public static StringWriter responseWriter;
    public static PrintStream responseCapture;

    public static StringWriter errorWriter;
    public static PrintStream errorCapture;

    @BeforeClass  //executed before the class
    public static void init(){
        RestAssured.baseURI = "http://localhost:8086";
        RestAssured.basePath = "/student";
    }

    @Before  //executed before each test in the class
    public void beforeEachTest(){
        requestWriter = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWriter), true);

        responseWriter = new StringWriter();
        responseCapture = new PrintStream(new WriterOutputStream(responseWriter), true);

        errorWriter = new StringWriter();
        errorCapture = new PrintStream(new WriterOutputStream(errorWriter), true);
    }

    @Test
    public void getStudent(){
        String response = RestAssured.given()
                .when()
                .get("/list")
                .asString();

        //System.out.println(response);

        RestAssured
                .given()
                .filter(new RequestLoggingFilter(requestCapture))
                .filter(new ResponseLoggingFilter(responseCapture))
                .when()
                .get("/list");

        System.err.println(requestWriter.toString());

        System.err.println(responseWriter.toString());

        RestAssured
                .given()
                .filter(new ErrorLoggingFilter(errorCapture))
                .when()
                .get("/listsssss");

        System.err.println(errorWriter.toString().toUpperCase());   //This only will print if the status code is between 400 and 500

    }

}
