package fr.bessugesv.starwarschauffeurprive.model

import com.google.gson.annotations.SerializedName
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi

/**
 * Created by Vincent on 3/17/2018.
 */
class Trip {
    @SerializedName(StarWarsApi.Schema.Trip.ID)
    var id: Long? = null
    @SerializedName(StarWarsApi.Schema.Trip.PILOT)
    var pilot: Pilot? = null
    @SerializedName(StarWarsApi.Schema.Trip.DISTANCE)
    var distance: Distance? = null
    @SerializedName(StarWarsApi.Schema.Trip.DURATION)
    var duration: Long? = null
    @SerializedName(StarWarsApi.Schema.Trip.PICK_UP)
    var pickUp: Destination? = null
    @SerializedName(StarWarsApi.Schema.Trip.DROP_OFF)
    var dropOff: Destination? = null
}