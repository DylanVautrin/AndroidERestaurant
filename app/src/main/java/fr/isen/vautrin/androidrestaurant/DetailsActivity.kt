package fr.isen.vautrin.androidrestaurant

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import fr.isen.vautrin.androidrestaurant.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var color = Color.GRAY
        val item = intent.getSerializableExtra("item") as Item
        when(item.categ_name_fr){
            "Entrées" -> color = Color.RED
            "Plats" -> color = Color.GREEN
            "Desserts" -> color = Color.CYAN
        }

        Log.d("DET00",item.toString())
        val url = item.images[0].ifEmpty { null }
        Picasso.get().load(url).error(R.drawable.noimg).into(binding.imageView)
        binding.constraintLayout.setBackgroundColor(color)
        binding.ItemText.text = item.name_fr
        binding.textView2.text = item.ingredients.joinToString { it.name_fr }
        binding.textView.text =  item.prices.joinToString { it.price+"€" }

    }
}