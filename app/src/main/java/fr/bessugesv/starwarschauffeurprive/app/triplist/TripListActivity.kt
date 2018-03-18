package fr.bessugesv.starwarschauffeurprive.app.triplist

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi
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


    class VH(context: Context) : RecyclerView.ViewHolder(TextView(context)) {
        val textView: TextView = itemView as TextView
        fun bind(trip: Trip) {
            textView.text = "${trip.pilot?.name} is going from ${trip.pickUp?.name} to ${trip.dropOff?.name}"
        }
    }


    class Adapter : RecyclerView.Adapter<VH>() {
        var data = emptyList<Trip>()

        override fun getItemCount() = data.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(parent.context)

        override fun onBindViewHolder(holder: VH, position: Int) {
            holder.bind(data[position])
        }
    }



}