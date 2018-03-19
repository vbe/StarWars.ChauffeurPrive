package fr.bessugesv.starwarschauffeurprive.app.trip

import android.os.Bundle
import fr.bessugesv.starwarschauffeurprive.app.trip.activity.TripActivity
import fr.bessugesv.starwarschauffeurprive.common.ActivityTest
import org.junit.Test

/**
 * Created by Vincent on 3/19/2018.
 */

class TripActivityTest : ActivityTest<TripActivity>(TripActivity::class) {
    override val extras = Bundle().apply {
        putLong(TripActivity.EXTRA_TRIP_ID, 1)
    }

    @Test
    fun networkError() {
        checkUiBehaviorWhenServerError()
    }

    @Test
    fun reloadAfterNetworkError () {
        checkReloadAfterServerError("trips_1.json")
    }
}