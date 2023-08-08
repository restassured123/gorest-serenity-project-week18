package com.gorest.curdtest;

import com.gorest.goreststeps.UserSteps;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserCURDTest extends TestBase {


    static String name = "gov" + TestUtils.getRandomString();
    static String email = TestUtils.getRandomString() + "@gmail.com";
    static String gender = "male";
    static String status = "active";
    static int newuserid;

    @Steps
    UserSteps userSteps;

    @Title("Get all Users Details")
    @Test
    public void test01_allUSers() {
        ValidatableResponse response = userSteps.getAllUsers();
        response.log().all().statusCode(200);
    }

    @Title("Create New User")
    @Test
    public void test02_createUser() {
        ValidatableResponse response = userSteps.createUser(name, email, gender, status);
        response.log().all().statusCode(201);
        newuserid = response.extract().path("id");
        System.out.println("NEWLY CREATED STORE ID IS:" + newuserid);
    }

    @Title("Verify Newly Created User")
    @Test
    public void test03_verifyUser() {
        ValidatableResponse response = userSteps.VerifyUser(newuserid);
        response.log().all().statusCode(200);
    }

    @Title("Update Newly Create Users")
    @Test
    public void test04_updateUser() {
        name = TestUtils.getRandomString();
        email = TestUtils.getRandomString() + "1@gmail.com";
        ValidatableResponse response = userSteps.updateUser(newuserid, name, email, gender, status);
        response.log().all().statusCode(200);
    }

    @Title("Delete User")
    @Test
    public void test05_deleteUser() {
        ValidatableResponse response = userSteps.deleteUser(newuserid);
        response.log().all().statusCode(204);
    }

}
