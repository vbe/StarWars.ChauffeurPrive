package fr.bessugesv.starwarschauffeurprive.common.utils

/**
 * Created by Vincent on 3/19/2018.
 */
object Format {

    fun millisecToReadableTime(ms: Long): String {
        var remainingSeconds = ms / 1000
        val hours = remainingSeconds / 3600
        remainingSeconds -= hours * 3600
        val minutes = remainingSeconds / 60
        val seconds = remainingSeconds - minutes * 60
        return String.format("%d:%02d:%02d", hours, minutes, seconds)
    }
}