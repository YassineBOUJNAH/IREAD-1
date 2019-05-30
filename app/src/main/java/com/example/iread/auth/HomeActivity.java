package com.example.iread.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.iread.Adapters.PageAdapter;
import com.example.iread.Fragment.BlankFragment;
import com.example.iread.Fragment.NewsPageFragment;
import com.example.iread.R;
import com.example.iread.base.BaseActivity;
import com.example.iread.controller.ALireActivity;
import com.example.iread.controller.AccepteDefi2Activity;
import com.example.iread.controller.DefinTimeAndFieandsActivity;
import com.example.iread.controller.DejaLuActivity;
import com.example.iread.controller.EntrainActivty;
import com.example.iread.controller.LivreActivity;
import com.example.iread.controller.QuizActivity;
import com.example.iread.controller.ResultatActivity;
import com.example.iread.controller.SearchBooks;
import com.example.iread.controller.SearchFriends;
import com.example.iread.controller.friend;
import com.example.iread.controller.invitation;
import com.example.iread.model.DefiAccepted;
import com.example.iread.options.SettingActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import static com.example.iread.Fragment.NewsPageFragment.btn1;
import static com.example.iread.Fragment.NewsPageFragment.btn2;
import static com.example.iread.Fragment.NewsPageFragment.btn3;

public class HomeActivity extends BaseActivity implements NewsPageFragment.OnQuizClickListener, BlankFragment.OnFriendClicklistener {
    private CollectionReference defi = FirebaseFirestore.getInstance().collection("Defi");
    private String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private  DefiAccepted defiAccepted;
    private String doc_id;
    private String quiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profil);
        //3 - Configure ViewPager
        this.configureViewPagerAndTabs();
        this.configureToolbar();

    }

    private void configureViewPagerAndTabs(){
        // 1 - Get ViewPager from layout
        ViewPager pager = (ViewPager)findViewById(R.id.activity_main_viewpager);
        // 2 - Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        TabLayout tabs= (TabLayout)findViewById(R.id.activity_main_tabs);
        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        //for (int i = 0; i < tabs.getTabCount(); i++) { tabs.getTabAt(i).setIcon(R.drawable.ic_search_white_24dp);tabs.getTabAt(i).setText(null); }
        tabs.getTabAt(2).setIcon(R.drawable.ic_user_profile);
        tabs.getTabAt(2).setText(null);
        tabs.getTabAt(1).setIcon(R.drawable.ic_chat);
        tabs.getTabAt(1).setText(null);
        //tabs.getTabAt(1).setIcon(R.drawable.ic_defi);
        //tabs.getTabAt(1).setText(null);
        tabs.getTabAt(0).setIcon(R.drawable.ic_quiz);
        tabs.getTabAt(0).setText(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_profile_activity, menu);
        return true;
    }
//// ne touch pas ca
    @Override
    public int getFragmentLayout() { return R.layout.activity_profile; }


    private void configureToolbar(){
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        // Sets the Toolbar
        setSupportActionBar(toolbar);
    }


    ///////// Les Action de menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.action_setting:
                startActivity(new Intent(this, SettingActivity.class));
                return true;
            case R.id.menu_activity_main_search:
                startActivity(new Intent(this, SearchBooks.class));
                return true;
            case R.id.action_help:

            default:
                return super.onOptionsItemSelected(item);
        }
    }




///manupiler AmisFrgament///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onAddButtonClick(View view) {
        startActivity(new Intent(this, SearchFriends.class));

    }

    @Override
    public void onfriendViewClick(View view) {
        startActivity(new Intent(this, friend.class));

    }

    @Override
    public void onInvitaionViewClick(View view) {
        startActivity(new Intent(this, invitation.class));

    }

    @Override
    public void onDejaluClick(View view) {
        startActivity(new Intent(this, DejaLuActivity.class));
    }

    @Override
    public void onEntrainClick(View view) {
        startActivity(new Intent(this, EntrainActivty.class));
    }
    @Override
    public void onALireClick(View view) {startActivity(new Intent(this, ALireActivity.class));}
    @Override
    public void onDefiReqView(View view){startActivity(new Intent(this, AccepteDefi2Activity.class));}

    ///Quiz Frag
    @Override
    public void OnStart1Click(View view) {
        if (btn1.getText().equals("lancer")) {
            Intent myint = new Intent(this, DefinTimeAndFieandsActivity.class);
            myint.putExtra("Quiz", 1);
            startActivity(myint);
        }
        else if (btn1.getText().equals("Resulta")){
            quiz = String.valueOf(1);
            Toast.makeText(getApplicationContext(),"Quiz bnt1: "+quiz,Toast.LENGTH_LONG).show();
            findResult();
        }else{
            Intent myint = new Intent(this, QuizActivity.class);
            myint.putExtra("child", String.valueOf(1));
            startActivity(myint);
        }
    }


    @Override
    public void OnStart2Click(View view){
        if (btn2.getText().equals("lancer")) {
            Intent myint = new Intent(this, DefinTimeAndFieandsActivity.class);
            myint.putExtra("Quiz", 2);
            startActivity(myint);
        }
        else if (btn2.getText().equals("Resulta")){
            quiz = String.valueOf(2);
            Toast.makeText(getApplicationContext(),"Quiz btn2: "+quiz,Toast.LENGTH_LONG).show();
            findResult();
        }else{
            Intent myint = new Intent(this, QuizActivity.class);
            myint.putExtra("child", String.valueOf(2));
            startActivity(myint);
        }




    }

    @Override
    public void OnStart3Click(View view){
        if (btn3.getText().equals("lancer")) {
            Intent myint = new Intent(this, DefinTimeAndFieandsActivity.class);
            myint.putExtra("Quiz", 3);
            startActivity(myint);
        }
        else if (btn3.getText().equals("Resulta")){
            quiz = String.valueOf(3);
            Toast.makeText(getApplicationContext(),"Quiz bnt3: "+quiz,Toast.LENGTH_LONG).show();
            findResult();
        }else{
            Intent myint = new Intent(this, QuizActivity.class);
            myint.putExtra("child", String.valueOf(3));
            startActivity(myint);
        }
    }
    @Override
    public void OnStart4Click(View view){
        Intent myint = new Intent(this,QuizActivity.class);
        myint.putExtra("child",String.valueOf(3));
        startActivity(myint);
    }
    @Override
    public void OnStart5Click(View view){
        Intent myint = new Intent(this,QuizActivity.class);
        myint.putExtra("child",String.valueOf(3));
        startActivity(myint);
    }
    @Override
    public void OnLire1Click(View view){
        Intent myint = new Intent(this,LivreActivity.class);
        myint.putExtra("child","1.pdf");
        startActivity(myint);
    }
    @Override
    public void OnLire2Click(View view){
        Intent myint = new Intent(this,LivreActivity.class);
        myint.putExtra("child","3.pdf");
        startActivity(myint);
    }
    @Override
    public void OnLire3Click(View view){
        Intent myint = new Intent(this,LivreActivity.class);
        myint.putExtra("child","3.pdf");
        startActivity(myint);
    }
    @Override
    public void OnLire4Click(View view){
        Intent myint = new Intent(this,LivreActivity.class);
        myint.putExtra("child","3.pdf");
        startActivity(myint);
    }
    @Override
    public void OnLire5Click(View view){
        Intent myint = new Intent(this,LivreActivity.class);
        myint.putExtra("child","3.pdf");
        startActivity(myint);
    }
    public void findResult(){
        defi.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                for (DocumentSnapshot documentSnapshot : documentSnapshots){
                    defiAccepted =documentSnapshot.toObject(DefiAccepted.class);
                    doc_id = documentSnapshot.getId();

                    if (quiz.equals(String.valueOf(defiAccepted.getLivre()))){
                        if (documentSnapshot.getId().equals(userid+quiz)){
                            Intent myint = new Intent(getApplicationContext(), ResultatActivity.class);
                            myint.putExtra("docid",userid+quiz);
                            myint.putExtra("sender",userid);
                            myint.putExtra("note",String.valueOf(defiAccepted.getNote()-10));
                            startActivity(myint);
                        }else {
                            defi.document(documentSnapshot.getId()).collection("Friends").document(userid).get()
                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        Intent myint = new Intent(getApplicationContext(), ResultatActivity.class);
                                        myint.putExtra("docid",doc_id);
                                        myint.putExtra("sender",defiAccepted.getUiSender());
                                        myint.putExtra("note",String.valueOf(defiAccepted.getNote()-10));
                                        startActivity(myint);
                                    }
                                });
                        }
                    }
                }
            }
        });
    }
}