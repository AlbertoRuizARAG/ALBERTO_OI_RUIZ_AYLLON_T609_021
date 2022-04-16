package com.prilux.cmr

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class MenusAdapter (private val menuList: ArrayList<MenusItem>, private val context: Context) : RecyclerView.Adapter<MenusAdapter.MenuViewHolder>() {

    private lateinit var mListener: onItemClickListener
    private var selected_cell: Int = -1

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class MenuViewHolder(itemView: View , listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.menu_imagen)
        val nombre: TextView = itemView.findViewById(R.id.menu_concepto)
        val descripcion: TextView = itemView.findViewById(R.id.menu_desdcripcion)


        init {

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)

        return MenuViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val currentItem = menuList[position]

        if (currentItem.imagenURL?.isEmpty() == true) {
            holder.imageView.setImageResource(R.drawable.ic_launcher_background)
        } else {

            holder.imageView.setImageResource(context.resources.getIdentifier(currentItem.imagenURL, "drawable", context.packageName))
            //imageview.setImageDrawable(getResources().getDrawable(R.drawable.logo));
       //     holder.imageView.setImageResource( Resources.getSystem().getIdentifier(currentItem.imagenURL, "drawable", "com.prilux.cmr" ))
           // Glide.with(holder.imageView).load(currentItem.imagenURL.toString()).into(holder.imageView);
        }
        holder.nombre.text = currentItem.nombre
        holder.descripcion.text = currentItem.descripcion

        if (position == selected_cell) {
            holder.itemView.setBackgroundColor( ContextCompat.getColor(context, com.prilux.biblioteca.R.color.colorselectedcell))
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE)
        }

    }


    fun setCellPosition(position: Int) {
        selected_cell = position
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    fun getItem(position: Int): MenusItem {
        //return if (mDataSet != null) mDataSet[position] else null
        return menuList.get(position)
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

