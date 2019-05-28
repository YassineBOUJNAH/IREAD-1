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
import com.example.iread.Adapters.FiendDefiAdapter;
import com.example.iread.R;
import com.example.iread.api.UserHelper;
import com.example.iread.auth.HomeActivity;
import com.example.iread.base.BaseActivity;
import com.example.iread.model.DefiAccepted;
import com.example.iread.model.friends;
import com.example.iread.model.friendsChallenge;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class DefiFormulaireActivity extends BaseActivity {

    private CollectionReference friendRef = UserHelper.getUsersCollection().document(getCurrentUser().getUid()).collection("friends");
    private CollectionReference friendChallenge =  FirebaseFirestore.getInstance().collection("DefiRequest");
    private CollectionReference Defi    =             FirebaseFirestore.getInstance().collection("Defi");
    private FiendDefiAdapter adapter;

    public Map<Integer,String> FriendData = new HashMap<Integer,String>();
    private int Nbrfriends = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureToolbar();
        setUpRecyclerView();



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
        Intent i = getIntent();
        int quiz = i.getIntExtra("Quiz",2);
        Date dd =new Date(i.getStringExtra("date"));
        final String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        for(Map.Entry m:FriendData.entrySet()){
            friendsChallenge challgdoc = new friendsChallenge(quiz,String.valueOf(m.getValue()), userid, dd);
            friendChallenge.document().set(challgdoc);
        }

        DefiAccepted defiAccepted = new DefiAccepted(userid,quiz,dd,0);
        Defi.document(userid+quiz).set(defiAccepted);

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
                friends friend = documentSnapshot.toObject(friends.class);
                String friendid = friend.getfriend();
                if (!FriendData.containsValue(friendid)){
                    FriendData.put(Nbrfriends,friendid);
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

