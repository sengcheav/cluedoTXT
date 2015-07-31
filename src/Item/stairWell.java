package Item;

import Piece.Room;

public class stairWell extends Item {
private static String type = "stairWell"; 
private Room inRoom ; 
private Room connectRoom ; 

	public stairWell(String i, String t) {
		super(i, type);
		// TODO Auto-generated constructor stub
	}
	
	public void setInRoom(Room r ){
		this.inRoom =r ; 
	}
	
	public Room getInRoom(){
		return this.inRoom ; 
	}
	
	public String getInRoomName(){
		return this.inRoom.getName() ; 
	}
	
	public void connectRoom(Room r ){
		this.connectRoom =r ; 
	}
	
	public Room getConnectRoom(){
		return this.connectRoom;
	}

}
