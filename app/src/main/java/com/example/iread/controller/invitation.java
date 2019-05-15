package com.example.iread.controller;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.iread.R;
import com.example.iread.api.FriendHelper;
import com.example.iread.api.UserHelper;
import com.example.iread.base.BaseActivity;
import com.example.iread.model.FriendRequest;
import com.example.iread.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class invitation extends BaseActivity implements View.OnClickListener {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.configureToolbar();
        linearLayout = (LinearLayout)findViewById(R.id.invitationNestedScrollView);
        getInvitation();
    }

    @Override
    public int getFragmentLayout() {return R.layout.activity_invitation ;}

    private void configureToolbar(){
        //Get the toolbar (Serialise)
        Toolbar toolbar = (Toolbar) findViewById(R.id.invitation_activity_toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }


    private void getInvitation(){

        FriendHelper.getFriendsCollection().whereEqualTo("iudResever",this.getCurrentUser().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                linearLayout.removeAllViews();
                for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                    FriendRequest friendRequest = documentSnapshot.toObject(FriendRequest.class);
                    UserHelper.getUser(friendRequest.getUidSender()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            User user = documentSnapshot.toObject(User.class) ;

                            LinearLayout layout = new LinearLayout(invitation.this);
                            layout.setOrientation(LinearLayout.HORIZONTAL);
                            layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,150));
                            TextView titleView = new TextView(invitation.this);
                            LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            titleView.setLayoutParams(lparams);
                            titleView.setText(user.getUsername());
                            titleView.setTextSize(30);
                            ImageView image=new ImageView(invitation.this);
                            LinearLayout.LayoutParams mparams = new LinearLayout.LayoutParams(120, 120);
                            if (user.getUrlPicture() != null){
                                Glide.with(invitation.this)
                                        .load(user.getUrlPicture())
                                        .apply(RequestOptions.circleCropTransform())
                                        .into(image);
                            }
                            else{
                                image.setImageResource(R.drawable.ic_anon_user_48dp);
                            }
                            image.setLayoutParams(mparams);
                            Button button=new Button(invitation.this);
                            LinearLayout.LayoutParams dparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            button.setLayoutParams(dparams);
                            lparams.weight = 1;
                            button.setText("accepter");
                            button.setOnClickListener(invitation.this);
                            button.setTag(user.getUid());
                            button.setTag(user.getUid());
                            layout.addView(image);
                            layout.addView(titleView);
                            layout.addView(button);
                            linearLayout.addView(layout);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Log.e("TAG", "onClick: " + v.getTag() );
        Map<String,String> map = new HashMap<String, String>();
        map.put("friend", (String) v.getTag());
        Map<String,String> map2 = new HashMap<String,String>();
        map2.put("friend",this.getCurrentUser().getUid());
        UserHelper.getUsersCollection().document(this.getCurrentUser().getUid()).collection("friends").document().set(map);
        UserHelper.getUsersCollection().document((String)v.getTag()).collection("friends").document().set(map2);
        FriendHelper.getFriendsCollection().whereEqualTo("iudResever",this.getCurrentUser().getUid()).whereEqualTo("uidSender",(String) v.getTag())
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                for (DocumentSnapshot documentSnapshot : documentSnapshots){
                    FriendHelper.deleteFriendRequest(documentSnapshot.getId().toString());
                }
            }
        });
        Toast.makeText(this, "Amis ajouter!", Toast.LENGTH_LONG).show();
        finish();

    }
}
