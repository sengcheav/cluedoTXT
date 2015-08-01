package Square;

import Piece.Characters;
import Piece.Piece;
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


}
