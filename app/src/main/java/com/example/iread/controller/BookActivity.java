package com.example.iread.controller;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.iread.R;
import com.example.iread.api.UserHelper;
import com.example.iread.base.BaseActivity;
import com.example.iread.model.BookFireBase;

public class BookActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        //hide the default actionBar
       // getSupportActionBar().hide();

        //Receive
        Bundle extras = getIntent().getExtras();
        String title =""
                , authors ="", description="" , categories ="", publishDate=""
                ,info ="",preview ="" ,thumbnail ="";
        if(extras != null){
            title = extras.getString("book_title");
            authors = extras.getString("book_author");
            description = extras.getString("book_desc");
            categories = extras.getString("book_categories");
            publishDate = extras.getString("book_publish_date");
            info = extras.getString("book_info");
            //buy = extras.getString("book_buy");
            preview = extras.getString("book_preview");
            thumbnail = extras.getString("book_thumbnail");
        }

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tvTitle = findViewById(R.id.aa_book_name);
        TextView tvAuthors = findViewById(R.id.aa_author);
        TextView tvDesc = findViewById(R.id.aa_description);
        TextView tvCatag = findViewById(R.id.aa_categorie);
        TextView tvPublishDate = findViewById(R.id.aa_publish_date);
        final Button tvInfo = findViewById(R.id.aa_select_book);
        //TextView tvInfo = findViewById(R.id.aa_info);
        //TextView tvPreview = findViewById(R.id.aa_preview);

        ImageView ivThumbnail = findViewById(R.id.aa_thumbnail);

        tvTitle.setText(title);
        tvAuthors.setText(authors);
        tvDesc.setText(description);
        tvCatag.setText(categories);
        tvPublishDate.setText(publishDate);

        collapsingToolbarLayout.setTitle(title);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        Glide.with(this).load(thumbnail).apply(requestOptions).into(ivThumbnail);
        final String finalAuthors = authors;
        final String finalThumbnail = thumbnail;
        final String finalTitle = title;
        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(BookActivity.this,tvInfo);
                popupMenu.getMenuInflater().inflate(R.menu.pop_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        //// Deja lu database //////////////////////////////////////////////////////////////////////////////////

                        BookFireBase bk = new BookFireBase(finalAuthors, finalThumbnail, finalTitle);
                        switch (menuItem.getItemId()){
                            case R.id.pp_deja :
                                UserHelper.getUsersCollection().document(getCurrentUser().getUid()).collection("BookRead").document(finalTitle).set(bk);
                                return true;

                            case R.id.pp_entrain :
                                UserHelper.getUsersCollection().document(getCurrentUser().getUid()).collection("CurrentlyReding").document(finalTitle).set(bk);
                                return true;
                            case R.id.pp_alire :
                                UserHelper.getUsersCollection().document(getCurrentUser().getUid()).collection("WantToRead").document(finalTitle).set(bk);
                                return true;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.activity_book;
    }
}

