package cardGame;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
<<<<<<< HEAD

=======
>>>>>>> master

public class CardGame {

	private static Card[] agent;
	private static Card[] player2;
	private static Card[] player3;
	private static Deck deck;
	static Card[] runningCards = new Card[3];
	static int round = 0;
	private static String trump;
	
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
		
		trump=c.Decide_Trump();
		System.out.println(trump);
				

		// distribute 3 more cards
		DisCards = c.DistributeCards(3, DisCards, HandCards);
		HandCards += 3;

		// distribute 2 more cards
		DisCards = c.DistributeCards(2, DisCards, HandCards);

		System.out.println("Agent cards are " + Arrays.toString(agent));
		System.out.println("Player 2 cards are " + Arrays.toString(player2));
		System.out.println("Player 3 cards are " + Arrays.toString(player3));
		
		round = round + 1;
		
		runningCards[0]=c.first_round();
		System.out.println("Agent played " + runningCards[0]);

		c.getInputfromPlayers(1);
		System.out.println("Agent played " + runningCards[0]);
		System.out.println("Player 1 played " + runningCards[1]);

		c.getInputfromPlayers(2);
		
		System.out.println(Arrays.toString(runningCards));
		
		System.out.println("Agent cards are " + Arrays.toString(agent));
		System.out.println("Player 2 cards are " + Arrays.toString(player2));
		System.out.println("Player 3 cards are " + Arrays.toString(player3));
		
<<<<<<< HEAD
		System.out.println("Agent cards are "+Arrays.toString(agent));
		System.out.println("Player 2 cards are "+Arrays.toString(player2));
		System.out.println("Player 3 cards are "+Arrays.toString(player3));
		
		c.getInputfromPlayers(new Card[3],1);
		System.out.println("Agent cards are "+Arrays.toString(agent));
		System.out.println("Player 2 cards are "+Arrays.toString(player2));
		System.out.println("Player 3 cards are "+Arrays.toString(player3));
=======
		System.out.println(c.winner());
>>>>>>> master
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
	 * returns and assigns value to the  global Trump variable
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
				trump = "S";
			case 1:
				trump = "H";
			case 2:
				trump = "C";
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
		int rank1 = sc.nextInt();
		String suit1 = sc.next();
		Card c1 = new Card(rank1, suit1);
		Card[] arr=null;
		if(turn==1) arr=player2;
		else if(turn==2) arr=player3;
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].getRank() == c1.getRank() && arr[i].getSuit().equals(c1.getSuit())) {
				runningCards[turn] = c1;
				arr[i] = null;
			}
		}
		if(turn==1) player2=arr;
		else if(turn==2) player3=arr;
	

//		System.out.println("Please enter the rank (INTEGER VALUE) and color (UPPER CASE CHARACTER) you want to play");
//		int rank2 = sc.nextInt();
//		String suit2 = sc.next();
//		Card c2 = new Card(rank2, suit2);
//		for (int i = 0; i < player2.length; i++) {
//			if (player3[i].getRank() == c2.getRank() && player3[i].getSuit().equals(c2.getSuit())) {
//				runningCards[turn] = c2;
//				player3[i] = null;
//			}
//		}
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
		int nmbr_round=round;
		Card card_played = null;
		
		if (nmbr_round ==1) {
		
			for (int i = 0; i < agent.length; i++) {

				suit = agent[i].getSuit();
				rank = agent[i].getRank();

				if (rank == 14) {
					card_played = agent[i];
					agent[i] = null;
					return card_played;
				}
				else if (prev_rank > rank && agent[i].getSuit() != trump) {
					prev_rank = rank;
					idx = i;
				}
			}
			card_played = agent[idx];
			agent[idx] = null;
			return card_played;
		}
		else {
			return null;
		}
	}

	public int winner() {

		int player_win = 0;
		int max = runningCards[0].getRank();
		int max_trump_rank = 0;
		String winSuit=runningCards[0].getSuit();
		int max_non_trump_rank=0;
		
		if (runningCards[0].getSuit().equals(runningCards[1].getSuit()) && 
				runningCards[1].getSuit().equals(runningCards[2].getSuit()) &&
						runningCards[0].getSuit().equals(runningCards[2].getSuit())) {
			
			if (max < runningCards[1].getRank()) {
				max = runningCards[1].getRank();
				player_win = 1;
			}
			
			if (max < runningCards[2].getRank()) {
				max = runningCards[2].getRank();
				player_win = 2;
			}
			
		}
		else {
							
			for (int i =0; i<runningCards.length;i++) {
				
				if (runningCards[i].getSuit().equals(trump)){
					if (max_trump_rank < runningCards[i].getRank()) {
						max_trump_rank = runningCards[i].getRank();
						player_win = i;
					}
					
				}
				
				//case 3: when there is non trump & different suits
				else {
					if(runningCards[i].getSuit().equals(winSuit) && 
							runningCards[i].getRank() > runningCards[0].getRank())
						max_non_trump_rank=runningCards[i].getRank();
					    player_win=i;
				}
				
			}
		}
	
		
		return player_win;

	}
	
	/**
	 * This will take input from players by asking the cards they want to play through scanner
	 * @param runningCards : 3 cards which are getting played
	 * @param turn: specifies the turn, which player ha to play now 
	 */
	public Card[] getInputfromPlayers(Card[] runningCards,int turn){
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter the rank (INTEGER VALUE) and color (UPPER CASE CHARACTER) you want to play");
		int rank1=sc.nextInt();
		String suit1=sc.next();
		Card c1=new Card(rank1,suit1);
		for(int i=0;i<player2.length;i++){
			if(player2[i].getRank()==c1.getRank() && player2[i].getSuit().equals(c1.getSuit())){
				runningCards[turn++]=c1;
				player2[i]=null;
			}
		}
		
		System.out.println("Please enter the rank (INTEGER VALUE) and color (UPPER CASE CHARACTER) you want to play");
		int rank2=sc.nextInt();
		String suit2=sc.next();
		Card c2=new Card(rank2,suit2);
		for(int i=0;i<player2.length;i++){
			if(player3[i].getRank()==c2.getRank() && player3[i].getSuit().equals(c2.getSuit())){
				runningCards[turn]=c2;
				player3[i]=null;
			}
		}
		return runningCards;
	}
}
