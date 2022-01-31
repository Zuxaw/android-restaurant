package fr.isen.cascio.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import fr.isen.cascio.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.starter.setOnClickListener {
            startCategoryActivity(ItemType.STARTER)
        }

        binding.main.setOnClickListener {
            startCategoryActivity(ItemType.MAIN)
        }

        binding.dessert.setOnClickListener {
            startCategoryActivity(ItemType.DESSERT)
        }

        val imageSlider = findViewById<ImageSlider>(R.id.imageSlider)
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("https://cdn.pixabay.com/photo/2021/07/19/16/04/pizza-6478478_960_720.jpg"))
        imageList.add(SlideModel("https://cdn.pixabay.com/photo/2019/01/29/18/05/burger-3962996_960_720.jpg"))
        imageList.add(SlideModel("https://cdn.pixabay.com/photo/2017/02/15/10/39/salad-2068220_960_720.jpg"))
        imageList.add(SlideModel("https://cdn.pixabay.com/photo/2017/05/07/08/56/pancakes-2291908_960_720.jpg"))

        imageSlider.setImageList(imageList,ScaleTypes.CENTER_CROP)

    }

    private fun startCategoryActivity(item: ItemType) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra(CATEGORY_NAME, item)
        startActivity(intent)
    }

    companion object {
        const val CATEGORY_NAME = "CATEGORY_NAME"
    }
}