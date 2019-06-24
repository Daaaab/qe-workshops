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

    private final ViewInteraction usernameEditText;
    private final ViewInteraction passwordEditText;
    private final ViewInteraction registerButton;

    public RegisterPageObject() {
        usernameEditText = onView(withId(R.id.newUsernameEditText));
        passwordEditText = onView(withId(R.id.newPasswordEditText));
        registerButton = onView(withId(R.id.registerButton));
    }

    public ItemListPageObject createUser(String username, String password) {
        usernameEditText.perform(replaceText(username));
        passwordEditText.perform(replaceText(password));

        registerButton.perform(click());

        return new ItemListPageObject();
    }


    public RegisterPageObject validate() {
        usernameEditText.check(matches(isDisplayed()));
        passwordEditText.check(matches(isDisplayed()));
        passwordEditText.check(matches(withInputType(INPUT_TYPE)));
        registerButton.check(matches(isDisplayed()));

        return this;
    }

    public LoginPageObject goBack() {
        Espresso.closeSoftKeyboard();
        Espresso.pressBack();

        return new LoginPageObject();
    }

    ////////////////// For volunteers //////////////////

    public RegisterPageObject createUserWithError(String username, String password) {
        createUser(username, password);

        return this;
    }

    public RegisterPageObject validateError(@StringRes int errorTextId) {
        onView(withText(errorTextId)).check(matches(isDisplayed()));

        return this;
    }
}