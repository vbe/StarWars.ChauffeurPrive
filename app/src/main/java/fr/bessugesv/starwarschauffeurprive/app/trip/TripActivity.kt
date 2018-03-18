package fr.bessugesv.starwarschauffeurprive.app.trip

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.bessugesv.starwarschauffeurprive.R
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi
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

        StarWarsApi.service.trip(tripId).enqueue(object : Callback<Trip> {
            override fun onResponse(call: Call<Trip>?, response: Response<Trip>?) {
                val trip = response?.body()
                binding.textPilot.text = trip?.pilot?.name
                Glide.with(binding.root.context).load(StarWarsApi.BASE_URL+trip?.pilot?.avatarPath).into(binding.image)

                val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
                // pick up
                binding.infoPickUp.label = getString(R.string.Departure)
                binding.infoPickUp.value = trip?.pickUp?.name
                binding.infoPickUp.details = format.format(trip?.pickUp?.date)
                // drop off
                binding.infoDropOff.label = getString(R.string.Arrival)
                binding.infoDropOff.value = trip?.dropOff?.name
                binding.infoDropOff.details = format.format(trip?.dropOff?.date)
                // distance
                binding.infoDistance.label = getString(R.string.Trip_Distance)
                binding.infoDistance.value = getString(R.string.distance_with_unit, trip?.distance?.value?.toString(), trip?.distance?.unit)
                // duration
                binding.infoDuration.label = getString(R.string.Trip_Duration)
                binding.infoDuration.value = trip?.duration?.toString()
            }

            override fun onFailure(call: Call<Trip>?, t: Throwable?) {
                Log.e("TripListActivity", "error getting trip list", t)
            }

        })

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