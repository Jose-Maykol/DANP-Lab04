package org.idnp.jetpackpagingsample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.idnp.jetpackpagingsample.adapters.CountryAdapter
import org.idnp.jetpackpagingsample.paging.CountryViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val readFile = FillDatabase()
        val jsonCoutine = readFile.fillDatabase(this  )

        Log.i("MainActivity", "onCreate: " + jsonCoutine.toString())

        val viewModel = CountryViewModel(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val pagingAdapter = CountryAdapter()

        recyclerView.adapter = pagingAdapter
        recyclerView.apply {
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }

        lifecycleScope.launch {
            viewModel.items().collectLatest { pageData ->
                pagingAdapter.submitData(pageData)
            }
        }
    }

}