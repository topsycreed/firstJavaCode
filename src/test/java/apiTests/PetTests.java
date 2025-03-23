package apiTests;

import controller.UserController;
import dto.User;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PetTests {
    String baseUrl = "https://petstore.swagger.io/v2";

    @Test
    void createUserTest() {
        String userUrl = baseUrl + "/user";

        String body = """
                {
                  "id": 0,
                  "username": "gena",
                  "firstName": "gena",
                  "lastName": "chursov",
                  "email": "string",
                  "password": "string",
                  "phone": "string",
                  "userStatus": 0
                }""";

        Response response = given().baseUri(userUrl)
                .accept("application/json")
                .contentType("application/json")
                .when().body(body).post()
                .then().statusCode(200).extract().response();
        response.prettyPrint();

        assertEquals(200, response.statusCode());
    }

    @Test
    void createUserWithDTOTest() {
        String userUrl = baseUrl + "/user";

        User user = new User(0, "qwerty123", "gena", "chursov", "string", "string", "string", 0);

        Response response = given().baseUri(userUrl)
                .accept("application/json")
                .contentType("application/json")
                .when().body(user).post()
                .then().statusCode(200).extract().response();
        response.prettyPrint();

        assertEquals(200, response.statusCode());
    }

//    @Test
//    void greatUserTest() {
//        UserController userController = new UserController();
//
//        Response response = userController.createUser(DEFAULT_USER);
//
//        assertEquals(DEFAULT_USER, response.as(User.class));
//    }

    @Test
    void getUserTest() {
        String userUrl = baseUrl + "/user";
        String username = "gena";

        Response response = given().baseUri(userUrl).accept("application/json").contentType("application/json")
                .when().get("/" + username)
                .then().statusCode(200)
                .body("username", equalTo("gena"))
                .body("id", any(Long.class))
                .body("id", greaterThanOrEqualTo(1000000000000000000L))
                .body("id", lessThanOrEqualTo(Long.MAX_VALUE))
                .extract().response();
        response.prettyPrint();

        assertEquals(200, response.statusCode());
    }

    @Test
    void getUserWithJUnitTest() {
        String userUrl = baseUrl + "/user";
        String username = "qwerty123";

        Response response = given().baseUri(userUrl)
                .header("accept", "application/json")
                .header("contentType", "application/json")
                .when().get("/" + username)
                .andReturn();
        response.prettyPrint();
        //parsing, marshalling/unmarshalling JSON -> Java Object; Java Object -> JSON
        User user = response.as(User.class);

        assertEquals(200, response.statusCode());
        assertEquals(username, user.getUsername());
    }
}
