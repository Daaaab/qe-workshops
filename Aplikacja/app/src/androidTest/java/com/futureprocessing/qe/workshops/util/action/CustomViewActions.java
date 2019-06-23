package com.futureprocessing.qe.workshops.util.action;


import android.view.View;
import androidx.annotation.IdRes;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import org.hamcrest.Matcher;

import static androidx.test.espresso.action.ViewActions.click;

public final class CustomViewActions {

    public static ViewAction clickOnChild(@IdRes final int childId) {
        return new ViewAction() {

            @Override
            public void perform(UiController uiController, View view) {
                View viewToClick = view.findViewById(childId);
                click().perform(uiController, viewToClick);
            }

            @Override
            public String getDescription() {
                return "Click on child";
            }

            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(View.class);
            }
        };
    }
}