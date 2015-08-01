package Player;

import java.util.ArrayList;
import java.util.Scanner;

import Square.Position;
import Square.Square;
import Board.Dice;
import Card.Card;

public class Player {
private int index ; 
private ArrayList<Card> containsCard = new ArrayList<Card>(); 
private Square onSquare ;
private Position pos ; 
	
	public Player(int i){
		
		this.index = i ; 
		
	}
	public void setPos( Position p ){
		this.pos = p ; 
	}
	
	public Position getPos( ){
		return this.pos; 
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
	
	
	
	public boolean canMove( Position from , Position to , Square[][] s ){
		int xFrom = from.getX() ;
		int yFrom = from.getY() ; 
		int xTo = to.getX() ;
		int yTo = to.getY() ;
		
		if( from.diffXIsOne(to) && from.diffYIsZero(to)){
			return true ;
		}else if (from.diffYIsOne(to)  && from.diffXIsZero(to)){
			return true ; 
		}
		return false ; 
				
	}
	
	public String ask( String message){
		Scanner s = new Scanner(System.in);
		System.out.println(message);
		String command = s.next() ; 
		return command ; 
	}
	
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
	public boolean inRoom(Square[][] s ){
		return false ; 
	}
	
	
	public void turn ( Square[][] s ,Dice dice   ){
		int rolled = dice.roll() ;
		Position from = this.getPos() ; 
		
		while (rolled >0){
			String m = ask("You got "+ rolled + " moves Respond with u for up, d for down, l for left, r for right as move ");
			if(!m.equals("l") || !m.equals("r") || !m.equals("u") || !m.equals("d")){
				System.err.println("Please respond with l , r , u , d ");
				m = ask("You got "+ rolled + " moves Respond with u for up, d for down, l for left, r for right as move ");
			}
			Position to = calculatePos( this.pos , m) ; 
			if(canMove(this.pos, to ,s)){
				s[this.pos.getX()][this.pos.getY()].removeCharactersOn();
				if(s[this.pos.getX()][this.pos.getY()].setCharactersOn(this)) { // if no other character on the square 
					this.pos = to.getPos() ; 
				}
				...should be sth 
			}
			rolled--;	
		}
		if(inRoom(s) ){
			suggestion();
		}
		
	}
	
	public void suggestion ( ){
		String character  = ask("Which Character you think is the murderer ?  "); 
		String weapon  = ask("Which weapon you think is the murderer used ?  "); 
		String room  = ask("Which Room you think is the murdere in ?  "); 
		
	}
	
}
