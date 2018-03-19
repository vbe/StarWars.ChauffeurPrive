package fr.bessugesv.starwarschauffeurprive.app.trip.ui

import fr.bessugesv.starwarschauffeurprive.common.ui.infoblock.InfoBlockData

/**
 * Created by Vincent on 3/19/2018.
 */
data class TripDetailsViewData(
        val imageUrl: String?,
        val title: String?,
        val infoTopLeft: InfoBlockData,
        val infoBottomLeft: InfoBlockData,
        val infoTopRight: InfoBlockData,
        val infoBottomRight: InfoBlockData,
        val rateLabel:String?,
        val rate: Float?
)