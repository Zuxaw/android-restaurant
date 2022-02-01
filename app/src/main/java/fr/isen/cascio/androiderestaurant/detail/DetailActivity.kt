package fr.isen.cascio.androiderestaurant.detail

import android.os.Bundle
import fr.isen.cascio.androiderestaurant.BaseActivity
import fr.isen.cascio.androiderestaurant.R
import fr.isen.cascio.androiderestaurant.basket.Basket
import fr.isen.cascio.androiderestaurant.basket.BasketItem
import fr.isen.cascio.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.cascio.androiderestaurant.network.Dish
import com.google.android.material.snackbar.Snackbar
import kotlin.math.max

class DetailActivity : BaseActivity() {
    companion object {
        const val DISH_EXTRA = "DISH_EXTRA"
    }

    lateinit var binding: ActivityDetailBinding
    private var itemCount = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra(DISH_EXTRA) as? Dish
        dish?.let {
            setupContent(it)
        }
        val fragment = DetailViewFragment(dish)
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit()
    }

    private fun setupContent(dish: Dish) {
        refreshShopButton(dish)

        binding.less.setOnClickListener {
            itemCount = max(1, itemCount - 1)
            refreshShopButton(dish)
        }

        binding.more.setOnClickListener {
            itemCount += 1
            refreshShopButton(dish)
        }

        binding.shopButton.setOnClickListener {
            addToBasket(dish, itemCount)
        }
    }

    private fun refreshShopButton(dish: Dish) {
        val price = itemCount * dish.prices.first().price.toFloat()
        binding.itemCount.text = itemCount.toString()
        binding.shopButton.text = "${getString(R.string.total)} $price€"
    }

    private fun addToBasket(dish: Dish, count: Int) {
        val basket = Basket.getBasket(this)
        basket.addItem(BasketItem(dish, count))
        basket.save(this)
        refreshMenu(basket)
        Snackbar.make(binding.root, getString(R.string.basket_validation), Snackbar.LENGTH_LONG).show()
    }

    private fun refreshMenu(basket: Basket) {
        invalidateOptionsMenu()
    }
}