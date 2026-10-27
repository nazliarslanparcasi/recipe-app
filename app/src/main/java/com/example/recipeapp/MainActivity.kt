
package com.example.recipeapp

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*

class MainActivity : Activity() {

    private val foods = mapOf(
        "Chicken breast" to doubleArrayOf(165.0, 31.0, 3.6, 0.0),
        "Rice (cooked)" to doubleArrayOf(130.0, 2.7, 0.3, 28.0),
        "Egg" to doubleArrayOf(155.0, 13.0, 11.0, 1.1),
        "Apple" to doubleArrayOf(52.0, 0.3, 0.2, 14.0),
        "Banana" to doubleArrayOf(89.0, 1.1, 0.3, 23.0),
        "Oats" to doubleArrayOf(389.0, 16.9, 6.9, 66.3),
        "Potato (boiled)" to doubleArrayOf(87.0, 1.9, 0.1, 20.1),
        "Salmon" to doubleArrayOf(208.0, 20.0, 13.0, 0.0),
        "Yogurt" to doubleArrayOf(61.0, 3.5, 3.3, 4.7),
        "Bread" to doubleArrayOf(265.0, 9.0, 3.2, 49.0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(36, 32, 36, 24)
            setBackgroundColor(Color.rgb(250, 247, 239))
        }

        val title = TextView(this).apply {
            text = "🥗 Recipe & Calories"
            textSize = 28f
            setTextColor(Color.rgb(39, 94, 69))
            gravity = Gravity.CENTER
        }
        layout.addView(title)

        val intro = TextView(this).apply {
            text = "Calculate calories and nutrients in your food."
            textSize = 16f
            setPadding(0, 20, 0, 20)
        }
        layout.addView(intro)

        val foodLabel = TextView(this).apply {
            text = "Choose a food"
            textSize = 16f
        }
        layout.addView(foodLabel)

        val spinner = Spinner(this)
        val names = foods.keys.toList()
        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            names
        )
        layout.addView(spinner)

        val gramsInput = EditText(this).apply {
            hint = "Amount in grams (e.g. 150)"
            inputType = 2
            setText("100")
        }
        layout.addView(gramsInput)

        val calculate = Button(this).apply {
            text = "Calculate nutrition"
        }
        layout.addView(calculate)

        val result = TextView(this).apply {
            textSize = 18f
            setTextColor(Color.rgb(39, 94, 69))
            setPadding(0, 24, 0, 0)
        }
        layout.addView(result)

        calculate.setOnClickListener {
            val grams = gramsInput.text.toString().toDoubleOrNull()
            if (grams == null || grams <= 0) {
                result.text = "Please enter a valid amount."
            } else {
                val values = foods[names[spinner.selectedItemPosition]]!!
                val factor = grams / 100.0
                result.text = String.format(
                    java.util.Locale.US,
                    "Nutrition for %.0f g\n\nCalories: %.1f kcal\nProtein: %.1f g\nFat: %.1f g\nCarbohydrates: %.1f g\n\nValues are estimates.",
                    grams,
                    values[0] * factor,
                    values[1] * factor,
                    values[2] * factor,
                    values[3] * factor
                )
            }
        }

        setContentView(
            ScrollView(this).apply {
                addView(
                    layout,
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                )
            }
        )
    }
}
