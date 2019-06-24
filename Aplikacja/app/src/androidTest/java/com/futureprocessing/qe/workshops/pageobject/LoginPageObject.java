package com.futureprocessing.qe.workshops.pageobject;


import androidx.test.espresso.ViewInteraction;
import com.futureprocessing.qe.workshops.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

public class LoginPageObject {

    private static final int INPUT_TYPE = 129;

    private final ViewInteraction usernameEditText;
//    private final ViewInteraction passwordEditText;
//    private final ViewInteraction loginButton;
//    private final ViewInteraction registerButton;

    public LoginPageObject() {
        usernameEditText = onView(withId(R.id.usernameEditText));
        // initialize all views

    }

    public ItemListPageObject login(String username, String password) {

        return new ItemListPageObject();
    }

    public RegisterPageObject openRegisterScreen() {

        return new RegisterPageObject();
    }


    public LoginPageObject validate() {
        // new method: withInputType

        return this;
    }

    ////////////////// For volunteers //////////////////

    public LoginPageObject loginWithError(String username, String password) {
        // reuse existing method

        return this;
    }

    public LoginPageObject validateError() {
        // new method: withText

        return this;
    }
}