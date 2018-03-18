package fr.bessugesv.starwarschauffeurprive

import fr.bessugesv.starwarschauffeurprive.model.Destination
import fr.bessugesv.starwarschauffeurprive.model.Distance
import fr.bessugesv.starwarschauffeurprive.model.Pilot
import fr.bessugesv.starwarschauffeurprive.model.Trip
import java.util.*

/**
 * Created by Vincent on 3/18/2018.
 */

val YAVIN_4_TO_NABOO_TRIP = Trip().apply {
    id = 1L
    pilot = Pilot().apply {
        name = "Dark Vador"
        avatarPath = "/static/dark-vador.png"
        rating = 5.0f
    }
    distance = Distance().apply {
        value = 2478572L
        unit = "km"
    }
    duration = 19427000L
    pickUp = Destination().apply {
        name = "Yavin 4"
        picturePath = "/static/yavin-4.png"
        date = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
            set(Calendar.YEAR, 2017)
            set(Calendar.MONTH, Calendar.DECEMBER)
            set(Calendar.DAY_OF_MONTH, 9)
            set(Calendar.HOUR_OF_DAY, 14)
            set(Calendar.MINUTE, 12)
            set(Calendar.SECOND, 51)
            set(Calendar.MILLISECOND, 0)
        }.time
    }
    dropOff = Destination().apply {
        name = "Naboo"
        picturePath = "/static/naboo.png"
        date = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
            set(Calendar.YEAR, 2017)
            set(Calendar.MONTH, Calendar.DECEMBER)
            set(Calendar.DAY_OF_MONTH, 9)
            set(Calendar.HOUR_OF_DAY, 19)
            set(Calendar.MINUTE, 35)
            set(Calendar.SECOND, 51)
            set(Calendar.MILLISECOND, 0)
        }.time
    }
}