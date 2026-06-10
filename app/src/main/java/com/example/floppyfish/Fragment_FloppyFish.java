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
    private boolean auramode;

    public Fragment_FloppyFish(boolean aura)
    {
        auramode = aura;
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        gameView = new GameView(requireContext(), auramode);
        return gameView;
    }
    public void onGameOver() {

        if (gameView != null) {
            gameView.running = false;

            // Clean up audio
            if (gameView.mp != null) {
                gameView.mp.stop();
                gameView.mp.release();
            }
            if (gameView.mpDeath != null) {
                gameView.mpDeath.release();
            }

            gameView.getHolder().getSurface().release();
        }

        getParentFragmentManager().beginTransaction()
                .remove(this)
                .replace(android.R.id.content, new Fragment_Question())
                .commit();
    }

}