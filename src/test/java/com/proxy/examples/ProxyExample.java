package com.proxy.examples;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProxyExample {

    public static RequestSpecBuilder rSpec;
    public static RequestSpecification rP;

    @BeforeClass
    public static void Init(){

        ProxySpecification ps = new ProxySpecification("localhost", 5555, "http");  //This port is the port where the proxy is running.
        RestAssured.baseURI = "http://localhost:8086/student";

        rSpec = new RequestSpecBuilder();
        rSpec.setProxy(ps);

        rP = rSpec.build();

    }

     @Test
    public void sendRequest(){

        RestAssured.given()
                .spec(rP)
                .when()
                .get("/list")
                .then()
                .log()
                .all();
     }
}
