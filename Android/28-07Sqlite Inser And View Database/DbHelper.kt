package com.example.sqliteinsertview


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.Cursor


class DbHelper(context: Context):SQLiteOpenHelper(context,DB_NAME,null,DB_VERSION) {

    companion object{
        var DB_NAME="Rutvik.db"
        var TABLE_NAME="Details"
        var ID="id"
        var NAME="name"
        var NUMBER="number"
        var DB_VERSION=1
    }

    override fun onCreate(db: SQLiteDatabase?) {

        var query ="CREATE TABLE " + TABLE_NAME + "("+ ID + " INTEGER PRIMARY KEY," + NAME + " TEXT,"+ NUMBER + " TEXT" + ")"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var upquery="DROP TABLE IF EXISTS"+ TABLE_NAME
        db!!.execSQL(upquery)
        onCreate(db)

    }
    fun insertData(m:Model) :Long
    {
        var db=writableDatabase
        var contentValues=ContentValues()
        contentValues.put(NAME,m.name)
        contentValues.put(NUMBER,m.number)
        var query=db.insert(TABLE_NAME, ID,contentValues)
        return query
    }
    fun viewData():ArrayList<Model>
    {
        var list=ArrayList<Model>()
        var db=readableDatabase
        var data= arrayOf(ID, NAME, NUMBER)
        var cursor=db.query(TABLE_NAME,data,null,null,null,null,null,null)

        while (cursor.moveToNext())
        {
            var id=cursor.getInt(0)
            var name=cursor.getString(1)
            var num=cursor.getString(2)

            var m=Model()
            m.id=id
            m.name=name
            m.number=num

            list.add(m)


        }
        return list
    }

}