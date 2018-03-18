package fr.bessugesv.starwarschauffeurprive.common.extensions

import android.view.View
import android.databinding.BindingAdapter

/**
 * Created by Vincent on 3/18/2018.
 */

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean) {
    visibility = if(visible) View.VISIBLE else View.GONE
}
