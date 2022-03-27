package com.agesadev.passengerlist.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.agesadev.passengerlist.models.Passenger
import com.agesadev.passengerlist.network.PassesngerAPI

class PassengersDataSource(private val api: PassesngerAPI) : PagingSource<Int, Passenger>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Passenger> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response = api.getPassengersData(nextPageNumber)
            LoadResult.Page(
                data = response.data,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.totalPages) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Passenger>): Int? {
        return state.anchorPosition
    }
}