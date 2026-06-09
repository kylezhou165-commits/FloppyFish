package com.example.floppyfish;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    Context context;
    Flash flash;
    public RecyclerViewAdapter(Context context, Flash flash)
    {
        this.context = context;
        this.flash = flash;
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.nc, parent, false);
        return new RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        Card currentCard = flash.getCards().get(position);
        holder.front.setText(currentCard.getValue());
        holder.front.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    currentCard.setValue(holder.front.getText().toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return flash.getCards().size() / 2;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        EditText front;
        EditText back;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            front = itemView.findViewById(R.id.front);
            back = itemView.findViewById(R.id.back);
        }
    }
}
