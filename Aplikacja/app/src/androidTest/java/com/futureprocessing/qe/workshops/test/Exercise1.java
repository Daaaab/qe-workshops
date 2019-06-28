package com.futureprocessing.qe.workshops.test;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import com.futureprocessing.qe.workshops.MyApplication;
import com.futureprocessing.qe.workshops.R.id;
import com.futureprocessing.qe.workshops.login.LoginActivity;
import com.futureprocessing.qe.workshops.util.TestDataHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

@LargeTest
public class Exercise1{

    private TestDataHelper helper;

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

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
     * 1. Find register button
     * 2. Click on it
     * 3. Validate if the screen is opened
     * 4. Move to the previous screen
     * 5. Validate if the screen is opened
     *
     * Methods: onView(), withId(), perform(), click(), check(), matches(), isDisplayed(), closeSoftKeyboard(), pressBack()
     */
    @Test
    public void openRegisterScreen() {
        //TODO 1,2,3
        Espresso.onView(
                ViewMatchers.withId(id.registerButton))
                .perform(ViewActions.click());

        Espresso.onView(
                ViewMatchers.withId(id.newUsernameEditText))
        .check(ViewAssertions.matches(
                ViewMatchers.isDisplayed()
        ));

        Espresso.closeSoftKeyboard();
        Espresso.pressBack();

        Espresso.onView(
                ViewMatchers.withId(id.usernameEditText))
                .perform(ViewActions.replaceText("TEST TEXT"));

        Espresso.onView(ViewMatchers.withId(id.usernameEditText)).perform(ViewActions.replaceText("test"));


    }

    /* TODO TASK 2
     *
     * 1. Click on register button
     * 2. Validate if the register screen is opened
     * 3. Type username
     * 4. Type password
     * 5. Click on register button
     * 6. Validate if the item list screen is opened
     *
     * New methods: typeText()
     */
    @Test
    public void registerUser() {

    }

    /* TODO TASK 3
     *
     * 1. Click on register button
     * 2. Validate if the register screen is opened
     * 3. Type username
     * 4. Type password
     * 5. Click on register button
     * 6. Validate if the item list screen is opened
     * 7. Click on add item button
     * 8. Validate if the add item screen is opened
     * 9. Type item name
     * 10. Click save button
     * 11. Verify if item is added
     *
     */
    @Test
    public void addItem() {

    }

    /* TODO TASK 4
     *
     * 1. Click on register button
     * 2. Validate if the register screen is opened
     * 3. Type username
     * 4. Type password
     * 5. Click on register button
     * 6. Validate if the item list screen is opened
     * 7. Click on add item button
     * 8. Validate if the add item screen is opened
     * 9. Type item name
     * 10. Click on category spinner
     * 11. Click on chosen category
     * 12. Click save button
     * 13. Click on add item button
     * 14. Validate if the add item screen is opened
     * 15. Type item name
     * 16. Click on priority spinner
     * 17. Click on chosen priority
     * 18. Click save button
     * 19. Verify if items are added
     */
    @Test
    public void addTheSameItemNameTwice() {

    }
}