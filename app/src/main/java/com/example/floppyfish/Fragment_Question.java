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
public class Fragment_Question extends Fragment {
    Button cardReturnButton;
    private Flash flash;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    Button addCard;
    MediaPlayer m;

    public Fragment_Question(MediaPlayer mp) {

        super(R.layout.notecards);
        flash = new Flash();
        m = mp;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle savedInstanceState)

    {
        return inflater.inflate(R.layout.quiz, view, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

    }



}
