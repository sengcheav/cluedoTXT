package Square;

import Piece.Piece;

public class Spawn extends Square {
private Piece piece; 
	
public Spawn(Piece p , String c ){
		
		super(p ,c ); 
		this.piece = p ; 
		
	}

public Spawn(Piece p , String c , Position po){
	
	super(p ,c, po ); 
	this.piece = p ; 
	
}

public Piece getPiece(){
	return this.piece ; 
}
}
