package pl.lizardproject.qe2018.pageobject;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.view.View;

import org.hamcrest.Matcher;

import pl.lizardproject.qe2018.R;
import pl.lizardproject.qe2018.model.Category;
import pl.lizardproject.qe2018.model.Priority;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static pl.lizardproject.qe2018.util.action.CustomViewActions.clickOnChild;

public class ItemListPageObject {
    private final ViewInteraction itemList;
    private final ViewInteraction addItemFab;

    public ItemListPageObject() {
        itemList = onView(withId(R.id.recyclerViewList));
        addItemFab = onView(withId(R.id.fabAdd));
    }

    public EditItemPageObject openAddItemScreen() {
        addItemFab.perform(click());

        return new EditItemPageObject();
    }

    public EditItemPageObject clickOnItem(String itemName) {
        itemList.perform(RecyclerViewActions.actionOnItem(withChild(withText(itemName)), click()));

        return new EditItemPageObject();
    }

    public ItemListPageObject removeItem(String itemName) {
        itemList.perform(RecyclerViewActions.actionOnItem(withChild(withText(itemName)), clickOnChild(R.id.deleteButton)));

        return this;
    }

    public ItemListPageObject checkItem(String itemName) {
        itemList.perform(RecyclerViewActions.actionOnItem(withChild(withText(itemName)), clickOnChild(R.id.checkbox)));

        return this;
    }

    public ItemListPageObject validateItemExists(String name, Category category, Priority priority, boolean isChecked) {
        getItemView(name, category, priority, isChecked).check(matches(isDisplayed()));

        return this;
    }

    public ItemListPageObject validateItemNotExists(String name, Category category, Priority priority, boolean isChecked) {
        getItemView(name, category, priority, isChecked).check(doesNotExist());

        return this;
    }

    public ItemListPageObject validate() {
        itemList.check(matches(isDisplayed()));
        addItemFab.check(matches(isDisplayed()));

        return this;
    }

    private ViewInteraction getItemView(String name, Category category, Priority priority, boolean isChecked) {
        String categoryString = "Category: " + category.name().toLowerCase();
        String priorityString = "Priority: " + priority.name().toLowerCase();

        Matcher<View> checkedMatcher;
        if (isChecked) {
            checkedMatcher = isChecked();
        } else {
            checkedMatcher = isNotChecked();
        }

        return onView(allOf(withId(R.id.checkbox), hasSibling(withText(name)), hasSibling(withText(categoryString)), hasSibling(withText(priorityString)), hasSibling(checkedMatcher)));
    }
}