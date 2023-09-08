package com.example.taskmanassign

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteStatement
import android.view.Display.Mode
import android.widget.Toast

class DbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION)
{
    companion object
    {
        var DB_NAME="user.db"
        var TABLE_NAME="info"
        var ID="id"
        var NAME="name"
        var DESC="desc"
        var STATUS = "status"
        var PROIRITY = "priority"
        var DATE = "date"
        var TIME = "time"
        var DB_VERSION=1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var query="CREATE TABLE " + TABLE_NAME + "("+ ID + " INTEGER PRIMARY KEY," + NAME + " TEXT,"+ DESC + " TEXT,"  + STATUS + " TEXT,"+ PROIRITY + " TEXT,"+ DATE + " DATE,"+ TIME + " TIME" + ")"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var upquery="DROP TABLE IF EXISTS "+ TABLE_NAME
        db!!.execSQL(upquery)
        onCreate(db)
    }

    fun insertdata(m:Model) : Long
    {
        var db:SQLiteDatabase = this.writableDatabase
        var values = ContentValues()
        values.put(NAME,m.name)
        values.put(DESC,m.description)
        values.put(STATUS,m.status)
        values.put(PROIRITY,m.priority)
        values.put(DATE,m.date)
        values.put(TIME,m.time)
        var id = db.insert(TABLE_NAME,ID,values)
        return id
    }

    fun viewdata():MutableList<Model>
    {
        var list :MutableList<Model> = ArrayList()
        var db:SQLiteDatabase = this.readableDatabase
        var cursor:Cursor=db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY $DATE ASC", null)
        while (cursor.moveToNext())
        {
            var id =cursor.getInt(0)
            var name=cursor.getString(1)
            var description = cursor.getString(2)
            var status = cursor.getString(3)
            var priority = cursor.getString(4)
            var date = cursor.getString(5)
            var time = cursor.getString(6)

            var m = Model()
            m.id=id
            m.name= name
            m.description= description
            m.status = status
            m.priority = priority
            m.date = date
            m.time = time
            list.add(m)
        }
        return list
    }

    fun getid(position : Int) : Int
    {
        var db:SQLiteDatabase = this.readableDatabase
        var cursor:Cursor=db.rawQuery("SELECT * FROM $TABLE_NAME",null)
        cursor.move(position)
        var id = cursor.getInt(0)
        return id
    }

    fun deleteall(id:Int)
    {
        var db:SQLiteDatabase = this.writableDatabase
        db.delete(TABLE_NAME,"id = "+ id,null)
    }

    fun deletedata(id: Int)
    {
        var db:SQLiteDatabase = this.writableDatabase
        db.delete(TABLE_NAME,"id = "+id,null)
    }

    fun updatename(m:Model,id: Int)
    {
        var db:SQLiteDatabase = this.writableDatabase
        var values = ContentValues()
        values.put(NAME,m.name)
        db.update(TABLE_NAME, values,"id = "+id, null)
    }
    fun updatedesc(m:Model,id: Int)
    {
        var db:SQLiteDatabase = this.writableDatabase
        var values = ContentValues()
        values.put(DESC,m.description)
        db.update(TABLE_NAME, values,"id = "+id, null)
    }
    fun updatestatus(m:Model,id: Int)
    {
        var db:SQLiteDatabase = this.writableDatabase
        var values = ContentValues()
        values.put(STATUS,m.status)
        db.update(TABLE_NAME, values,"id = "+id, null)
    }
}