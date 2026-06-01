package com.example.floppyfish;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    Context context;
    ArrayList<Card> cards;
    public RecyclerViewAdapter(Context context, ArrayList<Card> cards)
    {
        this.context = context;
        this.cards = cards;
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.nc, parent, false);
        return new RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (cards.get(position).getFlipped() == true)
            holder.text.setText(cards.get(position).getAnswer());
        else
            holder.text.setText(cards.get(position).getValue());

        holder.text.setOnClickListener(view -> {
            cards.get(position).flip();
            if (cards.get(position).getFlipped() == true)
                holder.text.setText(cards.get(position).getAnswer());
            else
                holder.text.setText(cards.get(position).getValue());
        }
        );
    }

    @Override
    public int getItemCount() {
        return cards.size() / 2;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
        }
    }
}
