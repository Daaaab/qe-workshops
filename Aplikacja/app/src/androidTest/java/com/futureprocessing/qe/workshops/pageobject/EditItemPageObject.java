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

//    private final ViewInteraction itemNameEditText;
//    private final ViewInteraction categorySpinner;
//    private final ViewInteraction prioritySpinner;
//    private final ViewInteraction saveItemFab;

    public EditItemPageObject() {

    }

    public ItemListPageObject saveItem(String name, Category category, Priority priority) {
        // new method: clearText

        return new ItemListPageObject();
    }

    public EditItemPageObject validate(String itemName, Category itemCategory, Priority itemPriority) {
        // new method: withChild

        return this;
    }
}