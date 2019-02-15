package com.soap.examples;

import io.restassured.RestAssured;
import org.junit.Test;

public class HolidayWsExample {

    @Test
    public void getHolidays(){

        String wsdlUrl = "http://www.holydaywebservice.com/HolidayService_v2/HolidayService2.asmx?.wsdl";
        String payload = "";

        RestAssured.given()
                .contentType("text/xml")
                .body(payload)
                .post(wsdlUrl)
                .then()
                .log()
                .all();


    }
}
