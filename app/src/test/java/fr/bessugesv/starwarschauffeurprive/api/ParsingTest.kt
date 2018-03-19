package fr.bessugesv.starwarschauffeurprive.api

import android.support.annotation.CallSuper
import com.squareup.okhttp.internal.io.FileSystem
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import fr.bessugesv.starwarschauffeurprive.TestHelpers
import fr.bessugesv.starwarschauffeurprive.YAVIN_4_TO_NABOO_TRIP
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

/**
 * Created by Vincent on 3/18/2018.
 */
abstract class ParsingTest {

    lateinit var service: StarWarsService
    lateinit var server: MockWebServer

    fun enqueueMockFile(fileName: String) {
        TestHelpers.enqueueMockFile(server, File("./mockdata/$fileName"))
    }

    @CallSuper
    @Before
    open fun setup() {
        server = MockWebServer().also { it.start() }
        service = StarWarsApi.getService(server.url("/").toString())
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
        assertThat(trip.id, equalTo(YAVIN_4_TO_NABOO_TRIP.id))
    }

    fun tripHasYavin4ToNabooTripDuration(trip: Trip) {
        assertThat(trip.duration, equalTo(YAVIN_4_TO_NABOO_TRIP.duration))
    }

    fun tripHasYavin4ToNabooTripPilot(pilot: Pilot) {
        with(YAVIN_4_TO_NABOO_TRIP.pilot!!) {
            assertThat(pilot.name, equalTo(name))
            assertThat(pilot.avatar?.path, equalTo(avatar?.path))
            assertThat(pilot.rating, equalTo(rating))
        }
    }

    fun tripHasYavin4ToNabooTripDistance(distance: Distance) {
        with(YAVIN_4_TO_NABOO_TRIP.distance!!) {
            assertThat(distance.value, equalTo(value))
            assertThat(distance.unit, equalTo(unit))
        }
    }

    fun tripHasYavin4ToNabooTripPickUp(destination: Destination) {
        destinationsAreEqual(YAVIN_4_TO_NABOO_TRIP.pickUp!!, destination)
    }

    fun tripHasYavin4ToNabooTripDropOff(destination: Destination) {
        destinationsAreEqual(YAVIN_4_TO_NABOO_TRIP.dropOff!!, destination)
    }

    fun destinationsAreEqual(destination1: Destination, destination2: Destination) {
        assertThat(destination1.name, equalTo(destination2.name))
        assertThat(destination1.picture?.path, equalTo(destination2.picture?.path))
        assertThat(destination1.date, equalTo(destination2.date))
    }


}