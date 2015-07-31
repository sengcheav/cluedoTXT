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
}
