package fr.bessugesv.starwarschauffeurprive.app.triplist

import android.support.test.runner.AndroidJUnit4
import fr.bessugesv.starwarschauffeurprive.app.triplist.activity.TripListActivity
import fr.bessugesv.starwarschauffeurprive.common.ActivityTest
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Vincent on 3/19/2018.
 */
@RunWith(AndroidJUnit4::class)
class TripListActivityTest : ActivityTest<TripListActivity>(TripListActivity::class){
    @Test
    fun networkError() {
        checkUiBehaviorWhenServerError()
    }

    @Test
    fun reloadAfterNetworkError () {
        checkReloadAfterServerError("trips.json")
    }
}