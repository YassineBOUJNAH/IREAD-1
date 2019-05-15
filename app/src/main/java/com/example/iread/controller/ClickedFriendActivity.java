package com.example.iread.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.iread.R;
import com.example.iread.api.UserHelper;
import com.example.iread.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public class ClickedFriendActivity extends AppCompatActivity {

    ImageView imageview;
    User user;
    TextView deja;
    TextView entrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicked_friend);
        imageview = (ImageView)findViewById(R.id.clicked_friend_activity_imageview_profile);
        deja = (Button)findViewById(R.id.Clicked_friend_btn_deja);
        entrain = (Button)findViewById(R.id.Clicked_friend_btn_entrain);
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            UserHelper.getUser(extras.getString("key")).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    user = documentSnapshot.toObject(User.class);
                    Glide.with(ClickedFriendActivity.this)
                            .load(user.getUrlPicture())
                            .apply(RequestOptions.circleCropTransform())
                            .into(imageview);

                    deja.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i=new Intent(ClickedFriendActivity.this, FriendDejaLuActivity.class);
                            i.putExtra("key",extras.getString("key"));
                            startActivity(i);
                        }
                    });
                    entrain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i=new Intent(ClickedFriendActivity.this, FriendEntrainActivity.class);
                            i.putExtra("key",extras.getString("key"));
                            startActivity(i);
                        }
                    });


                }
            });

        }
    }
}
