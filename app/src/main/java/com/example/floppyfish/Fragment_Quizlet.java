package com.example.floppyfish;



import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.content.Context;

import android.widget.Button;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class Fragment_Quizlet extends Fragment {
    Button cardReturnButton;
    private Flash flash;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    Button addCard;
    MediaPlayer m;
    final int deckId = 1;

    public Fragment_Quizlet(MediaPlayer mp) {

        super(R.layout.notecards);
        flash = new Flash();
        m = mp;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notecards, view, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = getContext();
        cardSetUp();
        recyclerView = view.findViewById(R.id.notelist);
        adapter = new RecyclerViewAdapter(context, flash);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        cardReturnButton = view.findViewById(R.id.returnB);
        cardReturnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                QuizletDatabase db = QuizletDatabase.getDatabase(requireContext());
                Flash.saveCardsToDb(db.deckDao(), deckId, flash);
                getParentFragmentManager().beginTransaction()
                        .remove(Fragment_Quizlet.this)
                        .commit();
            }
        });
        addCard = view.findViewById(R.id.addButton);
        addCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                flash.add("", "");
                adapter.notifyDataSetChanged();
            }
        });
    }


    private void cardSetUp() {
        for (int i = 0; i < 2; i++) {
            flash.add("", "");
        }
    }
}



