package com.turskyi.paging.controllers

import com.turskyi.paging.models.Employee

class EmployeeStorage {

    fun getData(from: Int, to: Int): List<Employee> {
        val list = mutableListOf<Employee>()
        for (i in from until from + to) {
            if (i == 100) break
            list.add(Employee(i))
        }
        return list
    }
}