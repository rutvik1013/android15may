package com.example.empjson


import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.empjson.databinding.ActivityViewBinding
import org.json.JSONArray
import org.json.JSONException

class ViewActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityViewBinding
    lateinit var list: MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        list = ArrayList()

        registerForContextMenu(binding.list)

        var stringRequest:StringRequest = StringRequest(Request.Method.GET,"https://rutvikbabariya.000webhostapp.com/API2/view_assignment.php",
            {
                    response->
                try
                {

                    var jsonArray = JSONArray(response)

                    for(i in 0 until jsonArray.length())
                    {
                        var jsonObject = jsonArray.getJSONObject(i)

                        var id = jsonObject.getString("id")
                        var email = jsonObject.getString("email")
                        var password = jsonObject.getString("password")

                        var m = Model()
                        m.id=id.toInt()
                        m.email=email
                        m.password=password
                        list.add(m)


                    }

                    var adapter = Myadapter(applicationContext,list)
                    binding.list.adapter=adapter



                }
                catch(e: JSONException)
                {
                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }




            })
        {
            Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()
        }

        var queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(stringRequest)

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)
    {
        var m1 = menu!!.add(0,1,0,"Update")
        var m2 = menu!!.add(0,2,0,"Delete")
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean
    {
        var acm:AdapterContextMenuInfo = item.menuInfo as AdapterContextMenuInfo
        var id = acm.position
        var user = list[id]

        when(item.itemId)
        {
            1->
            {
                var i = Intent(applicationContext,UpdateActivity::class.java)
                i.putExtra("id",user.id)
                i.putExtra("email",user.email)
                i.putExtra("password",user.password)
                startActivity(i)
            }
            2->
            {
                var alretdialog = AlertDialog.Builder(this)
                alretdialog.setTitle("Are you sure you want to delete?")
                alretdialog.setPositiveButton("YES",{ dialogInterface: DialogInterface, i: Int ->

                    var stringrequest = object:StringRequest(Request.Method.POST,"https://rutvikbabariya.000webhostapp.com/API2/delete_assignment.php",
                        Response.Listener {

                            Toast.makeText(applicationContext,"Deleted data",Toast.LENGTH_LONG).show()
                            var i = Intent(applicationContext,ViewActivity::class.java)
                            startActivity(i)

                        }, Response.ErrorListener {

                            Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()


                        })
                    {
                        override fun getParams(): MutableMap<String, String>?
                        {

                            var map = HashMap<String,String>()
                            map.put("id",user.id.toString())
                            return map
                        }
                    }


                    var queue: RequestQueue = Volley.newRequestQueue(this)
                    queue.add(stringrequest)

                })
                alretdialog.setNegativeButton("NO",{ dialogInterface: DialogInterface, i: Int ->

                    dialogInterface.cancel()
                })
                alretdialog.show()
            }
        }
        return super.onContextItemSelected(item)
    }

}
