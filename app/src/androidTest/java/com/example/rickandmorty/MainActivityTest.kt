package com.example.rickandmorty

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @Rule
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp(){
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }
    @After
    fun tearDown() {

    }

    @Test
    fun test() {
        Espresso.onView(ViewMatchers.withId(R.id.button_load_message)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Button clicked!")).check(matches(ViewMatchers.isDisplayed()))
    }

}