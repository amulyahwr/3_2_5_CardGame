package cardGame;

import java.util.Arrays;
import java.util.Random;

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
		c.DistributeCards(5);
		c.Decide_Trump();
		
		//c.DistributeCards(3);
		//c.DistributeCards(2);
		System.out.println("Agent cards are "+Arrays.toString(agent));
		System.out.println("Player 2 cards are "+Arrays.toString(player2));
		System.out.println("Player 3 cards are "+Arrays.toString(player3));
	}
	
	public void DistributeCards(int k){
		Card[] hand = deck.select(k);
		for(int i=0;i<hand.length;i++){
			agent[i]=hand[i];
		}
		hand = deck.select(k);
		for(int i=0;i<hand.length;i++){
			player2[i]=hand[i];
		}
		hand = deck.select(k);
		for(int i=0;i<hand.length;i++){
			player3[i]=hand[i];
		}
	}
	public String Decide_Trump(){
		
		int cs=0,cd=0,ch=0,cc=0;
		for(int i=0;i<5;i++) {
			if(agent[i].getSuit().equals("S"));
		}
		return null;
	}
}
