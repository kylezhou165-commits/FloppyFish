package com.example.floppyfish;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import java.util.List;

@Dao

public interface DeckDAO
{
  @Transaction
  @Query("SELECT * FROM Deck")
  List<DecksWithCards> getDecksWithCards();

  @Query("SELECT * FROM Flashcard WHERE deckContainer = :deckNumber")
  List<Flashcard> getFlashcardsByDeck(int deckNumber);

  @Insert
  long insertDeck(Deck deck);

  @Insert
  void insertFlashcards(List<Flashcard> flashcards);

  @Update
  void updateDeck(Deck deck);

  @Update
  void updateFlashcards(List<Flashcard> flashcards);

  @Delete
  void deleteDeck(Deck deck);
}
