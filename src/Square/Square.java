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
private String c ; 
private Player on ; 


public Square(T i, String c ){
	this.contain = i ;
	this.c =c ; 
}

public void setContain(T c){
	this.contain = c ; 
}
	
public T getContain(){
	return this.contain ; 
}

public void setPosition ( int x , int y ){
	this.position = new Position(x,y); 
}

public boolean setCharactersOn( Player player){
	if(on == null){
		this.on = player ; 
		return true ; 
	}
	return false ;
}

public Player getCharactersON(){
	return this.on ; 
}

public void removeCharactersOn(){
	this.on = null ; 
}

public String getC(){
	return this.c ; 
}

public boolean isContainHall(){
	if (this.contain instanceof Hallway){
		return true; 
	}
	return false ;
}

public boolean isContainDoor(){
	if (this.contain instanceof Door){
		return true; 
	}
	return false ;
}

public boolean isContainBlankSpace(){
	if (this.contain instanceof blankSpace){
		return true; 
	}
	return false ;
}

public boolean isContainRoom(){
	if (this.contain instanceof Room){
		return true; 
	}
	return false ;
}

public boolean isContainSpawn(){
	if (this.contain instanceof Spawn){
		return true; 
	}
	return false ;
}

public boolean isContainClue(){
	if (this.contain instanceof Room){
		Room r = (Room)contain ; 
		if(r.getName().equals("clue")){
			return true; 
		}
		
	}
	return false ;
}


}
