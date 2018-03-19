package fr.bessugesv.starwarschauffeurprive.app.trip.ui

import fr.bessugesv.starwarschauffeurprive.common.ui.infoblock.InfoBlockViewData

/**
 * Created by Vincent on 3/19/2018.
 */
data class TripDetailsViewData(
        val imageUrl: String?,
        val title: String?,
        val infoTopLeft: InfoBlockViewData,
        val infoBottomLeft: InfoBlockViewData,
        val infoTopRight: InfoBlockViewData,
        val infoBottomRight: InfoBlockViewData,
        val rateLabel:String?,
        val rate: Float?
)