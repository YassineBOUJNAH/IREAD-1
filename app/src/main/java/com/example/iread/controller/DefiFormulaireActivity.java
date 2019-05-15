package com.example.iread.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.iread.Adapters.FiendDefiAdapter;
import com.example.iread.R;
import com.example.iread.api.UserHelper;
import com.example.iread.auth.HomeActivity;
import com.example.iread.base.BaseActivity;
import com.example.iread.model.friends;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DefiFormulaireActivity extends BaseActivity {

    private CollectionReference friendRef = UserHelper.getUsersCollection().document(getCurrentUser().getUid()).collection("friends");
    private CollectionReference friendChallenge = UserHelper.getUsersCollection().document(getCurrentUser().getUid()).collection("friendsChallenge");


    private FiendDefiAdapter adapter;

    public Map<String, Object> FriendData = new HashMap<>();
    private int Nbrfriends = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_defi_formulaire);
        configureToolbar();
        setUpRecyclerView();
        //id checkbox :friend_defi_item_checkbox
        //save bar :friends_defi_toolbar
        Intent i = getIntent();
        int quiz = i.getIntExtra("Quiz",2);
        int time = i.getIntExtra("time",3);
        FriendData.put("Quiz",quiz);
        FriendData.put("Time",time);
        FriendData.put("SuperUser",FirebaseAuth.getInstance().getCurrentUser().getUid());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_friends,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.savefriends:
                save();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void save() {
        FriendData.put("NbrOfUser",Nbrfriends);
        friendChallenge.add(FriendData);
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.activity_defi_formulaire;
    }
    private void setUpRecyclerView() {
        Query query = friendRef.orderBy("friend");
        FirestoreRecyclerOptions<friends> options = new FirestoreRecyclerOptions.Builder<friends>()
                .setQuery(query, friends.class)
                .build();

        adapter = new FiendDefiAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.friends_defi_recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        adapter.setOnfriendClickListener(new FiendDefiAdapter.OnFriendClickListener() {
            @Override
            public void onFriendClick(DocumentSnapshot documentSnapshot, int position) {
                String friend = documentSnapshot.toObject(friends.class).getfriend();
                String id = documentSnapshot.getId();
                Toast.makeText(getApplicationContext(),"position : "+position+" id : "+id,Toast.LENGTH_SHORT).show();
                if (!FriendData.containsValue(id)){
                    FriendData.put(String.valueOf(Nbrfriends),id);
                    Nbrfriends++;
                }

                //Intent i=new Intent(getApplicationContext(), ClickedFriendActivity.class);
                //i.putExtra("key",friend);
                //startActivity(i);
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.friends_defi_toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }
}

