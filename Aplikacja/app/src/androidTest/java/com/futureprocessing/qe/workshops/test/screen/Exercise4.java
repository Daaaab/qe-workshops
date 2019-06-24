package com.futureprocessing.qe.workshops.test.screen;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import com.futureprocessing.qe.workshops.MyApplication;
import com.futureprocessing.qe.workshops.database.AppDatabase;
import com.futureprocessing.qe.workshops.database.model.UserEntity;
import com.futureprocessing.qe.workshops.itemlist.ItemListActivity;
import com.futureprocessing.qe.workshops.model.Category;
import com.futureprocessing.qe.workshops.model.Priority;
import com.futureprocessing.qe.workshops.model.UserSession;
import com.futureprocessing.qe.workshops.pageobject.ItemListPageObject;
import com.futureprocessing.qe.workshops.util.TestDataHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/*
 * Item list screen test
 */
@LargeTest
public class Exercise4{

    //In this Exercise we will launch our activity manually -> launchActivity = false
    @Rule public ActivityTestRule<ItemListActivity> activityTestRule = new ActivityTestRule<>(ItemListActivity.class, false, false);

    private UserEntity dbUser;
    private TestDataHelper testDataHelper;

    @Before
    public void setUp() {

        //We cannot launch Items List Activity since it require user to be logged in
        //without Activity running we have no context
        //without context we cannot log in our user via MyApplication
        //that's why we need to obtain our context in a different way then in previous Examples
        AppDatabase databaseFacade = ((MyApplication) ApplicationProvider.getApplicationContext()).getDb();
        UserSession userSession = ((MyApplication) ApplicationProvider.getApplicationContext()).getSession();

        testDataHelper = new TestDataHelper(databaseFacade);

        dbUser = testDataHelper.loginUser("user", "pass", userSession);
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
        activityTestRule.launchActivity(null);

        new ItemListPageObject()
                .validate();
    }

    /* TODO TASK 2
     *
     * 1. Click on add button
     * 2. Validate if the screen is opened
     *
    */
    @Test
    public void openAddItemScreen() {
        activityTestRule.launchActivity(null);

        new ItemListPageObject()
                .openAddItemScreen()
                .validate("", Category.FRUITS, Priority.NORMAL);
    }

    /* TODO TASK 3
    *
    * 1. Add item to database - use addItemToDatabase method from testDataHelper
    * 2. Click on item
    * 3. Validate if the screen is opened
    *
   */
    @Test
    public void openEditItemScreen() {
        String itemName = "new item";
        Category itemCategory = Category.FRUITS;
        Priority itemPriority = Priority.NORMAL;
        boolean isChecked = false;
        testDataHelper.addItemToDatabase(itemName, itemCategory, itemPriority, isChecked, dbUser);

        activityTestRule.launchActivity(null);

        new ItemListPageObject()
                .clickOnItem(itemName)
                .validate(itemName, itemCategory, itemPriority);
    }

    /* TODO TASK 4
     *
     * 1. Add item to database
     * 2. Remove item
     * 3. Validate if the item is removed
     *
    */
    @Test
    public void removeItem() {
        String itemName = "new item";
        Category itemCategory = Category.FRUITS;
        Priority itemPriority = Priority.NORMAL;
        boolean isChecked = false;
        testDataHelper.addItemToDatabase(itemName, itemCategory, itemPriority, isChecked, dbUser);

        activityTestRule.launchActivity(null);

        new ItemListPageObject()
                .removeItem(itemName)
                .validateItemNotExists(itemName, itemCategory, itemPriority, isChecked);
    }

    /* TODO TASK 5
     *
     * 1. Add item to database
     * 2. Check the item
     * 3. Validate if the item is checked
     *
    */
    @Test
    public void checkItem() {
        String itemName = "new item";
        Category itemCategory = Category.FRUITS;
        Priority itemPriority = Priority.NORMAL;
        boolean isChecked = false;
        testDataHelper.addItemToDatabase(itemName, itemCategory, itemPriority, isChecked, dbUser);

        activityTestRule.launchActivity(null);

        new ItemListPageObject()
                .checkItem(itemName)
                .validateItemExists(itemName, itemCategory, itemPriority, !isChecked);
    }
}