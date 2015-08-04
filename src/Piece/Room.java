package Piece;

import java.util.ArrayList;

import Item.Item;

public class Room extends Piece{
private String name ;
private Weapon containWepon  = null ;
private Character containCharacter = null ;
private static String type = "Room"; 
private ArrayList<Item> contains = new ArrayList<Item>(); 
private int indexInList ; 

/*
	kitchen,
	ballroom,
	conservatiory,
	billiardroom, 
	library,
	study,
	hall,
	lounge,
	dinngroom,
	
*/ 

public Room(String name) throws IllegalParameterException{
	super(name , type); 
	this.name = name ; 
	
}
	


//public void setContainWeapon(Weapon w){
//	this.containWepon =w ; 
//}
//public Weapon getContainWeapon() {
//	return this.containWepon ; 
//}
//
//public void setContainCharacter( Character c) {
//	this.containCharacter = c ;  
//}
//
//public Character getContainCharcter(){
//	return this.containCharacter ; 
//}

public void addContains(Item i){
	this.contains.add(i);
}

public ArrayList getContains(){
	return this.contains; 
}

public void setIndexInList(int i ){
	this.indexInList = i ; 
}

public int getIndexInList(){
	return this.indexInList ; 
}

}
