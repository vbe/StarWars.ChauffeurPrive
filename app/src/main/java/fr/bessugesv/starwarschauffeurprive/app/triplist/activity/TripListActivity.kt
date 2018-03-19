package fr.bessugesv.starwarschauffeurprive.app.triplist.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import fr.bessugesv.starwarschauffeurprive.R
import fr.bessugesv.starwarschauffeurprive.app.triplist.ui.TripList
import fr.bessugesv.starwarschauffeurprive.app.triplist.ui.TripListSeparator
import fr.bessugesv.starwarschauffeurprive.app.triplist.vm.TripListViewModel
import fr.bessugesv.starwarschauffeurprive.common.arch.ERROR
import fr.bessugesv.starwarschauffeurprive.common.arch.LOADING
import fr.bessugesv.starwarschauffeurprive.common.arch.SUCCESS
import fr.bessugesv.starwarschauffeurprive.common.arch.SingleDataPresenter
import fr.bessugesv.starwarschauffeurprive.databinding.ActivityTripListBinding

/**
 * Created by Vincent on 3/17/2018.
 */
class TripListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityTripListBinding>(this, R.layout.activity_trip_list)
        val adapter = TripList.Adapter()

        with(binding) {
            list.let {
                it.layoutManager = LinearLayoutManager(this@TripListActivity)
                it.adapter = adapter
                it.addItemDecoration(TripListSeparator())
            }
            toolbar.title = getString(R.string.Last_Trips)
        }

        SingleDataPresenter(
                this,
                ViewModelProviders.of(this).get(TripListViewModel::class.java),
                binding.progressBar,
                binding.errorView!!, {
                    adapter.data = it
                    adapter.notifyDataSetChanged()
                }
        ).load()
    }
}