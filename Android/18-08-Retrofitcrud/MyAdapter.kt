package com.example.retrofitcrud

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyAdapter( var context: Context, var list: MutableList<Model>):RecyclerView.Adapter<MyView>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        var inflater=LayoutInflater.from(context)
        var view=inflater.inflate(R.layout.design,parent,false)

        return MyView(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.txt1.setText(list.get(position).fullname)
        holder.txt2.setText(list.get(position).email)
        holder.txt3.setText(list.get(position).password)

        var apiinterface=Apiclint.getapiclient().create(Apiinterface::class.java)

        holder.itemView.setOnClickListener {
            var alert=AlertDialog.Builder(context)
            alert.setTitle("Select Option?")
            alert.setPositiveButton("Update Data?",{dialogInterface:DialogInterface,i:Int->

                var i=Intent(context,UpdateActivity::class.java)
                i.putExtra("id",list.get(position).id)
                i.putExtra("fullname",list.get(position).fullname)
                i.putExtra("email",list.get(position).email)
                i.putExtra("password",list.get(position).password)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            })
            alert.setNegativeButton("Error") { dialogInterface: DialogInterface, i: Int ->

                Toast.makeText(context,""+list.get(position).id,Toast.LENGTH_LONG).show()
                var call:Call<Void?>?=apiinterface.deletedata(list.get(position).id)

                call!!.enqueue(object :Callback<Void?>{
                    override fun onResponse(call: Call<Void?>, response: Response<Void?>) {

                        var i=Intent(context,View_Activity::class.java)
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(i)
                    }

                    override fun onFailure(call: Call<Void?>, t: Throwable) {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }

                })
                alert.show()
            }
        }
    }

}
class MyView(itemview:View):RecyclerView.ViewHolder(itemview)
{
    var txt1:TextView=itemview.findViewById(R.id.txtfullname)
    var txt2:TextView=itemview.findViewById(R.id.txtemail)
    var txt3:TextView=itemview.findViewById(R.id.txtpassword)

}