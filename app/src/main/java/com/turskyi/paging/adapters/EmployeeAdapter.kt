package com.turskyi.paging.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.turskyi.paging.R
import com.turskyi.paging.models.Employee
import com.turskyi.paging.viewholders.EmployeeViewHolder

class EmployeeAdapter : PagedListAdapter<Employee, EmployeeViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Employee> =
            object : DiffUtil.ItemCallback<Employee>() {

                override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
                    return oldItem.id.run { equals(newItem.id) }
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.employee, parent, false
        )
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        Log.d("===>", "bind, position = $position")
        holder.bind(getItem(position))
    }
}