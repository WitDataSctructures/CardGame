package uno;
import java.util.ArrayList;

public class Hand {
	
	private ArrayList<Card> playerHand;
	
	//Default Constructor
	public Hand(){
		playerHand = null;
	}
	
	
	//Returns an integer of how many cards currently in the player's hand.
	public int getHandSize(){
		return playerHand.size();
	}
	
	//Returns the playerHand.
	public ArrayList<Card> getHand(){
		return playerHand;
	}
	
	//Checks if a Card parameter exists in the player's hand.
	public boolean isCardInHand(Card cardToLookFor){
		for(int i = 0; i < playerHand.size(); i++){
			if(cardToLookFor == playerHand.get(i)){
				return true;
			}
		}
		return false;
	}
	

	//Add a Card parameter to the hand.
	public void addToHand(Card cardToBeAdded){
		playerHand.add(cardToBeAdded);
	}
	
	//If the Card exists in the hand, remove it, otherwise do nothing.
	public void removeFromHand(Card cardToBeRemoved){
		if(isCardInHand(cardToBeRemoved)){
			playerHand.remove(cardToBeRemoved);
		}
	}
}
