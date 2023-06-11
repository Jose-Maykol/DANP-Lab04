package org.idnp.jetpackpagingsample.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.idnp.jetpackpagingsample.entities.Country

class CountryRepositoryRoom (private val appDatabase: AppDatabase) {

    suspend fun getCountries(start: Int, rows: Int ): List<Country> {
        val end = start + rows
        return withContext(Dispatchers.IO) {
            appDatabase.countryDao().getCountries(start, end)
        }
    }
}