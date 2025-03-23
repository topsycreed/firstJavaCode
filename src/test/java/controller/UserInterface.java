package controller;

import dto.User;
import io.restassured.response.Response;

public interface UserInterface {
    Response addDefaultUser();
    Response addUser(User user);
    Response getUser(String userName);
    Response deleteUser(String userName);
}
