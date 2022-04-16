package com.prilux.cmr.corainfo

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.core.view.children
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.prilux.cmr.R


class CoraInfoAdapter (private val lista: ArrayList<CoraInfoItem>, private val context: Context) : RecyclerView.Adapter<CoraInfoAdapter.CoraInfoViewHolder>() {

    private lateinit var mListener: onItemClickListener

    private var selected_cell: Int = -1

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class CoraInfoViewHolder(itemView: View , listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.cora_nombre)
        val coraID: TextView = itemView.findViewById(R.id.cora_mac)
        val address: TextView = itemView.findViewById(R.id.cora_address)
        val cellSelected: Boolean = itemView.isSelected

        init {

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoraInfoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cora_info_item, parent, false)

        return CoraInfoViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: CoraInfoViewHolder, position: Int) {
        val currentItem = lista[position]

        holder.nombre.text = currentItem.nombre
        holder.coraID.text = currentItem.coraID
        holder.address.text = currentItem.addressMap


      /*  holder.itemView.setOnClickListener {
           selected_cell = holder.adapterPosition
            notifyDataSetChanged()
        }*/

        if (position == selected_cell) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, com.prilux.biblioteca.R.color.colorselectedcell))
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE)
        }
    }

    fun setCellPosition(position: Int) {
        selected_cell = position
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun getItem(position: Int): CoraInfoItem {
        //return if (mDataSet != null) mDataSet[position] else null
        return lista.get(position)
    }

    fun setCellBackgroundColor(position: Int, holder: RecyclerView, color: Int){
        holder[position].setBackgroundColor(color)
        notifyDataSetChanged()
    }


    fun cleanBackground(holder: RecyclerView) {
        // Limpiar la selección de la cell
        holder.children.iterator().forEach { item ->
                item.isSelected = false
                 item.setBackgroundColor(Color.WHITE)
         }
    }


/* fun filter(filter: String) {
     productsList.forEach {
         if (it.categoriaId == filter ) {

         }
         //cargar todos los elementos o hacer un intent a otra pantalla
         //notifyDataSetChanged()
     }

 }*/


}

