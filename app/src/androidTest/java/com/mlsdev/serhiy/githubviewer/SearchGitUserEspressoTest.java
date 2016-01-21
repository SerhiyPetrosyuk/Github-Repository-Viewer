package com.mlsdev.serhiy.githubviewer;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.mlsdev.serhiy.githubviewer.view.activity.SearchActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchGitUserEspressoTest {
    public static final String STRING_TO_BE_TYPED = "petrosyuk";

    @Rule
    public ActivityTestRule<SearchActivity> mSearchActivityRule = new ActivityTestRule<>(SearchActivity.class);

    @Test
    public void searchGitUserTest() {
        Espresso.onView(ViewMatchers.withId(R.id.et_user_name))
                .perform(ViewActions.typeText(STRING_TO_BE_TYPED), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.btn_search))
                .perform(ViewActions.click());
    }
}
