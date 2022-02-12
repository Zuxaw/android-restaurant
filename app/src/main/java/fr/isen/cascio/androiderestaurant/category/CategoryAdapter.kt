package fr.isen.cascio.androiderestaurant.category

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.cascio.androiderestaurant.R
import fr.isen.cascio.androiderestaurant.databinding.DishesCellBinding
import fr.isen.cascio.androiderestaurant.network.Dish
import com.squareup.picasso.Picasso

class CategoryAdapter(private val entries: List<Dish>,
                      private val entryClickListener: (Dish) -> Unit)
    : RecyclerView.Adapter<CategoryAdapter.DishesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesViewHolder {
        return DishesViewHolder(DishesCellBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DishesViewHolder, position: Int) {
        val dish = entries[position]
        holder.layout.setOnClickListener {
            entryClickListener.invoke(dish)
        }
        holder.bind(dish)
    }

    override fun getItemCount(): Int {
        return entries.count()
    }

    class DishesViewHolder(dishesBinding: DishesCellBinding): RecyclerView.ViewHolder(dishesBinding.root) {
        val titleView: TextView = dishesBinding.dishesTitle
        val priceView: TextView = dishesBinding.dishPrice
        val imageView: ImageView = dishesBinding.dishImageView
        val layout = dishesBinding.root

        fun bind(dish: Dish) {
            titleView.text = dish.name
            priceView.text = "${dish.prices.first().price} €" // dish.prices.first().price + " €"
            Picasso.get()
                .load(dish.getThumbnailUrl())
                .placeholder(R.drawable.logo_restaurant)
                .into(imageView)
        }
    }
}