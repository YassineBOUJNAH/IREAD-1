package com.example.iread.Adapters;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iread.R;
import com.example.iread.model.BookFireBase;
import com.example.iread.model.Message;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MessageAdapter extends FirestoreRecyclerAdapter<Message,MessageAdapter.MessageHolder> {

    public MessageAdapter(@NonNull FirestoreRecyclerOptions<Message> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MessageHolder holder, int position, @NonNull Message model) {
        holder.text.setText(model.getMessage());
        if(model.getUserSender().compareTo(getCurrentUser().getUid()) != 0){
            holder.text.setBackgroundColor(0xFFFCFF9B);
            CoordinatorLayout.LayoutParams  lllp= (CoordinatorLayout.LayoutParams) holder.text.getLayoutParams();
            lllp.gravity=Gravity.LEFT;
            holder.text.setLayoutParams(lllp);
        }

    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_chat_item,parent,false);
        return new MessageHolder(v);
    }

    class MessageHolder extends RecyclerView.ViewHolder{

        TextView text;

        public MessageHolder(View itemView){
            super(itemView);
            text = itemView.findViewById(R.id.message_item_text);

        }
    }

    protected FirebaseUser getCurrentUser(){ return FirebaseAuth.getInstance().getCurrentUser(); }
}

