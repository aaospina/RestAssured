package com.filedownloads.examples;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*; //Added manually
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileDownLoadExample {

    //Download a file & compare it with an Expected file
    //Check the size of the file
    @Test
    public void verifyDownloadedFile(){
        //read the input file

        File inputFile = new File(System.getProperty("user.dir")+File.separator+"geckodriver-v0.24.0-linux64.tar.gz");

        //Length of input file
        int expectedSize = (int)inputFile.length();

        System.out.println("The size of expected file is: " + expectedSize + "bytes");

        // https://github.com/mozilla/geckodriver/releases/download/v0.24.0/geckodriver-v0.24.0-linux32.tar.gz

        // Download the file
        byte[] actualValue = given()
                .when()
                .get("https://github.com/mozilla/geckodriver/releases/download/v0.24.0/geckodriver-v0.24.0-linux64.tar.gz")
                .then()
                .extract()
                .asByteArray();

        System.out.println("The size of actual file is: " + actualValue.length + "bytes");

        assertThat(expectedSize, equalTo(actualValue.length));
    }
}
