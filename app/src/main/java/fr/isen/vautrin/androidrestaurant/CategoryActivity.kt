package fr.isen.vautrin.androidrestaurant

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView




class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorie)

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
        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.Liste)
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)
        // ArrayList of class ItemsViewModel
        var array = arrayOf("")
        var arrayprice = intArrayOf(0)
        var arrayimg = arrayOf("")
        val data = ArrayList<ItemViewModel>()
        when(name)
        {
            "Entrées" ->{ array = resources.getStringArray(R.array.ListEntree)
                arrayprice = resources.getIntArray(R.array.PriceEntree)
                arrayimg = resources.getStringArray(R.array.ImgEntree)}
            "Plats" ->{ array = resources.getStringArray(R.array.ListPlats)
                arrayprice = resources.getIntArray(R.array.PricePlats)
                arrayimg = resources.getStringArray(R.array.ImgPlats)}
            "Desserts" ->{ array = resources.getStringArray(R.array.ListDesserts)
              arrayprice = resources.getIntArray(R.array.PriceDesserts)
                arrayimg = resources.getStringArray(R.array.ImgDesserts)}
        }

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 0..4) {
            data.add(ItemViewModel(array[i], arrayprice[i].toString()+ "€",resources.getIdentifier(arrayimg[i],"drawable",this.packageName)))
        }
        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter



    }

}