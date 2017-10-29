package cardGame;

public class CardGameTest {

	public static void main(String[] args) {
		Deck deck = new Deck();
	    Card[] hand = deck.select(5);
	    System.out.println(Card.toString(hand));
	}
}
