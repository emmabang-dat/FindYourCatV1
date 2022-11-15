package com.example.findyourcatv1

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.findyourcatv1", appContext.packageName)

        Espresso.onView(ViewMatchers.withText("List Fragment"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.fab)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withText("Login Fragment"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }
}