package com.example.floppyfish;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Card> cd = new ArrayList<>();
    Button cardsButton;
    Button gameButton;
    Button cardReturnButton;

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

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
            recyclerView = findViewById(R.id.notelist);
            adapter = new RecyclerViewAdapter(this, cd);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            cardsButton = findViewById(R.id.cardb);
            cardReturnButton = findViewById(R.id.returnB);

            cardsButton.setOnClickListener(view -> setContentView(R.layout.notecards));

            cardReturnButton.setOnClickListener(view -> setContentView(R.layout.activity_main));

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
            cd.add(new Card(a, b));
        }
    }


}