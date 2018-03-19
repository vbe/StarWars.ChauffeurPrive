package fr.bessugesv.starwarschauffeurprive.app.triplist.vm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi
import fr.bessugesv.starwarschauffeurprive.common.arch.*
import fr.bessugesv.starwarschauffeurprive.model.Trip
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Vincent on 3/19/2018.
 */
class TripListViewModel : SingleDataViewModel<List<Trip>>() {

    private val tripList : MutableLiveData<DataResult<List<Trip>>> by lazy {
        loadTripList()
        MutableLiveData<DataResult<List<Trip>>>().also { it.value = LOADING() }
    }

    override fun getData(): LiveData<DataResult<List<Trip>>> = tripList.also {
        // reloading if last value was an error
        if (it.value is ERROR) {
            it.postValue(LOADING())
            loadTripList()
        }
    }

    private fun loadTripList() {
        StarWarsApi.service.listTrips().enqueue(object : Callback<List<Trip>> {
            override fun onResponse(call: Call<List<Trip>>?, response: Response<List<Trip>>?) {
                if (response != null && response.isSuccessful) {
                    tripList.postValue(SUCCESS(response.body() ?: emptyList()))
                }
                else {
                    tripList.postValue(ERROR())
                }
            }

            override fun onFailure(call: Call<List<Trip>>?, t: Throwable) {
                tripList.postValue(ERROR())
            }
        })
    }
}