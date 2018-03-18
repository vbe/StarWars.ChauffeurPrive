package fr.bessugesv.starwarschauffeurprive.app.triplist

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import fr.bessugesv.starwarschauffeurprive.R
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi
import fr.bessugesv.starwarschauffeurprive.app.trip.TripActivity
import fr.bessugesv.starwarschauffeurprive.databinding.ItemTripListBinding
import fr.bessugesv.starwarschauffeurprive.model.Trip
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Vincent on 3/17/2018.
 */
class TripListActivity : AppCompatActivity() {

    lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = Adapter()

        setContentView(RecyclerView(this).also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
            it.background = ContextCompat.getDrawable(this, R.drawable.background)
        })

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
            Glide.with(binding.root.context).load(StarWarsApi.BASE_URL+trip.pilot?.avatarPath).into(binding.image)
            binding.textPilot.text = trip.pilot?.name
            binding.textFrom.text = trip.pickUp?.name
            binding.textTo.text = trip.dropOff?.name
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