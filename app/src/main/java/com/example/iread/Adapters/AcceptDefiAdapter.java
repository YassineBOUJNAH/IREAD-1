package com.example.iread.Adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.iread.R;
import com.example.iread.api.UserHelper;
import com.example.iread.model.BookQuiz;
import com.example.iread.model.friendsChallenge;
import com.example.iread.model.User;
import com.example.iread.model.friendsDefi;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class AcceptDefiAdapter extends FirestoreRecyclerAdapter<friendsChallenge, AcceptDefiAdapter.AccptDefiHolder> {

    public AcceptDefiAdapter(@NonNull FirestoreRecyclerOptions<friendsChallenge> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final AccptDefiHolder holder, final int position, @NonNull final friendsChallenge model) {
        DocumentReference colBook = FirebaseFirestore.getInstance().collection("Books").document(String.valueOf(model.getLivre2()));
        colBook.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                BookQuiz bookQuiz = documentSnapshot.toObject(BookQuiz.class);
                holder.Bookname.setText("''"+bookQuiz.getName()+"''");
                if(bookQuiz.getImage() != null)
                    Glide.with(holder.itemView.getContext())
                            .load(bookQuiz.getImage())
                            .into(holder.Bookimg);
            }
        });
        UserHelper.getUsersCollection().document(model.getUiSender2()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                holder.Sender.setText(user.getUsername());
            }
        });
        holder.Refus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(holder.getAdapterPosition());
            }
        });
        holder.Bookdure.setText("Duree : "+String.valueOf(model.getDateFin()));

        holder.Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friendsDefi friendsdefi = new friendsDefi(model.getUiResever(),0);
                CollectionReference defi = FirebaseFirestore.getInstance().collection("Defi").document(model.getUiSender2()+String.valueOf(model.getLivre2())).collection("Friends");
                defi.document(model.getUiResever()).set(friendsdefi).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        deleteItem(holder.getAdapterPosition());
                    }
                });
            }
        });


    }

    private void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();
    }


    @Override
    public AccptDefiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.accepte_defi_item,parent,false);
        return new AccptDefiHolder(v);
    }

    class AccptDefiHolder extends RecyclerView.ViewHolder{
        TextView Sender;
        TextView Bookname;
        TextView Bookdure;
        ImageView Bookimg;
        Button Refus;
        Button Accept;

        public AccptDefiHolder(View view) {
            super(view);
            Sender = view.findViewById(R.id.acceptdefi2_activity_sender);
            Bookname =view.findViewById(R.id.acceptdefi2_activity_book);
            Bookimg = view.findViewById(R.id.acceptdefi2_activity_img);
            Refus = view.findViewById(R.id.acceptdefi2_activity_refus);
            Bookdure =view.findViewById(R.id.acceptdefi2_activity_duree);
            Accept = view.findViewById(R.id.acceptdefi2_activity_accepte);
        }
    }



}
