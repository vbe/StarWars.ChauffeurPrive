package fr.bessugesv.starwarschauffeurprive.triplist

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.Button
import android.widget.ProgressBar
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi
import fr.bessugesv.starwarschauffeurprive.app.triplist.activity.TripListActivity
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Vincent on 3/19/2018.
 */
@RunWith(AndroidJUnit4::class)
class TripListActivityTest {

    lateinit var server: MockWebServer
    @Rule @JvmField
    val activityRule: ActivityTestRule<TripListActivity> = ActivityTestRule<TripListActivity>(TripListActivity::class.java, false, false)

    @Before
    fun setup() {
        server = MockWebServer().also { it.start() }
        StarWarsApi.service = StarWarsApi.getService(server.url("/").toString())
        activityRule.launchActivity(null)
    }

    fun assertThatProgressBarVisibility(visible: Boolean) {
        onView(instanceOf(ProgressBar::class.java)).check(matches(if (visible) isDisplayed() else not(isDisplayed())))
    }

    @Test
    fun networkError() {
        // waiting for server response, a progress bar should be displayed
        assertThatProgressBarVisibility(true)
        server.enqueue(MockResponse().setResponseCode(500))
        // got the response, the progress bar should be hidden
        assertThatProgressBarVisibility(false)
        // the response was an error, a reload button should be displayed
        onView(withText("Reload")).check(matches(allOf(isDisplayed(), instanceOf(Button::class.java))))
    }


}