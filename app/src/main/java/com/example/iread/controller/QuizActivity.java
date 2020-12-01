package com.example.iread.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.iread.model.DefiAccepted;
import com.example.iread.model.Quiz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.iread.controller.ResultatActivity;


import com.example.iread.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import static com.example.iread.Fragment.NewsPageFragment.btn1;
import static com.example.iread.Fragment.NewsPageFragment.btn2;
import static com.example.iread.Fragment.NewsPageFragment.btn3;
import static com.example.iread.Fragment.NewsPageFragment.btn4;
import static com.example.iread.Fragment.NewsPageFragment.btn5;


public class QuizActivity extends AppCompatActivity {
    private Button btn11,btn22,btn33,btn44;
    private TextView quest,time;
    private  int correct=0;
    private  int wrong=0;
    private  int total=1;
    private int save=0;
    private ProgressBar progressBar;
    DatabaseReference reference;
    String childe;
    private CollectionReference defi = FirebaseFirestore.getInstance().collection("Defi");
    private String userid= FirebaseAuth.getInstance().getCurrentUser().getUid();
    private DefiAccepted defiAccepted;



    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        btn11 = findViewById(R.id.qiuz1_btn1);
        btn22 = findViewById(R.id.qiuz1_btn2);
        btn33 = findViewById(R.id.qiuz1_btn3);
        btn44 = findViewById(R.id.qiuz1_btn4);
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
        },2000);
        UpdateQuiz();
        Countdown(45,time);







    }

    private void UpdateQuiz() {
        if (total > 10){
            if (save == 0) {
                save++;
                countDownTimer.cancel();
                SaveInfo();
            }
        }else{
            reference= FirebaseDatabase.getInstance().getReference().child("Quiz").child(childe).child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Quiz question = dataSnapshot.getValue(Quiz.class);
                    quest.setText(question.getQuestion());
                    btn11.setText(question.getOpt1());
                    btn22.setText(question.getOpt2());
                    btn33.setText(question.getOpt3());
                    btn44.setText(question.getOpt4());


                    btn11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (btn11.getText().toString().equals(question.getAnswer())) {
                                btn11.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btn11.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        UpdateQuiz();
                                    }
                                }, 1500);
                            } else {
                                wrong++;
                                btn11.setBackgroundColor(Color.RED);
                                if (btn22.getText().toString().equals(question.getAnswer())) {
                                    btn22.setBackgroundColor(Color.GREEN);
                                }else if (btn33.getText().toString().equals(question.getAnswer())) {
                                    btn33.setBackgroundColor(Color.GREEN);
                                } else if (btn44.getText().toString().equals(question.getAnswer())) {
                                    btn44.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btn11.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn22.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn33.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn44.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        UpdateQuiz();
                                    }
                                },1500);
                            }
                        }
                    });







                    ///////////////////////////////     btn22
                    btn22.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (btn22.getText().toString().equals(question.getAnswer())) {
                                btn22.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btn22.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        UpdateQuiz();
                                    }
                                }, 1500);
                            } else {
                                wrong++;
                                btn22.setBackgroundColor(Color.RED);
                                if (btn11.getText().toString().equals(question.getAnswer())) {
                                    btn11.setBackgroundColor(Color.GREEN);
                                }else if (btn33.getText().toString().equals(question.getAnswer())) {
                                    btn33.setBackgroundColor(Color.GREEN);
                                } else if (btn44.getText().toString().equals(question.getAnswer())) {
                                    btn44.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btn11.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn22.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn33.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn44.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        UpdateQuiz();
                                    }
                                },1500);
                            }
                        }
                    });















                    ////////////////////////////////BTN 3
                    btn33.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (btn33.getText().toString().equals(question.getAnswer())) {
                                btn33.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btn33.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        UpdateQuiz();
                                    }
                                }, 1500);
                            } else {
                                wrong++;
                                btn33.setBackgroundColor(Color.RED);
                                if (btn22.getText().toString().equals(question.getAnswer())) {
                                    btn22.setBackgroundColor(Color.GREEN);
                                }else if (btn11.getText().toString().equals(question.getAnswer())) {
                                    btn11.setBackgroundColor(Color.GREEN);
                                } else if (btn44.getText().toString().equals(question.getAnswer())) {
                                    btn44.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btn11.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn22.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn33.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn44.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        UpdateQuiz();
                                    }
                                },1500);
                            }
                        }
                    });





                    ///////////////////////////             btn44
                    btn44.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (btn44.getText().toString().equals(question.getAnswer())) {
                                btn44.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btn44.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        UpdateQuiz();
                                    }
                                }, 1500);
                            } else {
                                wrong++;
                                btn44.setBackgroundColor(Color.RED);
                                if (btn22.getText().toString().equals(question.getAnswer())) {
                                    btn22.setBackgroundColor(Color.GREEN);
                                }else if (btn33.getText().toString().equals(question.getAnswer())) {
                                    btn33.setBackgroundColor(Color.GREEN);
                                } else if (btn11.getText().toString().equals(question.getAnswer())) {
                                    btn11.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btn11.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn22.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn33.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        btn44.setBackgroundColor(Color.parseColor("#03A9F4"));
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
        countDownTimer = new CountDownTimer(sec * 1000+1000,1000){
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
        defi.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                for (DocumentSnapshot documentSnapshot : documentSnapshots){
                     DefiAccepted defiAccepted =documentSnapshot.toObject(DefiAccepted.class);

                    if (childe.equals(String.valueOf(defiAccepted.getLivre())))
                    {
                        if (defiAccepted.getUiSender().equals(userid))
                        {
                            defi.document(documentSnapshot.getId()).update("note",correct+10);
                            ChangeButton(defiAccepted.getLivre());
                            ChangeActivity(documentSnapshot.getId(),defiAccepted.getUiSender(),String.valueOf(correct));

                        }else {
                            defi.document(documentSnapshot.getId()).collection("Friends").document(userid).update("note",correct+10);
                            ChangeButton(defiAccepted.getLivre());
                            ChangeActivity(documentSnapshot.getId(),defiAccepted.getUiSender(),String.valueOf(defiAccepted.getNote()-10));

                        }
                    }

                }
            }
        });





    }

    private void ChangeActivity(String id, String uiSender, String note) {
        Intent myint = new Intent(getApplicationContext(), ResultatActivity.class);
        myint.putExtra("docid",id);
        myint.putExtra("sender",uiSender);
        myint.putExtra("note",note);
        myint.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(myint);
        finish();
    }

    public void ChangeButton(int i){
        switch (i) {
            case 1:
                btn1.setText("Resulta");
                break;
            case 2:
                btn2.setText("Resulta");
                break;
            case 3:
                btn3.setText("Resulta");
                break;
            case 4:
                btn4.setText("Resulta");
                break;
            case 5:
                btn5.setText("Resulta");
                break;
        }
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        countDownTimer.cancel();
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
