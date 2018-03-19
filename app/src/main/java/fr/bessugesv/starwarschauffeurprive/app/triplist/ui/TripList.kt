package fr.bessugesv.starwarschauffeurprive.app.triplist.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import fr.bessugesv.starwarschauffeurprive.app.trip.activity.TripActivity
import fr.bessugesv.starwarschauffeurprive.app.triplist.ViewDataMappers
import fr.bessugesv.starwarschauffeurprive.databinding.ItemTripListBinding
import fr.bessugesv.starwarschauffeurprive.model.Trip

/**
 * Created by Vincent on 3/19/2018.
 */
object TripList {

    class VH(
            parent: ViewGroup,
            val binding: ItemTripListBinding = ItemTripListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        : RecyclerView.ViewHolder(binding.root) {

        var tripId: Long? = null

        init {
            itemView.setOnClickListener { view ->
                tripId?.let {
                    TripActivity.openMe(view.context, it)
                }
            }
        }

        fun bind(trip: Trip) {
            tripId = trip.id
            binding.data = ViewDataMappers.TripListItem.fromTrip(trip)
        }
    }


    class Adapter : RecyclerView.Adapter<VH>() {
        var data = emptyList<Trip>()

        override fun getItemCount() = data.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(parent)

        override fun onBindViewHolder(holder: VH, position: Int) {
            holder.bind(data[position])
        }
    }
}