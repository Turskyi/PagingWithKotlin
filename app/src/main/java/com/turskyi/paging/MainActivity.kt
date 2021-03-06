package com.turskyi.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.turskyi.paging.adapters.EmployeeAdapter
import com.turskyi.paging.controllers.EmployeeStorage
import com.turskyi.paging.controllers.MainThreadExecutor
import com.turskyi.paging.controllers.MyPositionalDataSource
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors.newSingleThreadExecutor

/**
 * Kotlin version of lesson from
 * https://startandroid.ru/ru/uroki/vse-uroki-spiskom/27-course/architecture-components/543-urok-15-paging-library-chast-2.html
 * */

const val KEY_START = "key_start"

/** List of numbers in the recycler view, just for the demonstration of paging */
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var adapter: EmployeeAdapter

    private var initPage: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            initPage = savedInstanceState.getInt(KEY_START)
        }

        /* DataSource */
        val dataSource = MyPositionalDataSource(EmployeeStorage())

        /* PagedList */
        val config = PagedList.Config.Builder()
            /* If "true", then it should be created another viewType in Adapter "onCreateViewHolder"
             while uploading */
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPageSize(10)
            .build()

        val pagedList = PagedList.Builder(dataSource, config)
            .setNotifyExecutor(MainThreadExecutor())
            .setFetchExecutor(newSingleThreadExecutor())
            /* number of start page position */
            .setInitialKey(initPage)
            .build()

        /* Adapter */
        adapter = EmployeeAdapter()
        adapter.submitList(pagedList)

        /* RecyclerView */
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_START, initPage)
    }
}
