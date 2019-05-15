package com.example.iread.controller;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.iread.R;
import com.example.iread.model.DefiPersonnel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.database.DatabaseReference;

public class DefiPersoActivity extends AppCompatActivity {

    private EditText bookName,messag;
    private Button  btn;
    private NumberPicker Contdown;
    private CollectionReference db = FirebaseFirestore.getInstance().collection("DefiPerso");
    private static final String KEY_TITLE = "titre";
    private static final String KEY_TIME = "duree";
    private static final String KEY_MSG = "message";
    private static final String TAG = "DefiPersoActivity";
    //private FirebaseUser myAuth = FirebaseAuth.getInstance().getCurrentUser();
    //private CollectionReference defiP = UserHelper.getUsersCollection().document(myAuth.getUid()).collection("DefiPerso");
    //FirebaseFirestore.getInstance().collection("users");
    //private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defi_perso);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        bookName = findViewById(R.id.defi_book);
        Contdown = findViewById(R.id.defi_time);
        messag = findViewById(R.id.defi_msg);
        btn =findViewById(R.id.defi_btn);
        Contdown.setMaxValue(31);
        Contdown.setMinValue(1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String book_name = bookName.getText().toString();
                //String time = Contdown.getText().toString();
                String message = messag.getText().toString();
                if (book_name.isEmpty()){
                    showMessage("Veuillez vérifier tous les champs");
                }else {
                    int time = Contdown.getValue();
                    //Map<String,Object> tab = new HashMap<>();
                    //tab.put(KEY_TITLE,book_name);
                    //tab.put(KEY_TIME,time);
                    //to retriefe data we can use DefiPersonnel tab = documentSnapshot.toObject(DefiPersonnel.class);
                    DefiPersonnel defi = new DefiPersonnel(book_name, message, time);
                    db.add(defi)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    showMessage("Succes");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    showMessage("Failed");
                                    Log.d(TAG,e.toString());

                                }
                            });
                    Toast.makeText(getApplicationContext(),"Succes",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }
    private void showMessage(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
}
