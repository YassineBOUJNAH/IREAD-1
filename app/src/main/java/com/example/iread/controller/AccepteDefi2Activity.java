package com.example.iread.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private CollectionReference col = db.collection("Defi");

    private AcceptDefiAdapter Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepte_defi2);
        setupRecyleView();
    }
    //
    private void setupRecyleView() {
        Query query = col.whereEqualTo("uiResever",FirebaseAuth.getInstance().getCurrentUser().getUid()).orderBy("dateFin", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<friendsChallenge> options = new FirestoreRecyclerOptions.Builder<friendsChallenge>()
                .setQuery(query, friendsChallenge.class)
                .build();
        Adapter=new AcceptDefiAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.acceptdefi2_activity_recyle);
        recyclerView.setHasFixedSize(true);
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


}





