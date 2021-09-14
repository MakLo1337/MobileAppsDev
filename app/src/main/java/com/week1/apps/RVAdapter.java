package com.week1.apps;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import model.Orang;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.modelViewHolder> {
    private ArrayList<Orang> userList;

    public RVAdapter(ArrayList<Orang> userList){
        this.userList = userList;
    }

    @NonNull
    @Override
    public modelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_user, parent, false);
        return new modelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.modelViewHolder holder, int position) {
        holder.namaView.setText(userList.get(position).getName());
        holder.ageView.setText(String.valueOf(userList.get(position).getAge()));
        holder.addressView.setText(userList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class modelViewHolder extends RecyclerView.ViewHolder{

        private TextView namaView, ageView, addressView;
        private CardView cardView;

        public modelViewHolder(@NonNull View itemView) {
            super(itemView);
            namaView = itemView.findViewById(R.id.namaView);
            ageView = itemView.findViewById(R.id.ageView);
            addressView = itemView.findViewById(R.id.addressView);
            cardView = itemView.findViewById(R.id.cardView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nama = userList.get(getAdapterPosition()).getName();
                    String address = userList.get(getAdapterPosition()).getAddress();
                    int age = userList.get(getAdapterPosition()).getAge();
                    Orang editOrang = new Orang(nama, address, age);
                    Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                    intent.putExtra("editOrang", editOrang);
                    intent.putExtra("position", getAdapterPosition());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
