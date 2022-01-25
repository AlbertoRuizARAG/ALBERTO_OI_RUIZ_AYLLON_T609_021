package com.example.shopapp



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductsAdapter (private val productsList: ArrayList<ProductsItem>) : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class ProductsViewHolder(itemView: View , listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.ivImage)
        val textView1: TextView = itemView.findViewById(R.id.tvTitle)
        val descripcion: TextView = itemView.findViewById(R.id.ivDescripcion)
        val stockDisponible: TextView = itemView.findViewById(R.id.ivCantidadDisponible)
        val precio: TextView = itemView.findViewById(R.id.ivPrecio)

        init {

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)

        return ProductsViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val currentItem = productsList[position]

        if (currentItem.imagenURL?.isEmpty() == true){
            holder.imageView.setImageResource(R.drawable.ic_baseline_food_bank_24)
        } else {

            Glide.with(holder.imageView).load(currentItem.imagenURL.toString()).into(holder.imageView);
            // Declaring executor to parse the URL
  /*          val executor = Executors.newSingleThreadExecutor()
            // Once the executor parses the URL
            // and receives the image, handler will load it
            // in the ImageView
            val handler = Handler(Looper.getMainLooper())
            // Image URL
            val imageURL = currentItem.imagenURL.toString()
            // Initializing the image
            var image: Bitmap? = null

            // Only for Background process (can take time depending on the Internet speed)
            executor.execute {

                // Tries to get the image and post it in the ImageView
                // with the help of Handler
                try {
                    val `in` = java.net.URL(imageURL).openStream()
                    image = BitmapFactory.decodeStream(`in`)

                    // Only for making changes in UI
                    handler.post {
                        holder.imageView.setImageBitmap(image)
                    }

                }
                // If the URL doesnot point to
                // image or any other kind of failure
                catch (e: Exception) {
                    e.printStackTrace()
                }
            }*/
            //holder.imageView.setImageResource("@draw")
        }
        holder.textView1.text = currentItem.nombre
        holder.descripcion.text = currentItem.descProducto
        holder.stockDisponible.text = "Stock: " + currentItem.cantidadActual.toString()
        holder.precio.text = currentItem.price.toString() + "€"

    }


    override fun getItemCount(): Int {
        return productsList.size
    }

    fun getItem(position: Int): ProductsItem {
        //return if (mDataSet != null) mDataSet[position] else null
        return productsList.get(position)
    }


    fun filter(filter: String) {
        productsList.forEach {
            if (it.categoriaId == filter ) {

            }
            //cargar todos los elementos o hacer un intent a otra pantalla
            //notifyDataSetChanged()
        }

    }


}

