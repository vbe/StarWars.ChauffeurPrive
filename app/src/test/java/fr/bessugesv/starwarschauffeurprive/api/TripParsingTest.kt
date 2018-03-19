package fr.bessugesv.starwarschauffeurprive.api

import fr.bessugesv.starwarschauffeurprive.TestHelpers
import fr.bessugesv.starwarschauffeurprive.model.Trip
import org.junit.Test

/**
 * Created by Vincent on 3/18/2018.
 */
class TripParsingTest : ParsingTest() {

    lateinit var trip: Trip

    override fun setup() {
        super.setup()
        TestHelpers.enqueueMockFile(server, "trips_1.json")
        trip = service.trip(1).execute().body()!!
    }

    @Test
    fun tripContainsData() {
        tripContainsData(trip)
    }

    @Test
    fun tripHasYavin4ToNabooTripId() {
        tripHasYavin4ToNabooTripId(trip)
    }

    @Test
    fun tripHasYavin4ToNabooTripDuration() {
        tripHasYavin4ToNabooTripDuration(trip)
    }

    @Test
    fun tripHasYavin4ToNabooTripPilot() {
        tripHasYavin4ToNabooTripPilot(trip.pilot!!)
    }

    @Test
    fun tripHasYavin4ToNabooTripDistance() {
        tripHasYavin4ToNabooTripDistance(trip.distance!!)
    }

    @Test
    fun tripHasYavin4ToNabooTripPickUp() {
        tripHasYavin4ToNabooTripPickUp(trip.pickUp!!)
    }

    @Test
    fun tripHasYavin4ToNabooTripDropOff() {
        tripHasYavin4ToNabooTripDropOff(trip.dropOff!!)
    }
}