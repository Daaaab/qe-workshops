package com.futureprocessing.qe.workshops.pageobject;


import androidx.test.espresso.ViewInteraction;
import com.futureprocessing.qe.workshops.R;
import com.futureprocessing.qe.workshops.model.Category;
import com.futureprocessing.qe.workshops.model.Priority;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

public class EditItemPageObject {

    private final ViewInteraction itemNameEditText;
    private final ViewInteraction categorySpinner;
    private final ViewInteraction prioritySpinner;
    private final ViewInteraction saveItemFab;

    public EditItemPageObject() {
        itemNameEditText = onView(withId(R.id.newItemEditText));
        categorySpinner = onView(withId(R.id.category_spinner));
        prioritySpinner = onView(withId(R.id.priority_spinner));
        saveItemFab = onView(withId(R.id.fabSave));
    }

    public ItemListPageObject saveItem(String name, Category category, Priority priority) {
        itemNameEditText.perform(clearText(), replaceText(name));

        categorySpinner.perform(click());
        onView(withText(category.name().toLowerCase())).perform(click());

        prioritySpinner.perform(click());
        onView(withText(priority.name().toLowerCase())).perform(click());

        saveItemFab.perform(click());

        return new ItemListPageObject();
    }

    public EditItemPageObject validate(String itemName, Category itemCategory, Priority itemPriority) {
        itemNameEditText.check(matches(isDisplayed()));
        itemNameEditText.check(matches(withText(itemName)));
        categorySpinner.check(matches(isDisplayed()));
        categorySpinner.check(matches(withChild(withText(itemCategory.name().toLowerCase()))));
        prioritySpinner.check(matches(isDisplayed()));
        prioritySpinner.check(matches(withChild(withText(itemPriority.name().toLowerCase()))));
        saveItemFab.check(matches(isDisplayed()));

        return this;
    }
}