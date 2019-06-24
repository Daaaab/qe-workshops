package com.futureprocessing.qe.workshops.pageobject;

import androidx.annotation.StringRes;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import com.futureprocessing.qe.workshops.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

public class RegisterPageObject {

    private static final int INPUT_TYPE = 129;

//    private final ViewInteraction usernameEditText;
//    private final ViewInteraction passwordEditText;
//    private final ViewInteraction registerButton;

    public RegisterPageObject() {

    }

    public ItemListPageObject createUser(String username, String password) {

        return new ItemListPageObject();
    }


    public RegisterPageObject validate() {

        return this;
    }

    public LoginPageObject goBack() {
        // remember to close keyboard

        return new LoginPageObject();
    }

    ////////////////// For volunteers //////////////////

    public RegisterPageObject createUserWithError(String username, String password) {

        return this;
    }

    public RegisterPageObject validateError(@StringRes int errorTextId) {

        return this;
    }
}