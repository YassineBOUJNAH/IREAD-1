package com.example.iread.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iread.R;
import com.example.iread.auth.HomeActivity;
import com.example.iread.model.BookQuiz;
import com.example.iread.options.SettingActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewsPageFragment extends Fragment {

    private TextView titre1,titre2,titre3;
    private TextView auth1,auth2,auth3;
    private RatingBar rat1,rat2,rat3;
    private ImageView img1,img2,img3;
    DatabaseReference reference,reference2,reference3;


    public static NewsPageFragment newInstance() {
        return (new NewsPageFragment());
    }




    public  interface  OnQuizClickListener{
        void OnStart1Click(View view);
        void OnStart2Click(View view);
        void OnStart3Click(View view);
        void OnLire1Click(View view);
        //void OnStart4Click(View view);
        //void OnStart5Click(View view);

        void OnLire2Click(View view);
        void OnLire3Click(View view);
        //void OnLire4Click(View view);
        //void OnLire5Click(View view);

    }
    private NewsPageFragment.OnQuizClickListener addCalback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_news_page, container, false);
        titre1 = result.findViewById(R.id.defi_title);
        titre2 = result.findViewById(R.id.defi_title2);
        titre3 = result.findViewById(R.id.defi_title3);

        auth1 = result.findViewById(R.id.defi_author);
        auth2 = result.findViewById(R.id.defi_author2);
        auth3 = result.findViewById(R.id.defi_author3);


        rat1 = result.findViewById(R.id.defi_rating);
        rat2 = result.findViewById(R.id.defi_rating2);
        rat3 = result.findViewById(R.id.defi_rating3);


        img1 = result.findViewById(R.id.defi_thumbnail);
        img2 = result.findViewById(R.id.defi_thumbnail2);
        img3 = result.findViewById(R.id.defi_thumbnail3);

        reference= FirebaseDatabase.getInstance().getReference().child("Books").child("book1");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    final BookQuiz book = dataSnapshot.getValue(BookQuiz.class);
                    titre1.setText(book.getName());
                    auth1.setText(book.getAuteur());
                    rat1.setRating(Float.parseFloat(book.getStar()));
                    Glide.with(getContext()).load(book.getImage()).into(img1);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        reference2= FirebaseDatabase.getInstance().getReference().child("Books").child("book2");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final BookQuiz book = dataSnapshot.getValue(BookQuiz.class);
                titre2.setText(book.getName());
                auth2.setText(book.getAuteur());
                rat2.setRating(Float.parseFloat(book.getStar()));
                Glide.with(getContext()).load(book.getImage()).into(img2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        reference3= FirebaseDatabase.getInstance().getReference().child("Books").child("book3");
        reference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final BookQuiz book = dataSnapshot.getValue(BookQuiz.class);
                titre3.setText(book.getName());
                auth3.setText(book.getAuteur());
                rat3.setRating(Float.parseFloat(book.getStar()));
                Glide.with(getContext()).load(book.getImage()).into(img3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        result.findViewById(R.id.defi_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalback.OnStart1Click(v);
            }
        });
        result.findViewById(R.id.defi_start2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalback.OnStart2Click(v);
            }
        });
        result.findViewById(R.id.defi_start3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalback.OnStart3Click(v);
            }
        });
        result.findViewById(R.id.defi_lire).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCalback.OnLire1Click(view);
            }
        });
        result.findViewById(R.id.defi_lire2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCalback.OnLire2Click(view);
            }
        });
        result.findViewById(R.id.defi_lire3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCalback.OnLire3Click(view);
            }
        });


        return result;
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.createCallbackToParentActivity();
    }

    private void createCallbackToParentActivity(){
        try{
            addCalback = (NewsPageFragment.OnQuizClickListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnButtonClickedListener");        }
    }
}