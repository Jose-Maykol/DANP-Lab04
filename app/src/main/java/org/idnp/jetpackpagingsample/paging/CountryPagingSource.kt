package org.idnp.jetpackpagingsample.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.idnp.jetpackpagingsample.entities.Country
import org.idnp.jetpackpagingsample.entities.User
import org.idnp.jetpackpagingsample.model.CountryRepositoryRoom
import java.io.IOException

class CountryPagingSource(
    private val countryRepository: CountryRepositoryRoom
) : PagingSource<Int, Country>() {

    override val keyReuseSupported: Boolean = true

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Country> {
        return try {
            val nextPageNumber = params.key ?: 1
            val country = countryRepository.getCountries(nextPageNumber, nextPageNumber + 20)

            return LoadResult.Page(
                data = country ?: listOf(),
                prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else 1,
                nextKey = nextPageNumber + 1
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Country>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}