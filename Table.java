import java.util.ArrayList;

public class Table {
final static int MAXPLAYER=4;
private Deck deck;
private Player[] player;
private Dealer dealer;
private int[] pos_betArray;
private int nDecks;
public Table(int nDeck){
	this.nDecks = nDecks;
	this.deck = new Deck(nDeck);
	this.player = new Player[MAXPLAYER];	
}
public void set_player(int pos, Player p){
	this.player[pos]=p;
}
public Player[] get_player(){
	return this.player;
}
public void set_dealer(Dealer d){
	this.dealer=d;
}
public Card get_face_up_card_of_dealer(){
	ArrayList<Card> getcard =this.dealer.getOneRoundCard();
	Card card = getcard.get(1);
	return card;
}
private void ask_each_player_about_bets(){
	 this.pos_betArray = new int[this.player.length];
	    for (int i = 0; i < this.player.length; i++) {
	      if (this.player[i] != null)
	      {
	        this.player[i].sayHello();
	        int bet = this.player[i].makeBet();
	        if (bet > this.player[i].getCurrentChips())
	        {
	          //this.player[i].setBet(0);
	          this.pos_betArray[i] = 0;
	        }
	        else
	        {
	          this.pos_betArray[i] = this.player[i].makeBet();
	        }
	      }
	    }
}
private void distribute_cards_to_dealer_and_players(){
	 for (int i = 0; i < this.player.length; i++) {
	      if ((this.player[i] != null) && (this.pos_betArray[i] != 0))
	      {
	        ArrayList<Card> playersCard = new ArrayList();
	        playersCard.add(this.deck.getOneCard(true));
	        playersCard.add(this.deck.getOneCard(true));
	        this.player[i].setOneRoundCard(playersCard);
	      }
	    }
	    if (this.dealer != null)
	    {
	      ArrayList<Card> dealersCard = new ArrayList();
	      dealersCard.add(this.deck.getOneCard(true));
	      dealersCard.add(this.deck.getOneCard(false));
	      this.dealer.setOneRoundCard(dealersCard);
	      System.out.println("Dealer's face up card is ");
	      Card dealersFaceUpCard = get_face_up_card_of_dealer();
	      dealersFaceUpCard.printCard();
	    }	   
}
private void ask_each_player_about_hits()
{
  for (int i = 0; i < this.player.length; i++) {
    if ((this.player[i] != null) && (this.pos_betArray[i] != 0))
    {
      System.out.print(this.player[i].getName() + "'s Cards now:");
      this.player[i].printAllCard();
      hit_process(i, this.player[i].getOneRoundCard());
      System.out.println(this.player[i].getName() + "'s hit is over!");
    }
  }
}
private void hit_process(int pos, ArrayList<Card> cards)
{
  boolean hit;
  do
  {
    hit = this.player[pos].hit_me(this);
    if (hit)
    {
      cards.add(this.deck.getOneCard(true));
      this.player[pos].setOneRoundCard(cards);
      System.out.print("Hit! ");
      System.out.print(this.player[pos].getName() + "'s Cards now:");
      this.player[pos].printAllCard();
      if (this.player[pos].getTotalValue() > 21) {
        hit = false;
      }
    }
    else
    {
      System.out.println("Pass hit!");
    }
  } while (hit);
}
 private void ask_dealer_about_hits()
 {
   ArrayList<Card> cards = this.dealer.getOneRoundCard();
   boolean hit;
   do
   {
     hit = this.dealer.hit_me(this);
     if (hit)
     {
       cards.add(this.deck.getOneCard(true));
       this.dealer.setOneRoundCard(cards);
     }
     if (this.dealer.getTotalValue() > 21) {
       hit = false;
     }
   } while (hit);
   System.out.println("Dealer's hit is over!");
 }
 private void calculate_chips(){
	 int dealersCradValue = this.dealer.getTotalValue();
	    System.out.print("Dealer's card value is " + dealersCradValue + " ,Cards:");
	    this.dealer.printAllCard();
	    for (int i = 0; i < this.player.length; i++) {
	      if ((this.player[i] != null) && (this.pos_betArray[i] != 0))
	      {
	        System.out.print(this.player[i].getName() + "'s Cards: ");
	        this.player[i].printAllCard();
	        System.out.print(this.player[i].getName() + " card value is " + this.player[i].getTotalValue());
	        if (this.player[i].getTotalValue() > 21)
	        {
	          if (this.dealer.getTotalValue() > 21)
	          {
	            System.out.println(", chips have no change!, the Chips now is: " + this.player[i].getCurrentChips());
	          }
	          else
	          {
	            this.player[i].increaseChips(-this.pos_betArray[i]);
	            System.out.println(", Loss " + this.pos_betArray[i] + " Chips, the Chips now is: " + this.player[i].getCurrentChips());
	          }
	        }
	        else if (this.player[i].getTotalValue() == 21)
	        {
	          if ((this.player[i].getOneRoundCard().size() == 2) && (this.player[i].hasAce()))
	          {
	            if (this.dealer.getTotalValue() != 21)
	            {
	              this.player[i].increaseChips(this.pos_betArray[i] * 2);
	              System.out.println(",Black jack!!! Get " + this.pos_betArray[i] + " Chips, the Chips now is: " + this.player[i].getCurrentChips());
	            }
	            else if ((this.dealer.getOneRoundCard().size() == 2) && (this.dealer.hasAce()))
	            {
	              System.out.println(",Black Jack!!!! But chips have no change!, the Chips now is: " + this.player[i].getCurrentChips());
	            }
	            else
	            {
	              this.player[i].increaseChips(this.pos_betArray[i] * 2);
	              System.out.println(",Black jack!!! Get " + this.pos_betArray[i] + " Chips, the Chips now is: " + this.player[i].getCurrentChips());
	            }
	          }
	          else if (this.dealer.getTotalValue() != 21)
	          {
	            this.player[i].increaseChips(this.pos_betArray[i] * 2);
	            System.out.println(",Get " + this.pos_betArray[i] + " Chips, the Chips now is: " + this.player[i].getCurrentChips());
	          }
	          else
	          {
	            System.out.println(",chips have no change!The Chips now is: " + this.player[i].getCurrentChips());
	          }
	        }
	        else if (this.dealer.getTotalValue() > 21)
	        {
	          this.player[i].increaseChips(this.pos_betArray[i]);
	          System.out.println(", Get " + this.pos_betArray[i] + " Chips, the Chips now is: " + this.player[i].getCurrentChips());
	        }
	        else if (this.dealer.getTotalValue() < this.player[i].getTotalValue())
	        {
	          this.player[i].increaseChips(this.pos_betArray[i]);
	          System.out.println(", Get " + this.pos_betArray[i] + " Chips, the Chips now is: " + this.player[i].getCurrentChips());
	        }
	        else if (this.dealer.getTotalValue() > this.player[i].getTotalValue())
	        {
	          this.player[i].increaseChips(-this.pos_betArray[i]);
	          System.out.println(", Loss " + this.pos_betArray[i] + " Chips, the Chips now is: " + this.player[i].getCurrentChips());
	        }
	        else
	        {
	          System.out.println(", chips have no change! The Chips now is: " + this.player[i].getCurrentChips());
	        }

	      }
	    }
 }
 public int[] get_players_bet()
 {
   return this.pos_betArray;
 }
 public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	} 
 
}

