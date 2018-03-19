package fr.bessugesv.starwarschauffeurprive.app.trip.ui

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import fr.bessugesv.starwarschauffeurprive.R
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi
import fr.bessugesv.starwarschauffeurprive.app.trip.mappers.TripDataMappers
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

        StarWarsApi.service.trip(tripId).enqueue(object : Callback<Trip> {
            override fun onResponse(call: Call<Trip>?, response: Response<Trip>?) {
                binding.details?.data = TripDataMappers.TripDetails.tripToTripDetailsViewData(this@TripActivity, response?.body())
            }

            override fun onFailure(call: Call<Trip>?, t: Throwable?) {
                Log.e("TripListActivity", "error getting trip list", t)
            }

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