package com.example.iread.controller;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Button;

import com.example.iread.Adapters.BookAdapter;
import com.example.iread.Adapters.friendAdapter;
import com.example.iread.R;
import com.example.iread.api.UserHelper;
import com.example.iread.auth.HomeActivity;
import com.example.iread.base.BaseActivity;
import com.example.iread.model.BookFireBase;
import com.example.iread.model.friends;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

public class friend extends BaseActivity {

    private CollectionReference friendRef = UserHelper.getUsersCollection().document(getCurrentUser().getUid()).collection("friends");

    private friendAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureToolbar();
        setUpRecyclerView();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.activity_friend;
    }

    private void setUpRecyclerView() {
        Query query = friendRef.orderBy("friend");
        FirestoreRecyclerOptions<friends> options = new FirestoreRecyclerOptions.Builder<friends>()
                .setQuery(query, friends.class)
                .build();

        adapter = new friendAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.friends_recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteItem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new friendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String friend = documentSnapshot.toObject(friends.class).getfriend();
                Intent i=new Intent(friend.this, ClickedFriendActivity.class);
                i.putExtra("key",friend);
                startActivity(i);
            }
        });
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.friends_toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
