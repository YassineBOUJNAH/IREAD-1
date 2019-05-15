package com.example.iread.Adapters;

import android.content.ClipData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iread.R;
import com.example.iread.model.DefiPersonnel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class DefiPAdapter extends FirestoreRecyclerAdapter<DefiPersonnel ,DefiPAdapter.DefiPerHolder> {
    public DefiPAdapter(@NonNull FirestoreRecyclerOptions<DefiPersonnel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DefiPerHolder holder, int position, @NonNull DefiPersonnel model) {
        holder.textTitle.setText(model.getTitre());
        holder.textCount.setText(String.valueOf(model.getDuree()));
    }

    @NonNull
    @Override
    public DefiPerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.defi_perso_item,parent,false);
        return new DefiPerHolder(v);
    }

    class DefiPerHolder extends RecyclerView.ViewHolder{
        TextView textTitle;
        TextView textCount;
        public DefiPerHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.titre_livre);
            textCount = itemView.findViewById(R.id.count_livre);
        }
    }
}
