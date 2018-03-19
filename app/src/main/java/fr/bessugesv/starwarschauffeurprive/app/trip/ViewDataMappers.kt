package fr.bessugesv.starwarschauffeurprive.app.trip

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
object ViewDataMappers {

    object InfoBlock {
        val TIME_FORMAT = SimpleDateFormat("h:mm a", Locale.getDefault())
        val DISTANCE_FORMAT = DecimalFormat()

        fun fromTripPickUp(context: Context, trip: Trip?) =
                fromDestination(context.getString(R.string.Departure), trip?.pickUp)

        fun fromTripDropOff(context: Context, trip: Trip?) =
                fromDestination(context.getString(R.string.Arrival), trip?.dropOff)

        fun fromDestination(label: String, destination: Destination?) = InfoBlockViewData(
                label,
                destination?.name?.toUpperCase() ?: "",
                destination?.date?.let { TIME_FORMAT.format(it) } ?: ""
        )

        fun fromDistance(context: Context, distance: Distance?) = InfoBlockViewData(
                context.getString(R.string.Trip_Distance),
                context.getString(
                        R.string.distance_with_unit,
                        distance?.value?.let { DISTANCE_FORMAT.format(it) },
                        distance?.unit?.toUpperCase()
                )
        )

        fun fromDuration(context: Context, duration: Long?) = InfoBlockViewData(
                context.getString(R.string.Trip_Duration),
                duration?.let { Format.millisecToReadableTime(it) } ?: ""
        )
    }

    object TripDetails {
        fun fromTrip(context: Context, trip: Trip?) = TripDetailsViewData(
                StarWarsApi.BASE_URL+trip?.pilot?.avatarPath,
                trip?.pilot?.name,
                InfoBlock.fromTripPickUp(context, trip),
                InfoBlock.fromDistance(context, trip?.distance),
                InfoBlock.fromTripDropOff(context, trip),
                InfoBlock.fromDuration(context, trip?.duration),
                context.getString(R.string.Pilot_Rating),
                trip?.pilot?.rating
        )
    }
}