package com.agesadev.passengerlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agesadev.passengerlist.network.PassesngerAPI

class PassengersViewModelFactory(
    private val api: PassesngerAPI
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PassengersViewModel(api) as T
    }
}