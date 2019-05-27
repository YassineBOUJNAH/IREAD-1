package com.example.iread.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.iread.R;
import com.example.iread.auth.HomeActivity;

public class ResultatActivity extends AppCompatActivity {
    private Button home;
    private TextView t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        t1 = findViewById(R.id.rest_tota2);
        t2 = findViewById(R.id.rest_correct2);
        t3 = findViewById(R.id.rest_wrong2);
        home = findViewById(R.id.rest_home);
        Intent i = getIntent();

        String total =i.getStringExtra("total");
        String correct =i.getStringExtra("correct");
        String incorrect =i.getStringExtra("incorrect");
        t1.setText(total);
        t2.setText(correct);
        t3.setText(incorrect);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
                startActivity(intent);
            }
        });
    }
}
/*
 defi.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                for (DocumentSnapshot documentSnapshot : documentSnapshots){
                    final DefiAccepted defiAccepted =documentSnapshot.toObject(DefiAccepted.class);
                    if (defiAccepted.getUiSender() == userid)
                    {
                         correct = defiAccepted.getNote();
                    }else {
                        defi.document(documentSnapshot.getId()).collection("Friends").document(userid).get()
                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        correct = Integer.parseInt(documentSnapshot.getString("note"));
                                    }
                                });
                    }
                }
            }
        });

 */
