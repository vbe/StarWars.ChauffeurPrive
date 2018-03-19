package fr.bessugesv.starwarschauffeurprive.common.ui.rating

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import fr.bessugesv.starwarschauffeurprive.R
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Vincent on 3/19/2018.
 */
class StarView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        initGraphicsIfNeeded(resources)
    }

    var value: Float = 0f
     set(value) {
         field = when {
             value <= 0 -> 0f
             value >= 1 -> 1f
             else -> value
         }
         requestLayout()
         invalidate()
     }

    private val filledSrcRect = Rect()
    private val filledDstRect = RectF()
    private val emptySrcRect = Rect()
    private val emptyDstRect = RectF()

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val separationOnBitmap = (FILLED_BITMAP.width * value).toInt()
        val separationOnCanvas = width * value

        filledSrcRect.set(0, 0, separationOnBitmap, FILLED_BITMAP.height)
        filledDstRect.set(0f, 0f, separationOnCanvas, height.toFloat())

        emptySrcRect.set(separationOnBitmap, 0, EMPTY_BITMAP.width, EMPTY_BITMAP.height)
        emptyDstRect.set(separationOnCanvas, 0f, width.toFloat(), height.toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(FILLED_BITMAP, filledSrcRect, filledDstRect, PAINT)
        canvas.drawBitmap(EMPTY_BITMAP, emptySrcRect, emptyDstRect, PAINT)
    }

    companion object {
        lateinit private var FILLED_BITMAP: Bitmap
        lateinit private var EMPTY_BITMAP: Bitmap
        private val PAINT = Paint()

        private val INIT_DONE = AtomicBoolean(false)

        private fun initGraphicsIfNeeded(resources: Resources) {
            if (INIT_DONE.compareAndSet(false, true)) {
                FILLED_BITMAP = BitmapFactory.decodeResource(resources, R.drawable.img_star_filled)
                EMPTY_BITMAP = BitmapFactory.decodeResource(resources, R.drawable.img_star_empty)
            }
        }
    }
}