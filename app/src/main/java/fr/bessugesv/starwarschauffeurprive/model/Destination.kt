package fr.bessugesv.starwarschauffeurprive.model

import com.google.gson.annotations.SerializedName
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi

/**
 * Created by Vincent on 3/17/2018.
 */
class Destination {
    @SerializedName(StarWarsApi.Schema.Destination.NAME)
    var name: String? = null
    @SerializedName(StarWarsApi.Schema.Destination.PICTURE)
    var picturePath: String? = null
    @SerializedName(StarWarsApi.Schema.Destination.DATE)
    var date: String? = null
}