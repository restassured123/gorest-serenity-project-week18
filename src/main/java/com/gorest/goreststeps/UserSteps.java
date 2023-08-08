package com.gorest.goreststeps;

import com.gorest.constants.EndPoint;
import com.gorest.model.UserPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;


public class UserSteps {
    @Step("Get all user information")
    public ValidatableResponse getAllUsers(){
        return  SerenityRest.given().log().all()
                .when()
                .get(EndPoint.GET_ALL_USERS)
                .then()
                .statusCode(200);

    }

    @Step("Create User")
    public ValidatableResponse createUser(String name, String email, String gender, String status){

        UserPojo userPojo=new UserPojo();

        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        return  SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer f4a966cfb8b1f8762386c84c483edfe3e4d73f9b92bd3e88426ca701e310525b")
                .when()
                .body(userPojo)
                .post(EndPoint.CREATE_USER)
                .then()
                .statusCode(201);
    }

    @Step("Verify Newly Create User")
    public ValidatableResponse VerifyUser(int userid){
        return  SerenityRest.given().log().all()
                .header("Authorization","Bearer f4a966cfb8b1f8762386c84c483edfe3e4d73f9b92bd3e88426ca701e310525b")
                .pathParam("userID",userid)
                .when()
                .get(EndPoint.GET_USER_BY_ID)
                .then()
                .statusCode(200);

    }

    @Step("Update User")
    public ValidatableResponse updateUser(int userid, String name, String email, String gender, String status){

        UserPojo userPojo=new UserPojo();

        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        return  SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer f4a966cfb8b1f8762386c84c483edfe3e4d73f9b92bd3e88426ca701e310525b")
                .pathParam("userID",userid)
                .when()
                .body(userPojo)
                .put(EndPoint.UPDATE_USER_BY_ID)
                .then()
                .statusCode(200);
    }

    @Step("Delete User")
    public ValidatableResponse deleteUser(int userid){

        return  SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer f4a966cfb8b1f8762386c84c483edfe3e4d73f9b92bd3e88426ca701e310525b")
                .pathParam("userID",userid)
                .when()
                .delete(EndPoint.DELETE_USER_BY_ID)
                .then()
                .statusCode(204);
    }


}
