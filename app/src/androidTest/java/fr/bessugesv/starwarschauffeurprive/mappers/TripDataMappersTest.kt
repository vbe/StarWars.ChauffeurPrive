package fr.bessugesv.starwarschauffeurprive.mappers

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import fr.bessugesv.starwarschauffeurprive.YAVIN_4_TO_NABOO_TRIP
import fr.bessugesv.starwarschauffeurprive.app.trip.ViewDataMappers
import fr.bessugesv.starwarschauffeurprive.common.ui.infoblock.InfoBlockViewData
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
                ViewDataMappers.InfoBlock.fromTripPickUp(appContext, YAVIN_4_TO_NABOO_TRIP),
                equalTo(InfoBlockViewData("Departure", "YAVIN 4", "2:12 PM"))
        )
    }

    @Test
    fun tripDropOffToInfoBlockData() {
        assertThat(
                ViewDataMappers.InfoBlock.fromTripDropOff(appContext, YAVIN_4_TO_NABOO_TRIP),
                equalTo(InfoBlockViewData("Arrival", "NABOO", "7:35 PM"))
        )
    }

    @Test
    fun distanceToInfoBlockData() {
        assertThat(
                ViewDataMappers.InfoBlock.fromDistance(appContext, YAVIN_4_TO_NABOO_TRIP.distance),
                equalTo(InfoBlockViewData("Trip Distance", "2,478,572 KM"))
        )
    }

    @Test
    fun durationToInfoBlockData() {
        assertThat(
                ViewDataMappers.InfoBlock.fromDuration(appContext, YAVIN_4_TO_NABOO_TRIP.duration),
                equalTo(InfoBlockViewData("Trip Duration", "5:23:47"))
        )
    }

}
