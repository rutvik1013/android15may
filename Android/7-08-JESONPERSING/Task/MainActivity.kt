package com.example.jsonperceingtask


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity()
{
    lateinit var listView: ListView
    lateinit var list:MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.list)
        list = ArrayList()

        var stringrequest= StringRequest(Request.Method.POST,"https://simplifiedcoding.net/demos/marvel/",object :Response.Listener<String>{
            override fun onResponse(response: String?)
            {

                var jsonarray = JSONArray(response)
                for(i in 0 until  jsonarray.length())
                {
                    var jsonobject = jsonarray.getJSONObject(i)

                   var name=jsonobject.getString("name")
                    var realname=jsonobject.getString("realname")
                    var team=jsonobject.getString("team")
                    var firstapperence=jsonobject.getString("firstappearance")
                    var createdby=jsonobject.getString("createdby")
                    var publisher=jsonobject.getString("publisher")
                    var imageurl=jsonobject.getString("imageurl")
                    var bio=jsonobject.getString("bio")

                    var m=Model()

                    m.name=name
                    m.realname=realname
                    m.team=team
                    m.firstapperence=firstapperence
                    m.createdby=createdby
                    m.publisher=publisher
                    m.bio=bio
                    m.imageurl=imageurl

                    list.add(m)

                }
                var adapter = Myadapter(applicationContext,list)
                listView.adapter=adapter

            }
        },object :Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?)
            {
                Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()
            }
        })

        var queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(stringrequest)



    }
}