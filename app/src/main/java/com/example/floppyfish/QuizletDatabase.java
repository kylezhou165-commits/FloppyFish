package com.example.floppyfish;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Deck.class, Flashcard.class}, version = 1)
public abstract class QuizletDatabase extends RoomDatabase {
    public abstract DeckDAO deckDao();
}

