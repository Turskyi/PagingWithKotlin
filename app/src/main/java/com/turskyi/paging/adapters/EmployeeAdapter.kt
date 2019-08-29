package com.turskyi.paging.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.recyclerview.widget.DiffUtil
import androidx.paging.PagedListAdapter
import com.turskyi.paging.R
import com.turskyi.paging.models.Employee
import com.turskyi.paging.viewholders.EmployeeViewHolder


internal open class EmployeeAdapter(diffUtilCallback: DiffUtil.ItemCallback<Employee>) :
    PagedListAdapter<Employee, EmployeeViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.employee, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        Log.d(TAG, "bind, position = $position")
        holder.bind(getItem(position))
    }
}