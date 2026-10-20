package com.student.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvRecipes = findViewById<TextView>(R.id.tvRecipes)

        try {
            val jsonString = assets.open("recipes.json").bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(jsonString)

            val builder = StringBuilder()
            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                val title = item.getString("title")
                val description = item.getString("description")
                builder.append("• ").append(title).append("\n  ").append(description).append("\n\n")
            }

            tvRecipes.text = builder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            tvRecipes.text = "Tarifler yüklenirken hata oluştu."
        }
    }
}
