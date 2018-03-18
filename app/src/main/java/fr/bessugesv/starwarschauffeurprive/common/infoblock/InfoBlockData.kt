package fr.bessugesv.starwarschauffeurprive.common.infoblock

/**
 * Created by Vincent on 3/18/2018.
 */
data class InfoBlockData(val label: String, val value: String, val details: String = "", val detailsVisible: Boolean = details.isNotEmpty())