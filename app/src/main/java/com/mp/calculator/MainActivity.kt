package com.mp.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.IllegalStateException


class MainActivity : AppCompatActivity() {

    var numbers: MutableList<String> = arrayListOf<String>()
    var operation: MutableList<String> = arrayListOf<String>()
    var NumberClicked: String = ""
    var answer = ""
    var negateNumber = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun flatten(list: MutableList<String>): String {

        for (item in list) {
            answer += item
        }
        return answer
    }

    fun emptyList(list: MutableList<String>) {
        list.clear()
    }

    fun Equals(view: View) {
        val button = view as Button

        resultExpression.text = ""

        if (mathExpression.text.toString().equals("")) {
            println("Nothing")
        } else {

            var returned = flatten(numbers)
            operation.add(returned)
            val answer2 = calculate(operation)

            resultExpression.append(answer2.toString())
            answer = ""

        }
        emptyList(operation)
        emptyList(numbers)
        mathExpression.text = ""
    }

    fun dotClicked(view: View) {
        val button = view as Button

        if (mathExpression.text.toString().takeLast(1).equals(".")) {
            println("Do Nothing")
        } else {
            NumberClicked = button.text.toString()
            numbers.add((button.text).toString())
            mathExpression.append(button.text.toString())
        }
    }

    fun OperationClicked(view: View) {
        val button = view as Button
        var returned = flatten(numbers)

        if (mathExpression.text.toString().equals("")) {
            println("Do Nothing")
        } else {
            operation.add(returned)
            operation.add(button.text.toString())
            emptyList(numbers)
            answer = ""
            mathExpression.append(button.text.toString())
        }

    }

    fun Clear(view: View) {
        resultExpression.text = ""
        mathExpression.text = ""
        numbers.clear()
        operation.clear()
    }

    fun NumberClicked(view: View) {
        val button = view as Button
        NumberClicked = button.text.toString()
        numbers.add((button.text).toString())
        mathExpression.append(button.text.toString())
    }

    fun calculate(list: MutableList<String>): Double {
        var op = ""
        var num = 0.0

        try {

            for (item in list) {
                if (negateNumber == 1) {
                    num = -1 * item.toDouble()
                }

                when {
                    item.equals("+") -> op = item
                    item.equals("-") -> op = item
                    item.equals("/") -> op = item
                    item.equals("x") -> op = item
                    op.equals("+") -> num += item.toDouble()
                    op.equals("-") -> num -= item.toDouble()
                    op.equals("x") -> num *= item.toDouble()
                    op.equals("/") -> num /= item.toDouble()
                    else ->
                        num = item.toDouble()
                }
            }
            return num
        } catch (e: IllegalStateException) {
            return 0.0
        } finally {
            return num
        }
    }
}
