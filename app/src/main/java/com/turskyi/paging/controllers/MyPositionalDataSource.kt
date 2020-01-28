package com.turskyi.paging.controllers

import android.util.Log
import androidx.paging.PositionalDataSource
import com.turskyi.paging.models.Employee

internal class MyPositionalDataSource(private val employeeStorage: EmployeeStorage) :
    PositionalDataSource<Employee>() {

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<Employee>
    ) {
        Log.d("MyPositionalDataSource", "start = ${params.requestedStartPosition}," +
                " load size =  ${params.requestedLoadSize}")
        val list =
            employeeStorage.getData(params.requestedStartPosition, params.requestedLoadSize)
        callback.onResult(list, 0)
    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<Employee>
    ) {
        Log.d("MyPositionalDataSource", "start = ${params.startPosition}, load size =  ${params.loadSize}")
        val result = employeeStorage.getData(params.startPosition, params.loadSize)
        callback.onResult(result)
    }
}