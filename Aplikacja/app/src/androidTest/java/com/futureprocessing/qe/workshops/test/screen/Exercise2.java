package com.futureprocessing.qe.workshops.test.screen;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import com.futureprocessing.qe.workshops.MyApplication;
import com.futureprocessing.qe.workshops.database.AppDatabase;
import com.futureprocessing.qe.workshops.login.LoginActivity;
import com.futureprocessing.qe.workshops.pageobject.LoginPageObject;
import com.futureprocessing.qe.workshops.util.TestDataHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/*
 * Login screen test
 */
@LargeTest
public class Exercise2 {

    @Rule public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

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
     * 1. Add code inside LoginPageObject
     * 2. Validate if the screen is opened
     *
     */
    @Test
    public void openLoginScreen() {
        new LoginPageObject()
                .validate();
    }

    /* TODO TASK 2
     *
     * 1. Open register screen
     * 2. Add code inside RegisterPageObject
     * 3. Validate if the screen is opened
     *
     */
    @Test
    public void openRegisterScreen() {

    }

    /* TODO TASK 3
     *
     * 1. Add user to database - use addUserToDatabase method from testDataHelper
     * 2. Login as added user
     * 3. Add code inside ItemListPageObject
     * 4. Validate if the screen is opened
     *
     */
    @Test
    public void login() {

    }

    ////////////////// For volunteers //////////////////

    /* TODO TASK 4
     *
     * 1. Add code inside LoginPageObject
     * 2. Try to login
     * 3. Validate if the error is displayed
     *
     */
    @Test
    public void loginError() {

    }

    /* TODO TASK 5
     *
     * 1. Add user to database
     * 2. Try to login with wrong password
     * 3. Validate if the error is displayed
     *
     */
    @Test
    public void loginWithWrongPasswordError() {

    }
}