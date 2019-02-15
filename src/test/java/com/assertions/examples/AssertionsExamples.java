package com.assertions.examples;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;  //Added manually
import static org.hamcrest.Matchers.*; //Added manually

import static io.restassured.RestAssured.given;

public class AssertionsExamples {

    static final String APIKEY = "uu3j7n9trwby6qy2bfwe2ft8";

    @BeforeClass
    public static void init(){

        RestAssured.baseURI = "http://api.walmartlabs.com";
        RestAssured.basePath = "/v1";

    }

    // 1) Verify if the number of items is equal to 10
    @Test
    public void test001(){

        given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .body("numItems", equalTo(10));
    }

    // 2) Verify Query
    @Test
    public void test002(){
        given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .body("query", equalToIgnoringCase("IPOD"));
    }

    // 3) Check single name in arraylist
    @Test
    public void test003(){
        given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .body("items.name", hasItem("Apple iPod touch 32GB"));
    }

    // 4) Check multiple names in arraylist
    @Test
    public void test004(){
        given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .body("items.name", hasItems("Apple iPod touch 32GB", "Apple iPod nano 16GB"));
    }

    // 5) Verify the image Entities for the first product (Checking Values inside Map using hasValue())
    @Test
    public void test005(){
        given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .body("items[-1].imageEntities[0]", hasKey("thumbnailImage"));
    }

    // 6) Check hashmap values inside a list
    @Test
    public void test006(){
        given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .body("items.findAll{it.name=='Apple iPod touch 32GB'}", hasItems(hasEntry("name", "Apple iPod touch 32GB")));
    }

    // 7) Checking multiple values in the same statement
    @Test
    public void test007(){
        given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .body("items[-1].imageEntities[0]", hasKey("thumbnailImage"))
                .body("items.findAll{it.name=='Apple iPod touch 32GB'}", hasItems(hasEntry("name", "Apple iPod touch 32GB")))
                .body("items.name", hasItem("Apple iPod touch 32GB"))
                .statusCode(200);
    }

    // 8) Logical assertions
    @Test
    public void test008(){
        given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .body("items.size()", equalTo(10))
                .body("items.size()", greaterThan(5))
                .body("items.size()", lessThan(11))
                .body("items.size()", greaterThanOrEqualTo(10))
                .body("items.size()", greaterThanOrEqualTo(10));
    }

}
