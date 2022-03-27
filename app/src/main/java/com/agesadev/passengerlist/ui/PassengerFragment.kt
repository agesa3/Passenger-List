package com.agesadev.passengerlist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.agesadev.passengerlist.R
import com.agesadev.passengerlist.databinding.FragmentPassengerBinding
import com.agesadev.passengerlist.network.PassesngerAPI
import com.agesadev.passengerlist.paging.PassengersAdapater
import com.agesadev.passengerlist.viewmodel.PassengersViewModel
import com.agesadev.passengerlist.viewmodel.PassengersViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PassengerFragment : Fragment() {

    private lateinit var viewModel: PassengersViewModel
    private lateinit var binding: FragmentPassengerBinding

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Toast.makeText(context, "onCreate", Toast.LENGTH_SHORT).show()
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPassengerBinding.inflate(inflater, container, false).also {
        binding = it
    }.root


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = PassengersViewModelFactory(PassesngerAPI())
        viewModel = ViewModelProvider(this, factory).get(PassengersViewModel::class.java)

        val passengersAdapter = PassengersAdapater()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = passengersAdapter

        lifecycleScope.launch {
            viewModel.passengers.collectLatest { pagedData ->
                passengersAdapter.submitData(pagedData)
                Toast.makeText(requireContext(), "Total $pagedData", Toast.LENGTH_SHORT).show()

            }
        }


    }
}