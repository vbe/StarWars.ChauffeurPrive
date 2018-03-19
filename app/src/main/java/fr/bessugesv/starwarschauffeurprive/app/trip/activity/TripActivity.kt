package fr.bessugesv.starwarschauffeurprive.app.trip.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import fr.bessugesv.starwarschauffeurprive.R
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi
import fr.bessugesv.starwarschauffeurprive.app.trip.ViewDataMappers
import fr.bessugesv.starwarschauffeurprive.app.trip.vm.TripViewModel
import fr.bessugesv.starwarschauffeurprive.databinding.ActivityTripBinding
import fr.bessugesv.starwarschauffeurprive.model.Trip
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Vincent on 3/18/2018.
 */
class TripActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tripId = intent.getLongExtra(EXTRA_TRIP_ID, -1L)
        if (tripId == -1L) {
            finish()
            return
        }

        val binding = DataBindingUtil.setContentView<ActivityTripBinding>(this, R.layout.activity_trip)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.title = ""
        }

        ViewModelProviders.of(this).get(TripViewModel::class.java).getTrip(tripId).observe(this, Observer {
            binding.details?.data = ViewDataMappers.TripDetails.fromTrip(this, it)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_TRIP_ID = "EXTRA_TRIP_ID"

        fun openMe(context: Context, tripId: Long) {
            context.startActivity(Intent(context, TripActivity::class.java).apply {
                putExtra(EXTRA_TRIP_ID, tripId)
            })
        }
    }
}