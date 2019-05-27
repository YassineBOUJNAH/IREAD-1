package com.example.iread.controller;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iread.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DefinTimeAndFieandsActivity extends AppCompatActivity {

    private Button start,add;
    private DatePicker datePicker;
    private Date d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defin_time_and_fieands);
        configureToolbar();


        start = findViewById(R.id.defin_challenge_start);
        add = findViewById(R.id.defin_challenge_add_friend);




        datePicker = findViewById(R.id.datePicker1);
        final Calendar cal = Calendar.getInstance() ;
        d = new Date(119,cal.get(Calendar.MONTH) ,cal.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE));
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DAY_OF_MONTH,30);
        datePicker.setMinDate(cal.getTimeInMillis());
        datePicker.setMaxDate(cal2.getTimeInMillis());
        datePicker.init(cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.YEAR), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
              d = new Date(119, datePicker.getMonth(), datePicker.getDayOfMonth(),cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE));

            }
        });




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getIntent();
                int quiz = i.getIntExtra("Quiz",3);
                Intent intent = new Intent(DefinTimeAndFieandsActivity.this, DefiFormulaireActivity.class);
                intent.putExtra("Quiz",quiz);
                intent.putExtra("date",String.valueOf(d));
                startActivity(intent);
            }
        });
    }
    private void configureToolbar(){
        //Get the toolbar (Serialise)
        Toolbar toolbar = (Toolbar) findViewById(R.id.defin_challenge_bar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }


}
