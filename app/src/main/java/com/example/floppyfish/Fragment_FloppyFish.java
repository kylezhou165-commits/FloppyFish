package com.example.floppyfish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.content.Context;

import android.widget.Button;


public class Fragment_FloppyFish extends Fragment {
    private GameView gameView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        gameView = new GameView(requireContext(), true);
        return gameView;
    }

}