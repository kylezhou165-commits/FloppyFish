package com.example.floppyfish;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Flash flash = new Flash();
    Button cardsButton;
    Button gameButton;
    private MediaPlayer mp;

    boolean firstTime = true;

    ImageButton auraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            cardSetUp();
            gameButton = findViewById(R.id.gameb);
            cardsButton = findViewById(R.id.cardb);
            auraButton = findViewById(R.id.tempfish);

            mp = new MediaPlayer();
            mp.setAudioAttributes(
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
            );

            mp = MediaPlayer.create(this, R.raw.menue);

            mp.setLooping(true);
            if(firstTime) {
                mp.start();
            }

            firstTime = false;
            auraButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment_FloppyFish frag = new Fragment_FloppyFish(true);
                    FragmentManager a = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = a.beginTransaction();
                    fragmentTransaction.add(R.id.fragment_container_game, frag);
                    fragmentTransaction.commit();
                    mp.pause();
                    mp.release();
                }
            });

            cardsButton.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Fragment_Quizlet frag = new Fragment_Quizlet();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.fragment_container_menu, frag);
                    fragmentTransaction.commit();
                }
            });
            gameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Fragment_FloppyFish frag = new Fragment_FloppyFish(false);
                    FragmentManager a = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = a.beginTransaction();
                    fragmentTransaction.add(R.id.fragment_container_game, frag);
                    fragmentTransaction.commit();
                    mp.pause();
                    mp.release();

                }
            });
            return insets;
        });
    }


    private void cardSetUp()
    {
        String[] defs = getResources().getStringArray(R.array.card_defs);
        String[] anses = getResources().getStringArray(R.array.card_anses);

        for(int i = 0; i < defs.length; i++)
        {
            String a = defs[i];
            String b = anses[i];
            flash.add(a,b);
        }
    }


}