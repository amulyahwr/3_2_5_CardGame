package cardGame;

import java.util.Random;

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
	public Card[] select(int k,int x) {
		Random r = new Random();
		Card[] distribute = new Card[k];
		for (int i = 0; i < k; i++) {
			int j = r.nextInt(cards.length- i-x);
			distribute[i] = cards[j];
			swap(cards, 29 - i-x, j);
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
		cards = new Card[30];
		int index = 0;
		cards[index] = new Card(7, "C");
		index += 1;
		cards[index] = new Card(7, "D");
		index += 1;
		for (int rank = 8; rank <= 14; ++rank) {
			cards[index] = new Card(rank, "C");
			index += 1;
			cards[index] = new Card(rank, "D");
			index += 1;
			cards[index] = new Card(rank, "H");
			index += 1;
			cards[index] = new Card(rank, "S");
			index += 1;
		}
		

	}
}
