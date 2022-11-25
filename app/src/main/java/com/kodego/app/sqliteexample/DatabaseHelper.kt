package com.kodego.app.sqliteexample

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, "company.db", null, 1) {

    //this is called when a new database is created
    override fun onCreate(db: SQLiteDatabase?) {

        //to create a table (check the SQL code with Programiz)
        var createTable: String = "CREATE TABLE EMPLOYEE_TABLE (id integer primary key autoincrement, name varchar(30), salary int)"
        db?.execSQL(createTable)
    }

    //whenever there are changes to the database
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //no function
    }

    //create a function to add name and salary
    fun addOne(employeeModel: EmployeeModel){
        //declare the database
        var db = this.writableDatabase

        //declare content values to put values what we want to place in the database
        var cv = ContentValues()

        cv.put("name", employeeModel.name)
        cv.put("salary", employeeModel.salary)

        //insert the values to the database
        db.insert("EMPLOYEE_TABLE", null, cv)
    }

    //create a function to view enries
    fun getAllData(): MutableList<EmployeeModel>{
        var returnList = ArrayList<EmployeeModel>()
        var queryString = "SELECT * FROM EMPLOYEE_TABLE"
        var db = this.readableDatabase

        //create a cursor object as storage
        var cursor: Cursor = db.rawQuery(queryString, null)

        if(cursor.moveToFirst()){
            do{
                var id = cursor.getInt(0)
                var name = cursor.getString(1)
                var salary = cursor.getInt(2)

                var newEmployeeModel: EmployeeModel = EmployeeModel(id, name, salary)
                returnList.add(newEmployeeModel)

            }while(cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return returnList
    }

    //to delete
    fun deleteOne(employeeModel: EmployeeModel){
        var db = this.writableDatabase
        var queryString = "DELETE FROM EMPLOYEE_TABLE WHERE id = ${employeeModel.id}"

        //declare cursor
        var cursor = db.rawQuery(queryString, null)
        cursor.moveToFirst()  //>>this is to rearrange remaining list after deletion of one row



    }

}