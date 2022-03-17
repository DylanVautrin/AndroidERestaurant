package fr.isen.vautrin.androidrestaurant

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.vautrin.androidrestaurant.databinding.ActivityCategorieBinding
import org.json.JSONObject


class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategorieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategorieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val color = intent.getIntExtra("color", Color.GRAY)
        val name = intent.getStringExtra("name")
        val texte = findViewById<TextView>(R.id.text1)
        val imgasset = findViewById<ImageView>(R.id.Img)
        texte.text = name
        texte.setBackgroundColor(color)
        texte.background.alpha = 200
        imgasset.setImageResource(intent.getIntExtra("img", 0))
        val toast =
            Toast.makeText(this, "Vous êtes dans le menu des ".plus(name), Toast.LENGTH_SHORT)
        toast.show()
        binding.Liste.layoutManager = LinearLayoutManager(this)

        binding.Liste.adapter = CustomAdapter(arrayListOf()) {}
        ///////API//////////////////////////
        jsonParse()
    }

    private fun jsonParse() {
        val param = HashMap<String, String>()
        param["id_shop"] = "1"
        val jsonObject = JSONObject(param as Map<*, *>)
        var text = ""
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val request = JsonObjectRequest(Request.Method.POST, url, jsonObject,
            { response ->
                text = response.toString()
                val data = Gson().fromJson(text, ItemViewModel::class.java)
                val items =
                    data.data.firstOrNull { it.name_fr == intent.getStringExtra("name") }?.items

                binding.Liste.adapter = CustomAdapter(items?: arrayListOf()) {
                    val intent = Intent(this, DetailsActivity::class.java)
                    intent.putExtra("item", it)
                    startActivity(intent)
                }

            }, { error -> error.printStackTrace() })
        Volley.newRequestQueue(this).add(request)


    }
}

