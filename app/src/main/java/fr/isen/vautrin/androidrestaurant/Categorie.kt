package fr.isen.vautrin.androidrestaurant

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Categorie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorie)
        val img = intent.getStringExtra("img")
        val color = intent.getIntExtra("color", Color.GRAY)
        val name = intent.getStringExtra("name")
        val texte = findViewById<TextView>(R.id.text1)
        val img_asset = findViewById<ImageView>(R.id.Img)
        texte.text=name
        texte.setBackgroundColor(color)
        img_asset.setImageResource(intent.getIntExtra("img",0))
        val toast = Toast.makeText(this, "Vous êtes dans le menu des ".plus(name), Toast.LENGTH_SHORT)
        toast.show()
    }
}