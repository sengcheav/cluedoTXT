package Square;

public class Position {
private int x ;
private int y ;

public Position(int x ,int y){
	this.x = x ;
	this.y = y ;
}

public Position getPos(){
	return this;
}
	
public int getX(){
	return this.x;
}

public int getY(){
	return this.y; 
}

public boolean diffXIsOne(  Position other){
	int xFrom = this.x ;
	int yFrom = this.y ;
	int xTo =  other.x ;
	int yTo = other.y ; 
	
	if(  (xFrom- xTo == -1 || xFrom - xTo == 1) ){  
		return true ; 
	}
	return false ; 
}


public boolean diffYIsOne(  Position other){
	int xFrom = this.x ;
	int yFrom = this.y ;
	int xTo =  other.x ;
	int yTo = other.y ; 
	
	if(  (yFrom- yTo == -1 || yFrom - yTo == 1) ){  
		return true ; 
	}
	return false ; 
}

public boolean diffXIsZero(Position other){
	if( this.x - other.x == 0){
		return true ; 
	}
	return false ; 
}

public boolean diffYIsZero(Position other){
	if( this.y - other.y == 0){
		return true ; 
	}
	return false ; 
}

}
