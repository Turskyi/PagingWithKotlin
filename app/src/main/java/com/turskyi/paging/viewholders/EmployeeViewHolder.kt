package com.turskyi.paging.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.turskyi.paging.R
import com.turskyi.paging.models.Employee

class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val idTextView:TextView = itemView.findViewById(R.id.id)

    fun bind(employee: Employee?){
        idTextView.text = employee?.id.toString()
    }
}