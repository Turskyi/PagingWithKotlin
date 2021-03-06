package com.turskyi.paging.controllers

import com.turskyi.paging.models.Employee
import com.turskyi.paging.models.EmployeeData
import java.util.*
import java.util.logging.Handler

class EmployeeStorage {

    fun getInitialData(from: Int, to: Int): EmployeeData {
        val list = mutableListOf<Employee>()
        /**
         *  here should be use case when start or end position ("from", "to")
         *  is bigger then we have in list from list received from API,
         *  but for the sake of example it is just a template of required logic
         * */
        if(from < 100 && to < 70){
            for (i in from until from + to) {
                if (i == 100) break
                list.add(Employee(i))
            }
        } else {
            for (i in from until from + to) {
                if (i == 100) break
                list.add(Employee(i))
            }
        }
        /* 100 is the total number of list. Used if we know the total number of list items*/
        return EmployeeData(list, from, 100)
    }

    fun getData(from: Int, to: Int): List<Employee> {
        /* emulating waiting for query from server */
        Thread.sleep(1_000)
        val list = mutableListOf<Employee>()
        for (i in from until from + to) {
            if (i == 100) break
            list.add(Employee(i))
        }
        return list
    }
}