package com.zaomeng.zaomeng.fastandroid;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.zaomeng.zaomeng.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;

/**
 * Created by Sampson on 2019/3/29.
 * FastAndroid
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTestActivity {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void testActivityMain() {
        onView(withId(R.id.nav_fragment));
    }

}
