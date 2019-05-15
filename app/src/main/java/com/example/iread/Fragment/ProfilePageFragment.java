package com.example.iread.Fragment;
//Defi Perso

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.iread.Adapters.DefiPAdapter;
import com.example.iread.MainActivity;
import com.example.iread.R;
import com.example.iread.auth.HomeActivity;
import com.example.iread.model.DefiPersonnel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.BreakIterator;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilePageFragment extends Fragment {
    private FirebaseFirestore db =  FirebaseFirestore.getInstance();
    private CollectionReference defref = db.collection("DefiPerso");
    private static final String TAG = "ProfilePageFragment";
    View result;
    private DefiPAdapter adapter;

    public static ProfilePageFragment newInstance() {
        return (new ProfilePageFragment());
    }
    //gestion des call bac

    public interface OnAddClicklistener{
        void onAddClick(View view);
    }
    private OnAddClicklistener addCalback;


    ///
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         result=inflater.inflate(R.layout.fragment_profile_page, container, false);


        FloatingActionButton floatingActionButton = (FloatingActionButton)result.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalback.onAddClick(v);
            }
        });
        setUpRecyclerView();
        //try{ setUpRecyclerView(); }catch(Exception e){ Log.d(TAG," ERROUR         =    " + e.toString() ); }
        return result;
    }


    private void setUpRecyclerView() {
        Query query = defref.orderBy("duree", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<DefiPersonnel> options = new FirestoreRecyclerOptions.Builder<DefiPersonnel>()
                .setQuery(query , DefiPersonnel.class)
                .build();
        adapter = new DefiPAdapter(options);
        RecyclerView rec = result.findViewById(R.id.friend_chat_recycleView);
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rec.setAdapter(adapter);

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

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.createCallbackToParentActivity();
    }

    private void createCallbackToParentActivity(){
        try{
            addCalback = (OnAddClicklistener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnButtonClickedListener");        }
    }


}
