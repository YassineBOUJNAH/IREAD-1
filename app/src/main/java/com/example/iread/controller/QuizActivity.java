package com.example.iread.controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.iread.model.Quiz;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.iread.controller.ResultatActivity;


import com.example.iread.R;

public class QuizActivity extends AppCompatActivity {
    private Button btn1,btn2,btn3,btn4;
    private TextView quest,time;
    private  int correct=0;
    private  int wrong=0;
    private  int total=1;
    private int save=0;
    private ProgressBar progressBar;
    DatabaseReference reference;
    String childe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        btn1 = findViewById(R.id.qiuz1_btn1);
        btn2 = findViewById(R.id.qiuz1_btn2);
        btn3 = findViewById(R.id.qiuz1_btn3);
        btn4 = findViewById(R.id.qiuz1_btn4);
        progressBar = findViewById(R.id.quiz1_progress);
        time = findViewById(R.id.timeTxt);
        quest =findViewById(R.id.qiuz1_qst1);
        Intent myint= getIntent();
        childe = myint.getStringExtra("child");
        Handler myhandler = new Handler();
        myhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
            }
        },3000);
        UpdateQuiz();
        Countdown(45,time);
    }

    private void UpdateQuiz() {
        if (total > 10){
            if (save == 0) {
                save++;
                SaveInfo();
            }
        }else{
            reference= FirebaseDatabase.getInstance().getReference().child("Quiz").child(childe).child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Quiz question = dataSnapshot.getValue(Quiz.class);
                    quest.setText(question.getQuestion());
                    btn1.setText(question.getOpt1());
                    btn2.setText(question.getOpt2());
                    btn3.setText(question.getOpt3());
                    btn4.setText(question.getOpt4());


                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (btn1.getText().toString().equals(question.getAnswer())) {
                                btn1.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btn1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        UpdateQuiz();
                                    }
                                }, 1500);
                            } else {
                                wrong++;
                                btn1.setBackgroundColor(Color.RED);
                                if (btn2.getText().toString().equals(question.getAnswer())) {
                                    btn2.setBackgroundColor(Color.GREEN);
                                }else if (btn3.getText().toString().equals(question.getAnswer())) {
                                    btn3.setBackgroundColor(Color.GREEN);
                                } else if (btn4.getText().toString().equals(question.getAnswer())) {
                                    btn4.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btn1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        UpdateQuiz();
                                    }
                                },1500);
                            }
                        }
                    });







                    ///////////////////////////////     BTN2
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (btn2.getText().toString().equals(question.getAnswer())) {
                                btn2.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btn2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        UpdateQuiz();
                                    }
                                }, 1500);
                            } else {
                                wrong++;
                                btn2.setBackgroundColor(Color.RED);
                                if (btn1.getText().toString().equals(question.getAnswer())) {
                                    btn1.setBackgroundColor(Color.GREEN);
                                }else if (btn3.getText().toString().equals(question.getAnswer())) {
                                    btn3.setBackgroundColor(Color.GREEN);
                                } else if (btn4.getText().toString().equals(question.getAnswer())) {
                                    btn4.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btn1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        UpdateQuiz();
                                    }
                                },1500);
                            }
                        }
                    });















                    ////////////////////////////////BTN 3
                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (btn3.getText().toString().equals(question.getAnswer())) {
                                btn3.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btn3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        UpdateQuiz();
                                    }
                                }, 1500);
                            } else {
                                wrong++;
                                btn3.setBackgroundColor(Color.RED);
                                if (btn2.getText().toString().equals(question.getAnswer())) {
                                    btn2.setBackgroundColor(Color.GREEN);
                                }else if (btn1.getText().toString().equals(question.getAnswer())) {
                                    btn1.setBackgroundColor(Color.GREEN);
                                } else if (btn4.getText().toString().equals(question.getAnswer())) {
                                    btn4.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btn1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        UpdateQuiz();
                                    }
                                },1500);
                            }
                        }
                    });





                    ///////////////////////////             BTN4
                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (btn4.getText().toString().equals(question.getAnswer())) {
                                btn4.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btn4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        UpdateQuiz();
                                    }
                                }, 1500);
                            } else {
                                wrong++;
                                btn4.setBackgroundColor(Color.RED);
                                if (btn2.getText().toString().equals(question.getAnswer())) {
                                    btn2.setBackgroundColor(Color.GREEN);
                                }else if (btn3.getText().toString().equals(question.getAnswer())) {
                                    btn3.setBackgroundColor(Color.GREEN);
                                } else if (btn1.getText().toString().equals(question.getAnswer())) {
                                    btn1.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btn1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        UpdateQuiz();
                                    }
                                },1500);
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            total++;

        }

    }
    public void Countdown(int sec, final TextView tv){
        new CountDownTimer(sec * 1000+1000,1000){
            public void onTick(long millisUntilFinished) {
                int seconds = (int)(millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%02d",minutes) +":"+ String.format("%02d",seconds));
            }
            public void onFinish() {
                if (save ==0) {
                    tv.setText("Complete");
                    SaveInfo();
                }
                save++;

            }
        }.start();
    }
    public void SaveInfo(){

        Intent intent = new Intent(QuizActivity.this,ResultatActivity.class);
        intent.putExtra("total",String.valueOf(total-1));
        intent.putExtra("correct",String.valueOf(correct));
        intent.putExtra("incorrect",String.valueOf(wrong));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        finish();
        startActivity(intent);
    }
}
