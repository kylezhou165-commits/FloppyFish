package com.example.floppyfish;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                    Fragment_FloppyFish frag = new Fragment_FloppyFish();
                    FragmentManager a = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = a.beginTransaction();
                    fragmentTransaction.add(R.id.fragment_container_game, frag);
                    fragmentTransaction.commit();


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