package com.example.recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonViewHolder extends RecyclerView.ViewHolder {
    ImageView iV;
    TextView tV;
    public PersonViewHolder(@NonNull View itemView) {
        super(itemView);
        iV = itemView.findViewById(R.id.imgV);
        tV = itemView.findViewById(R.id.txtV);

    }
}
