package com.example.iread.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.iread.R;
import com.example.iread.auth.HomeActivity;
import com.example.iread.model.BookQuiz;
import com.example.iread.model.DefiAccepted;
import com.example.iread.model.friendsDefi;
import com.example.iread.options.SettingActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

public class NewsPageFragment extends Fragment {

    private TextView titre1,titre2,titre3,titre4,titre5,titre6;
    private TextView auth1,auth2,auth3,auth4,auth5,auth6;
    private RatingBar rat1,rat2,rat3,rat4,rat5,rat6;
    private ImageView img1,img2,img3,img4,img5,img6;
    private ProgressBar prg1,prg2,prg3,prg4,prg5,prg6;
    private TextView count1,count2,count3,count4,count5,count6;
    public static RelativeLayout relativeLayout1,relativeLayout2,relativeLayout3,relativeLayout4,relativeLayout5,relativeLayout6;
    public static Button  btn1,btn2,btn3,btn4,btn5;
    DatabaseReference reference,reference2,reference3,reference4,reference5;
    private CollectionReference defi = FirebaseFirestore.getInstance().collection("Defi");
    private String userid= FirebaseAuth.getInstance().getCurrentUser().getUid();
    public static NewsPageFragment newInstance() {
        return (new NewsPageFragment());
    }


    public  interface  OnQuizClickListener{
        void OnStart1Click(View view);
        void OnStart2Click(View view);
        void OnStart3Click(View view);
        void OnLire1Click(View view);
        void OnStart4Click(View view);
        void OnStart5Click(View view);

        void OnLire2Click(View view);
        void OnLire3Click(View view);
        void OnLire4Click(View view);
        void OnLire5Click(View view);

    }
    private NewsPageFragment.OnQuizClickListener addCalback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_news_page, container, false);
        titre1 = result.findViewById(R.id.defi_title);
        titre2 = result.findViewById(R.id.defi_title2);
        titre3 = result.findViewById(R.id.defi_title3);
        titre4 = result.findViewById(R.id.defi_title4);
        titre5 = result.findViewById(R.id.defi_title5);

        auth1 = result.findViewById(R.id.defi_author);
        auth2 = result.findViewById(R.id.defi_author2);
        auth3 = result.findViewById(R.id.defi_author3);
        auth4 = result.findViewById(R.id.defi_author4);
        auth5 = result.findViewById(R.id.defi_author5);


        rat1 = result.findViewById(R.id.defi_rating);
        rat2 = result.findViewById(R.id.defi_rating2);
        rat3 = result.findViewById(R.id.defi_rating3);
        rat4 = result.findViewById(R.id.defi_rating4);
        rat5 = result.findViewById(R.id.defi_rating5);


        img1 = result.findViewById(R.id.defi_thumbnail);
        img2 = result.findViewById(R.id.defi_thumbnail2);
        img3 = result.findViewById(R.id.defi_thumbnail3);
        img4 = result.findViewById(R.id.defi_thumbnail4);
        img5 = result.findViewById(R.id.defi_thumbnail5);

        btn1=result.findViewById(R.id.defi_start);
        btn2=result.findViewById(R.id.defi_start2);
        btn3=result.findViewById(R.id.defi_start3);
        btn4=result.findViewById(R.id.defi_start4);
        btn5=result.findViewById(R.id.defi_start5);

        prg1=result.findViewById(R.id.progressBar);
        prg2=result.findViewById(R.id.progressBar2);
        prg3=result.findViewById(R.id.progressBar3);
        prg4=result.findViewById(R.id.progressBar4);
        prg5=result.findViewById(R.id.progressBar5);

        count1=result.findViewById(R.id.textCountDown);
        count2=result.findViewById(R.id.textCountDown2);
        count3=result.findViewById(R.id.textCountDown3);
        count4=result.findViewById(R.id.textCountDown4);
        count5=result.findViewById(R.id.textCountDown5);

        relativeLayout1=result.findViewById(R.id.defi_relative1);
        relativeLayout1.setVisibility(View.INVISIBLE);
        relativeLayout2=result.findViewById(R.id.defi_relative2);
        relativeLayout2.setVisibility(View.INVISIBLE);
        relativeLayout3=result.findViewById(R.id.defi_relative3);
        relativeLayout3.setVisibility(View.INVISIBLE);
        relativeLayout4=result.findViewById(R.id.defi_relative4);
        relativeLayout4.setVisibility(View.INVISIBLE);
        relativeLayout5=result.findViewById(R.id.defi_relative5);
        relativeLayout5.setVisibility(View.INVISIBLE);
        relativeLayout6=result.findViewById(R.id.defi_relative6);
        relativeLayout6.setVisibility(View.INVISIBLE);


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
                StringBuffer strr = new StringBuffer(book.getName());
                titre2.setText(strr.insert(22,"\n"));
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
        reference4= FirebaseDatabase.getInstance().getReference().child("Books").child("book3");
        reference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final BookQuiz book = dataSnapshot.getValue(BookQuiz.class);
                titre4.setText(book.getName());
                auth4.setText(book.getAuteur());
                rat4.setRating(Float.parseFloat(book.getStar()));
                Glide.with(getContext()).load(book.getImage()).into(img4);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        reference5= FirebaseDatabase.getInstance().getReference().child("Books").child("book3");
        reference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final BookQuiz book = dataSnapshot.getValue(BookQuiz.class);
                titre5.setText(book.getName());
                auth5.setText(book.getAuteur());
                rat5.setRating(Float.parseFloat(book.getStar()));
                Glide.with(getContext()).load(book.getImage()).into(img5);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        defi.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                for (DocumentSnapshot documentSnapshot : documentSnapshots){
                    final DefiAccepted defiAccepted =documentSnapshot.toObject(DefiAccepted.class);
                    if (documentSnapshot.getId().equals(userid+String.valueOf(defiAccepted.getLivre()))){
                        ChangeView(defiAccepted.getLivre(),defiAccepted.getDateEnd(),defiAccepted.getNote());

                    }
                    defi.document(documentSnapshot.getId()).collection("Friends").document(userid).get()
                         .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                             @Override
                             public void onSuccess(DocumentSnapshot documentSnapshot2) {
                                 if (documentSnapshot2.exists()){
                                     friendsDefi friendsdefi = documentSnapshot2.toObject(friendsDefi.class);
                                     ChangeView(defiAccepted.getLivre(),defiAccepted.getDateEnd(),friendsdefi.getNote());

                                 }
                             }
                         });
                }
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

        result.findViewById(R.id.defi_start4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalback.OnStart4Click(v);
            }
        });
        result.findViewById(R.id.defi_start5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalback.OnStart5Click(v);
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
        result.findViewById(R.id.defi_lire4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCalback.OnLire4Click(view);
            }
        });
        result.findViewById(R.id.defi_lire5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCalback.OnLire5Click(view);
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

    public void ChangeView(int pos,Date arg,int note){
        Calendar cal = Calendar.getInstance();
        cal.set(2019,cal.get(Calendar.MONTH) ,cal.get(Calendar.DAY_OF_MONTH));
        Date currentday = cal.getTime();
        SimpleDateFormat dt = new SimpleDateFormat("dd");
        int jour = Integer.parseInt(dt.format(arg));
        SimpleDateFormat dm = new SimpleDateFormat("MM");//this line
        int mois=Integer.parseInt(dm.format(arg));
        cal.set(2019,mois-1,jour);
        Date dateFin = cal.getTime();
        long diffr = (dateFin.getTime()- currentday.getTime())/86400000;
        int prog = (int)(diffr*100)/30;
        switch (pos){
            case 1:
                if (note > 0){
                    relativeLayout1.setVisibility(View.VISIBLE);
                    btn1.setText("Resulta");
                    count1.setText(String.valueOf(diffr));
                    prg1.setProgress(prog);
                }else{
                    relativeLayout1.setVisibility(View.VISIBLE);
                    btn1.setText("Quiz");
                    count1.setText(String.valueOf(diffr));
                    prg1.setProgress(prog);
                }
                break;
            case 2:
                if (note > 0){
                    relativeLayout2.setVisibility(View.VISIBLE);
                    btn2.setText("Resulta");
                    count2.setText(String.valueOf(diffr));
                    prg2.setProgress(prog);
                }else{
                    relativeLayout2.setVisibility(View.VISIBLE);
                    btn2.setText("Quiz");
                    count2.setText(String.valueOf(diffr));
                    prg2.setProgress(prog);
                }
                break;
            case 3:
                relativeLayout3.setVisibility(View.VISIBLE);
                btn3.setText("Quiz");
                count3.setText(String.valueOf(diffr));
                prg3.setProgress(prog);
                break;
            case 4:
                relativeLayout4.setVisibility(View.VISIBLE);
                btn4.setText("Quiz");
                count4.setText(String.valueOf(diffr));
                prg4.setProgress(prog);
                break;
            case 5:
                relativeLayout5.setVisibility(View.VISIBLE);
                btn5.setText("Quiz");
                count5.setText(String.valueOf(diffr));
                prg5.setProgress(prog);
                break;
        }
    }
}