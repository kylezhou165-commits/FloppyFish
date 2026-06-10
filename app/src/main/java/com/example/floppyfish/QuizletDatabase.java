package com.example.floppyfish;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Deck.class, Flashcard.class}, version = 1)
public abstract class QuizletDatabase extends RoomDatabase {
    public abstract DeckDAO deckDao();
    private static QuizletDatabase INSTANCE;

    public static QuizletDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuizletDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuizletDatabase.class, "quizlet-db").build();
                }
            }
        }
        return INSTANCE;
    }
}

