package com.example.iread.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iread.R;
import com.example.iread.api.UserHelper;
import com.example.iread.model.User;
import com.example.iread.model.friends;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public class FiendDefiAdapter extends FirestoreRecyclerAdapter<friends, FiendDefiAdapter.FriendDefiHolder> {

    private FiendDefiAdapter.OnFriendClickListener listener;

    public FiendDefiAdapter(@NonNull FirestoreRecyclerOptions<friends> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final FiendDefiAdapter.FriendDefiHolder holder, int position, @NonNull friends model) {
        UserHelper.getUsersCollection().document(model.getfriend()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                holder.name.setText(user.getUsername());
                if(user.getUrlPicture() != null)
                    try{
                    Glide.with(holder.itemView.getContext()).load(user.getUrlPicture()).into(holder.imgfriend);
            }catch (Exception e){}
            }
        });
    }
    @Override
    public FiendDefiAdapter.FriendDefiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_defi_select,parent,false);
        return new FiendDefiAdapter.FriendDefiHolder(v);
    }
    class FriendDefiHolder extends RecyclerView.ViewHolder{

        ImageView imgfriend;
        TextView name;
        CheckBox check;

        public FriendDefiHolder(View itemView){
            super(itemView);
            imgfriend = itemView.findViewById(R.id.friend_defi_item_imageview);
            name = itemView.findViewById(R.id.friend_defi_item_name);
            check = itemView.findViewById(R.id.friend_defi_item_checkbox);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null ){
                        listener.onFriendClick(getSnapshots().getSnapshot(position),position);
                        if(check.isChecked()){
                            check.setChecked(false);
                        }else {
                            check.setChecked(true);
                        }
                    }
                }
            });
        }
    }
    public interface OnFriendClickListener{
        void onFriendClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnfriendClickListener(FiendDefiAdapter.OnFriendClickListener  listener){
        this.listener= listener;
    }

}

