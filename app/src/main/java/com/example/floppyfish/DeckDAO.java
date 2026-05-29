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
  public List<DecksWithCards> getDecksWithCards();

  @Transaction
  @Query("SELECT * FROM Flashcard where deckContainer = :deckNumber")
  public List<DecksWithCards> getFlashcardsByDeck(int deckNumber);

  @Insert
  int insertDeck(Deck deck);

  @Insert
  void insertFlashcards(List<Flashcard> flashcards);

  @Update
  void updateDeck(Deck deck);

  @Update
  void updateFlashcard(List<Flashcard> flashcards);

  @Delete
  void deleteDeck(Deck deck);
}
