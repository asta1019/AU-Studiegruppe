package exercise2;

public class playingcard {

    // final sørger for at kortet ikke kan laves om, men der kan godt oprettes flere af det samme kort
    private final Suit suit;
    private final Rank rank;

    // enum sikre at kortet har en af "værdierne" fra Rank
    // et sted at opbevare strenge/værdier
    public enum Rank {
        ace, two, three, four, five, six, seven, eight, nine, ten, jack, queen, king
    }

    public enum Suit {
        clubs, diamonds, hearts, spades
    }

    // this: det kort jeg oprtter nu får denne rank og denne suit
    public playingcard(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    // retunere ønsket output
    public String toString() {
        return "(" + rank + "," + suit + ")";
    }
}

