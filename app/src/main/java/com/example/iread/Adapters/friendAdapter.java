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
import com.example.iread.model.BookFireBase;
import com.example.iread.model.User;
import com.example.iread.model.friends;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public class friendAdapter extends FirestoreRecyclerAdapter<friends,friendAdapter.friendHolder> {

    private OnItemClickListener listener;

    public friendAdapter(@NonNull FirestoreRecyclerOptions<friends> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final friendHolder holder, int position, @NonNull friends model) {
        UserHelper.getUsersCollection().document(model.getfriend()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                holder.name.setText(user.getUsername());
                if(user.getUrlPicture() != null)
                    Glide.with(holder.itemView.getContext()).load(user.getUrlPicture()).into(holder.imgfriend);
            }
        });
    }
    @Override
    public friendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item,parent,false);
        return new friendHolder(v);
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }
    class friendHolder extends RecyclerView.ViewHolder{

        ImageView imgfriend;
        TextView name;

        public friendHolder(View itemView){
            super(itemView);
            imgfriend = itemView.findViewById(R.id.friend_item_imageview);
            name = itemView.findViewById(R.id.friend_item_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null ){
                        listener.onItemClick(getSnapshots().getSnapshot(position),position);
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot,int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener= listener;
    }

}
