package fr.bessugesv.starwarschauffeurprive.model

import com.google.gson.annotations.SerializedName
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi

/**
 * Created by Vincent on 3/17/2018.
 */
class Distance {
    @SerializedName(StarWarsApi.Schema.Distance.VALUE)
    var value: Long? = null
    @SerializedName(StarWarsApi.Schema.Distance.UNIT)
    var unit: String? = null
}