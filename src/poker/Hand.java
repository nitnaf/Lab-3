package poker;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hand{
	private ArrayList<Card> CardsInHand;

	private int HandStrength;
	private int HiHand;
	private int LoHand;
	private int Kicker;
	private boolean bScored = false;

	private boolean Flush;
	private boolean Straight;
	private boolean Ace;
	private boolean Joker;
	private int numberOfJokers;

	public Hand(Deck d) {
		ArrayList<Card> Import = new ArrayList<Card>();
		for (int x = 0; x < 5; x++) {
			Import.add(d.drawFromDeck());
		}
		CardsInHand = Import;
	}

	public ArrayList<Card> getCards() {
		return CardsInHand;
	}

	public int getHandStrength() {
		return HandStrength;
	}

	public int getKicker() {
		return Kicker;
	}

	public int getHighPairStrength() {
		return HiHand;
	}

	public int getLowPairStrength() {
		return LoHand;
	}

	public boolean getAce() {
		return Ace;
	}
	
	public static Hand EvalHand(ArrayList<Card> SeededHand) {
		Deck d = new Deck();
		Hand h = new Hand(d);
		// Saves the cards that aren't the joker so that the sorted hand doesn't
		// get overwritten
		ArrayList<Card> SavedArray = new ArrayList<Card>();
		SavedArray.add(h.CardsInHand.get(eCardNo.SecondCard.getCardNo()));
		SavedArray.add(h.CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
		SavedArray.add(h.CardsInHand.get(eCardNo.FourthCard.getCardNo()));
		SavedArray.add(h.CardsInHand.get(eCardNo.FifthCard.getCardNo()));

		h.CardsInHand = SeededHand;
		/** if (h.CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == eRank.JOKER) {
			for (short i = 0; i <= 3; i++) {
				eSuit SuitValue = eSuit.values()[i];
				for (short j = 0; j <= 12; j++) {
					eRank RankValue = eRank.values()[j];
					Card NewCard = new Card(SuitValue, RankValue);
					h.CardsInHand.set(eCardNo.FirstCard.getCardNo(), NewCard);

					Hand EvaluatedHand = EvalHand(h.CardsInHand);
					int highestHandSoFar = 0;
					if (highestHandSoFar <= EvaluatedHand.getHandStrength()) {
						highestHandSoFar = EvaluatedHand.getHandStrength();
					}
				   h.CardsInHand = new ; 
				}
			}
		} else {} */
			
		h.EvalHand();
		

		return h;
	}

	public void EvalHand() {
		// Evaluates if the hand is a flush and/or straight then figures out
		// the hand's strength attributes

		// Sort the cards!
		Collections.sort(CardsInHand, Card.CardRank);

		
		// Ace Evaluation
		for (int i = 0; i < 5; i++){
			if (CardsInHand.get(i).getRank() == eRank.ACE) {
				Ace = true;
			}
		}
		
		// Flush Evaluation
		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getSuit()) {
			Flush = true;
		} else {
			Flush = false;
		}

		// Straight Evaluation
		if (Ace) {
			// Looks for Ace, King, Queen, Jack, 10
			if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == eRank.KING
					&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.QUEEN
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo())
							.getRank() == eRank.JACK
					&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN) {
				Straight = true;
				// Looks for Ace, 2, 3, 4, 5
			} else if (CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TWO
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo())
							.getRank() == eRank.THREE
					&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.FOUR
					&& CardsInHand.get(eCardNo.SecondCard.getCardNo())
							.getRank() == eRank.FIVE) {
				Straight = true;
			} else {
				Straight = false;
			}
			// Looks for straight without Ace
		} else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
				.getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo())
				.getRank().getRank() + 1
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() + 2
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getRank()
						.getRank() + 3
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank().getRank() + 4) {
			Straight = true;
		} else {
			Straight = false;
		}

		// Evaluates the hand type
		
		// Royal Flush with Joker
		if (Joker == true
				&& Straight == true
				&& Flush == true
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN
				&& Ace) {
			ScoreHand(eHandStrength.RoyalFlush, 0, 0, 0);
		}
		
		// Natural Royal Flush
		if (Straight == true
				&& Flush == true
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN
				&& Ace) {
			ScoreHand(eHandStrength.NaturalRoyalFlush, 0, 0, 0);
		}

		// Straight Flush
		else if (Straight == true && Flush == true) {
			ScoreHand(eHandStrength.StraightFlush,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0, 0);
		}
		// Five of a Kind
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.FiveOfAKind,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,
					CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()
							.getRank());
		}
		
		// Four of a Kind
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.FourOfAKind,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,
					CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()
							.getRank());
		}

		else if (CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.FourOfAKind,
					CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()
							.getRank(), 0,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank());
		}

		// Full House
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.ThirdCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.FullHouse,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(),
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
							.getRank(), 0);
		}

		else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.FifthCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.SecondCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.FullHouse,
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
							.getRank(),
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0);
		}

		// Flush
		else if (Flush) {
			ScoreHand(eHandStrength.Flush,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0, 0);
		}

		// Straight
		else if (Straight) {
			ScoreHand(eHandStrength.Straight,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0, 0);
		}

		// Three of a Kind
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.ThirdCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.ThreeOfAKind,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
							.getRank());
		}

		else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.FourthCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.ThreeOfAKind,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
							.getRank(), 0,
					CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()
							.getRank());
		} else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.FifthCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.ThreeOfAKind,
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
							.getRank(), 0,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank());
		}

		// Two Pair
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getRank()
				&& (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getRank())) {
			ScoreHand(eHandStrength.TwoPair,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(),
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
							.getRank(),
					CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()
							.getRank());
		} else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getRank()
				&& (CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank())) {
			ScoreHand(eHandStrength.TwoPair,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(),
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
							.getRank(),
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
							.getRank());
		} else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.ThirdCard.getCardNo()).getRank()
				&& (CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank())) {
			ScoreHand(eHandStrength.TwoPair,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
							.getRank(),
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
							.getRank(),
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank());
		}

		// Pair
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.Pair,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
							.getRank());
		} else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.ThirdCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.Pair,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
							.getRank(), 0,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank());
		} else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.FourthCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.Pair,
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
							.getRank(), 0,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank());
		} else if (CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.FifthCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.Pair,
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
							.getRank(), 0,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank());
		}

		else {
			ScoreHand(eHandStrength.HighCard,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
							.getRank());
		}
	}
	
	
	public ArrayList<Hand> ExplodeHand(ArrayList<Hand> Hands, int SubPosition){
		ArrayList<Hand> PossibleHands = new ArrayList<Hand>();
		
		for (Hand h : Hands){
			if (h.getCards().get(SubPosition).getRank() == eRank.JOKER){
				Joker = true;
				
				for (short i = 0; i <= 3; i++) {
					eSuit SuitValue = eSuit.values()[i];
					for (short j = 0; j <= 12; j++) {
						eRank RankValue = eRank.values()[j];
						Card NewCard = new Card(SuitValue, RankValue);

						h.getCards().set(SubPosition, NewCard);

						PossibleHands.add(h);
						
					}
				}
				
			} else PossibleHands.add(h);
		}
		return PossibleHands;
	}
		
	public Hand PickBestHand(ArrayList<Hand> Hands) throws exHand {

		try {
			for (int i = 0; i < Hands.get(0).getCards().size(); i++) {
				Hands = ExplodeHand(Hands, i);
			}

			for (Hand h : Hands) {
				h.EvalHand();
			}

			Collections.sort(Hands, Hand.HandRank);

			if (Joker) {

				if (Hands.get(Hands.size()-1).HandStrength == Hands.get(1).HandStrength
						&& Hands.get(0).HiHand == Hands.get(1).HiHand
						&& Hands.get(0).LoHand == Hands.get(1).LoHand
						&& Hands.get(0).Kicker == Hands.get(1).Kicker) {
					    throw new exHand(Hands.get(0));
				}
			}
		} catch (exHand e) {
			System.out.println("Two or more hands with identicle scores."
					+ " Hand Strength: " + Hands.get(0).getHandStrength()
					+ " High Hand: " + Hands.get(0).getHighPairStrength()
					+ " Low Hand: " + Hands.get(0).getLowPairStrength()
					+ " Kicker: " + Hands.get(0).getKicker());
			e.printStackTrace();
		}
		return Hands.get(0);
	}
	
/*	public ArrayList<Hand> ExplodeHand(Hand h) {
		ArrayList<Hand> ExplodedHands = new ArrayList<Hand>();
		ExplodedHands.add(h);

		for (short x = 0; x < 5; x++) {
			if (h.getCards().get(x).getRank() == eRank.JOKER) {
				
				//for (Hand hand : ExplodedHands){

				for (short i = 0; i <= 3; i++) {
					eSuit SuitValue = eSuit.values()[i];
					for (short j = 0; j <= 12; j++) {
						eRank RankValue = eRank.values()[j];
						Card NewCard = new Card(SuitValue, RankValue);

						h.getCards().set(x, NewCard);
						ExplodedHands.add(h);
					}
				}

			}

		}
		return ExplodedHands;
	}*/
	

	
/*	public ArrayList<Hand> ExplodeHand(Hand h) {
		ArrayList<Hand> ExplodedHands = new ArrayList<Hand>();
		Collections.sort(h.getCards());

		if (h.getCards().get(0).getRank() == eRank.JOKER) {
			for (short i = 0; i <= 3; i++) {
				eSuit SuitValue = eSuit.values()[i];
				for (short j = 0; j <= 12; j++) {
					eRank RankValue = eRank.values()[j];
					Card NewCard = new Card(SuitValue, RankValue);
					h.getCards().set(0, NewCard);
					ExplodedHands.add(h);
				}

			}
		}
		ExplodedHands.add(h);
		return ExplodedHands;
	}*/
	
/*	public ArrayList<Hand> AllPossibleHands(ArrayList<Hand> h){
		for (Hand hand : h) {
			for (Card card : hand) {
				if (card.getRank() == eRank.JOKER){
					
				
					for (short i = 0; i <= 3; i++) {
						eSuit SuitValue = eSuit.values()[i];
						for (short j = 0; j <= 12; j++) {
							eRank RankValue = eRank.values()[j];
							Card NewCard = new Card(SuitValue, RankValue);
							hand.set(x, NewCard);
							// How do I set the specific card that is being iterated on to the newly made Card?
							
							h.add(hand);
						}
					}
					
				}
			}
		}
	}*/
	
/*	public ArrayList<Hand> AllPossibleHands() {

		// Joker Evaluation
		for (int i = 0; i < 5; i++) {
			if (CardsInHand.get(i).getRank() == eRank.JOKER) {
				Joker = true;
				numberOfJokers++;
			}
		}

		ArrayList<Hand> allPossibleHands = new ArrayList<Hand>();
		if (Joker) {

			for (short x = 0; x < numberOfJokers; x++) {

				for (short i = 0; i <= 3; i++) {
					eSuit SuitValue = eSuit.values()[i];
					for (short j = 0; j <= 12; j++) {
						eRank RankValue = eRank.values()[j];
						Card NewCard = new Card(SuitValue, RankValue);
						CardsInHand.set(x, NewCard);

						// ArrayList<Card> Hand = new ArrayList<Card>();
						// Hand.addAll(getCards());

						allPossibleHands.add(CardsInHand);

						// How do I get this to work? It complains because I'm
						// passing in an ArrayList<Card>
						// as a parameter for adding to an ArrayList<Hand>.

						// I need something like, ArrayList<ArrayList<Card>>
						// since a hand is just an ArrayList<Card>
					}
				}
			}
			return allPossibleHands;
		}

		return allPossibleHands.add(CardsInHand);

	}*/
	
	private void ScoreHand(eHandStrength hST, int HiHand, int LoHand, int Kicker) {
		this.HandStrength = hST.getHandStrength();
		this.HiHand = HiHand;
		this.LoHand = LoHand;
		this.Kicker = Kicker;
		this.bScored = true;

	}
	
/*	public static Hand PickBestHand(ArrayList<Hand> Hands) throws exHand {
		for(Hand h : Hands){
			h.EvalHand();
		}
		Hands.sort(HandStrength);
		if (Hands.get(0).HandRank == Hands.get(1).HandRank) {
			exHand e = new exHand("There exist two Hands of identical strength.");
			throw e;
		}
		return Hands.get(0);
	}*/

	/**
	 * Custom sort to figure the best hand in an array of hands
	 */
	public static Comparator<Hand> HandRank = new Comparator<Hand>() {

		public int compare(Hand h1, Hand h2) {

			int result = 0;

			result = h2.HandStrength - h1.HandStrength;

			if (result != 0) {
				return result;
			}

			result = h2.HiHand - h1.HiHand;
			if (result != 0) {
				return result;
			}

			result = h2.LoHand = h1.LoHand;
			if (result != 0) {
				return result;
			}

			result = h2.Kicker = h1.Kicker;
			if (result != 0) {
				return result;
			}

			return 0;
		}
	};
}
