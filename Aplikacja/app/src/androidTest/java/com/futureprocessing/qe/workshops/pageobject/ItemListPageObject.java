package com.futureprocessing.qe.workshops.pageobject;

import android.view.View;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import com.futureprocessing.qe.workshops.R;
import com.futureprocessing.qe.workshops.model.Category;
import com.futureprocessing.qe.workshops.model.Priority;
import org.hamcrest.Matcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import static com.futureprocessing.qe.workshops.util.action.CustomViewActions.clickOnChild;


import static org.hamcrest.core.AllOf.allOf;

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