package fr.bessugesv.starwarschauffeurprive.api

import fr.bessugesv.starwarschauffeurprive.model.Trip
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Vincent on 3/17/2018.
 */
interface StarWarsService {

    @GET(StarWarsApi.PATHS.TRIPS)
    fun listTrips(): Call<List<Trip>>

    @GET("${StarWarsApi.PATHS.TRIPS}/{id}")
    fun trip(@Path("id") tripId: Long): Call<Trip>
}