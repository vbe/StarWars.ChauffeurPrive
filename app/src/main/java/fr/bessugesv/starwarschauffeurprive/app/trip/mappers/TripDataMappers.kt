package fr.bessugesv.starwarschauffeurprive.app.trip.mappers

import fr.bessugesv.starwarschauffeurprive.common.infoblock.InfoBlockData
import fr.bessugesv.starwarschauffeurprive.model.Trip
import android.content.Context
import fr.bessugesv.starwarschauffeurprive.R
import fr.bessugesv.starwarschauffeurprive.model.Destination
import fr.bessugesv.starwarschauffeurprive.model.Distance

/**
 * Created by Vincent on 3/18/2018.
 */
object TripDataMappers {

    fun tripPickUpToInfoBlockData(context: Context, trip: Trip?) =
            destinationToInfoBlockData(context.getString(R.string.Departure), trip?.pickUp)

    fun tripDropOffToInfoBlockData(context: Context, trip: Trip?) =
            destinationToInfoBlockData(context.getString(R.string.Arrival), trip?.dropOff)

    fun destinationToInfoBlockData(label: String, destination: Destination?) = InfoBlockData(
            label,
            destination?.name ?: "",
            destination?.date?.toString() ?: ""
    )

    fun distanceToInfoBlockData(context: Context, distance: Distance?) = InfoBlockData(
            context.getString(R.string.Trip_Distance),
            context.getString(R.string.distance_with_unit, distance?.value?.toString(), distance?.unit)
    )

    fun durationToInfoBlockData(context: Context, duration: Long?) = InfoBlockData(
            context.getString(R.string.Trip_Duration),
            duration?.toString() ?: ""
    )
}