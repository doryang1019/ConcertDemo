package com.example.concertdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    final String TAG = "CONCERT DEMO";
    // addd test 1231231232123
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Log.d(TAG,"Started Concert Demo...");

        //findViewById must be called only after setContentView
        //method call
        EditText editTextNumTix
                = findViewById(R.id.editTextNumTix);
        Spinner spinnerConcertTypes
                = findViewById(R.id.spinnerConcertTypes);
        Button btnBookTickets = findViewById(R.id.btnBookTickets);

        //Practice Exercise: Create an empty views activity
        //ResultsActivity - in that activity, put a Textview (txtViewResults)
        //Set constraints on all four sides to parent
        //set layout height, width to 0dp (match constraint)

        spinnerConcertTypes.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if (position == 0){
                   Toast.makeText(MainActivity.this, "Clicked on rock", Toast.LENGTH_SHORT).show();
               } else if (position == 1){
                   Toast.makeText(MainActivity.this, "Clicked on jazz", Toast.LENGTH_SHORT).show();
               } else if (position == 2){
                   Toast.makeText(MainActivity.this, "Click on blues", Toast.LENGTH_SHORT).show();
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnBookTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextNumTix
                        .getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter number of tickets",
                                            Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        int numTix = Integer.parseInt(editTextNumTix.getText().toString());

                        int selectedInd = spinnerConcertTypes.getSelectedItemPosition();
                        double cost = 0;
                        switch(selectedInd){
                            case 0:
                                cost = numTix*79.99;
                                break;
                            case 1:
                                cost = numTix*59.99;
                                break;
                            case 2:
                                cost = numTix*69.99;
                                break;
                        }
                        DecimalFormat df = new DecimalFormat("$#.##");
                        String outputTxt = df.format(cost);

                        Toast.makeText(MainActivity.this,
                                "Total cost is " + outputTxt, Toast.LENGTH_SHORT).show();

                        //Create a bundle
                        Bundle bundle = new Bundle();

                        //put data in the bundle
                        bundle.putInt("NUMTIX",numTix);
                        bundle.putString("TYPE",
                                spinnerConcertTypes.getSelectedItem().toString());
                        bundle.putDouble("COST",cost);
                        //Create an intent and put the bundle
                        Intent intent = new Intent
                                            (MainActivity.this,ResultsActivity.class);
                        intent.putExtras(bundle);

                        //start activity with this intent
                        startActivity(intent);
                    } catch (Exception ex){
                        //print stack trace
                        ex.printStackTrace();
                        Log.d(TAG,"Error occurred in "
                                + editTextNumTix.getText().toString());
                        Toast.makeText(MainActivity.this,
                                "Invalid input, must be a whole positive number", Toast.LENGTH_SHORT).show();
                        
                    }
                }
            }
        });


    }
}