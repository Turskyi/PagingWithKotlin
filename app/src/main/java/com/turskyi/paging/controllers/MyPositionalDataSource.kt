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
        Log.d("===>", "init start = ${params.requestedStartPosition}," +
                " load size =  ${params.requestedLoadSize}")
        val resultList =
            employeeStorage.getData(params.requestedStartPosition, params.requestedLoadSize)
//           the start number is   "params.requestedStartPosition" since we set it explicitly  via
//           .setInitialKey(initPage) in Main Activiy
        callback.onResult(resultList, params.requestedStartPosition)
    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<Employee>
    ) {
        Log.d("===>", "start = ${params.startPosition}, load size =  ${params.loadSize}")
        val resultList = employeeStorage.getData(params.startPosition, params.loadSize)
        callback.onResult(resultList)
    }
}