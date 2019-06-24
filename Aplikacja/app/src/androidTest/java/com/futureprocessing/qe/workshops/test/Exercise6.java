package com.futureprocessing.qe.workshops.test;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import com.futureprocessing.qe.workshops.MyApplication;
import com.futureprocessing.qe.workshops.login.LoginActivity;
import com.futureprocessing.qe.workshops.model.Category;
import com.futureprocessing.qe.workshops.model.Priority;
import com.futureprocessing.qe.workshops.pageobject.LoginPageObject;
import com.futureprocessing.qe.workshops.util.TestDataHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;



@LargeTest
public class Exercise6 {

    private TestDataHelper helper;

    @Rule public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setup(){
        helper = new TestDataHelper(((MyApplication)activityTestRule.getActivity().getApplication()).getDb());
        helper.dropDatabase();
    }

    @After
    public void tearDown() {
        helper.dropDatabase();
    }

    /* TODO TASK 1
     *
     * 1. Open register screen
     * 2. Validate if the screen is opened
     * 3. Move to the previous screen
     * 4. Validate if the screen is opened
     *
    */
    @Test
    public void openRegisterScreen() {
        new LoginPageObject()
                .openRegisterScreen()
                .validate()
                .goBack()
                .validate();
    }

    /* TODO TASK 2
     *
     * 1. Open register screen
     * 2. Validate if the register screen is opened
     * 3. Create user
     * 4. Validate if the item list screen is opened
     *
    */
    @Test
    public void registerUser() {
        new LoginPageObject()
                .openRegisterScreen()
                .validate()
                .createUser("user", "pass")
                .validate();
    }

    /* TODO TASK 3
     *
     * 1. Open register screen
     * 2. Validate if the register screen is opened
     * 3. Create user
     * 4. Validate if the item list screen is opened
     * 5. Open add item screen
     * 6. Validate if the add item screen is opened
     * 7. Add new item
     * 8. Verify if item is added
     *
     */
    @Test
    public void addItem() {
        new LoginPageObject()
                .openRegisterScreen()
                .validate()
                .createUser("user", "pass")
                .validate()
                .openAddItemScreen()
                .validate("", Category.FRUITS, Priority.NORMAL)
                .saveItem("item", Category.AGD, Priority.CRITICAL)
                .validateItemExists("item", Category.AGD, Priority.CRITICAL, false);
    }

    /* TODO TASK 4
     *
     * 1. Open register screen
     * 2. Validate if the register screen is opened
     * 3. Create user
     * 4. Validate if the item list screen is opened
     * 5. Open add item screen
     * 6. Validate if the add item screen is opened
     * 7. Add new item
     * 8. Open add item screen
     * 9. Validate if the add item screen is opened
     * 10. Add new item
     * 11. Verify if items are added
     */
    @Test
    public void addTheSameItemNameTwice() {
        new LoginPageObject()
                .openRegisterScreen()
                .validate()
                .createUser("user", "pass")
                .validate()
                .openAddItemScreen()
                .validate("", Category.FRUITS, Priority.NORMAL)
                .saveItem("item", Category.AGD, Priority.CRITICAL)
                .openAddItemScreen()
                .validate("", Category.FRUITS, Priority.NORMAL)
                .saveItem("item2", Category.DAIRY, Priority.MINOR)
                .validateItemExists("item", Category.AGD, Priority.CRITICAL, false)
                .validateItemExists("item2", Category.DAIRY, Priority.MINOR, false);
    }
}