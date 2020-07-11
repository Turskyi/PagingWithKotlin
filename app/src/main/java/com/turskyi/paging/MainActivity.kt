package com.turskyi.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.turskyi.paging.adapters.EmployeeAdapter
import com.turskyi.paging.controllers.EmployeeStorage
import com.turskyi.paging.controllers.MainThreadExecutor
import com.turskyi.paging.controllers.MyPositionalDataSource
import com.turskyi.paging.models.Employee
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors.newSingleThreadExecutor

/** List of numbers in the recycler view, just for the demonstration of paging */
class MainActivity : AppCompatActivity() {

    private lateinit var adapter: EmployeeAdapter

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Employee> =
            object : DiffUtil.ItemCallback<Employee>() {

                override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
                    return oldItem.run { equals(newItem) }
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* DataSource */
        val dataSource = MyPositionalDataSource(EmployeeStorage())

        /* PagedList */
        val config = PagedList.Config.Builder()
            /* If "true", then it should be created another viewType in Adapter "onCreateViewHolder"
             while uploading */
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        val pagedList = PagedList.Builder(dataSource, config)
            .setFetchExecutor(newSingleThreadExecutor())
            .setNotifyExecutor(MainThreadExecutor())
            .build()

        /* Adapter */
        adapter = EmployeeAdapter(DIFF_CALLBACK)
        adapter.submitList(pagedList)

        /* RecyclerView */
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
