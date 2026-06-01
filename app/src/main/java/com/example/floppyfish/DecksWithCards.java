
package com.example.floppyfish;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

public class DecksWithCards
{
    @Embedded
    public Deck deck;

    @Relation(
        parentColumn = "deckNumber",    
        entityColumn = "deckContainer"    
    )
    public List<Flashcard> Flashcards;
}

