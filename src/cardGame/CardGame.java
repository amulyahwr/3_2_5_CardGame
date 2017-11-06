package cardGame;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CardGame {

	private static Card[] agent;
	private static Card[] player2;
	private static Card[] player3;
	private static Deck deck;
	int[] table_cards = new int[3];
	static int round = 0;
	
	public CardGame() {
		deck = new Deck();
		agent = new Card[10];
		player2 = new Card[10];
		player3 = new Card[10];
		System.out.println("Player " + 2 + " is dealer");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CardGame c = new CardGame();

		// First distribute 5 cards
		int DisCards = 0;
		int HandCards = 0;

		DisCards = c.DistributeCards(5, DisCards, HandCards);
		HandCards += 5;

		c.Decide_Trump();

		// distribute 3 more cards
		DisCards = c.DistributeCards(3, DisCards, HandCards);
		HandCards += 3;

		// distribute 2 more cards
		DisCards = c.DistributeCards(2, DisCards, HandCards);

		System.out.println("Agent cards are " + Arrays.toString(agent));
		System.out.println("Player 2 cards are " + Arrays.toString(player2));
		System.out.println("Player 3 cards are " + Arrays.toString(player3));
		
		round = round + 1;
		c.first_round(agent, round);

		//c.getInputfromPlayers(new Card[3], 1);
		//System.out.println("Agent cards are " + Arrays.toString(agent));
		//System.out.println("Player 2 cards are " + Arrays.toString(player2));
		//System.out.println("Player 3 cards are " + Arrays.toString(player3));
	}

	/**
	 * This function randomly distribute the cards to each player
	 * 
	 * @param k
	 *            : how many cards has to be distributed
	 * @param DisCards:
	 *            how many cards from deck are distributed till now
	 * @param HandCards
	 *            : how many cards are there in each player's hand
	 * @return DisCards
	 */
	public int DistributeCards(int k, int DisCards, int HandCards) {
		Card[] hand = deck.select(k, DisCards);
		for (int i = 0; i < hand.length; i++) {
			agent[i + HandCards] = hand[i];
			DisCards++;
		}
		hand = deck.select(k, DisCards);
		for (int i = 0; i < hand.length; i++) {
			player2[i + HandCards] = hand[i];
			DisCards++;
		}
		hand = deck.select(k, DisCards);
		for (int i = 0; i < hand.length; i++) {
			player3[i + HandCards] = hand[i];
			DisCards++;
		}
		return DisCards;
	}

	public String Decide_Trump() {

		int cs = 0, cd = 0, ch = 0, cc = 0;
		for (int i = 0; i < 5; i++) {
			if (agent[i].getSuit().equals("S"))
				;
		}
		return null;
	}

	/**
	 * This will take input from players by asking the cards they want to play
	 * through scanner
	 * 
	 * @param runningCards
	 *            : 3 cards which are getting played
	 * @param turn:
	 *            specifies the turn, which player ha to play now
	 */
	public Card[] getInputfromPlayers(Card[] runningCards, int turn) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the rank (INTEGER VALUE) and color (UPPER CASE CHARACTER) you want to play");
		int rank1 = sc.nextInt();
		String suit1 = sc.next();
		Card c1 = new Card(rank1, suit1);
		for (int i = 0; i < player2.length; i++) {
			if (player2[i].getRank() == c1.getRank() && player2[i].getSuit().equals(c1.getSuit())) {
				runningCards[turn++] = c1;
				player2[i] = null;
			}
		}

		System.out.println("Please enter the rank (INTEGER VALUE) and color (UPPER CASE CHARACTER) you want to play");
		int rank2 = sc.nextInt();
		String suit2 = sc.next();
		Card c2 = new Card(rank2, suit2);
		for (int i = 0; i < player2.length; i++) {
			if (player3[i].getRank() == c2.getRank() && player3[i].getSuit().equals(c2.getSuit())) {
				runningCards[turn] = c2;
				player3[i] = null;
			}
		}
		return runningCards;
	}
	/**
	 * 
	 * @param agent
	 * @param round
	 * @return
	 */
	public Card first_round(Card[] agent, int round) {

		return priority(agent,round);

	}
	/**
	 * 
	 * @param cards_hand
	 * @param nmbr_round
	 * @return
	 */
	public Card priority(Card[] cards_hand, int nmbr_round) {
		String suit = null;
		int rank = 0;
		int prev_rank = 100;
		int idx = 0;
		
		if (nmbr_round ==1) {
		
			for (int i = 0; i < cards_hand.length; i++) {

				suit = cards_hand[i].getSuit();
				rank = cards_hand[i].getRank();

				if (rank == 14) {
					return cards_hand[i];
				}
				else if (prev_rank > rank && cards_hand[i].getSuit() != trump) {
					prev_rank = rank;
					idx = i;
				}
			}

			return cards_hand[idx];
		}
		else {
			
		}
	}

	public int winner(int[] table_cards) {

		int player_win = 0;

		return player_win;

	}
}
