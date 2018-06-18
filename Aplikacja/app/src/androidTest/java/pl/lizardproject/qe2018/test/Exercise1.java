package pl.lizardproject.qe2018.test;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import pl.lizardproject.qe2018.R.id;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.lizardproject.qe2018.MyApplication;
import pl.lizardproject.qe2018.login.LoginActivity;

import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class Exercise1 {

    @Rule public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @After
    public void tearDown() {
        ((MyApplication) activityTestRule.getActivity().getApplication()).getDatabaseFacade().drop();
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
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        //TODO 4,5
        Espresso.closeSoftKeyboard();
        Espresso.pressBack();
        Espresso.onView(
                ViewMatchers.withId(id.usernameEditText))
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
     * New methods: typeText()
    */
    @Test
    public void registerUser() {
        //TODO 1,2
        Espresso.onView(
                ViewMatchers.withId(id.registerButton))
                .perform(ViewActions.click());

        Espresso.onView(
                ViewMatchers.withId(id.newUsernameEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        //TODO 3,4,5
        Espresso.onView(
                ViewMatchers.withId(id.newUsernameEditText))
                .perform(ViewActions.typeText("user"));

        Espresso.onView(
                ViewMatchers.withId(id.newPasswordEditText))
                .perform(ViewActions.typeText("pass"));

        Espresso.onView(
                ViewMatchers.withId(id.registerButton))
                .perform(ViewActions.click());

        //TODO 6
        Espresso.onView(
                ViewMatchers.withId(pl.lizardproject.qe2018.R.id.fabAdd))
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
        Espresso.onView(
                ViewMatchers.withId(id.registerButton))
                .perform(ViewActions.click());

        Espresso.onView(
                ViewMatchers.withId(id.newUsernameEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        //TODO 3,4,5,6
        Espresso.onView(
                ViewMatchers.withId(id.newUsernameEditText))
                .perform(ViewActions.typeText("user"));

        Espresso.onView(
                ViewMatchers.withId(id.newPasswordEditText))
                .perform(ViewActions.typeText("pass"));

        Espresso.onView(
                ViewMatchers.withId(id.registerButton))
                .perform(ViewActions.click());

        Espresso.onView(
                ViewMatchers.withId(id.fabAdd))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        //TODO 7,8
        Espresso.onView(
                ViewMatchers.withId(id.fabAdd))
                .perform(ViewActions.click());

        Espresso.onView(
                ViewMatchers.withId(id.newItemEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // TODO 9,10
       Espresso.onView(
               ViewMatchers.withId(id.newItemEditText))
               .perform(ViewActions.typeText("ziemniaki"));

       Espresso.onView(
               ViewMatchers.withId(id.fabSave))
               .perform(ViewActions.click());

        // TODO 11
        Espresso.onView(
                ViewMatchers.withId(id.text))
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
        Espresso.onView(
                ViewMatchers.withId(id.registerButton))
                .perform(ViewActions.click());

        Espresso.onView(
                ViewMatchers.withId(id.newUsernameEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        //TODO 3,4,5,6
        Espresso.onView(
                ViewMatchers.withId(id.newUsernameEditText))
                .perform(ViewActions.typeText("user"));

        Espresso.onView(
                ViewMatchers.withId(id.newPasswordEditText))
                .perform(ViewActions.typeText("pass"));

        Espresso.onView(
                ViewMatchers.withId(id.registerButton))
                .perform(ViewActions.click());

        Espresso.onView(
                ViewMatchers.withId(id.fabAdd))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        //TODO 7,8,9,10,11,12
        Espresso.onView(
                ViewMatchers.withId(id.fabAdd))
                .perform(ViewActions.click());

        Espresso.onView(
                ViewMatchers.withId(id.newItemEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(
                ViewMatchers.withId(id.newItemEditText))
                .perform(ViewActions.typeText("buraki"));

        Espresso.onView(
                ViewMatchers.withId(id.category_spinner))
                .perform(ViewActions.click());

        Espresso.onView(
                ViewMatchers.withText("other"))
                .perform(ViewActions.click());

        Espresso.onView(
                ViewMatchers.withId(id.fabSave))
                .perform(ViewActions.click());

        //TODO 13,14,15,16,17,18
        Espresso.onView(
                ViewMatchers.withId(id.fabAdd))
                .perform(ViewActions.click());

        Espresso.onView(
                ViewMatchers.withId(id.newItemEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(
                ViewMatchers.withId(id.newItemEditText))
                .perform(ViewActions.typeText("buraki"));

        Espresso.onView(
                ViewMatchers.withId(id.priority_spinner))
                .perform(ViewActions.click());

        Espresso.onView(
                ViewMatchers.withText("critical"))
                .perform(ViewActions.click());

        Espresso.onView(
                ViewMatchers.withId(id.fabSave))
                .perform(ViewActions.click());

        //TODO 19
        Espresso.onView(allOf(
                ViewMatchers.withId(id.text),
                ViewMatchers.withText("buraki"),
                ViewMatchers.hasSibling(ViewMatchers.withText("Category: other"))))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(allOf(
                ViewMatchers.withId(id.text),
                ViewMatchers.withText("buraki"),
                ViewMatchers.hasSibling(ViewMatchers.withText("Priority: critical"))))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}