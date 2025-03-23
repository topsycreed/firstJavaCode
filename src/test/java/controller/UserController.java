package controller;

import dto.User;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static apiConstants.Constants.BASE_URL;
import static apiConstants.Constants.DEFAULT_USER;
import static io.restassured.RestAssured.given;

public class UserController implements UserInterface {
    private static final String USER_ENDPOINT = "/user";
    RequestSpecification requestSpecification;

    public UserController() {
        requestSpecification = given().baseUri(BASE_URL + USER_ENDPOINT)
                .accept("application/json")
                .contentType("application/json")
                .filter(new AllureRestAssured());
    }

    @Override
    @Step("Create a new default user")
    public Response addDefaultUser() {
        return given(requestSpecification)
                .when().body(DEFAULT_USER).post()
                .andReturn();
    }

    @Override
    @Step("Create a new user")
    public Response addUser(User user) {
        return given(requestSpecification)
                .when().body(user).post()
                .andReturn();
    }

    @Override
    @Step("Get a user")
    public Response getUser(String userName) {
        return given(requestSpecification)
                .when().get("/" + userName)
                .andReturn();
    }

    @Override
    @Step("Delete a user")
    public Response deleteUser(String userName) {
        return given(requestSpecification)
                .when().delete("/" + userName)
                .andReturn();
    }
}
