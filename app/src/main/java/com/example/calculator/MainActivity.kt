package com.example.calculator


import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    var revert: Boolean = false

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.displayText.setText("")
        binding.displayText.showSoftInputOnFocus = true

        binding.displayText.setOnClickListener {
            if (getString(R.string.display) == binding.displayText.text.toString()) {
                binding.displayText.setText("")
            }
        }

    }


    private fun updateText(strToAdd: String) {

        val oldStr = binding.displayText.text.toString()
        val cursorPos = binding.displayText.selectionStart
        val leftStr = oldStr.substring(0, cursorPos)
        val rightsStr = oldStr.substring(cursorPos)

        binding.displayText.setText(String.format("%s%s%s", leftStr, strToAdd, rightsStr))
        binding.displayText.setSelection(cursorPos + 1)

    }

    fun plusMinusBtn(view: View) {
        val stringText = getString(R.string.plusMinus)
        updateText(stringText)

    }

    fun zeroBtn(view: View) {
        val stringText = getString(R.string.zero)
        updateText(stringText)
    }

    fun pointBtn(view: View) {
        val stringText = getString(R.string.point)
        updateText(stringText)
    }

    fun equalsBtn(view: View) {
        if (binding.displayText.text.isEmpty()) {
            val stringText = getString(R.string.please_write_a_condition)
            Toast.makeText(applicationContext, stringText, Toast.LENGTH_SHORT).show()
            return
        }

        var variableSymbol = binding.displayText.text.toString()
        val stringDivide = getString(R.string.divide)
        val stringMultiply = getString(R.string.multiply)
        variableSymbol = variableSymbol.replace(stringDivide, "/")
        variableSymbol = variableSymbol.replace(stringMultiply, "*")


        val result: Double
        try {
            val expression = ExpressionBuilder(variableSymbol).build()
            result = expression.evaluate()
        } catch (t: Throwable) {
            val stringText = getString(R.string.can_not_calculate_this_expression)
            Toast.makeText(applicationContext, stringText, Toast.LENGTH_SHORT).show()
            return
        }

        val longResult = result.toLong()
        val conversionLong = longResult.toString()
        val conversionDouble = result.toString()

        if (result == longResult.toDouble()) {
            binding.displayText.setText(conversionLong)
        } else {
            binding.displayText.setText(conversionDouble)
        }

        // Move cursor to the last position in display
        binding.displayText.setSelection(binding.displayText.text.toString().length)

        Log.d("SLAVIK", "++++++++++++++++++++++++++++++++++++++")
        Log.d("SLAVIK", "variable $variableSymbol")
        Log.d("SLAVIK", "++++++++++++++++++++++++++++++++++++++")
        Log.d("SLAVIK", "result $result")
    }

    fun oneBtn(view: View) {
        val stringText = getString(R.string.one)
        updateText(stringText)
    }

    fun twoBtn(view: View) {
        val stringText = getString(R.string.two)
        updateText(stringText)
    }

    fun threeBtn(view: View) {
        val stringText = getString(R.string.three)
        updateText(stringText)
    }

    fun addBtn(view: View) {
        val stringText = getString(R.string.add)
        updateText(stringText)
    }

    fun fourBtn(view: View) {
        val stringText = getString(R.string.four)
        updateText(stringText)
    }

    fun fiveBtn(view: View) {
        val stringText = getString(R.string.five)
        updateText(stringText)
    }

    fun sixBtn(view: View) {
        val stringText = getString(R.string.six)
        updateText(stringText)
    }

    fun subtractBtn(view: View) {
        val stringText = getString(R.string.subtract)
        updateText(stringText)
    }

    fun sevenBtn(view: View) {
        val stringText = getString(R.string.seven)
        updateText(stringText)
    }

    fun eightBtn(view: View) {
        val stringText = getString(R.string.eight)
        updateText(stringText)
    }

    fun nineBtn(view: View) {
        val stringText = getString(R.string.nine)
        updateText(stringText)
    }

    fun multiplyBtn(view: View) {
        val stringText = getString(R.string.multiply)
        updateText(stringText)
    }

    fun clearBtn(view: View) {
        binding.displayText.setText("")
    }

    fun parenthesesOpenBtn(view: View) {
        val stringText = getString(R.string.parentheses_open)
        updateText(stringText)
    }

    fun parenthesesClosedBtn(view: View) {
        val stringText = getString(R.string.parentheses_closed)
        updateText(stringText)

    }

    fun divideBtn(view: View) {
        val stringText = getString(R.string.divide)
        updateText(stringText)
    }

    fun buttonPressed(view: View) {
        val button = findViewById<ImageButton>(R.id.historyBTN)
        var icon = R.drawable.history

        if (revert) {
            icon = R.drawable.history_revert_calculator
        }

        button.setImageResource(icon)
        revert = !revert
    }

    fun backspaceBtn(view: View) {

        val cursorPos = binding.displayText.selectionStart
        val textLen = binding.displayText.text.length

        if (cursorPos != 0 && textLen != 0) {
            val selection = SpannableStringBuilder(binding.displayText.text)
            selection.replace(cursorPos - 1, cursorPos, "")
            binding.displayText.text = selection
            binding.displayText.setSelection(cursorPos - 1)
        }
    }
    /* fun buttonEffect(button: View) {
         button.setOnTouchListener { v, event ->
             when (event.action) {
                 MotionEvent.ACTION_DOWN -> {
                     v.background.setColorFilter(-0x1f0b8adf, PorterDuff.Mode.DST)
                     v.invalidate()
                 }
                 MotionEvent.ACTION_UP -> {
                     v.background.clearColorFilter()
                     v.invalidate()
                 }
             }
             false
         }
     }*/
}



