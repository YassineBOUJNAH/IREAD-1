package com.example.iread.controller;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.iread.Adapters.BookAdapter;
import com.example.iread.Adapters.MessageAdapter;
import com.example.iread.R;
import com.example.iread.api.UserHelper;
import com.example.iread.base.BaseActivity;
import com.example.iread.model.BookFireBase;
import com.example.iread.model.Message;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class chatActivity extends BaseActivity {


    private Bundle extras;
    private CollectionReference MessageRef1 ;
    private CollectionReference MessageRef2 ;
    private MessageAdapter adapter;
    private TextInputEditText envoyer;
    private Button btnEnvoyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extras = getIntent().getExtras();
        MessageRef1 = FirebaseFirestore.getInstance().collection("chat").document(extras.getString("key1")).collection("message");
        MessageRef2 = FirebaseFirestore.getInstance().collection("chat").document(extras.getString("key2")).collection("message");
        envoyer= (TextInputEditText)findViewById(R.id.activity_mentor_chat_message_edit_text);
        btnEnvoyer= (Button)findViewById(R.id.activity_mentor_chat_send_button);
        configureToolbar();
        setUpRecyclerView();
        btnEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message(envoyer.getText().toString(),getCurrentUser().getUid());
                MessageRef1.document().set(msg);
                MessageRef2.document().set(msg);
                envoyer.setText("");
            }
        });
    }


    private void setUpRecyclerView() {
        Query query = MessageRef1.orderBy("dateCreated");
        FirestoreRecyclerOptions<Message> options = new FirestoreRecyclerOptions.Builder<Message>()
                .setQuery(query, Message.class)
                .build();
        adapter = new MessageAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.activity_mentor_chat_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition(adapter.getItemCount());

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
    @Override
    public int getFragmentLayout() {
        return R.layout.activity_mentor_chat;
    }

    private void configureToolbar(){
        //Get the toolbar (Serialise)
        Toolbar toolbar = (Toolbar) findViewById(R.id.mento_toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

    }
}
