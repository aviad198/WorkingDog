package com.example.workingdog

import android.content.Intent
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class HomeActivity3 : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var spinner1: Spinner
    lateinit var spinner2: Spinner
    lateinit var spinner3: Spinner
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home3)
        var text1: String
        spinner1 = findViewById(R.id.job_spinner) as Spinner
        spinner2 = findViewById(R.id.num0_24_spinner) as Spinner
        spinner3 = findViewById(R.id.q3_spinner) as Spinner
        button = findViewById(R.id.buttonDone)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.jobs,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner1.adapter = adapter
            spinner1.onItemSelectedListener =this
        }
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.numbers0_24,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner2.adapter = adapter
            spinner3.adapter = adapter
            spinner2.onItemSelectedListener =this
            spinner3.onItemSelectedListener =this
        }



//        spinner1.onItemSelectedListener = object :
//            AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>,
//                                        view: View, position: Int, id: Long) {
//                text1 = spinner1.getSelectedItem().toString()
//                Toast.makeText(applicationContext,text1,Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>) {
//                // write code to perform some action
//            }
//        }

        button.setOnClickListener{
            fun onClick(v: View?) {

                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)

            }
        }




    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var text1: String
        when (parent?.id){
            R.id.job_spinner->text1 = spinner1.getSelectedItem().toString()
            R.id.num0_24_spinner->text1 = spinner1.getSelectedItem().toString()
            R.id.q3_spinner->text1 = spinner1.getSelectedItem().toString()
            else -> {text1= view?.id.toString()
            }
        }
        Toast.makeText(applicationContext, text1, Toast.LENGTH_SHORT).show();
    }
}