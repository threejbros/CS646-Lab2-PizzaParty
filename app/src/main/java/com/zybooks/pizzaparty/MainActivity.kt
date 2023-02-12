package com.zybooks.pizzaparty

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

const val SLICES_PER_PIZZA = 8

/**
 * This is the class for the activity that starts when PizzaParty runs. This app will
 * ask the user's input for number of people and how hungry those people are and will output
 * a suggestion of how many pizzas the user should purchase.
 *
 * @author Jonathan Lee using code provided and instructed by ZyBooks.
 *
 * @since version 1.0.0
 *
 * @property [numAttendEditText] to handle the user's input of number of people
 * @property [numPizzasTextView] to display the number of suggested pizzas
 * @property [howHungryRadioGroup] to obtain the user's input on how hungry the people are.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup

    /**
     * Overrides the onCreate function from AppCompatActivity to load the layout and initialize
     * [numAttendEditText], [numPizzasTextView], and [howHungryRadioGroup]
     *
     * @param [savedInstanceState] the previously saved state of the activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)
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
        val numAttend = numAttendStr.toInt()

        // Determine how many slices on average each person will eat
        val slicesPerPerson = when (howHungryRadioGroup.checkedRadioButtonId) {
            R.id.light_radio_button -> 2
            R.id.medium_radio_button -> 3
            else -> 4
        }

        // Calculate and show the number of pizzas needed
        val totalPizzas = ceil(numAttend * slicesPerPerson / SLICES_PER_PIZZA.toDouble()).toInt()
        numPizzasTextView.text = "Total pizzas: $totalPizzas"
    }
}