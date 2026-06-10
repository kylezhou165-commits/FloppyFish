package com.example.floppyfish;



import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
public class Fragment_Question extends Fragment {
    private Flash flash;
    Button A;

    Button B;

    Button C;
    Button D;

    Button backToGame;

    public Fragment_Question() {

        super(R.layout.quiz);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quiz, container, false);

        A = view.findViewById(R.id.button);
        B = view.findViewById(R.id.button2);
        C = view.findViewById(R.id.button3);
        D = view.findViewById(R.id.button4);
        backToGame = view.findViewById(R.id.button5);
        backToGame.setOnClickListener(v -> getParentFragmentManager().beginTransaction().remove(this).commit());
        new Thread(() -> {
            QuizletDatabase db = QuizletDatabase.getDatabase(requireContext());
            this.flash = Flash.loadSync(db.deckDao(), 1);
            new Handler(Looper.getMainLooper()).post(() -> {
                if (this.flash == null || this.flash.getCards().isEmpty()) return;
                int randomIndex = (int) (Math.random() * this.flash.getCards().size());
                Card correctCard = this.flash.getCards().get(randomIndex);
                ArrayList<String> options = new ArrayList<>();
                options.add(correctCard.answer);
                while (options.size() < 4) {
                    String randomWrong = this.flash.getCards().get((int)(Math.random() * this.flash.getCards().size())).answer;
                    if (!options.contains(randomWrong)) options.add(randomWrong);
                }
                java.util.Collections.shuffle(options);
                Button[] buttons = {A, B, C, D};
                for (int i = 0; i < 4; i++) {
                    buttons[i].setText(options.get(i));
                    final String answer = options.get(i);
                    buttons[i].setOnClickListener(v -> {
                        String msg = answer.equals(correctCard.answer) ? "Right!" : "Wrong!";
                        com.google.android.material.snackbar.Snackbar.make(view, msg, 1000).show();
                    });
                }
            });
        }).start();
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

    }



}
