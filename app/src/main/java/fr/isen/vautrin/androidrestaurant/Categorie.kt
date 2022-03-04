package fr.isen.vautrin.androidrestaurant

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView




class Categorie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorie)
        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.Liste)
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)
        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemViewModel>()
        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemViewModel("Entree n$i",i ,R.drawable.entree_img))
        }
        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        val img = intent.getStringExtra("img")
        val color = intent.getIntExtra("color", Color.GRAY)
        val name = intent.getStringExtra("name")
        val texte = findViewById<TextView>(R.id.text1)
        val img_asset = findViewById<ImageView>(R.id.Img)
        val list = findViewById<RecyclerView>(R.id.Liste)
        texte.text=name
        texte.setBackgroundColor(color)
        img_asset.setImageResource(intent.getIntExtra("img",0))
        val toast = Toast.makeText(this, "Vous êtes dans le menu des ".plus(name), Toast.LENGTH_SHORT)
        toast.show()

    }

}