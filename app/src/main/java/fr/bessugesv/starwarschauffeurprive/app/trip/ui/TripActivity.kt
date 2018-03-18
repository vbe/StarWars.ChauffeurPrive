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
import fr.bessugesv.starwarschauffeurprive.databinding.PageTripBinding
import fr.bessugesv.starwarschauffeurprive.model.Trip
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

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

        val binding = DataBindingUtil.setContentView<PageTripBinding>(this, R.layout.page_trip)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.title = ""
        }

        StarWarsApi.service.trip(tripId).enqueue(object : Callback<Trip> {
            override fun onResponse(call: Call<Trip>?, response: Response<Trip>?) {
                val trip = response?.body()
                binding.textPilot.text = trip?.pilot?.name
                Glide.with(binding.root.context).load(StarWarsApi.BASE_URL+trip?.pilot?.avatarPath).into(binding.image)

                val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
                // pick up
                binding.infoPickUp?.data = TripDataMappers.tripPickUpToInfoBlockData(this@TripActivity, trip)
                // drop off
                binding.infoDropOff?.data = TripDataMappers.tripDropOffToInfoBlockData(this@TripActivity, trip)
                // distance
                binding.infoDistance?.data = TripDataMappers.distanceToInfoBlockData(this@TripActivity, trip?.distance)
                // duration
                binding.infoDuration?.data = TripDataMappers.durationToInfoBlockData(this@TripActivity, trip?.duration)
                // rating
                binding.rate.rate = trip?.pilot?.rating
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