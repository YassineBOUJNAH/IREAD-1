package com.example.iread.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iread.R;
import com.example.iread.api.UserHelper;
import com.example.iread.model.User;
import com.example.iread.model.friendsDefi;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public class ResultaAdapter extends FirestoreRecyclerAdapter<friendsDefi, ResultaAdapter.ResultaHolder> {

    public ResultaAdapter(@NonNull FirestoreRecyclerOptions<friendsDefi> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ResultaHolder holder, int position, @NonNull friendsDefi model) {
        UserHelper.getUsersCollection().document(model.getUiResever()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                holder.userName.setText(user.getUsername());
                if(user.getUrlPicture() != null)
                    try{
                        Glide.with(holder.itemView.getContext()).load(user.getUrlPicture()).into(holder.userImage);
                    }catch (Exception e){}
            }
        });
        if (model.getNote()==0){
            holder.userNote.setText("pas encore");
        }else {
            holder.userNote.setText(String.valueOf(model.getNote()-10)+"/10");
        }

    }

    @Override
    public ResultaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.resulta_item,parent,false);
        return new ResultaHolder(v);
    }

    class ResultaHolder extends RecyclerView.ViewHolder{
        TextView userName,userNote;
        ImageView userImage;
        public ResultaHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.resulta_item_username);
            userNote = itemView.findViewById(R.id.resulta_item_note);
            userImage = itemView.findViewById(R.id.resulta_item_img);
        }
    }
}
