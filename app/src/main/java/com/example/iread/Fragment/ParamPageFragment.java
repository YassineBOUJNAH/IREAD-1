package com.example.iread.Fragment;
//Chat

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iread.Adapters.ChatAdapter;
import com.example.iread.Adapters.friendAdapter;
import com.example.iread.R;
import com.example.iread.api.UserHelper;
import com.example.iread.auth.HomeActivity;
import com.example.iread.controller.ClickedFriendActivity;
import com.example.iread.controller.chatActivity;
import com.example.iread.model.friends;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class ParamPageFragment extends Fragment {
    
    private CollectionReference friendRef = UserHelper.getUsersCollection().document(getCurrentUser().getUid()).collection("friends");
    private CollectionReference msgRef = FirebaseFirestore.getInstance().collection("chat");
    protected FirebaseUser getCurrentUser(){ return FirebaseAuth.getInstance().getCurrentUser(); }

    private ChatAdapter adapter;
    View v;

    public static ParamPageFragment newInstance() {
        return (new ParamPageFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_param_page, container, false);
        setUpRecyclerView();
        return v;
    }

    private void setUpRecyclerView() {
        /*msgRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(final QuerySnapshot documentSnapshots) {
                for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                    msgRef.document(documentSnapshot.getId()).collection("message")
                            .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot documentSnapshots2) {
                            for (DocumentSnapshot doc : documentSnapshots2){
                                Query req =
                                msgRef.document(documentSnapshot.getId()).collection("message")
                                        .document(doc.getId()).orderBy("dateCreated", Query.Direction.ASCENDING);
                            }
                        }
                    });
                }
            }
        });*/



        Query query = friendRef.orderBy("friend");
        FirestoreRecyclerOptions<friends> options = new FirestoreRecyclerOptions.Builder<friends>()
                .setQuery(query, friends.class)
                .build();

        adapter = new ChatAdapter(options);

        RecyclerView recyclerView = v.findViewById(R.id.friend_chat_recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setOnChatClickListener(new ChatAdapter.OnChatClickListener() {
            @Override
            public void onChatClick(DocumentSnapshot documentSnapshot, int position) {
                String friend = documentSnapshot.toObject(friends.class).getfriend();
                Intent i=new Intent(getContext(), chatActivity.class);
                i.putExtra("key1",friend+"_"+getCurrentUser().getUid());
                i.putExtra("key2",getCurrentUser().getUid()+"_"+friend);
                startActivity(i);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}
