  package Square;

import Item.Door;
import Item.Hallway;
import Piece.Characters;
import Piece.Piece;
import Piece.Room;
import Player.Player;

public class Square<T> {
private T contain ; 
private Position position ;
private String c ; // label when it print on board
private final String cOriginal ; 
private Player on ; 


public Square(T i, String c ){
	this.contain = i ;
	this.c =c ; 
	cOriginal =  c ; 
}

public Square(T i, String c ,Position p ){
	this.contain = i ;
	this.c =c ; 
	cOriginal =  c ; 
	this.position = p ; 
}

public void setContain(T c){
	this.contain = c ; 
}
	
public T getContain(){
	return this.contain ; 
}

public void setPosition ( int x , int y ){
	this.position = new Position(y,x); 
}

public boolean setCharactersOn( Player player){
	if(on == null){
		this.on = player ; 
		int index = player.getIndex() +1 ; 
		this.c = ""+index ; 
		return true ; 
	}
	return false ;
}

public Player getCharactersON(){
	return this.on ; 
}

public void removeCharactersOn(){
	this.on = null ;
	this.c = this.cOriginal;
}

/*
 * Return the symbol of the square eg: study represent by s
 */
public String getC(){
	return this.c ; 
}

/*
 * Return true if the square contain hall
 */
public boolean isContainHall(){
	if (this.cOriginal.equals(" ")){
		return true; 
	}
	return false ;
}

/*
 * Return true if the square contain door
 */
public boolean isContainDoor(){
	if (this.cOriginal.equals("d")){
		return true; 
	}
	return false ;
}

/*
 * Return true if the square contain blank space
 */
public boolean isContainBlankSpace(){
 
	if (this.cOriginal.equals("/")){
		return true; 
	}
	return false ;
}

/*
 * Return true if the square contain room
 */
public boolean isContainRoom(){
	if (this.cOriginal.equals("k")||this.cOriginal.equals("B")||this.cOriginal.equals("c")||this.cOriginal.equals("D")
		||this.cOriginal.equals("b")||this.cOriginal.equals("l") || this.cOriginal.equals("L") || this.cOriginal.equals("h")){
		return true; 
	}
	return false ;
}

/*
 * Return true if the square contain the spawn
 */
public boolean isContainSpawn(){
	if (this.cOriginal.equals("1") || this.cOriginal.equals("2") || this.cOriginal.equals("3") || this.cOriginal.equals("4")
			|| this.cOriginal.equals("5") ||this.cOriginal.equals("6")){
		return true; 
	}
	return false ;
}

/*
 * Return true if the square contain the clue room
 */
public boolean isContainClue(){
	if(this.cOriginal.equals("*")){
		return true; 
	}		
	return false ;
}


/*
 * Return true if the square contain the stairWell 
 */
public boolean isContainStairWell(){
	if(this.cOriginal.equals("#")){
		return true; 
	}		
	return false ;
}

/*
 * Check if the adjacent square is the room ( use for door and stairwell only )
 */
public Room adjacentRoom(Square [][]s){
	int x = this.position.getX() ;
	int y = this.position.getY();
	if(s[y-1][x].isContainRoom()){
		Room r =(Room) s[y-1][x].getContain() ; 
		return r ; 
	}else if(s[y+1][x].isContainRoom()){
		Room r =(Room) s[y+1][x].getContain() ; 
		return r ; 
	}else if(s[y][x+1].isContainRoom()){
		Room r =(Room) s[y][x+1].getContain() ; 
		return r ; 
	}else if(s[y][x-1].isContainRoom()){
		Room r =(Room) s[y][x-1].getContain() ; 
		return r ; 
	}
	return null ; 
}

}
