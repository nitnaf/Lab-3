package poker;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

// For some reason, all of our Jokers come out at an Ace of Diamonds.

public class JPokerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws exHand {

		// Royal Flush
		ArrayList<Card> testHand = new ArrayList<Card>();
		Card c1 = new Card(eSuit.WILD, eRank.JOKER);
		Card c2 = new Card(eSuit.DIAMONDS, eRank.KING);
		Card c3 = new Card(eSuit.DIAMONDS, eRank.QUEEN);
		Card c4 = new Card(eSuit.DIAMONDS, eRank.JACK);
		Card c5 = new Card(eSuit.DIAMONDS, eRank.TEN);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		Deck d = Deck.testDeck(testHand);

		Hand h = new Hand(d);
		ArrayList<Hand> x = new ArrayList<Hand>();
		x.add(h);

		System.out.println(h.ExplodeHand(x, 0).toString());

		assertEquals(95, h.PickBestHand(x).getHandStrength());

		// Natural Royal Flush
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.CLUBS, eRank.ACE);
		c2 = new Card(eSuit.CLUBS, eRank.KING);
		c3 = new Card(eSuit.CLUBS, eRank.QUEEN);
		c4 = new Card(eSuit.CLUBS, eRank.JACK);
		c5 = new Card(eSuit.CLUBS, eRank.TEN);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(100, h.PickBestHand(x).getHandStrength());
		System.out.println(h.PickBestHand(x).getHandStrength());

		// Joker Straight Flush
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.WILD, eRank.JOKER);
		c2 = new Card(eSuit.CLUBS, eRank.THREE);
		c3 = new Card(eSuit.CLUBS, eRank.FOUR);
		c4 = new Card(eSuit.CLUBS, eRank.FIVE);
		c5 = new Card(eSuit.CLUBS, eRank.TWO);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(90, h.PickBestHand(x).getHandStrength());

		// Straight Flush
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.CLUBS, eRank.SIX);
		c2 = new Card(eSuit.CLUBS, eRank.THREE);
		c3 = new Card(eSuit.CLUBS, eRank.FOUR);
		c4 = new Card(eSuit.CLUBS, eRank.FIVE);
		c5 = new Card(eSuit.CLUBS, eRank.TWO);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(90, h.PickBestHand(x).getHandStrength());

		// Joker Five of a kind
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.WILD, eRank.JOKER);
		c2 = new Card(eSuit.SPADES, eRank.THREE);
		c3 = new Card(eSuit.CLUBS, eRank.THREE);
		c4 = new Card(eSuit.DIAMONDS, eRank.THREE);
		c5 = new Card(eSuit.HEARTS, eRank.THREE);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(85, h.PickBestHand(x).getHandStrength());

		// Joker Four of a kind
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.WILD, eRank.JOKER);
		c2 = new Card(eSuit.CLUBS, eRank.THREE);
		c3 = new Card(eSuit.CLUBS, eRank.FIVE);
		c4 = new Card(eSuit.HEARTS, eRank.FIVE);
		c5 = new Card(eSuit.SPADES, eRank.FIVE);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(80, h.PickBestHand(x).getHandStrength());

		// Four of a kind
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.CLUBS, eRank.FIVE);
		c2 = new Card(eSuit.SPADES, eRank.TWO);
		c3 = new Card(eSuit.CLUBS, eRank.TWO);
		c4 = new Card(eSuit.HEARTS, eRank.TWO);
		c5 = new Card(eSuit.DIAMONDS, eRank.TWO);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(80, h.PickBestHand(x).getHandStrength());

		// Joker Full house
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.WILD, eRank.JOKER);
		c2 = new Card(eSuit.CLUBS, eRank.THREE);
		c3 = new Card(eSuit.HEARTS, eRank.THREE);
		c4 = new Card(eSuit.SPADES, eRank.TWO);
		c5 = new Card(eSuit.DIAMONDS, eRank.TWO);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(70, h.PickBestHand(x).getHandStrength());

		// Full House
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.CLUBS, eRank.THREE);
		c2 = new Card(eSuit.SPADES, eRank.THREE);
		c3 = new Card(eSuit.HEARTS, eRank.THREE);
		c4 = new Card(eSuit.SPADES, eRank.TWO);
		c5 = new Card(eSuit.CLUBS, eRank.TWO);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(70, h.PickBestHand(x).getHandStrength());

		// JOKER FLUSH
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.WILD, eRank.JOKER);
		c2 = new Card(eSuit.CLUBS, eRank.THREE);
		c3 = new Card(eSuit.CLUBS, eRank.FOUR);
		c4 = new Card(eSuit.CLUBS, eRank.FIVE);
		c5 = new Card(eSuit.CLUBS, eRank.TWO);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(60, h.PickBestHand(x).getHandStrength());

		// Flush
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.CLUBS, eRank.JACK);
		c2 = new Card(eSuit.CLUBS, eRank.THREE);
		c3 = new Card(eSuit.CLUBS, eRank.FOUR);
		c4 = new Card(eSuit.CLUBS, eRank.FIVE);
		c5 = new Card(eSuit.CLUBS, eRank.TWO);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(60, h.PickBestHand(x).getHandStrength());

		// JOKER STRAIGHT
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.WILD, eRank.JOKER);
		c2 = new Card(eSuit.SPADES, eRank.THREE);
		c3 = new Card(eSuit.CLUBS, eRank.FOUR);
		c4 = new Card(eSuit.CLUBS, eRank.FIVE);
		c5 = new Card(eSuit.CLUBS, eRank.TWO);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(50, h.PickBestHand(x).getHandStrength());

		// Straight 
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.SPADES, eRank.SIX);
		c2 = new Card(eSuit.CLUBS, eRank.THREE);
		c3 = new Card(eSuit.CLUBS, eRank.FOUR);
		c4 = new Card(eSuit.CLUBS, eRank.FIVE);
		c5 = new Card(eSuit.CLUBS, eRank.TWO);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(50, h.PickBestHand(x).getHandStrength());

		// JOKER 3OAK
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.WILD, eRank.JOKER);
		c2 = new Card(eSuit.CLUBS, eRank.THREE);
		c3 = new Card(eSuit.HEARTS, eRank.THREE);
		c4 = new Card(eSuit.CLUBS, eRank.FIVE);
		c5 = new Card(eSuit.CLUBS, eRank.TWO);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(40, h.PickBestHand(x).getHandStrength());

		// 3OAK 
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.DIAMONDS, eRank.THREE);
		c2 = new Card(eSuit.CLUBS, eRank.THREE);
		c3 = new Card(eSuit.HEARTS, eRank.THREE);
		c4 = new Card(eSuit.CLUBS, eRank.FIVE);
		c5 = new Card(eSuit.CLUBS, eRank.TWO);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(40, h.PickBestHand(x).getHandStrength());


		// 2 PAIR
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.SPADES, eRank.TWO);
		c2 = new Card(eSuit.CLUBS, eRank.THREE);
		c3 = new Card(eSuit.SPADES, eRank.THREE);
		c4 = new Card(eSuit.CLUBS, eRank.FIVE);
		c5 = new Card(eSuit.CLUBS, eRank.TWO);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(30, h.PickBestHand(x).getHandStrength());

		// JOKER PAIR
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.WILD, eRank.JOKER);
		c2 = new Card(eSuit.CLUBS, eRank.THREE);
		c3 = new Card(eSuit.CLUBS, eRank.FOUR);
		c4 = new Card(eSuit.SPADES, eRank.JACK);
		c5 = new Card(eSuit.CLUBS, eRank.TWO);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(20, h.PickBestHand(x).getHandStrength());

		// PAIR
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.SPADES, eRank.THREE);
		c2 = new Card(eSuit.CLUBS, eRank.THREE);
		c3 = new Card(eSuit.CLUBS, eRank.FOUR);
		c4 = new Card(eSuit.CLUBS, eRank.FIVE);
		c5 = new Card(eSuit.CLUBS, eRank.TWO);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(20, h.PickBestHand(x).getHandStrength());

		// HIGH CARD
		testHand = new ArrayList<Card>();
		c1 = new Card(eSuit.SPADES, eRank.ACE);
		c2 = new Card(eSuit.CLUBS, eRank.THREE);
		c3 = new Card(eSuit.CLUBS, eRank.FOUR);
		c4 = new Card(eSuit.CLUBS, eRank.NINE);
		c5 = new Card(eSuit.CLUBS, eRank.TWO);

		testHand.add(c1);
		testHand.add(c2);
		testHand.add(c3);
		testHand.add(c4);
		testHand.add(c5);

		d = Deck.testDeck(testHand);

		h = new Hand(d);
		x = new ArrayList<Hand>();
		x.add(h);

		assertEquals(10, h.PickBestHand(x).getHandStrength());
	}
}

		