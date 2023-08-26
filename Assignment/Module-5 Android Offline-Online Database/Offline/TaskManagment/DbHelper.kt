package com.example.taskmanagment

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context):SQLiteOpenHelper(context,db_name,null,db_version) {
    companion object {
        var db_name = "user.db"
        var table_name = "info"
        var ID = "id"
        var NAME = "name"
        var DESCRIPTION = "description"
        var STATUS = "status"
        var PRIORITY = "priority"
        var DATE = "date"
        var TIME = "time"
        var db_version = 1

    }

    override fun onCreate(db: SQLiteDatabase?) {
        var query="CREATE TABLE " + table_name + "("+ ID + " INTEGER PRIMARY KEY," + NAME + " TEXT,"+ DESCRIPTION + " TEXT,"  + STATUS + " TEXT,"+ PRIORITY + " TEXT,"+ DATE + " DATE,"+ TIME + " TIME" + ")"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var upquery="DROP TABLE IF EXISTS"+ table_name
        db!!.execSQL(upquery)
        onCreate(db)
    }
    fun insertdata(m:Model):Long
    {
        var db:SQLiteDatabase=this.writableDatabase
        var values=ContentValues()
        values.put(NAME,m.name)
        values.put(DESCRIPTION,m.description)
        values.put(PRIORITY,m.priority)
        values.put(STATUS,m.status)
        values.put(DATE,m.date)
        values.put(TIME,m.time)
        var id=db.insert(table_name, ID,values)

        return id
    }
    fun viewdata():MutableList<Model>
    {
       var db=readableDatabase
        var list=ArrayList<Model>()
        var data= arrayOf(ID, NAME, DESCRIPTION, DATE, TIME, STATUS, PRIORITY)
        var cursor=db.query(table_name,data,null,null,null,null,null,null)
        while (cursor.moveToNext())
        {
            var id=cursor.getInt(0)
            var name=cursor.getString(1)
            var description=cursor.getString(2)
            var priority=cursor.getString(3)
            var date=cursor.getString(4)
            var time=cursor.getString(5)
            var status=cursor.getString(6)

            var user=Model()

            user.id=id
            user.name=name
            user.description=description
            user.priority=priority
            user.date=date
            user.time=time
            user.status=status

            list.add(user)
        }
        return list
    }
    fun deletedata(id:Int)
    {
        var db=writableDatabase
        var deletequery= ID+" ="+id
        db.delete(table_name,deletequery,null)
    }
    fun updatedata(m:Model)
    {
        var db=writableDatabase
        var values=ContentValues()

        values.put(ID,m.id)
        values.put(NAME,m.name)
        values.put(DESCRIPTION,m.description)
        values.put(PRIORITY,m.priority)
        values.put(DATE,m.date)
        values.put(TIME,m.time)
        values.put(STATUS,m.status)

        var updatequery= ID+" ="+m.id
        db.update(table_name,values,updatequery,null)
    }

}