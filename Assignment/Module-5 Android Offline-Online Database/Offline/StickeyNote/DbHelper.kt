package com.example.stickeynote

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context):SQLiteOpenHelper(context,db_name,null,db_version)
{
    companion object
    {
        var db_name="user.db"
        var table_name="list"
        var ID="id"
        var TITLE="title"
        var DESCRIPTION="description"
        var db_version=1

    }

    override fun onCreate(db: SQLiteDatabase?) {
        var query ="CREATE TABLE " + table_name + "("+ ID + " INTEGER PRIMARY KEY," + TITLE + " TEXT,"+ DESCRIPTION + " TEXT" + ")"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var upquery="DROP TABLE IF EXISTS "+ table_name
        db!!.execSQL(upquery)
        onCreate(db)
    }
    fun insertdata(m:Model):Long
    {
        var db=writableDatabase
        var contentValues=ContentValues()
        contentValues.put(TITLE,m.title)
        contentValues.put(DESCRIPTION,m.description)
        var query=db.insert(table_name, ID,contentValues)
        return query
    }
    fun viewdata():ArrayList<Model>
    {
        var list=ArrayList<Model>()
        var db=readableDatabase
        var data= arrayOf(ID, TITLE, DESCRIPTION)
        var cursor=db.query(table_name,data,null,null,null,null,null,null)

        while (cursor.moveToNext())
        {
            var id=cursor.getInt(0)
            var title=cursor.getString(1)
            var des=cursor.getString(2)

            var m=Model()
            m.id=id
            m.title=title
            m.description=des
            list.add(m)

        }
        return list
    }
    fun deletedata(id:Int)
    {
        var db=writableDatabase
        var deletequery= ID+"="+id
        db.delete(table_name,deletequery,null)
    }
    fun updatedata(m:Model)
    {
        var db=writableDatabase
        var values=ContentValues()
        values.put(ID,m.id)
        values.put(TITLE,m.title)
        values.put(DESCRIPTION,m.description)
        var updatequery= ID+"="+m.id
        db.update(table_name,values,updatequery,null)
    }
}