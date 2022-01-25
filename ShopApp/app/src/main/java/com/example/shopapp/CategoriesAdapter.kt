package com.example.shopapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoriesAdapter (private val categoriesList: List<CategoriesItem>) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class CategoriesViewHolder(itemView: View , listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView1: TextView = itemView.findViewById(R.id.textView1)

        init {

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)

        return CategoriesViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val currentItem = categoriesList[position]

        holder.imageView.setImageResource(currentItem.imagenResource)
        holder.textView1.text = currentItem.textDefinition


    }


    override fun getItemCount(): Int {
        return categoriesList.size
    }

    fun getItem(position: Int): CategoriesItem? {
        //return if (mDataSet != null) mDataSet[position] else null
        return categoriesList.get(position)
    }


    fun filter(filter: String) {
        categoriesList.forEach {
            if (it.textDefinition == filter ) {

            }
            //cargar todos los elementos o hacer un intent a otra pantalla
            //notifyDataSetChanged()
        }

    }


}

