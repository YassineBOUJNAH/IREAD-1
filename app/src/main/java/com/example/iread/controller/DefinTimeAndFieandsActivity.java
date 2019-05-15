package com.example.iread.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.iread.R;

public class DefinTimeAndFieandsActivity extends AppCompatActivity {

    private Button start,add;
    private EditText count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defin_time_and_fieands);

        count = findViewById(R.id.defin_challenge_time);
        start = findViewById(R.id.defin_challenge_start);
        add = findViewById(R.id.defin_challenge_add_friend);





        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time = Integer.parseInt(count.getText().toString());
                Intent intent = new Intent(DefinTimeAndFieandsActivity.this, DefiFormulaireActivity.class);
                intent.putExtra("time",time);
                Intent i = getIntent();
                int quiz = i.getIntExtra("Quiz",1);
                intent.putExtra("Quiz",quiz);
                startActivity(intent);
            }
        });
    }


}
