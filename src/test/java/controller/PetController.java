package controller;

import io.restassured.specification.RequestSpecification;

import static apiConstants.Constants.BASE_URL;
import static io.restassured.RestAssured.given;

public class PetController {
    private static final String PET_ENDPOINT = "/pet";
    RequestSpecification requestSpecification;

    public PetController() {
        requestSpecification = given().baseUri(BASE_URL + PET_ENDPOINT)
                .accept("application/json")
                .contentType("application/json");
    }

    //finish methods POST, GET, PUT, DELETE
}
