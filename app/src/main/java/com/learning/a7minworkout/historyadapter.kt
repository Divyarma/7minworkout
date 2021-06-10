package com.learning.a7minworkout

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_history.view.*

class historyadapter(val context: Context,val items :ArrayList<String>): RecyclerView.Adapter<historyadapter.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val tvItem=view.tvItem
        val tvPos=view.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date:String=items.get(position)
        holder.tvPos.text= (position+1).toString()
        holder.tvItem.text=date
    }

    override fun getItemCount(): Int {
        return items.size
    }
}