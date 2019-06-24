package com.futureprocessing.qe.workshops.test.screen;

import android.content.Intent;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import com.futureprocessing.qe.workshops.MyApplication;
import com.futureprocessing.qe.workshops.database.AppDatabase;
import com.futureprocessing.qe.workshops.database.model.ItemEntity;
import com.futureprocessing.qe.workshops.database.model.UserEntity;
import com.futureprocessing.qe.workshops.edititem.EditItemActivity;
import com.futureprocessing.qe.workshops.edititem.Henson;
import com.futureprocessing.qe.workshops.model.Category;
import com.futureprocessing.qe.workshops.model.Priority;
import com.futureprocessing.qe.workshops.model.UserSession;
import com.futureprocessing.qe.workshops.pageobject.EditItemPageObject;
import com.futureprocessing.qe.workshops.util.TestDataHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/*
 * Edit item screen test
 */
@LargeTest
public class Exercise5 {

    @Rule public ActivityTestRule<EditItemActivity> activityTestRule = new ActivityTestRule<>(EditItemActivity.class, false, false);

    private UserEntity dbUser;
    private TestDataHelper testDataHelper;

    @Before
    public void setUp() {
        AppDatabase databaseFacade = ( (MyApplication) ApplicationProvider.getApplicationContext()).getDb();
        UserSession userSession = ( (MyApplication) ApplicationProvider.getApplicationContext()).getSession();
        testDataHelper = new TestDataHelper(databaseFacade);

        testDataHelper.dropDatabase();
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

        new EditItemPageObject()
                .validate("", Category.FRUITS, Priority.NORMAL);
    }

    /* TODO TASK 2
     *
     * 1. Add item to database
     * 2. Validate if the screen is opened with chosen item
     *
    */
    @Test
    public void validateScreenWithItem() {
        String itemName = "new item";
        Category itemCategory = Category.FRUITS;
        Priority itemPriority = Priority.NORMAL;
        boolean isChecked = false;
        ItemEntity item = testDataHelper.addItemToDatabase(itemName, itemCategory, itemPriority, isChecked, dbUser);

        activityTestRule.launchActivity(getActivityIntent(item.getId()));

        new EditItemPageObject()
                .validate(itemName, itemCategory, itemPriority);
    }

    /* TODO TASK 3
     *
     * 1. Add the item
     * 2. Validate if the item is added
     *
    */
    @Test
    public void addItem() {
        String itemName = "new item";
        Category itemCategory = Category.FRUITS;
        Priority itemPriority = Priority.NORMAL;

        activityTestRule.launchActivity(null);

        new EditItemPageObject()
                .saveItem(itemName, itemCategory, itemPriority)
                .validateItemExists(itemName, itemCategory, itemPriority, false);
    }

    /* TODO TASK 4
    *
    * 1. Add item to database
    * 2. Update the item
    * 3. Validate if the item is updated
    *
   */
    @Test
    public void editItem() {
        String itemName = "new item";
        String newItemName = "updated item";
        Category itemCategory = Category.FRUITS;
        Priority itemPriority = Priority.NORMAL;
        Priority newItemPriority = Priority.MINOR;
        boolean isChecked = false;
        ItemEntity item = testDataHelper.addItemToDatabase(itemName, itemCategory, itemPriority, isChecked, dbUser);

        activityTestRule.launchActivity(getActivityIntent(item.getId()));

        new EditItemPageObject()
                .saveItem(newItemName, itemCategory, newItemPriority)
                .validateItemExists(newItemName, itemCategory, newItemPriority, isChecked)
                .validateItemNotExists(itemName, itemCategory, itemPriority, isChecked);
    }

    /* TODO TASK 5
    *
    * 1. Add checked item to database
    * 2. Update the item
    * 3. Validate if the item is updated and not checked
    *
   */
    @Test
    public void editCheckedItem() {
        String itemName = "new item";
        String newItemName = "updated item";
        Category itemCategory = Category.FRUITS;
        Priority itemPriority = Priority.NORMAL;
        Priority newItemPriority = Priority.MINOR;
        boolean isChecked = true;
        ItemEntity item = testDataHelper.addItemToDatabase(itemName, itemCategory, itemPriority, isChecked, dbUser);

        activityTestRule.launchActivity(getActivityIntent(item.getId()));

        new EditItemPageObject()
                .saveItem(newItemName, itemCategory, newItemPriority)
                .validateItemExists(newItemName, itemCategory, newItemPriority, !isChecked)
                .validateItemNotExists(itemName, itemCategory, itemPriority, isChecked);
    }

    private Intent getActivityIntent(int itemId) {
        return Henson.with(ApplicationProvider.getApplicationContext())
                .gotoEditItemActivity()
                .itemId(itemId)
                .build();
    }
}