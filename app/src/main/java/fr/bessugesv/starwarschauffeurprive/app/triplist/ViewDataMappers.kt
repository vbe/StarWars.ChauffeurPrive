package fr.bessugesv.starwarschauffeurprive.app.triplist

import fr.bessugesv.starwarschauffeurprive.app.triplist.ui.TripListItemViewData
import fr.bessugesv.starwarschauffeurprive.model.Trip

/**
 * Created by Vincent on 3/19/2018.
 */
object ViewDataMappers {

    object TripListItem {
        fun fromTrip(trip: Trip?) = TripListItemViewData(
                trip?.pilot?.avatar?.url,
                trip?.pilot?.name ?: "",
                trip?.pickUp?.name ?: "",
                trip?.dropOff?.name ?: ""
        )
    }
}