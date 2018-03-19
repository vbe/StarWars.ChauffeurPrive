package fr.bessugesv.starwarschauffeurprive.common.extensions

import android.view.View
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by Vincent on 3/18/2018.
 */

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean) {
    visibility = if(visible) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Glide.with(this.context).load(url).into(this)
}
