package fr.bessugesv.starwarschauffeurprive.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * Created by Vincent on 3/18/2018.
 */
class SeparatorView : View {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    var bounds = computeBounds()

    fun computeBounds() = RectF(0f, 0f, width.toFloat(), height.toFloat())

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        bounds = computeBounds()
    }

    override fun onDraw(canvas: Canvas) {
        drawOn(canvas, bounds)
    }

    companion object {
        val SIDE_OBJECT_PAINT = Paint().apply {
            color = Color.parseColor("#99ffffff")
            strokeWidth = 0f
        }
        val CENTER_LINE_PAINT = Paint().apply {
            color = Color.parseColor("#3fffffff")
            strokeWidth = 0f
        }

        fun drawOn(canvas: Canvas, bounds: RectF) {
            val sideObjectWidth = bounds.height() / 2
            val centerLineY = bounds.top + bounds.height() / 2
            val rightEnd = bounds.width() - 1

            // left side object
            canvas.drawLine(bounds.left, bounds.top, bounds.left, bounds.bottom, SIDE_OBJECT_PAINT)
            canvas.drawLine(bounds.left, centerLineY, bounds.left + sideObjectWidth, centerLineY, SIDE_OBJECT_PAINT)
            // center line
            canvas.drawLine(bounds.left + sideObjectWidth, centerLineY, rightEnd - sideObjectWidth, centerLineY, CENTER_LINE_PAINT)

            // left side object
            canvas.drawLine(rightEnd - sideObjectWidth, centerLineY, rightEnd, centerLineY, SIDE_OBJECT_PAINT)
            canvas.drawLine(rightEnd, bounds.top, rightEnd, bounds.bottom, SIDE_OBJECT_PAINT)

        }
    }
}