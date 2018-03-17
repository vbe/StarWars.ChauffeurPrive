package fr.bessugesv.starwarschauffeurprive.api

import com.squareup.okhttp.internal.io.FileSystem
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import fr.bessugesv.starwarschauffeurprive.model.Trip
import okio.Buffer
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import java.io.File

/**
 * Created by Vincent on 3/17/2018.
 */
class TripsParsingTest {

    lateinit var trips: List<Trip>

    @Before
    fun setup() {
        val server = MockWebServer().also { it.start() }
        val service = StarWarsApi.getRetrofit(server.url("/").toString()).create(StarWarsService::class.java)
        server.enqueue(MockResponse().apply {
            setResponseCode(200)
            body = Buffer().apply { writeAll(FileSystem.SYSTEM.source(File("./mockdata/trips.json"))) }
        })
        trips = service.listTrips().execute().body() ?: emptyList()
    }

    @Test
    fun testTrip1ContainsData() {
        val trip1 = trips[0]

        assertThat(trip1.id, equalTo(1L))
        assertThat(trip1.duration, equalTo(19427000L))
        assertThat(trip1.pilot, notNullValue())
        assertThat(trip1.distance, notNullValue())
        assertThat(trip1.pickUp, notNullValue())
        assertThat(trip1.dropOff, notNullValue())
    }

    @Test
    fun testTrip1Pilot() {
        val darkVador = trips[0].pilot!!

        assertThat(darkVador.name, equalTo("Dark Vador"))
        assertThat(darkVador.avatarPath, equalTo("/static/dark-vador.png"))
        assertThat(darkVador.rating, equalTo(5.0f))
    }

    @Test
    fun testTrip1Distance() {
        val distance = trips[0].distance!!

        assertThat(distance.unit, equalTo("km"))
        assertThat(distance.value, equalTo(2478572L))
    }

    @Test
    fun testTrip1DropOff() {
        val dropOff = trips[0].dropOff!!

        assertThat(dropOff.name, equalTo("Naboo"))
        assertThat(dropOff.picturePath, equalTo("/static/naboo.png"))
        assertThat(dropOff.date, equalTo("2017-12-09T19:35:51Z"))
    }
}