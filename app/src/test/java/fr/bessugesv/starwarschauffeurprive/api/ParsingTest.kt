package fr.bessugesv.starwarschauffeurprive.api

import android.support.annotation.CallSuper
import com.squareup.okhttp.internal.io.FileSystem
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import fr.bessugesv.starwarschauffeurprive.model.Destination
import fr.bessugesv.starwarschauffeurprive.model.Distance
import fr.bessugesv.starwarschauffeurprive.model.Pilot
import fr.bessugesv.starwarschauffeurprive.model.Trip
import okio.Buffer
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Before
import java.io.File
import java.util.*

/**
 * Created by Vincent on 3/18/2018.
 */
abstract class ParsingTest {

    lateinit var service: StarWarsService
    lateinit var server: MockWebServer

    fun enqueueMockFile(fileName: String) {
        server.enqueue(MockResponse().apply {
            setResponseCode(200)
            body = Buffer().apply { writeAll(FileSystem.SYSTEM.source(File("./mockdata/$fileName"))) }
        })
    }

    @CallSuper
    @Before
    open fun setup() {
        server = MockWebServer().also { it.start() }
        service = StarWarsApi.getRetrofit(server.url("/").toString()).create(StarWarsService::class.java)
    }

    val yavin4ToNabooTrip = Trip().apply {
        id = 1L
        pilot = Pilot().apply {
            name = "Dark Vador"
            avatarPath = "/static/dark-vador.png"
            rating = 5.0f
        }
        distance = Distance().apply {
            value = 2478572L
            unit = "km"
        }
        duration = 19427000L
        pickUp = Destination().apply {
            name = "Yavin 4"
            picturePath = "/static/yavin-4.png"
            date = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
                set(Calendar.YEAR, 2017)
                set(Calendar.MONTH, Calendar.DECEMBER)
                set(Calendar.DAY_OF_MONTH, 9)
                set(Calendar.HOUR_OF_DAY, 14)
                set(Calendar.MINUTE, 12)
                set(Calendar.SECOND, 51)
                set(Calendar.MILLISECOND, 0)
            }.time
        }
        dropOff = Destination().apply {
            name = "Naboo"
            picturePath = "/static/naboo.png"
            date = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
                set(Calendar.YEAR, 2017)
                set(Calendar.MONTH, Calendar.DECEMBER)
                set(Calendar.DAY_OF_MONTH, 9)
                set(Calendar.HOUR_OF_DAY, 19)
                set(Calendar.MINUTE, 35)
                set(Calendar.SECOND, 51)
                set(Calendar.MILLISECOND, 0)
            }.time
        }
    }

    fun tripContainsData(trip: Trip) {
        assertThat(trip.id, notNullValue())
        assertThat(trip.duration, notNullValue())
        assertThat(trip.pilot, notNullValue())
        assertThat(trip.distance, notNullValue())
        assertThat(trip.pickUp, notNullValue())
        assertThat(trip.dropOff, notNullValue())
    }

    fun tripHasYavin4ToNabooTripId(trip: Trip) {
        assertThat(trip.id, equalTo(yavin4ToNabooTrip.id))
    }

    fun tripHasYavin4ToNabooTripDuration(trip: Trip) {
        assertThat(trip.duration, equalTo(yavin4ToNabooTrip.duration))
    }

    fun tripHasYavin4ToNabooTripPilot(pilot: Pilot) {
        with(yavin4ToNabooTrip.pilot!!) {
            assertThat(pilot.name, equalTo(name))
            assertThat(pilot.avatarPath, equalTo(avatarPath))
            assertThat(pilot.rating, equalTo(rating))
        }
    }

    fun tripHasYavin4ToNabooTripDistance(distance: Distance) {
        with(yavin4ToNabooTrip.distance!!) {
            assertThat(distance.value, equalTo(value))
            assertThat(distance.unit, equalTo(unit))
        }
    }

    fun tripHasYavin4ToNabooTripPickUp(destination: Destination) {
        destinationsAreEqual(yavin4ToNabooTrip.pickUp!!, destination)
    }

    fun tripHasYavin4ToNabooTripDropOff(destination: Destination) {
        destinationsAreEqual(yavin4ToNabooTrip.dropOff!!, destination)
    }

    fun destinationsAreEqual(destination1: Destination, destination2: Destination) {
        assertThat(destination1.name, equalTo(destination2.name))
        assertThat(destination1.picturePath, equalTo(destination2.picturePath))
        assertThat(destination1.date, equalTo(destination2.date))
    }


}