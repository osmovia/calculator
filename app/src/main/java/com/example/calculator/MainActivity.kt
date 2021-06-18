package com.example.calculator


import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.displayText.setText("")
        binding.displayText.showSoftInputOnFocus = false

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
        updateText("-")

    }

    fun zeroBtn(view: View) {
        updateText("0")
    }

    fun pointBtn(view: View) {
        updateText(".")
    }

    fun equalsBtn(view: View) {
        if (binding.displayText.text.isEmpty()) {
            Toast.makeText(applicationContext, "Please write a condition! ", Toast.LENGTH_SHORT).show()
            return
        }

        var variableSymbol = binding.displayText.text.toString()
        variableSymbol = variableSymbol.replace("÷", "/")
        variableSymbol = variableSymbol.replace("×", "*")

        val expression = ExpressionBuilder(variableSymbol).build()
        val result: Double
        try {
            result = expression.evaluate()
        } catch (t: Throwable) {
            Toast.makeText(applicationContext, "Can not calculate this expression! ", Toast.LENGTH_SHORT).show()
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
        updateText("1")
    }

    fun twoBtn(view: View) {
        updateText("2")
    }

    fun threeBtn(view: View) {
        updateText("3")
    }

    fun addBtn(view: View) {
        updateText("+")
    }

    fun fourBtn(view: View) {
        updateText("4")
    }

    fun fiveBtn(view: View) {
        updateText("5")
    }

    fun sixBtn(view: View) {
        updateText("6")
    }

    fun subtractBtn(view: View) {
        updateText("-")
    }

    fun sevenBtn(view: View) {
        updateText("7")
    }

    fun eightBtn(view: View) {
        updateText("8")
    }

    fun nineBtn(view: View) {
        updateText("9")
    }

    fun multiplyBtn(view: View) {
        updateText("×")
    }

    fun clearBtn(view: View) {
        binding.displayText.setText("")
    }

    fun parenthesesOpenBtn(view: View) {
        updateText("(")
    }

    fun parenthesesClosedBtn(view: View) {
        updateText(")")

    }

    fun divideBtn(view: View) {
        updateText("÷")
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
}



