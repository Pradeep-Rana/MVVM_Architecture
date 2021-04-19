package com.wipro.news.app.view.activities;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.tabs.TabLayout;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class NewsActivityTest {
    @Rule
    public ActivityTestRule<NewsActivity> testRule = new ActivityTestRule(NewsActivity.class);

    @NonNull
    private static ViewAction selectTabAtPosition(final int position) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TabLayout.class));
            }

            @Override
            public String getDescription() {
                return "with tab at index" + position;
            }

            @Override
            public void perform(UiController uiController, View view) {
                if (view instanceof TabLayout) {
                    TabLayout tabLayout = (TabLayout) view;
                    TabLayout.Tab tab = tabLayout.getTabAt(position);

                    if (tab != null) {
                        tab.select();
                    }
                }
            }
        };
    }

    @Before
    public void testSetup() {

    }

    @Test
    public void testTab1Clicks() {
//        Espresso.onView(withId(R.id.tabLayout)).perform(selectTabAtPosition(0));
        Espresso.onView(withText("News")).perform(click());
    }

    @Test
    public void testTab2Clicks() {
//        Espresso.onView(withId(R.id.tabLayout)).perform(selectTabAtPosition(1));
        Espresso.onView(withText("History")).perform(click());
    }

    @After
    public void testAfter() {

    }
}
