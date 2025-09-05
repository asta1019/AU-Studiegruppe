Testlist for Hotstone
====

* [OK] Given an initialized game Findus is the player in turn
* [OK]  When Findus ends its turn, then it is Peddersen in turn
* [OK]  When Peddersen end his turn it is Findus in turn
* [OK] Given card Dos then it has attributes (2,2,2)
* [OK] Given card Uno then it has attributes (1,1,1)
* [OK] Given a turn ends turnNumber increase by one
* [OK] Given a game Findus has three cards
* [OK] Given a game Findus starts with the cards Tres, Dos, Uno in hand in index order 0, 1, 2.
* [OK] Given a game Peddersen starts with the cards Tres, Dos, Uno in hand in index order 0, 1, 2.
* [OK] Given a game Findus should have decksize with four cards
* [OK] Given a game Peddersen should have decksize with four cards
* [OK] When Peddersen’s turn ends, Findus’ deck decrease by one
* [OK] Given that Findus is taking his third turn, his deck should have two fewer cards than at the start of the game
* [OK] When Findus turn ends, Peddersen's deck decrease by one
* [OK] When Peddersen’s turn has ended, Findus’ hand should increase by one card
* [OK] When Findus has ended his first turn, Peddersen’s hand should increase by one card
* [OK] Given Findus’ second turn, the first card drawn from the deck is CUATRO_CARD.
* [OK] Findus’ hero has 21 health and 3 mana at game start.
* [OK] Peddersen’s hero has 21 health and 3 mana at game start.


Backlog

Play cards
* [] When Findus plays card Uno, card uno appears in the Findus' field.
* [] When a Peddersen plays card Uno, card uno appears in the Peddersen field.
* [] When a Findus plays a card Uno, his handsize should decrease by one
* When a player plays card then the other players hand does not decrease in size
* When a player plays card Uno to their field, then card Uno is removed from their hand
* When a player plays card Dos to their field, card Dos is removed from their hand
* When a player plays card Dos and card Uno already is in their field, fieldsize should be two.
* When Findus plays a card, field size should increase by one.
* When Peddersen plays a card, field size should increase by one.
* Findus’ hero has 3 mana at the start of each turn.
* Peddersen’s hero has 3 mana at the start of each turn.

Placement on field
* When card is removed from field then all other elements should shift index
* When card is removed from hand then all other elements should shift index
* When minion is removed from field then all other elements should shift index
* When a player plays card Uno at index 0 when field is empty, then card Uno is at index 0 in their field
* When a player plays card Dos at index 0 after card Uno is played, card Uno has index 1 in their field

Pay for cards
* When a player plays card Dos his mana should decrease by 2
* When a player plays card Uno his mana should decrease by 1

Constraints
* When a player plays card Tres, then card Dos cannot be played, because not enough mana
* When a players deck is empty and they attempt to get a new card, the hero loses 2 health each attempt

Minion (Card in field)
* When minon in the field then minon is active
* When a player plays a card Uno, card Uno is inactive until players next turn
* When a player plays a card Dos, card Dos is inactive until players next turn
* When a players minion attacks once, that minion gets inactive until player next turn
* When players A’s minion attacks player B’s minion, player A’s minion health is reduced by player B’s minion’s amount of health
* When a players minion has zero or less health that minion is removed from field
* When player A’s minion attacks player B’s hero, then player B’s hero’s health is reduced by player A’s minion’s attack
* When players Hero health is 0 or less, then player’s hero dies

Test
* Given Peddersen has Uno and Findus has Tres, when Tres attacks Uno, then Uno is removed from Peddersens field and Tres stays in Findus field with 2 health

Hero power
* When player gets in turn, hero power becomes active
* When player uses hero power, his hero power becomes inactive until their next turn
* When a player uses hero power, their mana will reduced by two
* Using hero power does not change board state, except mana cost and hero power status

Rules
* Findus wins after eight turns. Peddersen’s health should be zero or less 
