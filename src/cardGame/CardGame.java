package cardGame;

import java.util.Arrays;
import java.util.Random;

import cardGame.Card.Suit;

public class CardGame {

	private static Card[] agent;
	private static Card[] player2;
	private static Card[] player3;
	private static Deck deck;
	
	public CardGame(){
		deck = new Deck();
		agent=new Card[10];
		player2=new Card[10];
		player3=new Card[10]; 
		System.out.println("Player "+2+" is dealer");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CardGame c=new CardGame();
		
		//First distribute 5 cards
		int DisCards=0;
		int HandCards=0;
		
		DisCards=c.DistributeCards(5,DisCards,HandCards);
		HandCards+=5;
		
		c.Decide_Trump();
		
		//distribute 3 more cards
		DisCards=c.DistributeCards(3,DisCards,HandCards);
		HandCards+=3;
		
		//distribute 2 more cards
		DisCards=c.DistributeCards(2,DisCards,HandCards);
		
		System.out.println("Agent cards are "+Arrays.toString(agent));
		System.out.println("Player 2 cards are "+Arrays.toString(player2));
		System.out.println("Player 3 cards are "+Arrays.toString(player3));
	}
	
	/**
	 * This function randomly distribute the cards to each player
	 * @param k : how many cards has to be distributed
	 * @param DisCards: how many cards from deck are distributed till now
	 * @param HandCards : how many cards are there in each player's hand
	 * @return DisCards
	 */
	public int DistributeCards(int k,int DisCards,int HandCards){
		Card[] hand = deck.select(k,DisCards);
		for(int i=0;i<hand.length;i++){
			agent[i+HandCards]=hand[i];
			DisCards++;
		}
		hand = deck.select(k,DisCards);
		for(int i=0;i<hand.length;i++){
			player2[i+HandCards]=hand[i];
			DisCards++;
		}
		hand = deck.select(k,DisCards);
		for(int i=0;i<hand.length;i++){
			player3[i+HandCards]=hand[i];
			DisCards++;
		}
		return DisCards;
	}
		
	public Suit Decide_Trump(){
		
		return null;
	}
}
