package cardGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class CardGame {

	private static Card[] agent;
	private static Card[] player2;
	private static Card[] player3;
	private static Deck deck;
	static Card[] runningCards = new Card[3];
	static int round = 0;
	private static String trump;
	static int nextTurn = 0;
	static HashMap<String, ArrayList<Integer>> playedCards = new HashMap<>();
	static Map <String,Integer> trumpSuit = new HashMap<String,Integer>();
	static int[] trickCount= new int[3];

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
		
		//Set trump map
		trumpSuit.put("H", 0);
		trumpSuit.put("C", 0);
		trumpSuit.put("D", 0);
		trumpSuit.put("S", 0);
		
		trump = c.Decide_Trump();
			
		System.out.println("Trump is: "+trump);

		// distribute 3 more cards
		DisCards = c.DistributeCards(3, DisCards, HandCards);
		HandCards += 3;

		// distribute 2 more cards
		DisCards = c.DistributeCards(2, DisCards, HandCards);

		playedCards.put("H", new ArrayList<Integer>());
		playedCards.put("C", new ArrayList<Integer>());
		playedCards.put("D", new ArrayList<Integer>());
		playedCards.put("S", new ArrayList<Integer>());

		System.out.println("Agent cards are " + Arrays.toString(agent));
		System.out.println("Player 2 cards are " + Arrays.toString(player2));
		System.out.println("Player 3 cards are " + Arrays.toString(player3));

		round = round + 1;

		while (round <= 10) {

			runningCards = new Card[3];

			if (round == 1 || nextTurn == 0) {
				runningCards[0] = c.first_round();
				System.out.println("Agent played " + runningCards[0]);

				c.getInputfromPlayers(1);
				System.out.println("Agent played " + runningCards[0]);
				System.out.println("Player 1 played " + runningCards[1]);

				c.getInputfromPlayers(2);

			} else if (nextTurn == 1) {

				c.getInputfromPlayers(1);
				System.out.println("Player 1 played " + runningCards[1]);

				c.getInputfromPlayers(2);
				System.out.println("Player 2 played " + runningCards[2]);

				runningCards[0] = c.next_round();
				System.out.println("Agent played " + runningCards[0]);

			} else {
				// nextTurn==2

				c.getInputfromPlayers(2);
				System.out.println("Player 2 played " + runningCards[2]);

				runningCards[0] = c.next_round();
				System.out.println("Agent played " + runningCards[0]);

				c.getInputfromPlayers(1);
				System.out.println("Player 1 played " + runningCards[1]);

			}

			System.out.println(Arrays.toString(runningCards));

			System.out.println("Agent cards are " + Arrays.toString(agent));
			System.out.println("Player 2 cards are " + Arrays.toString(player2));
			System.out.println("Player 3 cards are " + Arrays.toString(player3));
			nextTurn = c.winner();
			System.out.println("The winner is:" + nextTurn);
			

			for (Card x : runningCards) {
				playedCards.get(x.getSuit()).add(x.getRank());
			}

			round++;
		}
		
		System.out.println("The trick counts are: "+ Arrays.toString(trickCount));

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

	/**
	 * returns and assigns value to the global Trump variable
	 */
	public String Decide_Trump() {
		int count[] = new int[4];
		//
		for (int i = 0; i < 5; i++) {
			if (agent[i].getSuit().equals("S"))
				count[0]++;
			else if (agent[i].getSuit().equals("H"))
				count[1]++;
			else if (agent[i].getSuit().equals("C"))
				count[2]++;
			else
				count[3]++;
		}

		// find max value
		int max = 0;
		for (int i = 0; i < 4; i++) {
			if (count[i] >= max)
				max = i;
		}

		if (count[max] != 2) {
			switch (max) {
			case 0:
				trump = "S"; break;
			case 1:
				trump = "H"; break;
			case 2:
				trump = "C"; break;
			case 3:
				trump = "D";
			}
		}
		// if max value=2, then consider the values of cards
		else {
			// fetch index of count==2
			int i1 = 0, i2 = 0;
			boolean f = false;
			for (int i = 0; i < 4; i++) {
				if (count[i] == 2 && !f) {
					i1 = i;
					f = true;
				} else if (f == false)
					i2 = i;
			}
			// Compare the values of cards
			String[] Suits = { "S", "H", "C", "D" };
			int val1 = 0, val2 = 0;
			for (int k = 0; k < 5; k++) {
				if (agent[k].getSuit().equals(Suits[i1]))
					val1 += agent[k].getRank();
				else if (agent[k].getSuit().equals(Suits[i2]))
					val2 += agent[k].getRank();
			}
			if (val1 >= val2)
				trump = Suits[i1];
			else
				trump = Suits[i2];
		}

		return trump;
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
	public Card[] getInputfromPlayers(int turn) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the rank (INTEGER VALUE) and color (UPPER CASE CHARACTER) you want to play");
		int rank1 = 0;
		if (sc.hasNextInt()) {
			rank1 = sc.nextInt();
		} else {
			String StrRank = sc.next();
			rank1 = StrRank.equalsIgnoreCase("Q") ? 12
					: StrRank.equalsIgnoreCase("J") ? 11 : StrRank.equalsIgnoreCase("K") ? 13 : 14;
		}
		String suit1 = sc.next();
		Card c1 = new Card(rank1, suit1);
		Card[] arr = null;
		if (turn == 1)
			arr = player2;
		else if (turn == 2)
			arr = player3;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i]!=null && arr[i].getRank() == c1.getRank() && arr[i].getSuit().equals(c1.getSuit())) {
				runningCards[turn] = c1;
				arr[i] = null;
			}
		}
		if (turn == 1)
			player2 = arr;
		else if (turn == 2)
			player3 = arr;

		// System.out.println("Please enter the rank (INTEGER VALUE) and color (UPPER
		// CASE CHARACTER) you want to play");
		// int rank2 = sc.nextInt();
		// String suit2 = sc.next();
		// Card c2 = new Card(rank2, suit2);
		// for (int i = 0; i < player2.length; i++) {
		// if (player3[i].getRank() == c2.getRank() &&
		// player3[i].getSuit().equals(c2.getSuit())) {
		// runningCards[turn] = c2;
		// player3[i] = null;
		// }
		// }
		return runningCards;
	}

	/**
	 * 
	 * @param agent
	 * @param round
	 * @return
	 */
	public Card first_round() {

		return priority();

	}

	public Card next_round() {

		return priority();

	}

	/**
	 * 
	 * @param cards_hand
	 * @param nmbr_round
	 * @return
	 */
	public Card priority() {
		String suit = null;
		int rank = 0;
		int prev_rank = 100;
		int idx = 0;
		int nmbr_round = round;
		Card card_played = null;

		if (nmbr_round == 1) {

			for (int i = 0; i < agent.length; i++) {

				suit = agent[i].getSuit();
				rank = agent[i].getRank();

				if (rank == 14) {
					card_played = agent[i];
					agent[i] = null;
					return card_played;
				} else if (prev_rank > rank && agent[i].getSuit() != trump) {
					prev_rank = rank;
					idx = i;
				}
			}
			card_played = agent[idx];
			agent[idx] = null;
			return card_played;
		} else if (nextTurn == 1){

			// fetch the suite being played
			suit = runningCards[nextTurn].getSuit();
			ArrayList<Integer> availRank = new ArrayList<Integer>();
			ArrayList<Integer> TrumpList = new ArrayList<Integer>();
			ArrayList<Integer> RemCards = new ArrayList<Integer>();

			for (int i = 0; i < agent.length; i++) {
				if (agent[i] != null && agent[i].getSuit().equals(suit))
					availRank.add(agent[i].getRank());

				else if (agent[i] != null && agent[i].getSuit().equals(trump)) {
					TrumpList.add(agent[i].getRank());
				} else if (agent[i] != null)
					RemCards.add(agent[i].getRank());
			}
			Collections.sort(availRank);
			Collections.sort(TrumpList);
			Collections.sort(RemCards);

			int Cardj = 0;
			Card x = null;

			if (suit.equals(runningCards[2].getSuit())) {
				int max = Math.max(runningCards[1].getRank(), runningCards[2].getRank());

				// if suit card is available
				if (!availRank.isEmpty()) {
					for (int j : availRank) {
						if (j > max) {
							Cardj = j;
							break;
						}
					}
					
					if(Cardj!=0)
						x = new Card(Cardj, suit);
					else {
						Collections.sort(availRank);
						 x= new Card(availRank.get(0), suit);
					}
				}

				// if suit is not available but trump is available
				else if (!TrumpList.isEmpty()) {
					x = new Card(TrumpList.get(0), trump);
				}

				// if suit is not available but trump is also not available
				else {
					int mincard=RemCards.get(0);
					for (int i = 0; i < agent.length; i++) {
						if (agent[i]!=null && agent[i].getRank() == mincard) {
							x = agent[i];
							break;
						}
					}
				}
				

			}
			// not same suit
			else {
				// 2nd player has trump
				if (runningCards[2].getSuit().equals(trump)) {
					if (!availRank.isEmpty()) {
						x = new Card(availRank.get(0), suit);
					}
					// agent doesn't have suit but has trump
					else if (!TrumpList.isEmpty()) {
						for (int j : TrumpList) {
							if (j > runningCards[2].getRank()) {
								Cardj = j;
								break;
							}
						}
						x = new Card(Cardj, trump);
					}
					// agent doesn't have suit and trump
					else {
						int mincard=RemCards.get(0);
						for (int i = 0; i < agent.length; i++) {
							if (agent[i]!=null && agent[i].getRank() == mincard) {
								x = agent[i];
								break;
							}
						}
					}

				}

				// 2nd player doesn't have trump
				else {
					// agent has suit card
					if (!availRank.isEmpty()) {
						for (int j : availRank) {
							if (j > runningCards[1].getRank()) {
								Cardj = j;
								break;
							}
						}
						x = new Card(Cardj, suit);
					}

					// agent doesn't have suit card but has trump
					else if (!TrumpList.isEmpty()) {
						x = new Card(TrumpList.get(0), trump);
					}

					else {
						int mincard=RemCards.get(0);
						for (int i = 0; i < agent.length; i++) {
							if (agent[i]!=null && agent[i].getRank() == mincard) {
								x = agent[i];
								break;
							}
						}
					}
				}

			}
			// make null
			for (int i = 0; i < agent.length; i++) {
				if (agent[i]!=null && agent[i].getRank() == x.getRank() && agent[i].getSuit().equals(x.getSuit())) {
					agent[i] = null;
					break;
				}
			}
			return x;
		}
		
		else if(nextTurn == 2) {
			suit = runningCards[nextTurn].getSuit();
			ArrayList<Integer> availRank = new ArrayList<Integer>();
			ArrayList<Integer> TrumpList = new ArrayList<Integer>();
			ArrayList<Integer> RemCards = new ArrayList<Integer>();
			
			for (int i = 0; i < agent.length; i++) {
				if (agent[i] != null && agent[i].getSuit().equals(suit))
					availRank.add(agent[i].getRank());
				else if (agent[i] != null && agent[i].getSuit().equals(trump)) {
					TrumpList.add(agent[i].getRank());
				} else if (agent[i] != null)
					RemCards.add(agent[i].getRank());
			}
			Collections.sort(availRank);
			Collections.sort(TrumpList);
			Collections.sort(RemCards);
			
			int Cardj = 0;
			Card x = null;
			
			if (!availRank.isEmpty()) {
				for (int j : availRank) {
					if (j > runningCards[nextTurn].getRank()) {
						Cardj = j;
						break;
					}
				}
				if(Cardj!=0)
					x = new Card(Cardj, suit);
				else {
					Collections.sort(availRank);
					 x= new Card(availRank.get(0), suit);
				}
			}

			// if suit is not available but trump is available
			else if (!TrumpList.isEmpty()) {
				x = new Card(TrumpList.get(0), trump);
			}

			// if suit is not available but trump is also not available
			else {
				int mincard=RemCards.get(0);
				for (int i = 0; i < agent.length; i++) {
					if (agent[i]!=null && agent[i].getRank() == mincard) {
						x = agent[i];
						break;
					}
				}
			}
			// make null
			for (int i = 0; i < agent.length; i++) {
				if (agent[i]!=null && agent[i].getRank() == x.getRank() && agent[i].getSuit().equals(x.getSuit())) {
					agent[i] = null;
					break;
				}
			}
			return x;
		}

		else {
			// agent starts round
			
			Map <String,ArrayList<Integer>> agentCards = new HashMap<String,ArrayList<Integer>>();
			agentCards.put("H", new ArrayList<Integer>());
			agentCards.put("C", new ArrayList<Integer>());
			agentCards.put("D", new ArrayList<Integer>());
			agentCards.put("S", new ArrayList<Integer>());
			ArrayList<Card> randomCard = new ArrayList<Card>();
			Card x=null, y= null;
			
			for (int i = 0; i < agent.length; i++) {
				if (agent[i] != null)
					agentCards.get(agent[i].getSuit()).add(agent[i].getRank());
			}
			
			for (String key: trumpSuit.keySet()) {
				int value = trumpSuit.get(key);
				
				ArrayList<Integer> playableCards = agentCards.get(key);
				Collections.sort(playableCards);
				
				if (value == 0 && !playableCards.isEmpty()) {
					
					ArrayList<Integer> subplayedCards = playedCards.get(key);
					
					Collections.sort(playableCards);
					Collections.sort(subplayedCards);
					
					randomCard.add(new Card(playableCards.get(0),key));
					
					int agentHighest = playableCards.get(playableCards.size()-1);
					boolean flag=true; 
					for (int i = agentHighest + 1;i<=14;i++) {
						if (!subplayedCards.contains(i)) {
							flag=false;
							break;
						}
					}
					if(flag==true) {
						x = new Card(agentHighest,key);
						// make null
						for (int i = 0; i < agent.length; i++) {
							if (agent[i]!=null && agent[i].getRank() == x.getRank() && agent[i].getSuit().equals(x.getSuit())) {
								agent[i] = null;
								break;
							}
						}
						return x;
					}
					
				}
				else if(!playableCards.isEmpty()) {
						y= new Card(playableCards.get(0),key);
				}
				
			}
			Random rand = new Random();
			if (x == null && !randomCard.isEmpty()) {
				x= randomCard.get(rand.nextInt(randomCard.size()));
				// make null
				for (int i = 0; i < agent.length; i++) {
					if (agent[i]!=null && agent[i].getRank() == x.getRank() && agent[i].getSuit().equals(x.getSuit())) {
						agent[i] = null;
						break;
					}
				}
				return x;
			}
			else {
				// make null
				for (int i = 0; i < agent.length; i++) {
					if (agent[i]!=null && agent[i].getRank() == y.getRank() && agent[i].getSuit().equals(y.getSuit())) {
						agent[i] = null;
						break;
					}
				}
				return y;
			}
			
		}

	}

	public int winner() {

		int player_win = 0;
		int max = runningCards[0].getRank();
		int max_trump_rank = 0;
		String winSuit = runningCards[nextTurn].getSuit();
		int max_non_trump_rank = 0;
		boolean trumpSet = false;

		if (runningCards[0].getSuit().equals(runningCards[1].getSuit())
				&& runningCards[1].getSuit().equals(runningCards[2].getSuit())
				&& runningCards[0].getSuit().equals(runningCards[2].getSuit())) {

			if (max < runningCards[1].getRank()) {
				max = runningCards[1].getRank();
				player_win = 1;
			}

			if (max < runningCards[2].getRank()) {
				max = runningCards[2].getRank();
				player_win = 2;
			}

		} else {

			for (int i = 0; i < runningCards.length; i++) {
				
				if (runningCards[i].getSuit().equals(trump)) {
					trumpSuit.put(runningCards[nextTurn].getSuit(), 1);
					
					if (max_trump_rank < runningCards[i].getRank()) {
						max_trump_rank = runningCards[i].getRank();
						player_win = i;
						trumpSet = true;
					}

				}

				// case 3: when there is non trump & different suits
				else {
					if (!trumpSet && runningCards[i].getSuit().equals(winSuit)
							&& runningCards[i].getRank() > runningCards[0].getRank()) {
						
						if (max_non_trump_rank < runningCards[i].getRank()) {
							max_non_trump_rank = runningCards[i].getRank();
							player_win = i;
						
						}
						
						
					}
				}

			}
		}
        trickCount[player_win]++;
		return player_win;

	}
}
