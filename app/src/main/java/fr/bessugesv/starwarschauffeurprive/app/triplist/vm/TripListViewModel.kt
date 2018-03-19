package fr.bessugesv.starwarschauffeurprive.app.triplist.vm

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
class TripListViewModel : ViewModel() {

    private val tripList : MutableLiveData<List<Trip>> by lazy {
        loadTripList()
        MutableLiveData<List<Trip>>()
    }

    fun getTripList(): LiveData<List<Trip>> = tripList

    private fun loadTripList() {
        StarWarsApi.service.listTrips().enqueue(object : Callback<List<Trip>> {
            override fun onResponse(call: Call<List<Trip>>?, response: Response<List<Trip>>?) {
                response.let {
                    if (it != null && it.isSuccessful) {
                        tripList.postValue(it.body())
                    }
                }
            }

            override fun onFailure(call: Call<List<Trip>>?, t: Throwable) {
                Log.e("TripListActivity", "error getting trip list", t)
            }
        })
    }
}