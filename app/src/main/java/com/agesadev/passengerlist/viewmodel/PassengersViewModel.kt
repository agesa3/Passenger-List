package com.agesadev.passengerlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.agesadev.passengerlist.network.PassesngerAPI
import com.agesadev.passengerlist.paging.PassengersDataSource

class PassengersViewModel(
    private val api: PassesngerAPI
) : ViewModel() {
    val passengers = Pager(PagingConfig(pageSize = 10)) {
        PassengersDataSource(api)
    }.flow.cachedIn(viewModelScope)
}