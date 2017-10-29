package cardGame;

/*public class Card {
	public enum Suit{
		Club(2), Spade(1), Heart(3), Diamond(4);
		int value;
		Suit(int v){
			value=v;
		}
	};
	private static int CRank;
	private static Suit CSuit;
	
	public Card(Suit s,int rank){
		CRank=rank;
		CSuit=s;	
	}
	
	public int getRank(){
		return CRank;
	}
	
	public Suit getSuit(){
		return CSuit;
	}
	
}*/


/**
 * Class representing a playing card with possible rank 1 through 13 and four
 * possible suits.
 */
public class Card
{
  /**
   * The four suits.
   */
  public enum Suit
  {
    C, D, H, S
  };

  /**
   * Suit for this card.
   */
  private final Suit suit;

  /**
   * Rank for this card.
   */
  private final int rank;

  /**
   * Names used for displaying strings.
   */
  //element 0 not used
  private final String[] NAMES = {"D","D","D","D","D","D","D",
		  "7", "8", "9","10", "J", "Q", "K","A"};

  /**
   * Constructs a card with the given rank and suit. Ranks are in the range 1 through 13.
   * 
   * @param givenRank
   *          rank for this card
   * @param givenSuit
   *          suit for this card
   */
  public Card(int givenRank, Suit givenSuit)
  {
    rank = givenRank;
    suit = givenSuit;
  }

  /**
   * Returns the rank for this card.
   * 
   * @return rank for this card
   */
  public int getRank()
  {
    return rank;
  }

  /**
   * Returns the suit for this card.
   * 
   * @return suit for this card
   */
  public Suit getSuit()
  {
    return suit;
  }

  /**
   * Returns a String representation of this card.
   */
  public String toString()
  {
    return NAMES[rank] + " " + suit;
  }

  /**
   * Returns a String representation of a given array of cards.
   * 
   * @param arr
   *          array of Card objects
   * @return a String representation of the given array
   */
  public static String toString(Card[] arr)
  {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < arr.length; ++i)
    {
      if (i > 0)
      {
        // every element after first one has comma before it
        sb.append(", ");
      }
      sb.append(arr[i]);
    }
    sb.append("]");
    return sb.toString();
  }

}

