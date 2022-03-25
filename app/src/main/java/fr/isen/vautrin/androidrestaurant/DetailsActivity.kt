package fr.isen.vautrin.androidrestaurant

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import fr.isen.vautrin.androidrestaurant.databinding.ActivityDetailsBinding


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root

        var price: Float = 0F
        var quantity = 0
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
        if(item.images.isNotEmpty()){
        binding.viewPager2.adapter = DetailImagePager(this,item.images)}
        else
            binding.imageView2.setImageResource(R.drawable.noimg)
        binding.constraintLayout.setBackgroundColor(color)
        binding.ItemText.text = item.name_fr
        binding.textView2.text = item.ingredients.joinToString { it.name_fr }
        binding.textView.text =  item.prices.joinToString { it.price+"€" }

        binding.buttonPrice.text = "Ajouter au panier : "+item.prices[0].price + "€"

        binding.buttonPLus.setOnClickListener{
            quantity++
            Log.i("quantity",quantity.toString())
            binding.itemQuantity.text = quantity.toString()
            price = quantity * item.prices[0].price.toFloat()
            binding.buttonPrice.text = "Ajouter au panier : "+price.toString()+"€"

        }
        binding.buttonMoins.setOnClickListener{
            if(quantity>=1) {
                quantity--
            }
            else
                quantity=0
            binding.itemQuantity.text = quantity.toString()
            price = quantity * item.prices[0].price.toFloat()
            binding.buttonPrice.text = "Ajouter au panier : "+price.toString()+"€"
        }

        binding.buttonPanier.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("item", item)
            startActivity(intent)
        }

        binding.buttonPrice.setOnClickListener{
            ShoppingCart.updateCart(ItemCart(item.images[0],item.name_fr,quantity,item.prices[0].price.toFloat()),this)
            //setupBadge()
            Snackbar.make(binding.root,"$quantity ${item.name_fr} bien ajouté au panier", Snackbar.LENGTH_SHORT ).show()
        }
    }
}
