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
import com.example.iread.controller.BookActivity;
import com.example.iread.controller.DejaLuActivity;
import com.example.iread.model.Book;
import com.example.iread.model.BookFireBase;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class BookAdapter extends FirestoreRecyclerAdapter<BookFireBase,BookAdapter.BookHolder> {


    public BookAdapter(@NonNull FirestoreRecyclerOptions<BookFireBase> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BookHolder holder, int position, @NonNull BookFireBase model) {
        Glide.with(holder.itemView.getContext()).load(model.getThumbnail()).into(holder.imgBook);
        holder.title.setText(model.getTitle());
        holder.author.setText(model.getAuthors());
    }

    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
        return new BookHolder(v);
    }

    class BookHolder extends RecyclerView.ViewHolder{

        ImageView imgBook;
        TextView title;
        TextView author;

        public BookHolder(View itemView){
            super(itemView);
            imgBook = itemView.findViewById(R.id.book_thumbnail);
            title = itemView.findViewById(R.id.book_title);
            author = itemView.findViewById(R.id.book_author);
        }
    }
}
