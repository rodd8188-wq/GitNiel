package com.example.otroproyectorecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter (private val dataset : Array<String>) : RecyclerView.Adapter<Adapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val textView: TextView

        init {
            textView = view.findViewById<TextView>(R.id.tvData)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder  {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_view,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        holder.textView.text = dataset[position]

    }

    //Igual que el de abajo pero mas compacto
    override fun getItemCount() = dataset.size
    /*
    override fun getItemCount(): Int {
        return dataset.size
    }
    */
}