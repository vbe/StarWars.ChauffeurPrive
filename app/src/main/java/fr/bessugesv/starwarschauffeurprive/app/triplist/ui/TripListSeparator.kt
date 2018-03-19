package fr.bessugesv.starwarschauffeurprive.app.triplist.ui

import android.graphics.Canvas
import android.graphics.RectF
import android.support.v7.widget.RecyclerView
import fr.bessugesv.starwarschauffeurprive.R
import fr.bessugesv.starwarschauffeurprive.common.ui.SeparatorView

/**
 * Created by Vincent on 3/19/2018.
 */
class TripListSeparator : RecyclerView.ItemDecoration() {
    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (HEIGHT == -1) {
            HEIGHT = parent.context.resources.getDimensionPixelSize(R.dimen.separator_height)
        }

        for (i in 0 until parent.childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            if (position < parent.adapter.itemCount - 1) {
                SeparatorView.drawOn(canvas, RectF(view.x, view.y + view.height - HEIGHT /2, view.x + view.width, view.y + view.height + HEIGHT /2))
            }
        }
    }

    companion object {
        var HEIGHT = -1
    }
}