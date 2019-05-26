package com.example.iread.controller;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Adapter;

import com.example.iread.Adapters.AcceptDefiAdapter;
import com.example.iread.R;
import com.example.iread.model.friendsChallenge;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AccepteDefi2Activity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference col = db.collection("DefiRequest");

    private AcceptDefiAdapter Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepte_defi2);
        setupRecyleView();
        configureToolbar();

    }
    //.orderBy("dateFin", Query.Direction.DESCENDING)
    private void setupRecyleView() {
        Query query = col.whereEqualTo("uiResever",FirebaseAuth.getInstance().getCurrentUser().getUid());
        FirestoreRecyclerOptions<friendsChallenge> options = new FirestoreRecyclerOptions.Builder<friendsChallenge>()
                .setQuery(query, friendsChallenge.class)
                .build();
        Adapter=new AcceptDefiAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.acceptdefi2_activity_recyle);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(Adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Adapter.stopListening();
    }
    private void configureToolbar(){
        //Get the toolbar (Serialise)
        Toolbar toolbar = (Toolbar) findViewById(R.id.acceptdefi2_activity_toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

}





