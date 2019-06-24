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
    private final ViewInteraction passwordEditText;
    private final ViewInteraction loginButton;
    private final ViewInteraction registerButton;

    public LoginPageObject() {
        usernameEditText = onView(withId(R.id.usernameEditText));
        passwordEditText = onView(withId(R.id.passwordEditText));
        loginButton = onView(withId(R.id.loginButton));
        registerButton = onView(withId(R.id.registerButton));
    }

    public ItemListPageObject login(String username, String password) {
        usernameEditText.perform(replaceText(username));
        passwordEditText.perform(replaceText(password));

        loginButton.perform(click());

        return new ItemListPageObject();
    }

    public RegisterPageObject openRegisterScreen() {
        registerButton.perform(click());

        return new RegisterPageObject();
    }


    public LoginPageObject validate() {
        usernameEditText.check(matches(isDisplayed()));
        passwordEditText.check(matches(isDisplayed()));
        passwordEditText.check(matches(withInputType(INPUT_TYPE)));
        loginButton.check(matches(isDisplayed()));
        registerButton.check(matches(isDisplayed()));

        return this;
    }

    ////////////////// For volunteers //////////////////

    public LoginPageObject loginWithError(String username, String password) {
        login(username, password);

        return this;
    }

    public LoginPageObject validateError() {
        onView(withText(R.string.loginError)).check(matches(isDisplayed()));

        return this;
    }
}