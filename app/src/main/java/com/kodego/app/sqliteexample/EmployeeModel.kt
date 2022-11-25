package com.kodego.app.sqliteexample

//create a data class to mirror the table in the DatabaseHelper
data class EmployeeModel (
    var id: Int,
    var name: String,
    var salary: Int
    )