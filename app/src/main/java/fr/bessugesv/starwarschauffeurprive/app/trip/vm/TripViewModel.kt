package fr.bessugesv.starwarschauffeurprive.app.trip.vm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi
import fr.bessugesv.starwarschauffeurprive.model.Trip
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Vincent on 3/19/2018.
 */
class TripViewModel : ViewModel() {

    private var tripId: Long? = null
    private val trip : MutableLiveData<Trip> by lazy {
        MutableLiveData<Trip>()
    }

    fun getTrip(tripId: Long): LiveData<Trip> = if (tripId == this.tripId) {
        trip
    }
    else {
        this.tripId = tripId
        loadTrip()
        trip
    }

    private fun loadTrip() {
        tripId?.let {
            StarWarsApi.service.trip(it).enqueue(object : Callback<Trip> {
                override fun onResponse(call: Call<Trip>?, response: Response<Trip>?) {
                    trip.postValue(response?.body())
                }

                override fun onFailure(call: Call<Trip>?, t: Throwable?) {
                    Log.e("TripListActivity", "error getting trip list", t)
                }

            })
        }
    }
}