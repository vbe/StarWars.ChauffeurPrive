package fr.bessugesv.starwarschauffeurprive.app.triplist.ui

/**
 * Created by Vincent on 3/19/2018.
 */
data class TripListItemViewData(
        val imageUrl: String?,
        val title: String,
        val from: String,
        val to: String,
        val rate: Float?,
        val ratingVisible: Boolean = rate ?: 0f > 0f
)