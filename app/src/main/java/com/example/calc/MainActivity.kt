package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {



    lateinit var textView:TextView
    var stack: Stack<Double> = Stack()
    var str=" "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView5)
        val button2= arrayOf(R.id.button3,R.id.button4,R.id.button22,R.id.button23
                            ,R.id.button24,R.id.button25,R.id.button26,R.id.button27
                            ,R.id.button28,R.id.button29,R.id.button30,R.id.button31
                            , R.id.button32,R.id.button33,R.id.button34,R.id.button35
                            ,R.id.button36,R.id.button37)
        for(i in button2.indices) {
            var button1: Button = findViewById(button2[i])
            button1.setOnClickListener {
                onclick(button1)
            }
        }

    }
    private fun onclick(button:Button){
        if((button.text).equals("=")){
            operate()
        }
        else {
			if((button.text).equals("C")){
				str =" "
				textView.text = ""
			}
			else {
				textView.text = (textView.text).toString() + button.text

				if ((button.text).equals("+") || (button.text).equals("-")
					|| (button.text).equals("*") || (button.text).equals("/")) {
					str += " " + button.text + " "
				}
				else {
					if((button.text).equals("%")){
                        str += " * 0.01"
					}
                    else {
                        str+=button.text
                    }
				}
			}
        }
    }
    private fun operate(){
        var strs=str.split(" ")
        stack.push((strs[1].toDouble()))
        for(i in strs.indices){
            if(i%2==0){
                if(strs[i].equals("+")){
                    stack.push(strs[i+1].toDouble())
                }
                if(strs[i].equals("-")){
                    stack.push(-(strs[i+1].toDouble()))
                }
                if(strs[i].equals("*")){
                    var m : Double = (stack.peek())
                    stack.pop()
                    stack.push(m*(strs[i+1].toDouble()))
                }
                if(strs[i].equals("/")){
                    var n : Double = stack.peek()
                    stack.pop()
                    stack.push(n/(strs[i+1].toDouble()))
                }
            }
        }
        var sum=0.0
        while(!stack.empty()){
            sum+=stack.peek()
            stack.pop()
        }
        str=" " + sum.toString()
        textView.setText(sum.toString())
    }
}