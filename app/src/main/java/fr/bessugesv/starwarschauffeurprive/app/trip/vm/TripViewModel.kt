package fr.bessugesv.starwarschauffeurprive.app.trip.vm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi
import fr.bessugesv.starwarschauffeurprive.common.arch.*
import fr.bessugesv.starwarschauffeurprive.model.Trip
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Vincent on 3/19/2018.
 */
class TripViewModel : SingleDataViewModelWithParams<Long, Trip>() {

    private var tripId: Long? = null
    private val trip : MutableLiveData<DataResult<Trip>> by lazy {
        MutableLiveData<DataResult<Trip>>().also {
            it.value = LOADING()
        }
    }

    override fun getData(params: Long?, forceReload: Boolean): LiveData<DataResult<Trip>> =
            if (!forceReload && params == this.tripId && trip.value !is ERROR) {
                trip
            }
            else {
                this.tripId = params
                trip.postValue(LOADING())
                loadTrip()
                trip
            }

    private fun loadTrip() {
        tripId?.let {
            StarWarsApi.service.trip(it).enqueue(object : Callback<Trip> {
                override fun onResponse(call: Call<Trip>?, response: Response<Trip>?) {
                    if (response != null && response.isSuccessful) {
                        response.body().let {
                            trip.postValue(if (it != null) SUCCESS(it) else ERROR())
                        }
                    }
                    else {
                        trip.postValue(ERROR())
                    }
                }

                override fun onFailure(call: Call<Trip>?, t: Throwable?) {
                    trip.postValue(ERROR())
                }
            })
        }
    }
}