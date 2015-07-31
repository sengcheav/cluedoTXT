package Player;

import java.util.ArrayList;

import Square.Square;
import Card.Card;

public class Player {
private int index ; 
private ArrayList<Card> containsCard = new ArrayList<Card>(); 
private Square onSquare ;
	
	public Player(int i){
		
		this.index = i ; 
		
	}
	
	public void addContainsCard( Card c ){
		if(c == null ) throw new NullPointerException("Card Can not be null") ;
		this.containsCard.add(c) ; 
	}
	
	public ArrayList<Card> getContainsCard() {
		return this.containsCard ; 
	}
	
	public void setOnSquare(Square sq ){
		this.onSquare =sq ; 
	}
	
	
	public Square onSquare(){
		return this.onSquare ; 
	}
	
	public int getIndex(){
		return this.index ; 
	}
	
}
