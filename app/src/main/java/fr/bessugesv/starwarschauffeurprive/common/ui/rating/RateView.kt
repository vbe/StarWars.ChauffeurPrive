package fr.bessugesv.starwarschauffeurprive.common.ui.rating

import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import fr.bessugesv.starwarschauffeurprive.R
import fr.bessugesv.starwarschauffeurprive.databinding.ViewRateBinding

/**
 * Created by Vincent on 3/18/2018.
 */
class RateView : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val binding: ViewRateBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_rate, this, true)

    var starImages: List<StarView>? = null

    var rate: Float? = null
        set(value) {
            if (field == value) {
                return
            }
            field = value
            if (value == null || value == 0f) {
                binding.textNoRate.visibility = View.VISIBLE
                binding.starContainer.visibility = View.GONE
            }
            else {
                binding.textNoRate.visibility = View.GONE
                binding.starContainer.visibility = View.VISIBLE
                updateStarValues()
            }
        }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (starImages == null && rate ?: 0f > 0f && height > 0) {
            createStars()
            updateStarValues()
        }
    }

    fun createStars() {
        starImages = List(5, {
            StarView(context).also {
                binding.starContainer.addView(it, height, height)
            }
        })
    }

    fun updateStarValues() {
        starImages?.forEachIndexed { index, starView ->
            starView.value = (rate ?: 0f) - index
        }
    }
}

@BindingAdapter("rate")
fun RateView.setRate(rate: Float?) {
    this.rate = rate
}