package com.example.mathapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val Number1 = findViewById<EditText>(R.id.edtNum1)
        val Number2 = findViewById<EditText>(R.id.edtNum2)
        val SwitchA = findViewById<Switch>(R.id.switchA)
        val SwitchS = findViewById<Switch>(R.id.switchS)
        val SwitchM = findViewById<Switch>(R.id.switchM)
        val SwitchD = findViewById<Switch>(R.id.switchD)
        val Answer = findViewById<TextView>(R.id.edtAnswer)
        val btnCalc = findViewById<Button>(R.id.btnCalc)
        val btnReset = findViewById<Button>(R.id.btnReset)

        btnCalc.setOnClickListener {
            val num1 = Number1.text.toString().toIntOrNull()
            val num2 = Number2.text.toString().toIntOrNull()
            if (num1 == null || num2 == null) {
                Answer.text = "Invalid Input"
                return@setOnClickListener
            }

            val checkedCount = listOf(SwitchA, SwitchS, SwitchM, SwitchD).count { it.isChecked }

            when {
                checkedCount == 0 -> Answer.text = "Please select an operation."
                checkedCount > 1 -> Answer.text = "Please select only one operation."
                SwitchA.isChecked -> Answer.text = "Addition: " + (num1 + num2).toString()
                SwitchS.isChecked -> Answer.text = "Subtraction: " + (num1 - num2).toString()
                SwitchM.isChecked -> Answer.text = "Multiplication: " + (num1 * num2).toString()
                SwitchD.isChecked -> {
                    if (num2 != 0) {
                        Answer.text = "Division: " + (num1 / num2).toString()
                    } else {
                        Answer.text = "Division: Cannot divide by zero"

                    }
                } else -> Answer.text = "Invalid Operation"
            }

            btnReset.setOnClickListener {
                Number1.setText("")
                Number2.setText("")
            }

        }
    }
}