package cardGame;

import java.util.Random;

public class CardGame {

	private static Card[] player1;
	private static Card[] player2;
	private static Card[] player3;
	private int dealer;
	Random r = new Random();
	private static Deck deck;
	
	public CardGame(){
		deck = new Deck();
		player1=new Card[10];
		player2=new Card[10];
		player3=new Card[10];
		dealer=r.nextInt(3); 
		System.out.println("Player "+dealer+" is dealer");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CardGame c=new CardGame();
		c.DistributeCards(5);
	}
	
	public void DistributeCards(int k){
		Card[] hand = deck.select(5);
	}
}
