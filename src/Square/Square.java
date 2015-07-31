package Square;

import Piece.Piece;

public class Square<T> {
private T contain ; 
private Position position ;
private String c ; 


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

private void setPosition ( int x , int y ){
	this.position = new Position(x,y); 
}

public String getC(){
	return this.c ; 
}

}
