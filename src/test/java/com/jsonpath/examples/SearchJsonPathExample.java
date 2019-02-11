package com.jsonpath.examples;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SearchJsonPathExample {

    static final String APIKEY = "uu3j7n9trwby6qy2bfwe2ft8";

    @BeforeClass
    public static void init(){

        RestAssured.baseURI = "http://api.walmartlabs.com";
        RestAssured.basePath = "/v1";
    }

    // 1) Extract num items
    @Test
    public void test001(){

        int numItems = given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .extract()
                .path("numItems");

        System.out.println("-------------------Starting test------------------------------");
        System.out.println("The total number of items are: " + numItems);
        System.out.println("-------------------End of the test----------------------------");
    }

    // 2) Extract query
    @Test
    public void test002(){

        String queryValue = given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .extract()
                .path("query");

        System.out.println("-------------------Starting test------------------------------");
        System.out.println("The search query is: " + queryValue);
        System.out.println("-------------------End of the test----------------------------");
    }

    // 3) Extract first productName by providing list index value
    @Test
    public void test003(){

        String productName = given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .extract()
                .path("items[0].name");

        System.out.println("-------------------Starting test------------------------------");
        System.out.println("The product name is: " + productName);
        System.out.println("-------------------End of the test----------------------------");
    }

    // 4) Get the gift options for the first product
    @Test
    public void test004(){

        HashMap<String, String> giftOptions= given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .extract()
                .path("items[0].giftOptions");

        System.out.println("-------------------Starting test------------------------------");
        System.out.println("The gift options under the first product are: " + giftOptions);
        System.out.println("-------------------End of the test----------------------------");
    }

    // 5) Print the size of items
    @Test
    public void test005(){

        int size = given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .extract()
                .path("items.size()");

        System.out.println("-------------------Starting test------------------------------");
        System.out.println("The size of the items is: " + size);
        System.out.println("-------------------End of the test----------------------------");
    }

    // 6) Get all the names
    @Test
    public void test006(){

        List<String> names = given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .extract()
                .path("items.name");

        System.out.println("-------------------Starting test------------------------------");
        System.out.println("The names of the products are: " + names);
        System.out.println("-------------------End of the test----------------------------");
    }

    // 7) Get the all the values for Name==Apple iPod touch 32GB
    @Test
    public void test007(){

        List<HashMap<String, Object>> values = given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .extract()
                .path("items.findAll{it.name=='Apple iPod touch 32GB'}");

        System.out.println("-------------------Starting test------------------------------");
        System.out.println("The values of item name Apple iPod touch 32GB are : " + values);
        System.out.println("-------------------End of the test----------------------------");
    }

    // 8) Get the sale price for Name==Apple iPod touch 32BG
    @Test
    public void test008(){

        List<Float> salePrice = given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .extract()
                .path("items.findAll{it.name=='Apple iPod touch 32GB'}.salePrice");

        System.out.println("-------------------Starting test------------------------------");
        System.out.println("The sale price of item name Apple iPod touch 32GB are : " + salePrice);
        System.out.println("-------------------End of the test----------------------------");
    }


    // 9) Get the name which have salePrice less than 150
    @Test
    public void test009(){

        List<String> names = given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .extract()
                .path("items.findAll{it.salePrice<150}.name");

        System.out.println("-------------------Starting test------------------------------");
        System.out.println("The items that are priced less than $150 are: " + names);
        System.out.println("-------------------End of the test----------------------------");
    }

    // 10) Get the msrp of items that start with name =Ref
    @Test
    public void test010(){

        List<String> msrpValues = given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .extract()
                .path("items.findAll{it.name==~/Ref.*/}.msrp");


        System.out.println("-------------------Starting test------------------------------");
        System.out.println("The msrp of items that start with Ref  are: " + msrpValues);
        System.out.println("-------------------End of the test----------------------------");
    }

    // 11) Get the saleprice of items that end with name =ed
    @Test
    public void test011(){

        List<String> saleprice = given()
                .queryParam("query", "ipod")
                .queryParam("apikey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .extract()
                .path("items.findAll{it.name==~/.*ed/}.salePrice");

        System.out.println("-------------------Starting test------------------------------");
        System.out.println("The saleprice of items that End with name = ed are: " + saleprice);
        System.out.println("-------------------End of the test----------------------------");
    }

}
