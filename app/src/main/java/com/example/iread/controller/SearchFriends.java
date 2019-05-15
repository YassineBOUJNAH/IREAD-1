package com.example.iread.controller;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.iread.R;
import com.example.iread.api.UserHelper;
import com.example.iread.base.BaseActivity;
import com.example.iread.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static com.example.iread.api.FriendHelper.createFriendRequest;

public class SearchFriends extends BaseActivity implements View.OnClickListener {

    EditText searchname ;
    Button searchButton;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.configureToolbar();
        searchname =(EditText)findViewById(R.id.search_activity_name);
        searchButton = (Button)findViewById(R.id.search_activity_search_button);
        linearLayout = (LinearLayout)findViewById(R.id.NestedScrollView);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserHelper.getUsersCollection().orderBy("username").startAt(searchname.getText().toString()).endAt(searchname.getText().toString()+"\uf8ff")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot documentSnapshots) {
                                String data = ""; int i=0;
                                linearLayout.removeAllViews();
                                for (DocumentSnapshot documentSnapshot : documentSnapshots) {

                                    User user = documentSnapshot.toObject(User.class);

                                    LinearLayout layout = new LinearLayout(SearchFriends.this);
                                    layout.setOrientation(LinearLayout.HORIZONTAL);
                                    layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,150));

                                    TextView titleView = new TextView(SearchFriends.this);
                                    LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    titleView.setLayoutParams(lparams);
                                    titleView.setText(user.getUsername());
                                    titleView.setTextSize(30);
                                    ImageView image=new ImageView(SearchFriends.this);
                                    LinearLayout.LayoutParams mparams = new LinearLayout.LayoutParams(120, 120);
                                    if (user.getUrlPicture() != null){
                                        Glide.with(SearchFriends.this)
                                                .load(user.getUrlPicture())
                                                .apply(RequestOptions.circleCropTransform())
                                                .into(image);
                                    }
                                    else{
                                        image.setImageResource(R.drawable.ic_anon_user_48dp);

                                    }
                                    image.setLayoutParams(mparams);
                                    Button button=new Button(SearchFriends.this);
                                    LinearLayout.LayoutParams dparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    button.setLayoutParams(dparams);
                                    lparams.weight = 1;
                                    button.setText("Ajouter");
                                    button.setOnClickListener(SearchFriends.this);
                                    button.setTag(user.getUid());
                                    layout.addView(image);
                                    layout.addView(titleView);
                                    layout.addView(button);
                                    linearLayout.addView(layout);
                                }
                            }
                        });
            }
        });

    }

    @Override
    public int getFragmentLayout() {
        return R.layout.activity_search_friends;
    }

    private void configureToolbar(){
        //Get the toolbar (Serialise)
        Toolbar toolbar = (Toolbar) findViewById(R.id.search_activity_toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }
////ajouter button
    @Override
    public void onClick(View v) {

        createFriendRequest(getCurrentUser().getUid(), (String)v.getTag()).addOnFailureListener(this.onFailureListener());
        Toast.makeText(this, "Invitation envoye!", Toast.LENGTH_LONG).show();
        finish();

    }
}
