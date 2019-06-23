package com.futureprocessing.qe.workshops.test;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import com.futureprocessing.qe.workshops.MyApplication;
import com.futureprocessing.qe.workshops.login.LoginActivity;
import com.futureprocessing.qe.workshops.util.TestDataHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import com.futureprocessing.qe.workshops.R.id;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.core.AllOf.allOf;

@LargeTest
public class Exercise1 {

    private TestDataHelper helper;

    @Rule public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setup(){
        helper = new TestDataHelper(((MyApplication) activityTestRule.getActivity().getApplication()).getDb());
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
        onView(
                withId(id.registerButton))
                .perform(ViewActions.click());

        onView(
                withId(id.newUsernameEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        //TODO 4,5
        Espresso.closeSoftKeyboard();
        Espresso.pressBack();
        onView(
                withId(id.usernameEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
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
     * New methods: replaceText()
    */
    @Test
    public void registerUser() {
        //TODO 1,2
        onView(
                withId(id.registerButton))
                .perform(ViewActions.click());

        onView(
                withId(id.newUsernameEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        //TODO 3,4,5
        onView(
                withId(id.newUsernameEditText))
                .perform(replaceText("user"));

        onView(
                withId(id.newPasswordEditText))
                .perform(replaceText("pass"));

        onView(
                withId(id.registerButton))
                .perform(ViewActions.click());

        //TODO 6
        onView(
                withId(id.fabAdd))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
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
        //TODO 1,2
        onView(
                withId(id.registerButton))
                .perform(ViewActions.click());

        onView(
                withId(id.newUsernameEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        //TODO 3,4,5,6
        onView(
                withId(id.newUsernameEditText))
                .perform(replaceText("user"));

        onView(
                withId(id.newPasswordEditText))
                .perform(replaceText("pass"));

        onView(
                withId(id.registerButton))
                .perform(ViewActions.click());

        onView(
                withId(id.fabAdd))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        //TODO 7,8
        onView(
                withId(id.fabAdd))
                .perform(ViewActions.click());

        onView(
                withId(id.newItemEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // TODO 9,10
       onView(
               withId(id.newItemEditText))
               .perform(replaceText("ziemniaki"));

       onView(
               withId(id.fabSave))
               .perform(ViewActions.click());

        // TODO 11
        onView(
                withId(id.text))
                .check(ViewAssertions.matches(ViewMatchers.withText("ziemniaki")));
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
        //TODO 1,2
        onView(
                withId(id.registerButton))
                .perform(ViewActions.click());

        onView(
                withId(id.newUsernameEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        //TODO 3,4,5,6
        onView(
                withId(id.newUsernameEditText))
                .perform(replaceText("user"));

        onView(
                withId(id.newPasswordEditText))
                .perform(replaceText("pass"));

        onView(
                withId(id.registerButton))
                .perform(ViewActions.click());

        onView(
                withId(id.fabAdd))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        //TODO 7,8,9,10,11,12
        onView(
                withId(id.fabAdd))
                .perform(ViewActions.click());

        onView(
                withId(id.newItemEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        onView(
                withId(id.newItemEditText))
                .perform(replaceText("buraki"));

        onView(
                withId(id.category_spinner))
                .perform(ViewActions.click());

        onView(
                ViewMatchers.withText("other"))
                .perform(ViewActions.click());

        onView(
                withId(id.fabSave))
                .perform(ViewActions.click());

        //TODO 13,14,15,16,17,18
        onView(
                withId(id.fabAdd))
                .perform(ViewActions.click());

        onView(
                withId(id.newItemEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        onView(
                withId(id.newItemEditText))
                .perform(replaceText("buraki"));

        onView(
                withId(id.priority_spinner))
                .perform(ViewActions.click());

        onView(
                ViewMatchers.withText("critical"))
                .perform(ViewActions.click());

        onView(
                withId(id.fabSave))
                .perform(ViewActions.click());

        //TODO 19
        onView(allOf(
                withId(id.text),
                ViewMatchers.withText("buraki"),
                ViewMatchers.hasSibling(ViewMatchers.withText("Category: other"))))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        onView(allOf(
                withId(id.text),
                ViewMatchers.withText("buraki"),
                ViewMatchers.hasSibling(ViewMatchers.withText("Priority: critical"))))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}