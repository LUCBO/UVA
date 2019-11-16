package _402;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Solution for UVa problem 402 M*A*S*H
 * 
 * @author Lucas Borg 2019-11-16
 */
public class Main {

	private static Scanner input;

	public void start() {
		input = new Scanner(System.in);
		int counter = 1;
		while (input.hasNext()) {
			System.out.println("Selection #" + counter + "\n" + getPositionsInLine(input.nextLine()) + "\n");
			counter++;
		}
	}

	private String getPositionsInLine(String line) {
		String[] lineNbrs = line.split(" ");
		int participants = Integer.parseInt(lineNbrs[0]);
		int nbrOfSpots = Integer.parseInt(lineNbrs[1]);
		LinkedList<Integer> listCardNbr = new LinkedList<Integer>();

		for (int i = 2; i < 22; i++) {
			listCardNbr.add(Integer.parseInt(lineNbrs[i]));
		}

		return calcPositions(nbrOfSpots, participants, listCardNbr);
	}

	private String calcPositions(int nbrOfSpots, int participants, LinkedList<Integer> listCardNbr) {
		int leftInLine = participants;
		boolean[] isInLine = new boolean[participants + 1];
		for (int i = 0; i < isInLine.length; i++)
			isInLine[i] = true;

		while (!listCardNbr.isEmpty() && leftInLine > nbrOfSpots) {
			int counter = 0;
			int nbr = listCardNbr.removeFirst();
			for (int j = 1; j <= participants; j++) {
				if (isInLine[j] == true)
					counter++;
				if (counter == nbr) {
					counter = 0;
					isInLine[j] = false;
					leftInLine--;
				}
				if (leftInLine == nbrOfSpots) {
					break;
				}

			}
		}

		StringBuilder res = new StringBuilder();

		for (int i = 1; i <= participants; i++) {
			if (isInLine[i]) {
				res.append(i + " ");
			}
		}

		return res.substring(0, res.length() - 1);
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.start();
	}
}
