
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
	d=dealer;
}
public Card get_face_up_card_of_dealer(){
	return ;
}
private void ask_each_player_about_bets(){
	

	
}
private void distribute_cards_to_dealer_and_players(){
	
}
		
	
}

