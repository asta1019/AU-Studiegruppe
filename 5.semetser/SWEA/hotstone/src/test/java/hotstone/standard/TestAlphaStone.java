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
import hotstone.framework.Player;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

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
        Card card = new StandardCard("Dos",2 ,2 ,2);
        // When card name is Dos
        assertThat(card.getName(), is("Dos"));
        // Then ManaCost is 2, Attack is 2 and Health is 2
        assertThat(card.getManaCost(), is(2));
        assertThat(card.getAttack(), is(2));
        assertThat(card.getHealth(), is(2));
    }

    @Test
    public void shouldGiveCardUnoWithAttributes111() {
        // Given an initialized card
        Card card = new StandardCard("Uno",1,1,1);
        // When card name is Uno
        assertThat(card.getName(), is("Uno"));
        // Then ManaCost is 1 Attack is 1 and Health is 1
        assertThat(card.getManaCost(), is(1));
        assertThat(card.getAttack(), is(1));
        assertThat(card.getHealth(), is(1));
    }


//    @Test
//    public void shouldHaveUnoDosTresCardsInitially() {
//        // Given a game
//        // When game starts
//        int count = game.getHandSize(Player.FINDUS);
//        // Then Findus should have three cards
//        assertThat(count, is(3));
//
//        // When I pick card 0
//        Card card = game.getCardInHand(Player.FINDUS, 0);
//        // Then is it Tres
//        assertThat(card.getName(), is(GameConstants.TRES_CARD));
//        // When I pick card 1
//        Card card1 = game.getCardInHand(Player.FINDUS, 1);
//        // Then is it Dos
//        assertThat(card.getName(), is(GameConstants.DOS_CARD));
//        // When I pick card 2
//        Card card2 = game.getCardInHand(Player.FINDUS, 2);
//        // Then is it Uno
//        assertThat(card.getName(), is(GameConstants.UNO_CARD));
//        }
  // Example of a later, more complex, test case:
  // Card handling

  // The HotStone specs are quite insisting on how
  // the cards, drawn from the deck, are organized
  // in the hand. So when drawing the top three cards
  // from the deck (uno, dos, tres) they have to
  // be organized in the hand as
  // index 0 = tres; index 1 = dos; index 2 = uno
  // That is, a newly drawn card is 'at the top'
  // of the hand - always entered at position 0
  // and pushing the rest of the cards 1 position
  // 'down'

}
