package fr.bessugesv.starwarschauffeurprive.common.ui.infoblock

/**
 * Created by Vincent on 3/18/2018.
 */
data class InfoBlockViewData(val label: String, val value: String, val details: String = "", val detailsVisible: Boolean = details.isNotEmpty())