package fr.bessugesv.starwarschauffeurprive.mappers

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import fr.bessugesv.starwarschauffeurprive.YAVIN_4_TO_NABOO_TRIP
import fr.bessugesv.starwarschauffeurprive.app.trip.mappers.TripDataMappers
import fr.bessugesv.starwarschauffeurprive.common.ui.infoblock.InfoBlockData
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Vincent on 3/18/2018.
 */
@RunWith(AndroidJUnit4::class)
class TripDataMappersTest {

    lateinit var appContext: Context

    @Before
    fun setup() {
        appContext = InstrumentationRegistry.getTargetContext()
    }

    @Test
    fun tripPickUpToInfoBlockData() {
        assertThat(
                TripDataMappers.InfoBlock.tripPickUpToInfoBlockData(appContext, YAVIN_4_TO_NABOO_TRIP),
                equalTo(InfoBlockData("Departure", "YAVIN 4", "2:12 PM"))
        )
    }

    @Test
    fun tripDropOffToInfoBlockData() {
        assertThat(
                TripDataMappers.InfoBlock.tripDropOffToInfoBlockData(appContext, YAVIN_4_TO_NABOO_TRIP),
                equalTo(InfoBlockData("Arrival", "NABOO", "7:35 PM"))
        )
    }

    @Test
    fun distanceToInfoBlockData() {
        assertThat(
                TripDataMappers.InfoBlock.distanceToInfoBlockData(appContext, YAVIN_4_TO_NABOO_TRIP.distance),
                equalTo(InfoBlockData("Trip Distance", "2,478,572 KM"))
        )
    }

    @Test
    fun durationToInfoBlockData() {
        assertThat(
                TripDataMappers.InfoBlock.durationToInfoBlockData(appContext, YAVIN_4_TO_NABOO_TRIP.duration),
                equalTo(InfoBlockData("Trip Duration", "5:23:47"))
        )
    }

}
