package com.example.androidtakehomeassignment

import android.os.SystemClock
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.activityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun testOpenChatButton(){
        SystemClock.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.btnOpenChat)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        SystemClock.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.btnOpenChat)).perform(ViewActions.click())
        SystemClock.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.quickSellChatUserName)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.quickSellChatLastSeenTime)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.btnQuickSellCamera)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.btnQuickSellEmojiFace)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.btnQuickSellAudioAndTextMsg)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.quickSellChatAudioCall)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.quickSellAttachFiles)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.quickSellChatMoreOptions)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        SystemClock.sleep(1000)
        Espresso.onView(ViewMatchers.withHint("Type a message")).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        SystemClock.sleep(1000)
    }

    @Test
    fun testSendNewTextButton(){
        SystemClock.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.btnOpenChat)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        SystemClock.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.btnOpenChat)).perform(ViewActions.click())
        SystemClock.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.quickSellChatUserName)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.quickSellChatLastSeenTime)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.btnQuickSellCamera)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.btnQuickSellEmojiFace)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.btnQuickSellAudioAndTextMsg)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.quickSellChatAudioCall)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.quickSellAttachFiles)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        Espresso.onView(ViewMatchers.withId(R.id.quickSellChatMoreOptions)).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        SystemClock.sleep(1000)
        Espresso.onView(ViewMatchers.withHint("Type a message")).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        SystemClock.sleep(1000)

        Espresso.onView(ViewMatchers.withId(R.id.etMessageText)).perform(
            ViewActions.typeText("Went for hiking in Lahaul valley."))
        SystemClock.sleep(1000)

        Espresso.onView(ViewMatchers.withId(R.id.btnQuickSellAudioAndTextMsg)).perform(ViewActions.click())

        SystemClock.sleep(1000)

    }

}