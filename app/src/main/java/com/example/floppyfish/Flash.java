package com.example.floppyfish;
import android.os.Handler;
import android.os.Looper;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Flash {
    private ArrayList<Card> arr;
    private int pointer = 0;

    public ArrayList<Card> getCards() {
        return arr;
    }

    public Flash() {
        arr = new ArrayList<Card>();
    }

    public void add(String s, String v) {
        Card tmp = new Card(s, v);

        arr.add(tmp);
    }


    public void remove(int i) {

        Card tmp = arr.remove(i);
    }

    public Card getTop() {
        return arr.get(pointer);
    }

    public Card nextCard() {
        if (pointer < arr.size()) {
            Card tmp = arr.get(pointer);
            pointer++;
            return tmp;
        }
        return null;
    }

    public void shuffle() {
        Collections.shuffle(arr);
        pointer = 0;
    }

    public String getRandomAnswer() {
        if (arr.size() > 4)
            return arr.get((int) (Math.random() * arr.size())).getAnswer();
        else
            return "gng wtf we doing";
    }

    public int getSize() {
        return arr.size();
    }

    public static void saveCardsToDb(DeckDAO dao, int deckId, Flash flash) {
        AppExecutors.dbExecutor.execute(() ->
        {
            List<Flashcard> entities = new ArrayList<>();

            synchronized (flash) {
                for (Card c : flash.getCards()) {
                    Flashcard fc = new Flashcard();
                    fc.deckContainer = deckId;
                    fc.value = c.getValue();
                    fc.answer = c.getAnswer();
                    entities.add(fc);
                }
            }
            dao.insertFlashcards(entities);
        });
    }
    public static Flash loadSync(DeckDAO dao, int deckId) {
        List<Flashcard> dbCards = dao.getFlashcardsByDeck(deckId);

        Flash flash = new Flash();
        for (Flashcard fc : dbCards) {
            flash.add(fc.value, fc.answer);
        }
        return flash;
    }

    private static class AppExecutors {
        public static final ExecutorService dbExecutor = Executors.newSingleThreadExecutor();
    }
}

