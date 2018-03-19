package fr.bessugesv.starwarschauffeurprive.app.trip.mappers

import fr.bessugesv.starwarschauffeurprive.common.ui.infoblock.InfoBlockViewData
import fr.bessugesv.starwarschauffeurprive.model.Trip
import android.content.Context
import fr.bessugesv.starwarschauffeurprive.R
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi
import fr.bessugesv.starwarschauffeurprive.app.trip.ui.TripDetailsViewData
import fr.bessugesv.starwarschauffeurprive.common.utils.Format
import fr.bessugesv.starwarschauffeurprive.model.Destination
import fr.bessugesv.starwarschauffeurprive.model.Distance
import java.text.SimpleDateFormat
import java.util.*
import java.text.DecimalFormat

/**
 * Created by Vincent on 3/18/2018.
 */
object TripDataMappers {

    object InfoBlock {
        val TIME_FORMAT = SimpleDateFormat("h:mm a", Locale.getDefault())
        val DISTANCE_FORMAT = DecimalFormat()

        fun tripPickUpToInfoBlockData(context: Context, trip: Trip?) =
                destinationToInfoBlockData(context.getString(R.string.Departure), trip?.pickUp)

        fun tripDropOffToInfoBlockData(context: Context, trip: Trip?) =
                destinationToInfoBlockData(context.getString(R.string.Arrival), trip?.dropOff)

        fun destinationToInfoBlockData(label: String, destination: Destination?) = InfoBlockViewData(
                label,
                destination?.name?.toUpperCase() ?: "",
                destination?.date?.let { TIME_FORMAT.format(it) } ?: ""
        )

        fun distanceToInfoBlockData(context: Context, distance: Distance?) = InfoBlockViewData(
                context.getString(R.string.Trip_Distance),
                context.getString(
                        R.string.distance_with_unit,
                        distance?.value?.let { DISTANCE_FORMAT.format(it) },
                        distance?.unit?.toUpperCase()
                )
        )

        fun durationToInfoBlockData(context: Context, duration: Long?) = InfoBlockViewData(
                context.getString(R.string.Trip_Duration),
                duration?.let { Format.millisecToReadableTime(it) } ?: ""
        )
    }

    object TripDetails {
        fun tripToTripDetailsViewData(context: Context, trip: Trip?) = TripDetailsViewData(
                StarWarsApi.BASE_URL+trip?.pilot?.avatarPath,
                trip?.pilot?.name,
                InfoBlock.tripPickUpToInfoBlockData(context, trip),
                InfoBlock.distanceToInfoBlockData(context, trip?.distance),
                InfoBlock.tripDropOffToInfoBlockData(context, trip),
                InfoBlock.durationToInfoBlockData(context, trip?.duration),
                context.getString(R.string.Pilot_Rating),
                trip?.pilot?.rating
        )
    }
}