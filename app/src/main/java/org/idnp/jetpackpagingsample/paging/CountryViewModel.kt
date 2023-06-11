package org.idnp.jetpackpagingsample.paging

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import org.idnp.jetpackpagingsample.entities.Country
import org.idnp.jetpackpagingsample.entities.User
import org.idnp.jetpackpagingsample.model.AppDatabase
import org.idnp.jetpackpagingsample.model.CountryRepositoryRoom

class CountryViewModel(val context:Context) : ViewModel() {
    private val countryRepository: CountryRepositoryRoom = CountryRepositoryRoom(AppDatabase.getInstance(context))

    fun items(): Flow<PagingData<Country>> {

        val pager = Pager(
            PagingConfig(
                pageSize = 5,
                enablePlaceholders = false,
                prefetchDistance = 3)
        ) {
            CountryPagingSource(countryRepository)
        }.flow.cachedIn(viewModelScope)
        return pager
    }
}