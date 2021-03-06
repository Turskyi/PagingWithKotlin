package com.turskyi.paging.controllers

import android.util.Log
import androidx.paging.PositionalDataSource
import com.turskyi.paging.models.Employee
import com.turskyi.paging.models.EmployeeData

internal class MyPositionalDataSource(private val employeeStorage: EmployeeStorage) :
    PositionalDataSource<Employee>() {

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<Employee>
    ) {
        /** in case we have a static list */
//        Log.d("===>", "init start = ${params.requestedStartPosition}," +
//                " load size =  ${params.requestedLoadSize}")
//        val resultList =
//            employeeStorage.getData(params.requestedStartPosition, params.requestedLoadSize)
///*           the start number is   "params.requestedStartPosition" not "0" since we set it explicitly  via
//           .setInitialKey(initPage) in Main Activity */
//        callback.onResult(resultList, params.requestedStartPosition)

        /** in case we have a dynamic list from server */
        Log.d(
            "===>",
            "loadInitial, requestedStartPosition = " + params.requestedStartPosition.toString() +
                    ", requestedLoadSize = " + params.requestedLoadSize
        )
        val result: EmployeeData =
            employeeStorage.getInitialData(params.requestedStartPosition, params.requestedLoadSize)
        if (params.placeholdersEnabled) {
            callback.onResult(result.data, result.position, result.count)
        } else {
            callback.onResult(result.data, result.position)
        }
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