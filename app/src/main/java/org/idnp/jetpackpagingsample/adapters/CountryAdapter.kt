package org.idnp.jetpackpagingsample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import org.idnp.jetpackpagingsample.R
import org.idnp.jetpackpagingsample.entities.Country
import org.idnp.jetpackpagingsample.entities.User

class CountryAdapter : PagingDataAdapter<Country, CountryViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_user, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
//        getItem(position)?.let { holder.bind(it) }
//        val item = getItem(position)
//        holder.bind(item)

        val item = getItem(position)
        item?.let { country ->
            holder.bind(country)
        }

    }

}

class DiffUtilCallBack : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }
}