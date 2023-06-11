package org.idnp.jetpackpagingsample.model

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.idnp.jetpackpagingsample.entities.Country
import org.idnp.jetpackpagingsample.entities.User

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(countries: Country)

    @Query("SELECT * FROM countries WHERE id >= :start AND id < :end")
    fun getCountries(start: Int, end: Int ): List<Country>

}