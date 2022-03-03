package fr.isen.vautrin.androidrestaurant

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val textClickEntree = findViewById<TextView>(R.id.textEntree)
        val textClickPlats = findViewById<TextView>(R.id.textPlats)
        val textClickDesserts = findViewById<TextView>(R.id.textDesserts)
        val intent = Intent(this, Categorie::class.java)
        val drawableResourceIdEntree = this.resources.getIdentifier("entree_img", "drawable", this.packageName)
        val drawableResourceIdPlats = this.resources.getIdentifier("plats_img", "drawable", this.packageName)
        val drawableResourceIdDesserts = this.resources.getIdentifier("dessert_img", "drawable", this.packageName)

        textClickEntree.setOnClickListener{
            intent.putExtra("name","Entrées")
            intent.putExtra("color",Color.RED)
            intent.putExtra("img",drawableResourceIdEntree)
            startActivity(intent)
        }

        textClickPlats.setOnClickListener{
            intent.putExtra("name","Plats")
            intent.putExtra("color",Color.GREEN)
            intent.putExtra("img",drawableResourceIdPlats)
            startActivity(intent)
        }

        textClickDesserts.setOnClickListener {
            intent.putExtra("name", "Desserts")
            intent.putExtra("color", Color.CYAN)
            intent.putExtra("img", drawableResourceIdDesserts)
            startActivity(intent)
        }

    }
}