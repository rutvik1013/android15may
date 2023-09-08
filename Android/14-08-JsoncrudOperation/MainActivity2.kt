package com.example.jsonpersingcrud

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.jsonpersingcrud.databinding.ActivityMain2Binding
import org.json.JSONArray


class MainActivity2 : AppCompatActivity()
{
    private lateinit var binding: ActivityMain2Binding

    lateinit var list: MutableList<Model>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        list = ArrayList()

        var stringrequest = StringRequest(Request.Method.POST,"https://rutvikbabariya.000webhostapp.com/API/view.php",object :
            Response.Listener<String>{
            override fun onResponse(response: String?)
            {

                var jsonarray = JSONArray(response)
                for(i in 0 until  jsonarray.length())
                {
                    var jsonobject = jsonarray.getJSONObject(i)

                    var p_id = jsonobject.getInt("p_id")
                    var p_name = jsonobject.getString("p_name")
                    var p_price = jsonobject.getString("p_price")
                    var p_des = jsonobject.getString("p_des")


                    var m = Model()
                    m.id=p_id
                    m.pname = p_name
                    m.pprice=p_price
                    m.pdes=p_des

                    list.add(m)

                }
                var adapter = Myadapter(applicationContext,list)
                binding.list.adapter=adapter

            }
        },object : Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?)
            {
                Toast.makeText(applicationContext,"Error", Toast.LENGTH_LONG).show()
            }
        })
        var queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(stringrequest)



    }
}