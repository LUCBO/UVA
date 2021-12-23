package _131;

import java.util.Scanner;

/**
 * Solution for UVa problem 131 The Psychic Poker Player
 * 
 * @author Lucas Borg 2019-08-26
 */
public class Main {

	private static Scanner input;

	private enum Suit {
		C, D, H, S
	}

	private String[] hand_values = new String[] { "straight-flush", "four-of-a-kind", "full-house", "flush", "straight",
			"three-of-a-kind", "two-pairs", "one-pair", "highest-card" };
	private boolean[] used_card;
	private int bestHand;
	private Card[] card;
	private int[] cardValue = new int[14];
	private boolean[][] deck;

	public void start() {
		input = new Scanner(System.in);
		String[] line;
		String bestHand;
		while (input.hasNextLine()) {
			line = input.nextLine().split(" ");
			bestHand = getBestHand(line);
			printResult(line, bestHand);
		}
	}

	private String getBestHand(String[] line) {
		deck = new boolean[4][14];
		used_card = new boolean[5];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 14; j++)
				deck[i][j] = false;
		}
		card = new Card[10];
		for (int i = 0; i < card.length; i++) {
			card[i] = new Card(getCardValue(line[i]), getCardSuit(line[i]));
		}
		bestHand = 8;
		findBestHand(0);
		return this.hand_values[bestHand];
	}

	private int getColorValue(Card card) {
		if (card.suit == Suit.C)
			return 0;
		else if (card.suit == Suit.D)
			return 1;
		else if (card.suit == Suit.H)
			return 2;
		return 3;
	}

	public void findBestHand(int n) {
		int i;
		if (n == 5) {
			int count = 0, num, color;
			for (i = 0; i < 5; i++)
				if (used_card[i]) {
					num = card[i].value;
					color = getColorValue(card[i]);
					cardValue[num]++;
					deck[color][num] = true;
					count++;
				}

			for (i = 5; count < 5; count++, i++) {
				num = card[i].value;
				color = getColorValue(card[i]);
				cardValue[num]++;
				deck[color][num] = true;
			}

			int current_score = evaluate_hand();
			if (bestHand > current_score)
				bestHand = current_score;

			for (i = 0; i < 4; i++)
				for (int j = 0; j < 14; j++)
					deck[i][j] = false;
			for (i = 0; i < 14; i++)
				cardValue[i] = 0;
		} else
			for (i = n; i < 5; i++) {
				used_card[i] = true;
				findBestHand(n + 1);
				used_card[i] = false;
				findBestHand(n + 1);
			}

	}

	private Suit getCardSuit(String card) {
		char c = card.charAt(1);
		if (c == 'C')
			return Suit.C;
		else if (c == 'D')
			return Suit.D;
		else if (c == 'H')
			return Suit.H;
		return Suit.S;
	}

	private int getCardValue(String card) {
		char c = card.charAt(0);
		if (c == 'A')
			return 1;
		else if (c == 'K')
			return 13;
		else if (c == 'Q')
			return 12;
		else if (c == 'J')
			return 11;
		else if (c == 'T')
			return 10;
		return Integer.parseInt(c + "");
	}

	private void printResult(String[] line, String bestHand) {
		System.out.print("Hand:");
		for (int i = 0; i < line.length; i++) {
			System.out.print(" " + line[i]);
			if (i == 4)
				System.out.print(" Deck:");
		}
		System.out.print(" Best hand: " + bestHand + "\n");
	}

	private class Card {
		private int value;
		private Suit suit;

		public Card(int value, Suit suit) {
			this.value = value;
			this.suit = suit;
		}
	}

	public int evaluate_hand() {
		int currentHand = 8, pair = 0, three = 0, i;
		for (i = 1; i <= 13; i++) {
			if (cardValue[i] == 4) {
				currentHand = 1;
				break;
			} else if (cardValue[i] == 3)
				three = 1;
			else if (cardValue[i] == 2)
				pair++;
		}

		if (three == 1 && pair == 1)
			currentHand = 2;
		else if (three == 1)
			currentHand = 5;
		else if (pair == 2)
			currentHand = 6;
		else if (pair == 1)
			currentHand = 7;

		if (currentHand < 8)
			return currentHand;
		if (cardValue[1] == 1 && cardValue[10] == 1 && cardValue[11] == 1 && cardValue[12] == 1 && cardValue[13] == 1) {
			currentHand = 4;
			for (i = 0; i < 4; i++)
				if (deck[i][1] && deck[i][10] && deck[i][11] && deck[i][12] && deck[i][13])
					currentHand = 0;
		}
		for (i = 1; i <= 9; i++)
			if (cardValue[i] == 1 && cardValue[i + 1] == 1 && cardValue[i + 2] == 1 && cardValue[i + 3] == 1
					&& cardValue[i + 4] == 1) {
				currentHand = 4;
				for (int j = 0; j < 4; j++)
					if (deck[j][i] && deck[j][i + 1] && deck[j][i + 2] && deck[j][i + 2] && deck[j][i + 4])
						currentHand = 0;
			}

		if (currentHand < 8)
			return currentHand;

		int count = 0;
		for (i = 0; i < 4; i++, count = 0) {
			for (int j = 1; j <= 13; j++)
				if (deck[i][j])
					count++;
			if (count == 5)
				return 3;
		}

		return 8;
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.start();
	}
}