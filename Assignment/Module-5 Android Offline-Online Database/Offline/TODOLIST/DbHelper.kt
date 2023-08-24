package com.example.todoapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//dbhelper class for cantrole

class dbHelper(context: Context):SQLiteOpenHelper(context,DB_NAME,null,DB_VERSION)
{
    companion object{
        //database name
       var DB_NAME="user.db"
        //tablename
        var TABLE_NAME="info"
        var ID="id"
        var TASK="task"
        var DES="description"
        var DB_VERSION=1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Query of table
        var query ="CREATE TABLE " + TABLE_NAME + "("+ ID + " INTEGER PRIMARY KEY," + TASK + " TEXT,"+ DES + " TEXT" + ")"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var upquery= "DROP TABLE IF EXISTS"+ TABLE_NAME
        db!!.execSQL(upquery)
        onCreate(db)

    }
    //insertdata
    fun insertdata(m:Model):Long
    {
        var db=writableDatabase
        var contentValues=ContentValues()
        contentValues.put(TASK,m.task)
        contentValues.put(DES,m.des)

        var query=db.insert(TABLE_NAME, ID,contentValues)

        return query
    }
    //viewdata
    fun viewdata():ArrayList<Model>
    {
        var list=ArrayList<Model>()
        var db=readableDatabase
        var data= arrayOf(ID, TASK, DES)
        var cursor=db.query(TABLE_NAME,data,null,null,null,null,null,null)

        while (cursor.moveToNext())
        {
            var id=cursor.getInt(0)
            var task=cursor.getString(1)
           var des=cursor.getString(2)
            var m=Model()

            m.id=id
            m.task=task
           m.des=des

            list.add(m)

        }
        return list
    }
    //deletedata
    fun deletedata(id:Int)
    {
        var db=writableDatabase
        var deletequery= ID+"="+id
        db.delete(TABLE_NAME,deletequery,null)
    }
    //updatadata
    fun updatedata(m:Model)
    {
        var db=writableDatabase
        var contentValues=ContentValues()
        contentValues.put(ID,m.id)
        contentValues.put(TASK,m.task)
        contentValues.put(DES,m.des)
        var updatequery= ID+"="+m.id
        db.update(TABLE_NAME,contentValues,updatequery,null)

    }

}