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

    private val starSize = resources.getDimensionPixelSize(R.dimen.rate_star_size)
    val starImages: List<StarView> by lazy {
        List(5, {
            StarView(context).also {
                binding.starContainer.addView(it, starSize, starSize)
            }
        })
    }

    var rate: Float? = null
        set(value) {
            if (field == value) {
                return
            }
            if (value == null || value == 0f) {
                binding.textNoRate.visibility = View.VISIBLE
                binding.starContainer.visibility = View.GONE
            }
            else {
                binding.textNoRate.visibility = View.GONE
                binding.starContainer.visibility = View.VISIBLE
                starImages.forEachIndexed { index, starView ->
                    starView.value = value - index
                }
            }
            field = value
        }
}

@BindingAdapter("rate")
fun RateView.setRate(rate: Float?) {
    this.rate = rate
}