package fr.bessugesv.starwarschauffeurprive.api

import fr.bessugesv.starwarschauffeurprive.model.Trip
import org.junit.Test

/**
 * Created by Vincent on 3/17/2018.
 */
class TripListParsingTest : ParsingTest() {

    lateinit var trips: List<Trip>

    override fun setup() {
        super.setup()
        enqueueMockFile("trips.json")
        trips = service.listTrips().execute().body()!!
    }

    @Test
    fun allTripsContainsData() {
        for (trip in trips) {
            tripContainsData(trip)
        }
    }

    @Test
    fun trip1HasRightValues() {
        tripHasYavin4ToNabooTripId(trips[0])
        tripHasYavin4ToNabooTripDuration(trips[0])
    }

    @Test
    fun trip1HasRightPilot() {
        tripHasYavin4ToNabooTripPilot(trips[0].pilot!!)
    }

    @Test
    fun trip1HasRightDistance() {
        tripHasYavin4ToNabooTripDistance(trips[0].distance!!)
    }

    @Test
    fun trip1HasRightPickUp() {
        tripHasYavin4ToNabooTripPickUp(trips[0].pickUp!!)
    }

    @Test
    fun trip1HasRightDripOff() {
        tripHasYavin4ToNabooTripDropOff(trips[0].dropOff!!)
    }
}