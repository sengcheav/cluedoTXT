package Board;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

import Card.Card;
import Card.RoomCard;
import Item.Door;
import Item.Hallway;
import Item.Item;
import Item.stairWell;
import Piece.Characters;
import Piece.IllegalParameterException;
import Piece.Piece;
import Piece.Piece.*;
import Piece.Room;
import Piece.Weapon;
import Player.Player;
import Square.Spawn;
import Square.Square;
import Square.blankSpace;

public class Board {
	
	private int numOfPlayer ; 
	private static int turn =0 ; 
	private Dice dice = new Dice() ; 
	private Square square[][] =  new Square[25][25];
	
	private ArrayList<Characters> characterList = new ArrayList<Characters>(); 
	private ArrayList<Room> roomList = new ArrayList<Room>(); 
	private ArrayList<Weapon> weaponList = new ArrayList<Weapon>(); 
	private static ArrayList<Player> playerList = new ArrayList<Player>() ;
	
	private ArrayList<Card> weaponCard = new ArrayList<Card>(); 
	private ArrayList<Card> charactersCard = new ArrayList<Card>(); 
	private static ArrayList<Card> roomCard = new ArrayList<Card>(); 
	private ArrayList<Card> solution = new ArrayList<Card>() ; 
	private static ArrayList<Card> cardLeft = new ArrayList<Card>(); 
	private static int currentTurn = 0 ;
	
	private boolean finished = false ;  // Game state  
	
	public Board() {
		
		this.numOfPlayer = ask("How many Players?") ; 
		CharacterSetup();
		PlayerSetup();
		RoomSetup() ;
		weaponSetup() ;
		cardSetup() ;
		solutionSetup() ;
		dealCard( cardLeft , playerList);
		squareSetup();
		System.out.println(" ------"+ this.numOfPlayer );
	
	}
	
	public int ask( String message){
		Scanner s = new Scanner(System.in);
		System.out.println(message);
		int number = s.nextInt() ; 
		return number ; 
	}
	
	public void PlayerSetup() {
		for ( int i = 0 ; i < this.numOfPlayer ; i++ ){
			playerList.add(new Player(i)) ; 
		}
	}
	
	public int whoturn(){
		if(this.currentTurn == numOfPlayer -1 ){
			currentTurn = 0 ;
			return 0 ; 
		}
		currentTurn++;
		return currentTurn;
	}

	public void CharacterSetup() {
		try {
			characterList.add(new Characters("Miss_Scarlett") ) ;
			characterList.add(new Characters("Colonel_Mustard")) ; 
			characterList.add(new Characters("Mrs_White")  ) ; 
			characterList.add(new Characters("The_Reverend_Green")) ; 
			characterList.add(new Characters("Mrs_Peacock") ) ; 
			characterList.add(new Characters("Professor_Plum") ) ; 
		} catch (IllegalParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*	In this method I create rooms such as kitchen , conservatory , hall etc ...
	 * 	And in each room I added item ( door, stairwell ) and also I added the connecting room 
	 *  between the stairWell 
	 *  All the room are added to the roomList
	 */
	public void RoomSetup(){
		try{
		Room kitchen = new Room("kitchen") ; 
		stairWell kitchenStair = new stairWell("kit_stairWell", "stairWell");
		kitchen.addContains(kitchenStair); 
		kitchen.addContains(new Item("door", "door"));
		kitchen.setIndexInList(0);
		
		Room ballroom = new Room("ballroom") ; 
		ballroom.addContains(new Item("door0" , "door")); 
		ballroom.addContains(new Item("door1" , "door")); 
		ballroom.addContains(new Item("door2" , "door")); 
		ballroom.addContains(new Item("door3" , "door")); 
		ballroom.setIndexInList(1);
		
		Room conservatory = new Room("conservatory") ; 
		stairWell conservatoryStair = new stairWell ("con_stairWell", "stairWell");
		conservatory.addContains( conservatoryStair) ; 
		conservatory.addContains(new Item ("door0" , "door"));
		conservatory.setIndexInList(2); 
		
		Room billiardroom = new Room("billiardroom") ; 
		billiardroom.addContains(new Item("door0", "door"));
		billiardroom.addContains(new Item("door1", "door"));
		billiardroom.setIndexInList(3); 
		
		Room library = new Room("library") ; 
		library.addContains(new Item("door0", "door"));
		library.addContains(new Item("door1", "door"));
		library.setIndexInList(4) ; 
		
		Room study = new Room("study") ; 
		stairWell studyStair = new stairWell("stu_stairWell", "stairWell"); 
		study.addContains(studyStair); 
		study.addContains(new Item("door0" , "door"));
		study.setIndexInList(5);
		
		Room hall = new Room("hall") ; 
		hall.addContains(new Item("door0", "door")); 
		hall.addContains(new Item("door1" , "door"));
		hall.setIndexInList(6);
		
		Room lounge = new Room("lounge") ; 
		stairWell loungeStair = new stairWell("lou_stairWell", "stairWell");
		lounge.addContains(loungeStair);
		lounge.addContains(new Item("door0", "door")); 
		lounge.setIndexInList(7);
		
		Room dinningroom = new Room("diningroom") ; 
		dinningroom.addContains(new Item("door0", "door"));
		dinningroom.addContains(new Item("door1", "door"));
		dinningroom.setIndexInList(8);
		
		Room clue = new Room("clue");
		clue.setIndexInList(9);
		
		kitchenStair.connectRoom(conservatory); 
		conservatoryStair.connectRoom(kitchen);
		studyStair.connectRoom(lounge);
		loungeStair.connectRoom(study); 
		
		/*
		 * the order of adding to list is important since I set the 
		 * IndexInList of each room to get easy access to the list
		 * so do not have to iterate through the list 
		 */
		roomList.add(kitchen);//0
		roomList.add(ballroom);//1
		roomList.add(conservatory);//2
		roomList.add(billiardroom);//3
		roomList.add(library);//4
		roomList.add(study);//5
		roomList.add(hall);//6
		roomList.add(lounge);//7
		roomList.add(dinningroom);//8
		roomList.add(clue);//9
		} catch (IllegalParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public void weaponSetup(){
		try{
		weaponList.add(new Weapon("Candlestick"));
		weaponList.add(new Weapon("Dagger"));
		weaponList.add(new Weapon("Lead_Pipe"));
		weaponList.add(new Weapon("Revolver"));
		weaponList.add(new Weapon("Rope"));
		weaponList.add(new Weapon("Spanner"));
		} catch (IllegalParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void cardSetup(){
		for (Characters c: characterList){
			charactersCard.add(new Card(c)); 	
		}
		for(Weapon w: weaponList ){
			weaponCard.add(new Card(w)); 
		}
		for(Room r : roomList){
			if(r.getName() != "clue"){ // do not add clue to roomCard
			roomCard.add(new Card(r));
			}
		}
	}
	
	public void squareSetup(){
		
		Square k = new Square ( roomList.get(0) , "k");
		ArrayList<Item> iList = roomList.get(0).getContains();
		Item i1 = iList.get(0) ; 
		Square s1 = new Square (i1 , "#");
			
		Square B = new Square ( roomList.get(1) , "B");  //Ballroom
		Square c = new Square ( roomList.get(2) , "c");
		ArrayList<Item> iList2 = roomList.get(2).getContains();
		Item i2 = iList2.get(0) ; 
		Square s2 = new Square (i2 , "#");
		
		Square b = new Square ( roomList.get(3) , "b");
		Square l = new Square ( roomList.get(4) , "l"); // library 
		Square s = new Square ( roomList.get(5) , "s");
		ArrayList<Item> iList3 = roomList.get(5).getContains();
		Item i3 = iList3.get(0) ; 
		Square s3 = new Square (i3 , "#");
		
		Square h = new Square ( roomList.get(6) , "h");
		Square L = new Square ( roomList.get(7) , "L");// Lounge 
		ArrayList<Item> iList4 = roomList.get(7).getContains(); 
		Item i4 = iList4.get(0) ; 
		Square s4 = new Square (i4 , "#");
		
		Square D = new Square ( roomList.get(8) , "d");
		Square cl = new Square (roomList.get(9) , "*") ; 
		Square H = new Square ( new Hallway("Hallway"), " ");  // Hallway 
		Square d = new Square ( new Door("d"), "d"); 
		Square p1 = new Spawn(characterList.get(0) ,"1" ) ;  // Miss_Scarlett
		Square p2 = new Spawn(characterList.get(1) ,"2" ) ; //Colonel_Mustard
		Square p3 = new Spawn(characterList.get(2) ,"3" ) ; //Mrs_White
		Square p4 = new Spawn(characterList.get(3) ,"4" ) ; //The_Reverend_Green
		Square p5 = new Spawn(characterList.get(4) ,"5" ) ; //Mrs_Peacock
		Square p6 = new Spawn(characterList.get(5) ,"6" ) ; //Professor_Plum
		Square z =  (new blankSpace("b" , "/") ); 
		
		Square square1[][]  = {
				{z,z,z,z,z,z,z,z,z,z,p1,z,z,z,z,p2,z,z,z,z,z,z,z,z,z} ,
				{z,k,k,k,k,k,s1,z,H,H,H,B,B,B,B,H,H,H,z,c,c,c,c,c,c } ,
				{z,k,k,k,k,k,k,H,H,B,B,B,B,B,B,B,B,H,H,c,c,c,c,c,c} , 
				{z,k,k,k,k,k,k,H,H,B,B,B,B,B,B,B,B,H,H,c,c,c,c,c,c} , 
				{z,k,k,k,k,k,k,H,H,B,B,B,B,B,B,B,B,H,H,d,c,c,c,c,c} , 
				{z,k,k,k,k,k,k,H,H,d,B,B,B,B,B,B,d,H,H,H,c,c,c,s2,z} ,
				{z,k,k,k,k,d,k,H,H,B,B,B,B,B,B,B,B,H,H,H,H,H,H,H,p3} ,
				{z,H,H,H,H,H,H,H,H,B,B,B,B,B,B,B,B,H,H,H,H,H,H,H,z} ,
				{z,z,H,H,H,H,H,H,H,H,H,H,H,H,H,H,H,H,H,b,b,b,b,b,b} ,
				{z,D,D,D,D,D,H,H,H,H,H,H,H,H,H,H,H,H,H,d,b,b,b,b,b} ,
				{z,D,D,D,D,D,D,D,D,H,H,cl,cl,cl,cl,cl,H,H,H,b,b,b,b,b,b} ,
				{z,D,D,D,D,D,D,D,D,H,H,cl,cl,cl,cl,cl,H,H,H,b,b,b,b,b,b} ,
				{z,D,D,D,D,D,D,D,d,H,H,cl,cl,cl,cl,cl,H,H,H,b,b,b,b,d,b} ,
				{z,D,D,D,D,D,D,D,D,H,H,cl,cl,cl,cl,cl,H,H,H,H,H,H,H,H,z} ,
				{z,D,D,D,D,D,D,D,D,H,H,cl,cl,cl,cl,cl,H,H,H,l,l,d,l,l,z} ,
				{z,D,D,D,D,D,D,d,D,H,H,cl,cl,cl,cl,cl,H,H,l,l,l,l,l,l,l} ,
				{z,H,H,H,H,H,H,H,H,H,H,cl,cl,cl,cl,cl,H,H,d,l,l,l,l,l,l} ,
				{z,p4,H,H,H,H,H,H,H,H,H,H,H,H,H,H,H,H,l,l,l,l,l,l,l} ,
				{z,z,H,H,H,H,H,H,H,H,h,h,d,d,h,h,H,H,H,l,l,l,l,l,z} ,
				{z,s3,L,L,L,L,L,d,H,H,h,h,h,h,h,h,H,H,H,H,H,H,H,p5,z} ,
				{z,L,L,L,L,L,L,L,H,H,h,h,h,h,h,d,H,H,H,H,H,H,H,z,z} ,
				{z,L,L,L,L,L,L,L,H,H,h,h,h,h,h,h,H,H,d,s,s,s,s,s,s4} ,
				{z,L,L,L,L,L,L,L,H,H,h,h,h,h,h,h,H,H,s,s,s,s,s,s,s} ,
				{z,L,L,L,L,L,L,L,H,H,h,h,h,h,h,h,H,H,s,s,s,s,s,s,s} ,
				{z,L,L,L,L,L,L,L,p6,H,h,h,h,h,h,h,H,H,s,s,s,s,s,s,s} 
				
		};
		square = square1 ;  
		
	}
	
	
	/*
	 * In this method It will generate random number and by the number it generated 
	 * it will go get the correspond element in Character, room , weapon 
	 * by index and add the card to the solution list and the card that left will 
	 * add to the cardLeft list 
	 */
	public void solutionSetup(){
		cardLeft.clear() ; // clear the cardLeft list before using 
		//randomNum = minimum + (int)(Math.random()*maximum); 
		int randomNum = (int)(Math.random()*charactersCard.size()-1);
		solution.add(charactersCard.get(randomNum)); 
		for (int i=0 ; i<charactersCard.size() ;i++){
			if(i != randomNum ){
				cardLeft.add(charactersCard.get(i)); 
			}
		}
		
		randomNum = (int)(Math.random()*roomCard.size()-1); // dnt need -2 because clue didnt add to the card 
		solution.add(roomCard.get(randomNum)); 
		for (int i=0 ; i<roomCard.size() ;i++){
			if(i != randomNum ){
				cardLeft.add(roomCard.get(i)); 
			}
		}	
		
		randomNum = (int)(Math.random()*weaponCard.size()-1);
		solution.add(weaponCard.get(randomNum)); 
		for (int i=0 ; i<weaponCard.size() ;i++){
			if(i != randomNum ){
				cardLeft.add(weaponCard.get(i)); 
			}
		}	
	}
	
	
	/*
	 * Deal the card left from solution to the player evenly 
	 */
	public void dealCard( ArrayList<Card> cardleft , ArrayList<Player> playerlist){
		for (int i = 0 ; i < playerlist.size() ; i++){
			int cardIndex = i ; 
			while(cardIndex < cardleft.size()){
				 playerlist.get(i).addContainsCard(cardleft.get(cardIndex)); 
				 System.out.println(i + " :"+ cardIndex ); 
				 cardIndex += playerlist.size() ;	 
			}
		}
	}
	
	
	
	public static void main (String args[]){
		Board b = new Board();
		for(Characters c: b.characterList){
			System.out.println("characterList "+ c.getName()); 
		}
		for(Weapon c: b.weaponList){
			System.out.println("weaponList "+ c.getName()); 
		}
		for(Room c: b.roomList){
			System.out.println("roomList: " + c.getName()); 
		}
		for(Card c:b.charactersCard){
			System.out.println("CharactersCard: " + c.getName()); 
		}
		for(Card c:b.weaponCard){
			System.out.println("weaponCard: " + c.getName()); 
		}
		for(Card c:b.roomCard){
			System.out.println("roomcard " +c.getName()); 
		}
		for(Card c:b.solution){
			System.out.println("solution: "+c.getName()); 
		}
		for(Card c:b.cardLeft){
			System.out.println("CLEFT: "+c.getName()); 
		}
		for( Player p : playerList){
			for(Card c : p.getContainsCard()){
				System.out.println(p.getIndex()
						+" :" + c.getName()); 
			}
		}
		
		for (int i =0 ; i<25 ; i++ ){//
			System.out.println();
			
			for (int ia =0 ; ia<25 ; ia++ ){
				System.out.print("|"+b.square[i][ia].getC());
			}
		}
		try { // this is return true 
//			Card n = new Card (new Room("kitchen"));
//			Card fromlist = cardLeft.get(0); 
//			System.out.println(fromlist.getName());
//			Card n1 = new Card (new Characters("Miss_Scarlett"));
//			System.out.println(n1.equals(fromlist)) ;
			
			Card n = new Card (new Room("kitchen"));
			
			Card n1 = new RoomCard (new Piece("kitchen", "Room"));
		
			System.out.println(n1.equals(n)) ;
		} catch (IllegalParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
}
