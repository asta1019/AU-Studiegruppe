/*
 * Copyright (C) 2022 - 2025. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package hotstone.standard;

import hotstone.framework.*;

import java.util.ArrayList;

import static hotstone.standard.GameConstants.*;

/** This is the 'temporary test stub' in TDD
 * terms: the initial empty but compilable implementation
 * of the game interface.
 * .
 * It already includes a bit of FAKE-IT code for the first
 * test case about hand management and player in turn.
 * .
 * Start solving the AlphaStone exercise by
 * following the TDD rhythm: pick a one-step-test
 * from your test list, quickly add a test,
 * run it to see it fail, and then modify this
 * implementing class (and supporting classes)
 * to make your test case run. Refactor and repeat.
 * .
 * While this is the implementation of Game for
 * the AlphaStone game, you will constantly
 * refactor it over the course of the exercises
 * to become the 'core implementation' which will
 * enable a lot of game variants. This is also
 * why it is not called 'AlphaGame'.
 */

public class StandardHotStoneGame implements Game {
    private Player currentPlayer = Player.FINDUS;
    private int turnNumber = 0;
    // private int totalMana = 3;

    // Create hands
    private ArrayList<Card> FindusHand = new ArrayList<>();
    private ArrayList<Card> PeddersenHand = new ArrayList<>();
    private ArrayList<Card> FindusDeck = new ArrayList<>();
    private ArrayList<Card> PeddersenDeck = new ArrayList<>();

    // Create cards
    private Card Uno = new StandardCard(UNO_CARD,1 ,1 ,1);
    private Card Dos = new StandardCard(DOS_CARD,2 ,2 ,2);
    private Card Tres = new StandardCard(TRES_CARD,3 ,3 ,3);
    private Card Cuatro = new StandardCard(CUATRO_CARD,2 ,3 ,1);
    private Card Cinco = new StandardCard(CINCO_CARD,3 ,5 ,1);
    private Card Seis = new StandardCard(SEIS_CARD,2 ,1 ,3);
    private Card Siete = new StandardCard(SIETE_CARD,3 ,2 ,4);

    // Create Hero
    private Hero FindusHeroBaby = new StandardHero(BABY_HERO_TYPE, 3,21);
    private Hero PeddersenHeroBaby = new StandardHero(BABY_HERO_TYPE, 3,21);

    // Create fields
    private ArrayList<Card> FindusField = new ArrayList<>();
    private ArrayList<Card> PeddersenField = new ArrayList<>();

    public StandardHotStoneGame() {
        // Add cards to Findus hand
        FindusHand.add(Tres);
        FindusHand.add(Dos);
        FindusHand.add(Uno);

        // Add cards to Peddersen hand
        PeddersenHand.add(Tres);
        PeddersenHand.add(Dos);
        PeddersenHand.add(Uno);

        // Add cards to Findus' deck
        FindusDeck.add(Cuatro);
        FindusDeck.add(Cinco);
        FindusDeck.add(Seis);
        FindusDeck.add(Siete);

        // Add cards to Peddersen' deck
        PeddersenDeck.add(Cuatro);
        PeddersenDeck.add(Cinco);
        PeddersenDeck.add(Seis);
        PeddersenDeck.add(Siete);

    }


    @Override
  public Player getPlayerInTurn() {
        return currentPlayer;
  }

  @Override
  public Hero getHero(Player who) {
        if (who == Player.FINDUS) {
            return FindusHeroBaby;
        } else {
            return PeddersenHeroBaby;
        }
  }

  @Override
  public Player getWinner() {
    return null;
  }

  @Override
  public int getTurnNumber() {;
      return turnNumber;
  }

    @Override
    public int getDeckSize(Player who) {
        if (who == Player.FINDUS) {
            return FindusDeck.size();
        } else {
            return PeddersenDeck.size();
        }
    }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
      if (who == Player.FINDUS) {
          return FindusHand.get(indexInHand);
      } else {
          return PeddersenHand.get(indexInHand);
        }
      }

  @Override
  public Iterable<? extends Card> getHand(Player who) {
      if (who == Player.FINDUS) {
          return FindusHand;
      } else {
          return PeddersenHand;
      }
  }

  @Override
  public int getHandSize(Player who) {
      if (who == Player.FINDUS) {
          return FindusHand.size();
      } else {
            return PeddersenHand.size();
      }
  }

  @Override
  public Card getCardInField(Player who, int indexInField) {
    if (who == Player.FINDUS) {
        return FindusField.get(indexInField);
    } else {
        return PeddersenField.get(indexInField);
    }
  }

  @Override
  public Iterable<? extends Card> getField(Player who) {
    return null;
  }

  @Override
  public int getFieldSize(Player who) {
        if (who == Player.FINDUS){
            return FindusField.size();
        } else {
            return PeddersenField.size();
        }
  }

  @Override
  public void endTurn() {
        // switch current player form Findus to Peddersen
      if (currentPlayer == Player.FINDUS) {
          currentPlayer = Player.PEDDERSEN;
          // When tunnumber more than 1 Peddersen gets a card in hand
          if (turnNumber >= 2) {
              Card PeddersenRemovedCard = PeddersenDeck.remove(0);
              PeddersenHand.add(0, PeddersenRemovedCard);
              //totalMana = 3;
          }
      } else {
          // switch current player form Peddersen to Findus
          currentPlayer = Player.FINDUS;
              // Findus next gets a card in hand
              Card FindusRemovedCard = FindusDeck.remove(0);
              FindusHand.add(0,FindusRemovedCard);
              // Findus hero will get 3 manas each round
              //totalMana = 3;
      }
      // everytime turn end turnnumber increase by one
      turnNumber += 1;

  }

  @Override
  public Status playCard(Player who, Card card, int atIndex) {
//        if (who == Player.FINDUS) {
//            //int cardIndex = FindusHand.indexOf(card);
//            //Card FindusRemovedCard = FindusHand.remove(2);
//            FindusField.add(atIndex, getCardInHand(Player.FINDUS, 2));
//        } else {
//            PeddersenField.add(atIndex, card);
//        }
//        return Status.OK;
      return null;
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    return null;
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    return null;
  }

  @Override
  public Status usePower(Player who) {
    return null;
  }
}
