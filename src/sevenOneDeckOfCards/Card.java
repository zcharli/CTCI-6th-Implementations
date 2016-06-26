package sevenOneDeckOfCards;

public class Card {
	protected FaceValue faceValue;
	protected Suit cardSuit;
	
	public enum FaceValue {
		NONE, ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
	}
	
	public enum Suit {
		NONE, HEARTS, DIAMOND, SPADES, CLUBS
	}
	
	public Card(FaceValue value, Suit suit) {
		this.faceValue = value;
		this.cardSuit = suit;
	}
	
	// Other helper methods as needed
}
