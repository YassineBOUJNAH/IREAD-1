package com.example.iread.controller;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.iread.Adapters.BookAdapter;
import com.example.iread.R;
import com.example.iread.api.UserHelper;
import com.example.iread.base.BaseActivity;
import com.example.iread.model.Book;
import com.example.iread.model.BookFireBase;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FriendDejaLuActivity extends BaseActivity {


    private Bundle extras;
    private CollectionReference bookRef ;

    private BookAdapter adapter;
    @Override
    public int getFragmentLayout() {
        return R.layout.activity_deja_lu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureToolbar();
        extras = getIntent().getExtras();
        bookRef = UserHelper.getUsersCollection().document(extras.getString("key")).collection("BookRead");

        setUpRecyclerView();
    }


    private void setUpRecyclerView() {
        Query query = bookRef.orderBy("title");
        Log.d("dfg",query.toString());
        FirestoreRecyclerOptions<BookFireBase> options = new FirestoreRecyclerOptions.Builder<BookFireBase>()
                .setQuery(query, BookFireBase.class)
                .build();
        adapter = new BookAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.deja_lu_recycleView);
        recyclerView.setHasFixedSize(true);
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.deja_lu_toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

}
