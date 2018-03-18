package fr.bessugesv.starwarschauffeurprive.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by Vincent on 3/18/2018.
 */
class SeparatorView : View {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas) {
        val height = height.toFloat()
        val sideObjectWidth = height / 2
        val centerLineY = height / 2
        val rightEnd = width.toFloat() - 1

        // left side object
        canvas.drawLine(0f, 0f, 0f, height, SIDE_OBJECT_PAINT)
        canvas.drawLine(0f, centerLineY, sideObjectWidth, centerLineY, SIDE_OBJECT_PAINT)
        // center line
        canvas.drawLine(sideObjectWidth, centerLineY, rightEnd - sideObjectWidth, centerLineY, CENTER_LINE_PAINT)

        // left side object
        canvas.drawLine(rightEnd - sideObjectWidth, centerLineY, rightEnd, centerLineY, SIDE_OBJECT_PAINT)
        canvas.drawLine(rightEnd, 0f, rightEnd, height, SIDE_OBJECT_PAINT)
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
    }
}