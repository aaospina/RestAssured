package com.jsoup.examples;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.filter.session.SessionFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

import java.util.ArrayList;


public class JsoupExamples {

    public static SessionFilter filter;

    @BeforeClass
    public static void init(){


        RestAssured.baseURI = "http://localhost:8086";
        filter = new SessionFilter();

        RestAssured.given().auth().form("admin", "admin", new FormAuthConfig("/login", "uname", "pwd"))
                .filter(filter).post().then();
    }

    @Test
    public void extractTitle(){

        String response = RestAssured.given()
                .when()
                .get("http://localhost:8086/")
                .asString();

        Document document = Jsoup.parse(response);
        System.out.println("The title of the page is: " + document.title().toUpperCase());
    }

    @Test
    public void extractingElementByTag(){

        String response = RestAssured.given()
                .when()
                .get("http://localhost:8086/")
                .asString();

        Document document = Jsoup.parse(response);

        Elements element = document.getElementsByTag("form");

        System.out.println("The number of form element is: " + element.size());
        for (Element e : element){
            System.out.println(e);
        }


    }

    @Test
    public void extractingElementById(){

        String response = RestAssured.given()
                .when()
                .get("http://localhost:8086/")
                .asString();

        Document document = Jsoup.parse(response);

        Element element = document.getElementById("command");

        System.out.println("The Element contents are: " + element.text());

    }

    @Test
    public void extractingLinks(){

        String response = RestAssured.given()
                .when()
                .get("http://localhost:8086/")
                .asString();

        Document document = Jsoup.parse(response);

        Elements links = document.select("a[href]");

        for (Element e : links){
            System.out.println("The link is: " + e.text());
        }

    }

    @Test
    public void extractingEmailInformation(){

        String response = RestAssured.given()
                .sessionId(filter.getSessionId())
                .when()
                .get("/student/list")
                .asString();

        Document document = Jsoup.parse(response);

        Elements emailId = document.select("table tbody tr td:nth-child(4)");

        ArrayList<String> actualValue = new ArrayList<String>();
        for (Element e : emailId){
            //System.out.println(e.text());
            actualValue.add(e.text());
        }

        assertThat(actualValue, hasItem("faucibus@sed.co.uk"));



    }
}
