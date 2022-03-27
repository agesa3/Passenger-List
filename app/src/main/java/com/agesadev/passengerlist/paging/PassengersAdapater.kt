package com.agesadev.passengerlist.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.agesadev.passengerlist.databinding.ItemPassengerBinding
import com.agesadev.passengerlist.models.Passenger
import com.agesadev.passengerlist.utils.loadImage

class PassengersAdapater :
    PagingDataAdapter<Passenger, PassengersAdapater.PassengersViewHolder>(PassengersComparator) {
    override fun onBindViewHolder(holder: PassengersViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindPassenger(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengersViewHolder {
        return PassengersViewHolder(
            ItemPassengerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class PassengersViewHolder(private val binding: ItemPassengerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindPassenger(passenger: Passenger) = with(binding) {
            imageViewAirlinesLogo.loadImage(passenger.airline.logo)
            textViewHeadquarters.text = passenger.airline.head_quarter
            textViewNameWithTrips.text = "${passenger.name}, ${passenger.trips} Trips"
        }
    }

    object PassengersComparator : DiffUtil.ItemCallback<Passenger>() {
        override fun areItemsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem == newItem
        }

    }


}
