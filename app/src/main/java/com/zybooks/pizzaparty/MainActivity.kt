package com.zybooks.pizzaparty

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

/**
 * This is the class for the activity that starts when PizzaParty runs. This app will
 * ask the user's input for number of people and how hungry those people are and will output
 * a suggestion of how many pizzas the user should purchase.
 *
 * @author Jonathan Lee using code provided and instructed by ZyBooks.
 *
 * @since version 2.0.0
 *
 * @property [numAttendEditText] to handle the user's input of number of people
 * @property [numPizzasTextView] to display the number of suggested pizzas
 * @property [howHungryRadioGroup] to obtain the user's input on how hungry the people are.
 */

const val PIZZA_TOTAL_STATE = "pizzaTotalState"

class MainActivity : AppCompatActivity() {

    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup
    private var totalPizzas = 0

    /**
     * Overrides the onCreate function from AppCompatActivity to load the layout and initialize
     * [numAttendEditText], [numPizzasTextView], and [howHungryRadioGroup]
     *
     * @param [savedInstanceState] the previously saved state of the activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Assign the correct XML attribute with the current property.
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)

        if (savedInstanceState != null) {
            totalPizzas = savedInstanceState.getInt(PIZZA_TOTAL_STATE)
            displayTotalPizzas()
        }
    }

    private fun displayTotalPizzas() {
        // Place totalPizzas into the string resource and display
        val totalText = getString(R.string.total_pizzas_num, totalPizzas)
        numPizzasTextView.setText(totalText)
    }

    /**
     * Function to handle what happens when the Calculate button is selected. The function
     * should output a suggested number of pizzas given the user's earlier input/selection.
     *
     * @param [view] the current view
     */
    fun calculateClick(view: View) {

        // Get the text that was typed into the EditText
        val numAttendStr = numAttendEditText.text.toString()

        // Convert the text into an integer
        val numAttend = numAttendStr.toIntOrNull() ?: 0

        // Get hunger level selection
        val hungerLevel = when (howHungryRadioGroup.getCheckedRadioButtonId()) {
            R.id.light_radio_button -> PizzaCalculator.HungerLevel.LIGHT
            R.id.medium_radio_button -> PizzaCalculator.HungerLevel.MEDIUM
            else -> PizzaCalculator.HungerLevel.RAVENOUS
        }

        // Get the number of pizzas needed
        val calc = PizzaCalculator(numAttend, hungerLevel)
        totalPizzas = calc.totalPizzas

        displayTotalPizzas()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PIZZA_TOTAL_STATE, totalPizzas)
    }

}