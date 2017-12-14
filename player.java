
public class Player extends Person{

		private String name;
		private int chips;
		private int bet;
		//private ArrayList<Card> oneRoundCard;
		public Player(String name, int chips) {
		this.name = name;
		this.chips=chips;
		//oneRoundCard = new ArrayList<Card>();
	}
		
		public String getName() {
			return name;
		}
		public int makeBet() {
			int bet = 1;
			return bet;
		}
		/*public void setOneRoundCard(ArrayList cards) {
			
			oneRoundCard=cards;
		}*/
		public boolean hit_me(Table table) {
			if(getTotalValue()<17) {
				return true;
			}
			else {
				return false;
			}
		}
		/*public int getTotalValue() {
			
			int Ace_count = 0;
			int total_value = 0;
			for (Card c : oneRoundCard) {
				if (Ace_count == 0 && c.getRank() == 1) {
					Ace_count = 1;
					continue;
				} else {
					if (c.getRank() == 11 || c.getRank() == 12 || c.getRank() == 13)
						total_value += 10;
					else
						total_value += c.getRank();
				}
			}
			if (Ace_count != 0) {
				if (total_value < 11) {
					total_value += 11;
				} else {
					total_value += 1;
				}

			}
			return total_value;
		}*/
		public int getCurrentChips() {
			return chips;
		}
		public void increaseChips (int diff) {
			chips+=diff;
		}
		public void sayHello() {
			System.out.println("Hello, I am " + name + ".");
			System.out.println("I have " + chips + " chips.");
		}



	}
