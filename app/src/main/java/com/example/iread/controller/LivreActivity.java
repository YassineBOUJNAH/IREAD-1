package com.example.iread.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.iread.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import static com.example.iread.controller.LivreActivity.pdfView;
import static com.example.iread.controller.LivreActivity.pageNumber;

public class LivreActivity extends AppCompatActivity implements OnPageChangeListener {
    static Integer pageNumber = 0;
    private StorageReference riversRef,mStorage;
    static PDFView pdfView;
    String childe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livre);
        pdfView = findViewById(R.id.pdfView);
        mStorage = FirebaseStorage.getInstance().getReference();
        Intent myint= getIntent();
        childe = myint.getStringExtra("child");
        riversRef = mStorage.child(childe);
        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                new RetrivePdfStream().execute(uri.toString());
            }
        });
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
    }


}

class RetrivePdfStream extends AsyncTask<String,Void, InputStream> {

    @Override
    protected InputStream doInBackground(String... strings) {
        InputStream inputStream = null;
        try {
            URL uRl = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) uRl.openConnection();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
            }
        } catch (IOException e) {
            return null;
        }
        return inputStream;
    }

    @Override
    protected void onPostExecute(InputStream inputStream) {
        pdfView.fromStream(inputStream)
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(pageNumber)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .pageSnap(true) // snap pages to screen boundaries
                .load();

    }
}