package com.specification.examples;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import static io.restassured.RestAssured.given;

public class ResponseSpecificationExample {

    static final String APIKEY = "uu3j7n9trwby6qy2bfwe2ft8";
    static RequestSpecBuilder builder;
    static RequestSpecification requestSpec;

    static ResponseSpecBuilder responseBuilder;
    static ResponseSpecification responseSpec;

    @BeforeClass
    public static void init(){
        RestAssured.baseURI = "http://api.walmartlabs.com";
        RestAssured.basePath = "/v1";

        builder = new RequestSpecBuilder();
        builder.addQueryParam("query", "ipod");
        builder.addQueryParam("apikey", APIKEY);
        builder.addQueryParam("format", "json");
        builder.addQueryParam("facet", "on");
        builder.addHeader("Accept", "*/*");

        requestSpec = builder.build();

        responseBuilder = new ResponseSpecBuilder();
        responseBuilder.expectHeader("Content-Type", "application/json; charset=utf-8");
        responseBuilder.expectHeader("Server", "Mashery Proxy");
        responseBuilder.expectStatusCode(200);

        responseBuilder.expectBody("query", equalTo("ipod"));
        responseBuilder.expectBody("numItems", equalTo(10));
        responseBuilder.expectBody("items.name", hasItem("Apple iPod touch 32GB"));

        responseSpec = responseBuilder.build();
    }

    @Test
    public void test001(){
        given()
                .spec(requestSpec)
                .when()
                .get("/search")
                .then()
                .spec(responseSpec);

    }
}
