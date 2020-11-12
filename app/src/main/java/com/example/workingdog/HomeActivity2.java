package com.example.workingdog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class HomeActivity2 extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
//        Spinner spinnerQ1,spinnerQ2,spinnerQ3;
//        String selectedS1 ,selectedS2 ,selectedS3;

        Button buttonDone ;
//        buttonDone.setVisibility(View.GONE);
        //spinner Q1
//        spinnerQ1 = findViewById(R.id.job_spinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.jobs, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerQ1.setAdapter(adapter);
//        spinnerQ1.setOnItemSelectedListener(this);
//        spinnerQ2 = findViewById(R.id.num0_24_spinner);
//        spinnerQ3 = findViewById(R.id.q3_spinner);
//
//        spinnerQ1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                             @Override
//                                             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                                 Toast.makeText(HomeActivity2.this,parent.toString(), Toast.LENGTH_LONG).show();
//                                             }
//        });
//        spinnerQ2.setOnItemClickListener((AdapterView.OnItemClickListener) this);
//        spinnerQ3.setOnItemClickListener((AdapterView.OnItemClickListener) this);
//
        buttonDone = findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity2.this,"Your Text!!",Toast.LENGTH_LONG).show();

            }
        });
//
//
    }
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
//
////        switch (parent.getId()) { //Run Code For Major Spinner
////            case R.id.job_spinner: { // code for first spinner. Depending on spinner.getselecteditem assign adapter to second spinner
////                Toast.makeText(HomeActivity2.this,selectedS1, Toast.LENGTH_LONG).show();
////
////            }
////            case R.id.num0_24_spinner: { // code for second spinner
////                //Use get item selected and get selected item position
////            }
////            case R.id.q3_spinner: { // code for second spinner
////                //Use get item selected and get selected item position
////            }
////        }
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}