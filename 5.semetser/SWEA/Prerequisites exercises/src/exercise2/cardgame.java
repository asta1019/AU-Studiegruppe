package exercise2;

public class cardgame {
    public static void main(String[] args) {

        // oprettelse af kort 1 og kort 2
        playingcard card1 = new playingcard(playingcard.Rank.two, playingcard.Suit.hearts);
        playingcard card2 = new playingcard(playingcard.Rank.two, playingcard.Suit.hearts);

        // print kort
        System.out.println("card1: " + card1.toString());
        System.out.println("card2: " + card2.toString());

    }
}
