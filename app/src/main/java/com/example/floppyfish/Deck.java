
package com.example.floppyfish;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity 
public class Deck
{
  @PrimaryKey (autoGenerate = true)
  public int deckNumber; 

  public Deck() {}

  public Deck(int deckNumber)
  {
    this.deckNumber = deckNumber;
  }
}

