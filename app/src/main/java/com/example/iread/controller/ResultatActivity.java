package com.example.iread.controller;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.iread.Adapters.ResultaAdapter;
import com.example.iread.R;
import com.example.iread.api.UserHelper;
import com.example.iread.auth.HomeActivity;
import com.example.iread.base.BaseActivity;
import com.example.iread.model.DefiAccepted;
import com.example.iread.model.User;
import com.example.iread.model.friendsDefi;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.Map;

public class ResultatActivity extends BaseActivity {
    private CollectionReference defi = FirebaseFirestore.getInstance().collection("Defi");
    //private String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    //private  DefiAccepted defiAccepted;
    private ResultaAdapter adapter;
    private TextView userName,userNote;
    private ImageView userImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureToolbar();
        userName = findViewById(R.id.resulta_activity_username);
        userNote = findViewById(R.id.resulta_activity_note);
        userImage = findViewById(R.id.resulta_activity_img);

        Intent i = getIntent();

        String userid =i.getStringExtra("docid");
        setUpRecyclerView(userid);
        String uiSender = i.getStringExtra("sender");
        int note=Integer.parseInt(i.getStringExtra("note"));
        setUseSender(uiSender,note);







    }

    @Override
    public int getFragmentLayout() {
        return R.layout.activity_resultat;
    }

    private void setUseSender(String id,int note){
        UserHelper.getUsersCollection().document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                userName.setText(user.getUsername());
                if(user.getUrlPicture() != null)
                    try{
                        Glide.with(getApplicationContext()).load(user.getUrlPicture()).into(userImage);
                    }catch (Exception e){}
            }
        });
        userNote.setText(String.valueOf(note)+"/10");
    }

    private void setUpRecyclerView(String uid) {
        CollectionReference dd = defi.document(uid).collection("Friends");
        Query query = dd.orderBy("note");
        FirestoreRecyclerOptions<friendsDefi> options = new FirestoreRecyclerOptions.Builder<friendsDefi>()
                .setQuery(query, friendsDefi.class)
                .build();
        adapter = new ResultaAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.resulta_activity_recycleView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void configureToolbar(){
        //Get the toolbar (Serialise)
        Toolbar toolbar = (Toolbar) findViewById(R.id.resulta_activity_toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
    }
}
