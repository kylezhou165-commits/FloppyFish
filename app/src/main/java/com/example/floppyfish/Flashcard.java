
package com.example.floppyfish;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Flashcard
{
    @PrimaryKey(autoGenerate = true)
    public int deckContainer;
    public int cardNumber;

    public Flashcard() {}

    public Flashcard(int deckContainer)
    {
        this.deckContainer = deckContainer;
    }
}

