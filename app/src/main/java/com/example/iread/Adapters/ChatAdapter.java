package com.example.iread.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.iread.R;
import com.example.iread.api.UserHelper;
import com.example.iread.model.User;
import com.example.iread.model.friends;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public class ChatAdapter extends FirestoreRecyclerAdapter<friends,ChatAdapter.ChatHolder> {


    public ChatAdapter(@NonNull FirestoreRecyclerOptions<friends> options) {
        super(options);
    }
    private OnChatClickListener listener;

    @Override
    protected void onBindViewHolder(@NonNull final ChatHolder holder, int position, @NonNull friends model) {
        UserHelper.getUsersCollection().document(model.getfriend())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                holder.name.setText(user.getUsername());
                if(user.getUrlPicture() != null)
                   Glide.with(holder.itemView.getContext())
                        .load(user.getUrlPicture())
                        .apply(RequestOptions.circleCropTransform())
                        .into(holder.imgfriend);
            }
        });
    }

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,parent,false);
        return new ChatHolder(v);
    }

    class ChatHolder extends RecyclerView.ViewHolder{

        ImageView imgfriend;
        TextView name;

        public ChatHolder(View itemView){
            super(itemView);
            imgfriend = itemView.findViewById(R.id.chat_item_imageview);
            name = itemView.findViewById(R.id.chat_item_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null ){
                        listener.onChatClick(getSnapshots().getSnapshot(position),position);
                    }
                }
            });

        }
    }

    public interface OnChatClickListener{
        void onChatClick(DocumentSnapshot documentSnapshot,int position);
    }
    public void setOnChatClickListener(OnChatClickListener listener){
        this.listener= listener;
    }
}
