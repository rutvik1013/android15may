package com.example.myloginreg

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso

class MyProjectAdapter(var context: Context,var list: MutableList<EmployeeModel>):RecyclerView.Adapter<MyprojectView>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyprojectView {
        var inflater=LayoutInflater.from(context)
        var view=inflater.inflate(R.layout.design,parent,false)
        return MyprojectView(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyprojectView, position: Int) {
        Picasso.get().load(list.get(position).image)
        var id=list.get(position).id
        var textfname=list.get(position).efirstname
        var textlname=list.get(position).elastname
        var e_experience=list.get(position).e_experience
        var email=list.get(position).email
        var password=list.get(position).password
        var mobile=list.get(position).mobile

        holder.itemView.setOnClickListener {
            var alert=AlertDialog.Builder(context)
            alert.setTitle("Select Option what you want?")
            alert.setPositiveButton("Update",{dialogInterface:DialogInterface,i:Int->
                dialogInterface.dismiss()

                var i=Intent(context,UpdateActivity::class.java)
                i.putExtra("id",id)
                i.putExtra("efirstname",textfname)
                i.putExtra("elastname",textlname)
                i.putExtra("e_experience",e_experience)
                i.putExtra("email",email)
                i.putExtra("password",password)
                i.putExtra("mobile",mobile)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            })
            alert.setNegativeButton("Delete",{dialogInterface:DialogInterface,i:Int->
                dialogInterface.dismiss()
                var stringrequest:StringRequest=object :StringRequest(Request.Method.POST,"https://rutvikbabariya.000webhostapp.com/API2/delete_emp_details.php",Response.Listener {
                    Toast.makeText(context, "Details Deleted", Toast.LENGTH_SHORT).show()
                    context.startActivity(Intent(context,ViewActivity::class.java))

                },Response.ErrorListener {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                })
                {
                    override fun getParams(): MutableMap<String, String>? {
                        var map=HashMap<String,String>()
                        map["id"]=id
                        return map
                    }
                }
                var queue:RequestQueue=Volley.newRequestQueue(context)
                queue.add(stringrequest)
            })
            alert.show()
        }


    }

}


class MyprojectView(itemview:View):RecyclerView.ViewHolder(itemview)
{

    var image:ImageView=itemview.findViewById(R.id.imgemp)
    var txtfname:TextView=itemview.findViewById(R.id.txtname)
    var txtlname:TextView=itemview.findViewById(R.id.txtl_name)
    var txtexp:TextView=itemview.findViewById(R.id.txtexp)
    var txtemail:TextView=itemview.findViewById(R.id.txtemail)
    var txtpassword:TextView=itemview.findViewById(R.id.txtpass)
    var txtmobile:TextView=itemview.findViewById(R.id.txtphone)
}