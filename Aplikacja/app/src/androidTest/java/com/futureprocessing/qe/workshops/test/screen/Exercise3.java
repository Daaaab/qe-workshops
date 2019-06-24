package com.futureprocessing.qe.workshops.test.screen;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import com.futureprocessing.qe.workshops.MyApplication;
import com.futureprocessing.qe.workshops.R;
import com.futureprocessing.qe.workshops.database.AppDatabase;
import com.futureprocessing.qe.workshops.pageobject.RegisterPageObject;
import com.futureprocessing.qe.workshops.register.RegisterActivity;
import com.futureprocessing.qe.workshops.util.TestDataHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/*
 * Register screen test
 */
@LargeTest
public class Exercise3 {

    @Rule public ActivityTestRule<RegisterActivity> activityTestRule = new ActivityTestRule<>(RegisterActivity.class);

    private TestDataHelper testDataHelper;

    @Before
    public void setUp() {
        AppDatabase databaseFacade = ((MyApplication) activityTestRule.getActivity().getApplication()).getDb();
        testDataHelper = new TestDataHelper(databaseFacade);
    }

    @After
    public void tearDown() {
        testDataHelper.dropDatabase();
    }

    /* TODO TASK 1
     *
     * 1. Validate if the screen is opened
     *
    */
    @Test
    public void validateScreen() {
        new RegisterPageObject()
                .validate();
    }

    /* TODO TASK 2
     *
     * 1. Register user
     * 2. Validate if the screen is opened
     *
    */
    @Test
    public void createUser() {
        String username = "user";
        String password = "password";

        new RegisterPageObject()
                .createUser(username, password)
                .validate();
    }

    ////////////////// For volunteers //////////////////

    /* TODO TASK 3
     *
     * 1. Add code inside RegisterPageObject
     * 2, Try to register with empty edit texts
     * 3. Validate if the error is displayed
     *
    */
    @Test
    public void registerError() {
        String username = "";
        String password = "";

        new RegisterPageObject()
                .createUserWithError(username, password)
                .validateError(R.string.registerError);
    }

    /* TODO TASK 4
     *
     * 1. Add user to database
     * 2, Try to register with existing user
     * 3. Validate if the error is displayed
     *
    */
    @Test
    public void registerWithExistingUserError() {
        String username = "user";
        String password = "password";

        testDataHelper.addUserToDatabase(username, password);

        new RegisterPageObject()
                .createUserWithError(username, password)
                .validateError(R.string.registerErrorUserExists);
    }
}