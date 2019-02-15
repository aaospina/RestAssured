package com.responseextraction.rootpath;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.*; //Added manually

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RootPathExamples {

    static HashMap<String, Object> parameters = new HashMap<String, Object>();

    @BeforeClass
    public static void init(){
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";

        parameters.put("q","select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")");
        parameters.put("format", "json");
        parameters.put("env", "store://datatables.org/alltableswithkeys");
    }

    // Adding multiple assertions in single test

    @Test
    public void test001(){
        given()
                .parameters(parameters)
                .when()
                .get("/yql")
                .then()
                .root("query.results.rate")

                .body("Name", hasItem("USD/INR"))
                .body("Name", hasItems("USD/INR", "USD/THB", "USD/BRL"))
                .body("id", hasItem("USDCAD"))

                .root("query")

                .body("count", lessThan(10))
                .body("count", greaterThanOrEqualTo(4));

    }
}
