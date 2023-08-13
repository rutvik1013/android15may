package com.example.jasonpassing

import android.app.VoiceInteractor.Request
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var list: MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView=findViewById(R.id.list)

        list=ArrayList()

        var stringrequest=StringRequest(Request.Method.POST,"https://vyasprakruti.000webhostapp.com/InventorymanaementSystem/cricket.php",object:Response.Listener<String>{
            override fun onResponse(response: String?) {
                var jsonArray=JSONArray(response)
                for (i in 0 until jsonArray.length())
                {
                  var jsonObject=jsonArray.getJSONObject(i)
                    var p_id = jsonObject.getString("name")
                    var p_name = jsonObject.getString("surname")
                    var p_price = jsonObject.getString("highest_score")
                    var p_image = jsonObject.getString("image")

                    var m = Model()
                    m.name = p_id
                    m.surname=p_name
                    m.highscore=p_price
                    m.image = p_image

                    list.add(m)
                }
                var adapter=Myadapter(applicationContext,list)
                listView.adapter=adapter

            },object:Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Toast.makeText(applicationContext,"NO Internet",Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}