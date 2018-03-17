package fr.bessugesv.starwarschauffeurprive.model

import com.google.gson.annotations.SerializedName
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi

/**
 * Created by Vincent on 3/17/2018.
 */
class Pilot {
    @SerializedName(StarWarsApi.Schema.Pilot.NAME)
    var name: String? = null
    @SerializedName(StarWarsApi.Schema.Pilot.AVATAR)
    var avatarPath: String? = null
    @SerializedName(StarWarsApi.Schema.Pilot.RATING)
    var rating: Float? = null
}