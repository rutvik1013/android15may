package com.example.empassign

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
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class Myadapter(var context: Context,var list: MutableList<Model>):RecyclerView.Adapter<MyView>()
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
        holder.txt1.setText(list.get(position).efirstname)
        holder.txt2.setText(list.get(position).elastname)
        holder.txt3.setText(list.get(position).e_experience)
        holder.txt4.setText(list.get(position).email)
        holder.txt5.setText(list.get(position).password)
        holder.txt6.setText(list.get(position).mobile)
        Picasso.get().load(list.get(position).image).into(holder.img)

        var retrofit=Retrofit.Builder().baseUrl("https://rutvikbabariya.000webhostapp.com/API2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiinterface::class.java)

        holder.itemView.setOnClickListener {
            var alert=AlertDialog.Builder(context)
            alert.setTitle("Select Option?")
            alert.setPositiveButton("Update",{dialogInterface:DialogInterface,i:Int->

                var i=Intent(context,UpdateActivity::class.java)
                i.putExtra("id",list.get(position).id)
                i.putExtra("efirstname",list.get(position).efirstname)
                i.putExtra("elastname",list.get(position).elastname)
                i.putExtra("e_experience",list.get(position).e_experience)
                i.putExtra("email",list.get(position).email)
                i.putExtra("password",list.get(position).password)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            })
            alert.setNegativeButton("Delete",{dialogInterface:DialogInterface,i:Int->
                Toast.makeText(context, ""+list.get(position).id, Toast.LENGTH_SHORT).show()
                var call:Call<Void>?=retrofit.deletedata(list.get(position).id)

                call!!.enqueue(object :Callback<Void?>{
                    override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                        var i=Intent(context,MainActivity2::class.java)
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(i)
                    }

                    override fun onFailure(call: Call<Void?>, t: Throwable) {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }

                })
            })
            alert.show()
        }
    }

}
class MyView(itemview:View):RecyclerView.ViewHolder(itemview)
{
    var txt1:TextView=itemview.findViewById(R.id.txtfname)
    var txt2:TextView=itemview.findViewById(R.id.txtlname)
    var txt3:TextView=itemview.findViewById(R.id.txtexp)
    var txt4:TextView=itemview.findViewById(R.id.txtemail)
    var txt5:TextView=itemview.findViewById(R.id.txtpass)
    var txt6:TextView=itemview.findViewById(R.id.txtmobile)
    var img:ImageView=itemview.findViewById(R.id.imageupload)
}