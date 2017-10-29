package cardGame;

import java.util.Random;

import cardGame.Card.Suit;

/**
 * Class representing a standard 52-card deck of playing cards from which cards
 * can be selected at random.
 */
public class Deck {
	/**
	 * This will store all the 52 cards.
	 */
	private static Card[] cards;

	/**
	 * Call of constructor to initialize all the cards with possible values
	 */
	public Deck() {
		init();
	}

	/**
	 * Returns a new array containing k elements selected at random from this
	 * deck. We are using this to distribute cards randomly between players. To
	 * get cards from deck "without replacement", we swap the random element
	 * with last element to get 5 distinct objects from the array.
	 */
	public Card[] select(int k) {
		Random r = new Random();
		Card[] distribute = new Card[k];
		for (int i = 0; i < k; i++) {
			int j = r.nextInt(cards.length - 1 - i);
			distribute[i] = cards[j];
			swap(cards, 51 - i, j);
		}
		return distribute;
	}

	// exchange the element at index i with the element at index j
	public static void swap(Card[] arr, int i, int j) {
		Card temp = cards[i];
		cards[i] = cards[j];
		cards[j] = temp;
	}

	/**
	 * Initializes a new deck of 52 cards.
	 */
	private void init() {
		cards = new Card[52];
		int index = 0;
		for (int rank = 1; rank <= 13; ++rank) {
			cards[index] = new Card(rank, Suit.CLUBS);
			index += 1;
			cards[index] = new Card(rank, Suit.DIAMONDS);
			index += 1;
			cards[index] = new Card(rank, Suit.HEARTS);
			index += 1;
			cards[index] = new Card(rank, Suit.SPADES);
			index += 1;
		}

	}
}
