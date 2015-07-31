package Board;

public class Dice {
private int state   ; 

public Dice() {
	this.state = 0 ; 
}

public int Roll(){
	//randomNum = minimum + (int)(Math.random()*maximum); 
	state = 0 + (int)(Math.random()*6); 
	return state ; 
}

public int getState(){
	return this.state; 
}

}
