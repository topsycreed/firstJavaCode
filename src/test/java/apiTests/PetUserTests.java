package apiTests;

import controller.UserController;
import dto.BaseResponse;
import dto.User;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PetUserTests {
    UserController userController;

    @BeforeAll
    void setup() {
        userController = new UserController();
    }

    @Test
    void createUserSmokeTest() {
        Response response = userController.addDefaultUser();

        assertEquals(200, response.statusCode());
    }

    @Test
    void createUserWithDTOTest() {
        User user = new User(0, "nikola", "nikola", "test", "test", "test", "test", 0);

        Response response = userController.addUser(user);

        assertEquals(200, response.statusCode());
    }

    @Test
    void createUserTest() {
        String userName = "nikola";
        User user = new User(0, userName, "nikola", "test", "test", "test", "test", 0);

        Response response = userController.addUser(user);
        assertEquals(200, response.statusCode());
        Response getResponse = userController.getUser(userName);
        User createdUser = getResponse.as(User.class);

        Assertions.assertThat(createdUser)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(user);
    }

    @Test
    void getUserTest() {
        String userName = "nikola";
        User user = new User(0, userName, "nikola", "test", "test", "test", "test", 0);
        //Given (pre-requisites)
        userController.addUser(user);

        Response getResponse = userController.getUser(userName);
        User createdUser = getResponse.as(User.class);

        assertEquals(200, getResponse.statusCode());
        Assertions.assertThat(createdUser)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(user);
    }

    @Test
    void deleteUserTest() {
        String userName = "nikola";
        User user = new User(0, userName, "nikola", "test", "test", "test", "test", 0);
        BaseResponse expectedBaseResponse = new BaseResponse(200, "unknown", userName);
        //Given (pre-requisites)
        userController.addUser(user);

        Response response = userController.deleteUser(userName);
        BaseResponse baseResponse = response.as(BaseResponse.class);

        assertEquals(200, response.statusCode());
        Assertions.assertThat(baseResponse)
                .usingRecursiveComparison()
                .isEqualTo(expectedBaseResponse);
    }
}
