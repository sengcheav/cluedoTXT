package Player;

import java.util.ArrayList;
import java.util.Scanner;

import Piece.Characters;
import Piece.IllegalParameterException;
import Piece.Room;
import Piece.Weapon;
import Square.Position;
import Square.Square;
import Board.Dice;
import Card.Card;

public class Player {
private int index ; 
private ArrayList<Card> containsCard = new ArrayList<Card>(); 
private Square onSquare ;
private Position pos ; 
private ArrayList<Card> announcementList  ; 

	
	public Player(int i){
		
		this.index = i ; 
		
	}
	
	/*
	 * Set the Position of the player
	 */
	public void setPos( Position p ){
		this.pos = p ; 
	}
	
	/*
	 * Get the Position of the player 
	 */
	public Position getPos( ){
		return this.pos; 
	}
	
	/*
	 * Add the card to the arrayList (containCard)
	 */
	public void addContainsCard( Card c ){
		if(c == null ) throw new NullPointerException("Card Can not be null") ;
		this.containsCard.add(c) ; 
	}
	
	/*
	 * Get the ArrayList of containCard
	 */
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
	
	
	/*
	 * First this method will check if the x y position to make sure either
	 * x or y position one by one square, return false if the pieces try
	 * to move over the wall , or piece try to move to the blank space or 
	 * the position x or < 0 or >25 , also return false if the piece try to 
	 * move to the clue room . Other wise return true 
	 */
	public boolean canMove( Position from , Position to , Square[][] s ){
		int xFrom = from.getX() ;
		int yFrom = from.getY() ; 
		int xTo = to.getX() ;
		int yTo = to.getY() ;
		
		if( (from.diffXIsOne(to) && from.diffYIsZero(to)) || (from.diffYIsOne(to)  && from.diffXIsZero(to)) ){
			// allow to move either x or y by one square at a time
			if(s[xFrom][yFrom].isContainHall() && s[xTo][yTo].isContainRoom()){ // can not move over the wall 
				return false ;
			}else if (s[xFrom][yFrom].isContainRoom() && s[xTo][yTo].isContainHall()){ // can not move over the wall
				return false ;
			}else if (s[xTo][yTo].isContainBlankSpace()){ // can not move to the blank space
				return false ; 
			}else if (xTo < 0 || xTo >24 || yTo <0 || yTo>24 ){ // can not to square -1 or 25
				return false ; 
			}else if (s[xTo][yTo].isContainClue()){
				return false ; // can not move to clue room 
			}
			return true ; 
		}
		
		return false ; 
				
	}
	
	/*
	 * This method will ask user and will return command as integer
	 */
	public String ask( String message){
		Scanner s = new Scanner(System.in);
		System.out.println(message);
		String command = s.next() ; 
		return command ; 
	}
	
	
	/*
	 * This method will ask user and will return command as String
	 */
	public int askInt( String message){
		Scanner s = new Scanner(System.in);
		System.out.println(message);
		int command = s.nextInt() ; 
		return command ; 
	}
	
	
	/*
	 * This method will return and calculate the new position according 
	 * to the command given by player.
	 */
	public Position calculatePos( Position p , String m){
	
		
		if( m.equals("l")){
			return new Position ( p.getX() -1 ,p.getY());
		}else if (m.equals("r")){
			return new Position (p.getX(), p.getY()); 
		}else if (m.equals("u")){
			return new Position(p.getX(), p.getY()-1);
			
		}else if (m.equals("d")){
			return new Position(p.getX(), p.getY()); 
		}else 
		
		return p ;
	}
	
	
	/*
	 * This method will return true if a player is currently in a room
	 */
	public boolean inRoom(Square  s ){
		if( s.getContain() instanceof Room) {
			return true ; 
		}
		return false ; 
	}
	
	
	/*
	 * This method will return the room that player is currently in if 
	 * player is in a room otherwise return null
	 */
	public Room getRoomPlayerIn( Square s){
		if(inRoom(s)){
			Room r = (Room) s.getContain() ; 
			return r ; 
		}
		return null ; 
	}
	
	/*
	 * Every player begin the turn by rolling the dice, then the method will 
	 * keep asking the player to move the piece one by one square respond to 
	 * the result of the dice, after get the command from player the method 
	 * will first check if the piece can move to the corresponding square, if 
	 * it can the piece will then need to updating it new location (square ) 
	 * otherwise it will print out telling player to chhose another direction. 
	 * Finally it will check if the piece is in the room or not if it is the 
	 * player will then have to make suggestion 
	 * 
	 */
	public void turn ( Square[][] s ,Dice dice   ){
		int rolled = dice.roll() ;
		Position from = this.getPos() ; 
		
		while (rolled >0){ // move correspond to result of the dice 
			String m = ask("You got "+ rolled + " moves Respond with u for up, d for down, l for left, r for right as move ");
			if(!m.equals("l") || !m.equals("r") || !m.equals("u") || !m.equals("d")){
				System.err.println("Please respond with l , r , u , d ");
				m = ask("You got "+ rolled + " moves Respond with u for up, d for down, l for left, r for right as move ");
			}
			Position to = calculatePos( this.pos , m) ; 
			if(canMove(this.pos, to ,s)){
				s[this.pos.getX()][this.pos.getY()].removeCharactersOn();
				if(s[this.pos.getX()][this.pos.getY()].setCharactersOn(this)) { 
					// if no other character on the square , set the character on that square 
					this.pos = to.getPos() ;  // and change the position of the player to that position 
				}
				
			}else {
				rolled++ ; // so the rolled wont change ( + and - value stay the same ) 
				System.err.println("Can not move to this square because another player already on this square \n" +
				"Please chhose other option") ;	
			}
			rolled--;	
		}
		if(inRoom(s[from.getX()][from.getY()]) ){
			suggestion(s[from.getX()][from.getY()]);
		}
		
	}
	
	/*
	 * This method will ask the player to choose character , weapon and the room they 
	 * currently in they'd like to do the annocement with then it will call on 
	 * announcement method . 
	 */
	
	public void suggestion ( Square s){
		String cString = "", wString = "" ;
		
		int character  = askInt("Which Character you think is the murderer ? \n 1.Miss_Scarlett, \n 2.Colonel_Mustard,"+
				"\n 3.Mrs_White, \n 4.The_Reverend_Green,\n  5.Mrs_Peacock, \n 6.Professor_Plum "); 
		int weapon  = askInt("Which weapon you think is the murderer used ? \n 1.Candlestick, \n 2.Dagger, \n 3.Lead_Pipe," +
				"\n 4.Revolver, \n 5.Rope, \n 6.Spanner "); 
		String room  = ask("Which Room you think is the murdere in ? \n 1.kitchen, \n 2.ballroom, \n 3.conservatiory, " +
				"\n 4.billiardroom,  \n 5.library, \n 6.study, \n 7.hall, \n 8.lounge \n 9.dinngroom, "); 
		try {
			switch (character){
			case 1 : cString = "Miss_Scarlett" ;
			case 2 : cString = "Colonel_Mustard" ;
			case 3 : cString = "Mrs_White" ;
			case 4 : cString = "The_Reverend_Green" ;
			case 5 : cString = "Mrs_Peacock" ;
			case 6 : cString = "Professor_Plum" ;
			}
			switch (weapon){
			case 1 : wString = "Candlestick";
			case 2 : wString = "Dagger";
			case 3 : wString = "Lead_Pipe";
			case 4 : wString = "Revolver";
			case 5 : wString = "Rope";
			case 6 : wString = "Spanner";
			}
			Room r1 = getRoomPlayerIn(s) ;
			Card r = new Card (r1) ;
			Card c = new Card (new Characters(cString));
			Card w = new Card (new Weapon(wString)); 
			this.announcementList.clear() ; // clear the card from last time announcemnent if any
			this.announcementList = announcement(c, w, r);
		} catch (IllegalParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}
	
	/*
	 * This method will return the arrayList that contain all the card that player
	 * would like to do the announcement with
	 */
	public ArrayList<Card> announcement (Card character , Card weapon , Card room){
		ArrayList<Card> announcement = new ArrayList<Card>() ; 
		announcement.add(character);
		announcement.add(weapon);
		announcement.add(room);
		return announcement;
	}
	
	
	/*
	 * The player will refute again the other players announcement (suggestion)
	 * by compare the card in hand list to suggestion list the method will return 
	 * true if player got any card that match any one card in the suggestion list
	 * otherwise false will be return.
	 */
	public boolean refute( ArrayList<Card> suggestion ){
		for( int i = 0 ; i < this.containsCard.size() ; i++){
			Card contain = this.containsCard.get(i);
			for(int ii = 0; ii<suggestion.size() ; ii++ ){
				if(contain.equals(suggestion.get(ii))){
					return true;
				}
			}
		}
		return false ; 
	}
	
	
	/*
	 * The Player making accusation (character, weapon ,room) card 
	 * These 3 card then will be checking again the solution Card 
	 * Correct Variable will be increment by 1 every time a card is correct
	 * If player can get 3 card correct the method will return true otherwise false.
	 */
	public boolean accusation(Card character, Card weapon , Card room , ArrayList<Card> solution){
		int correct = 0 ; 
		for(int i =0 ; i<solution.size() ; i++){
			if(character.equals(solution.get(i))){ correct++ ;}
			if(weapon.equals(solution.get(i))){ correct++ ;}
			if(room.equals(solution.get(i))){ correct++ ;}
		}
		if(correct ==3 ) {
			return true ;
		}
		return false ; 
	}
	
}
