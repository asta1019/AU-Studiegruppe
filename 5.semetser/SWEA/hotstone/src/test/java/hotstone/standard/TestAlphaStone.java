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

/**
 * Skeleton class for AlphaStone test cases
 *
 *    This source code is from the book
 *      "Flexible, Reliable Software:
 *        Using Patterns and Agile Development"
 *      2nd Edition
 *    Author:
 *      Henrik Bærbak Christensen
 *      Department of Computer Science
 *      Aarhus University
 */

import hotstone.framework.Card;
import hotstone.framework.Hero;
import hotstone.framework.Player;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static hotstone.standard.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import utility.TestHelper;

import hotstone.framework.Game;

/** Template for your own ongoing TDD process.
 * Fill it out until you have covered all
 * requirements for the alpha stone game.
 */
public class TestAlphaStone {
  private Game game;

  /** Fixture for AlphaStone testing. */
  @BeforeEach
  public void setUp() {
      game = new StandardHotStoneGame();
  }

  // Example of an early, simple test case:
  // Turn handling
  @Test
  public void shouldHaveFindusAsFirstPlayer() {
    // Given a game, initialized in 'beforeeach' method
    // When I ask for the player in turn
    Player player = game.getPlayerInTurn();
    // Then it should be Findus
    assertThat(player, is(Player.FINDUS));

    // NB: during development you can use the
    // following 'print full game state' helper
    // function, to ensure your assumptions
    // on game state is correct.
    // TestHelper.printGameState(game);
  }

    // Turn handling
    @Test
    public void shouldHavePeddersenInTurnAfterFindusEndsTurn() {
      // Given an initialized game
      // When Findus ends its turn
      game.endTurn();
      // Then Peddersen is in turn
      assertThat(game.getPlayerInTurn(), is(Player.PEDDERSEN));
    }

    @Test
    public void shouldHaveFindusInTurnAfterPeddersenEndsTurn() {
      // Given an initialized game
      // When Findus end its turn
      game.endTurn();
      // When Peddersen ends his turn
      game.endTurn();
      // Then Findus is its turn
      assertThat(game.getPlayerInTurn(), is(Player.FINDUS));
    }

    @Test
    public void shouldGiveCardDosWithAttributes222() {
        // Given an initialized card
        Card card = new StandardCard(DOS_CARD,2 ,2 ,2);
        // When card name is Dos
        assertThat(card.getName(), is(DOS_CARD));
        // Then ManaCost is 2, Attack is 2 and Health is 2
        assertThat(card.getManaCost(), is(2));
        assertThat(card.getAttack(), is(2));
        assertThat(card.getHealth(), is(2));
    }

    @Test
    public void shouldGiveCardUnoWithAttributes111() {
        // Given an initialized card
        Card card = new StandardCard(UNO_CARD,1,1,1);
        // When card name is Uno
        assertThat(card.getName(), is(UNO_CARD));
        // Then ManaCost is 1 Attack is 1 and Health is 1
        assertThat(card.getManaCost(), is(1));
        assertThat(card.getAttack(), is(1));
        assertThat(card.getHealth(), is(1));
    }

    @Test
    public void shouldIncraseEachTurnByOne() {
      // Given initialized game
      // When take turn
      int turnNumber = game.getTurnNumber();
      game.endTurn();
      int newTurnNumber = game.getTurnNumber();
      // Then take turn increase by one
      assertThat(turnNumber, is(newTurnNumber - 1));
    }

//    @Test
//    public void shouldFindusGetThreeInHandInFirstRound() {
//        // Given initialized game
//        // When game begins
//        if (game.getTurnNumber() < 2) {
//            // Then Findus has 3 cards
//            assertThat(game.getHandSize(Player.FINDUS), is(3));
//        }
//    }

    @Test
    public void shouldFindusGetThreeInHandInFirstRound() {
        // Given initialized game
        // When game begins
        // Then Findus has 3 cards
        int count = game.getHandSize(Player.FINDUS);
        assertThat(count, is(3));
    }

    @Test
    public void shouldFindusHaveUnoDosTresCardsInitiallyInIndexOrder() {
        // Given initialized game
        // When game starts
        // Then Findus should have Uno, Dos, Tres in hand
        assertThat(game.getCardInHand(Player.FINDUS, 0).getName(), is(TRES_CARD));
        assertThat(game.getCardInHand(Player.FINDUS, 1).getName(), is(DOS_CARD));
        assertThat(game.getCardInHand(Player.FINDUS, 2).getName(), is(UNO_CARD));
    }

    @Test
    public void shouldPeddersenHaveUnoDosTresCardsInitiallyInIndexOrder() {
        // Given initialized game
        // When game starts
        // Then Findus should have Uno, Dos, Tres in hand
        assertThat(game.getCardInHand(Player.PEDDERSEN, 0).getName(), is(TRES_CARD));
        assertThat(game.getCardInHand(Player.PEDDERSEN, 1).getName(), is(DOS_CARD));
        assertThat(game.getCardInHand(Player.PEDDERSEN, 2).getName(), is(UNO_CARD));
    }

    @Test
    public void shouldFindusDeckincludeFourCardsAtGameStarts() {
        // Given initialized game
        // When game starts
        // Then deck should contain four cards Cuatro, Cinco, Seis, Siete
        assertThat(game.getDeckSize(Player.FINDUS), is(4));
    }

    @Test
    public void shouldPeddersenDeckincludeFourCardsAtGameStarts() {
        // Given initialized game
        // When game starts
        // Then deck should contain four cards Cuatro, Cinco, Seis, Siete
        assertThat(game.getDeckSize(Player.PEDDERSEN), is(4));
    }

    @Test
    public void shouldFindusDeckDecreaseFirstNewTurn() {
        // Given initialized game
        // When Findus new turn, his deck decrease by one
        int deckSize = game.getDeckSize(Player.FINDUS);
        // Findus turn ends
        game.endTurn();
        // Peddersen turn ends
        game.endTurn();
        // Then Findus deck decrease by one
        assertThat(game.getDeckSize(Player.FINDUS), is(deckSize - 1));

    }

    @Test
    public void shouldFindusDeckDecreaseSecondNewTurn() {
        // Given initialized game
        // When Findus new turn, his deck decrease by one
        int deckSize = game.getDeckSize(Player.FINDUS);
        // Findus turn ends
        game.endTurn();
        // Peddersen turn ends
        game.endTurn();
        game.endTurn();
        game.endTurn();
        assertThat(game.getDeckSize(Player.FINDUS), is(deckSize - 2));

    }

    @Test
    public void shouldPeddersenDeckDecreaseEachNewTurn() {
        // Given initialized game
        // When Peddersen new turn, his deck decrease by one
        int deckSize = game.getDeckSize(Player.PEDDERSEN);
        // Findus turn ends
        game.endTurn();
        // Peddersen turn ends
        game.endTurn();
        // Findus turn ends
        game.endTurn();
        // Then Peddersen deck decrease by one
        assertThat(game.getDeckSize(Player.PEDDERSEN), is(deckSize - 1));
    }

    @Test
    public void shouldFindusIncreaseHandSizeByOneInTurn2() {
        // Given initialized game
        int oldHandSize = game.getHandSize(Player.FINDUS);
        // When Turn number is 2
        game.endTurn();
        game.endTurn();
        // Then Findus hand sizes increases by 1
        int newHandSize = game.getHandSize(Player.FINDUS);
        assertThat(newHandSize, is(oldHandSize + 1));
    }

    @Test
    public void shouldPeddersenIncreaseHandSizeByOneInTurn2() {
        // Given initialized game
        int oldHandSize = game.getHandSize(Player.PEDDERSEN);
        // When Turn number is 3
        game.endTurn();
        game.endTurn();
        game.endTurn();
        // Then Peddersens hand sizes increases by 1
        int newHandSize = game.getHandSize(Player.PEDDERSEN);
        assertThat(newHandSize, is(oldHandSize + 1));
    }

    @Test
    public void shouldFirstCardDrawnFromDeckBeCuatro() {
        // Given initialized game
        // When Turn number is 2
        game.endTurn();
        game.endTurn();
        // Then Findus should have Cuatro card at index 0
        assertThat(game.getCardInHand(Player.FINDUS, 0).getName(), is(CUATRO_CARD));
    }

    @Test
    public void shouldFindusHaveHeroHasHealth21AndMana3() {
        // Given initialized game
        // When player gets hero
        // Then hero has 21 health and 3 mana
        assertThat(game.getHero(Player.FINDUS).getType(), is(BABY_HERO_TYPE));
    }

    @Test
    public void shouldPeddersenHaveHeroHasHealth21AndMana3() {
        // Given initialized game
        // When player gets hero
        // Then hero has 21 health and 3 mana
        assertThat(game.getHero(Player.PEDDERSEN).getType(), is(BABY_HERO_TYPE));
    }

    @Test
    public void shouldFindusHeroHave3ManaEachTurn() {
        // Given initialized game
        // When Fidus starts each turn
        // Then Findus hero' mana' should be 3
        game.endTurn();
        game.endTurn();
        assertThat(game.getHero(Player.FINDUS).getMana(), is(3));
    }

    @Test
    public void shouldPeddersenHeroHave3ManaEachTurn() {
        // Given initialized game
        // When Fidus starts each turn
        // Then Findus hero' mana' should be 3
        game.endTurn();
        game.endTurn();
        game.endTurn();
        assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(3));
    }

    @Test
    public void shouldFindusFieldSizeBeSizeZeroAtBegining(){
      // Given initialized game
      // When game begins
      // Then Fundus field size should be 0
      assertThat(game.getFieldSize(Player.FINDUS), is(0));

    }

    @Test
    public void shouldPeddersenFieldSizeBeSizeZeroAtBegining(){
        // Given initialized game
        // When game begins
        // Then Fundus field size should be 0
        assertThat(game.getFieldSize(Player.PEDDERSEN), is(0));
    }


//    @Test
//    public void shouldFindusHeroHave2ManaAfterPlayedCardUno() {
//        // Given initialized game
//        // When Fidus Playes Card Uno
//        game.getCardInField(Player.FINDUS, 0)
//        // Then Findus hero' mana' should be 2
//        assertThat(game.getHero(Player.FINDUS).getMana(), is(2));
//    }
}
