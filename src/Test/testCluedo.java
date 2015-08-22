package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

import Board.Board;
import Card.Card;
import Card.weaponCard;
import Piece.Characters;
import Piece.IllegalParameterException;
import Piece.Room;
import Piece.Weapon;
import Player.Player;

public class testCluedo {
private ArrayList<Card> allCard = new ArrayList<Card>() ;
private ArrayList<Card> solutionCard = new ArrayList<Card>() ;
private ArrayList<Card> RoomCard = new ArrayList<Card>() ;
private ArrayList<Card> WeaponCard = new ArrayList<Card>() ;
private ArrayList<Card> CharactersCard = new ArrayList<Card>() ;
private ArrayList<Characters> charactersList = new ArrayList<Characters>() ;
private ArrayList<Weapon> weaponList = new ArrayList<Weapon>() ;
private ArrayList<Player> playerList = new ArrayList<Player>() ; 


 	@Test(expected = Piece.IllegalParameterException.class)
	public void testCreateCharacterWrongName() throws IllegalParameterException {
			Characters c = new Characters("hello");
	}
 	
 	@Test(expected = Piece.IllegalParameterException.class)
	public void testCreateRoomWrongName() throws IllegalParameterException {
			Characters c = new Characters("hello");
	}
 	
 	@Test(expected = Piece.IllegalParameterException.class)
	public void testCreateWeaponWrongName() throws IllegalParameterException {
			Characters c = new Characters("hello");
	}
 	@Test
	public void testCreateCharactereRightName() throws IllegalParameterException {
			Characters c = new Characters("Miss_Scarlett");
			assertTrue (c.getName().equals("Miss_Scarlett")); 
	}
 	
 	@Test
	public void testCreateWeaponRightName() throws IllegalParameterException {
			Weapon c = new Weapon("Candlestick");
			assertTrue (c.getName().equals("Candlestick")); 
 	}
 	
 	@Test
	public void testCreateRoomRightName() throws IllegalParameterException {
			Room c = new Room("study");
			assertTrue (c.getName().equals("study"));
 	}
 	
 	@Test
 	public void testCreatingPlayer(){
 		Player p = new Player(7);
 		assertTrue (p.getIndex() == 7 ); 
 	}
 	
 	@Test 
 	public void testCreateCard() throws IllegalParameterException{
 		Characters c = new Characters("Miss_Scarlett");
 		Card card = new Card(c);
 		assertTrue(card.getName().equals(c.getName())); 
 		
 	}
 	
 	@Test 
 	public void testBoard(){
 		Board b = new Board() ;
 	}
 	
	
}
