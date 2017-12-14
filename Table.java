import java.util.ArrayList;

public class Table {
final static int MAXPLAYER=4;
private Deck deck;
private Player[] player;
private Dealer dealer;
private int[] pos_betArray;
private Table(int nDeck){
	deck = new Deck(nDeck);
	player = new Player[MAXPLAYER];	
}
public void set_player(int pos, Player p){
	p=player[pos];
}
public Player[] get_player(){
	return player;
}
public void set_dealer(Dealer d){
	dealer=d;
}
public Card get_face_up_card_of_dealer(){
	ArrayList<Card> getcard =dealer.getOneRoundCard();
	Card card = getcard.get(1);
	return card;
}
private void ask_each_player_about_bets(){
	Player(name,chips);
	System.out.println("Hello, I am " + name + ".");
	System.out.println("I have " + chips + " chips.");

	
}
private void distribute_cards_to_dealer_and_players(){
	
}
		
	
}

