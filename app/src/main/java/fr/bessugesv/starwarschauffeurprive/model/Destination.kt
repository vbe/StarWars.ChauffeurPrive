package fr.bessugesv.starwarschauffeurprive.model

import com.google.gson.annotations.SerializedName
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi
import java.util.*

/**
 * Created by Vincent on 3/17/2018.
 */
class Destination {
    @SerializedName(StarWarsApi.Schema.Destination.NAME)
    var name: String? = null
    @SerializedName(StarWarsApi.Schema.Destination.PICTURE)
    var picturePath: String? = null
    @SerializedName(StarWarsApi.Schema.Destination.DATE)
    var date: Date? = null
}