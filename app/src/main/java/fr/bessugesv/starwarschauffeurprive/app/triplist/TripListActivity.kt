package fr.bessugesv.starwarschauffeurprive.app.triplist

import android.databinding.DataBindingUtil
import android.graphics.Canvas
import android.graphics.RectF
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import fr.bessugesv.starwarschauffeurprive.R
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi
import fr.bessugesv.starwarschauffeurprive.app.trip.activity.TripActivity
import fr.bessugesv.starwarschauffeurprive.common.ui.SeparatorView
import fr.bessugesv.starwarschauffeurprive.databinding.ActivityTripListBinding
import fr.bessugesv.starwarschauffeurprive.databinding.ItemTripListBinding
import fr.bessugesv.starwarschauffeurprive.model.Trip
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Vincent on 3/17/2018.
 */
class TripListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = Adapter()
        val binding = DataBindingUtil.setContentView<ActivityTripListBinding>(this, R.layout.activity_trip_list)

        binding.list.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
            it.addItemDecoration(Separator())
        }

        binding.toolbar.title = getString(R.string.Last_Trips)

        StarWarsApi.service.listTrips().enqueue(object : Callback<List<Trip>> {
            override fun onResponse(call: Call<List<Trip>>?, response: Response<List<Trip>>?) {
                response.let {
                    if (it != null && it.isSuccessful) {
                        adapter.data = it.body() ?: emptyList()
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<List<Trip>>?, t: Throwable) {
                Log.e("TripListActivity", "error getting trip list", t)
            }

        })
    }

    class Separator : RecyclerView.ItemDecoration() {


        override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            if (HEIGHT == -1) {
                HEIGHT = parent.context.resources.getDimensionPixelSize(R.dimen.separator_height)
            }

            for (i in 0 until parent.childCount) {
                val view = parent.getChildAt(i)
                val position = parent.getChildAdapterPosition(view)
                if (position < parent.adapter.itemCount - 1) {
                    SeparatorView.drawOn(canvas, RectF(view.x, view.y + view.height - HEIGHT/2, view.x + view.width, view.y + view.height + HEIGHT/2))
                }
            }
        }

        companion object {
            var HEIGHT = -1
        }
    }


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