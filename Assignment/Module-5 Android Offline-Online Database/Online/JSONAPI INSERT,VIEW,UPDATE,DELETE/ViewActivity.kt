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
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.empjson.databinding.ActivityViewBinding
import org.json.JSONArray

class ViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewBinding
    lateinit var list: MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityViewBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        registerForContextMenu(binding.list)
        list=ArrayList()

        //for view data

        var stringrequest=StringRequest(Request.Method.POST,"https://rutvikbabariya.000webhostapp.com/API2/view_assignment.php",object :Response.Listener<String>{
            override fun onResponse(response: String?) {

                var jsonarray=JSONArray(response)
                for ( i in 0 until jsonarray.length())
                {
                    var jsonObject=jsonarray.getJSONObject(i)

                    var id=jsonObject.getInt("id")
                    var empname=jsonObject.getString("empname")
                    var empsurname=jsonObject.getString("empsurname")
                    var email=jsonObject.getString("email")
                    var pass=jsonObject.getString("password")

                    var model=Model()

                    model.id=id
                    model.empname=empname
                    model.empsurname=empsurname
                    model.email=email
                    model.password=pass

                    list.add(model)
                }
                var adapter=Myadapter(applicationContext,list)
                binding.list.adapter=adapter
            }

        },object :Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }

        })
        var queue:RequestQueue=Volley.newRequestQueue(this)
        queue.add(stringrequest)
    }

    //context menu for update and delete employee data

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        var menu1:MenuItem=menu!!.add(0,1,0,"Update")
        var menu2:MenuItem=menu!!.add(0,2,0,"Delete")

        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var acm:AdapterContextMenuInfo=item.menuInfo as AdapterContextMenuInfo
        var id=acm.position
        var user=list[id]

        when(item.itemId)
        {
            1->
            {
                var i=Intent(applicationContext,UpdateActivity::class.java)
                i.putExtra("id",user.id)
                i.putExtra("empname",user.empname)
                i.putExtra("empsurname",user.empsurname)
                i.putExtra("email",user.email)
                i.putExtra("password",user.password)

                startActivity(i)
            }
            2->
            {
                //alert dialog

                var alert=AlertDialog.Builder(this)
                alert.setTitle("Are you sure you want to delete this data?")
                alert.setPositiveButton("Yes",{dialogInterface:DialogInterface,i:Int->

                    var stringrequest:StringRequest=object :StringRequest(Request.Method.POST,"https://rutvikbabariya.000webhostapp.com/API2/delete_assignment.php",Response.Listener {
                        Toast.makeText(applicationContext, "Deleted Employee Data", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext,ViewActivity::class.java))

                    },Response.ErrorListener {
                        Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()
                    })
                    {
                        override fun getParams(): MutableMap<String, String>? {
                            var map=HashMap<String,String>()
                            map["id"]=user.id.toString()

                            return map
                        }

                    }
                    var queue:RequestQueue=Volley.newRequestQueue(this)
                    queue.add(stringrequest)
                })
                alert.setNegativeButton("No",{dialogInterface:DialogInterface,i:Int->
                    dialogInterface.cancel()
                })
                alert.show()
            }
        }
        return super.onContextItemSelected(item)
    }

}
