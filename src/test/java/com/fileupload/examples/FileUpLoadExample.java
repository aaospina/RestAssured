package com.fileupload.examples;

import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class FileUpLoadExample {

    /**
     * Upload a gift file to zamzar.com and convert it into a png file
     */
    @Test
    public void uploadFileExample(){

        String apiKey = "11ceec3b64c3727e2ca7ac0bdcbf62f4dcf8489e";

        File inputFile = new File(System.getProperty("user.dir")+File.separator+"SampleGIFImage_350kbmb.gif");
        System.out.println(inputFile.length());
        String endpoint = "https://sandbox.zamzar.com/v1/jobs";

        given()
                .auth()
                .basic(apiKey, "")
                .multiPart("source_file", inputFile)
                .multiPart("target_format", "png")
                .when()
                .post(endpoint)
                .then()
                .log()
                .all();
    }

}
