package com.estudio.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.estudio.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener { calculateTip() }

    }

    private fun calculateTip() {

        val stringInTextField = binding.etCost.text.toString()
        val totalCost = stringInTextField.toDoubleOrNull()

        if (totalCost == null ||totalCost == 0.0) {
            displayTip(0.0)
            return
        }

        val tipPercentage = when (binding.rgTipOptions.checkedRadioButtonId) {
            R.id.rb20 -> 0.20
            R.id.rb18 -> 0.18
            else -> 0.15
        }

        var tip = totalCost * tipPercentage
        if (binding.swtRoundUp.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        displayTip(tip)

    }

    private fun displayTip(tip:Double){
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tvResult.text = getString(R.string.tip_amount, formattedTip)
    }
}
