package _278;

import java.util.Scanner;

/**
 * Solution for UVa problem 278 Chess
 * 
 * @author Lucas Borg 2019-08-26
 */
public class Main {

	private static Scanner input;

	public void start() {
		input = new Scanner(System.in);
		int nbr_of_problems = Integer.parseInt(input.nextLine());
		while (nbr_of_problems > 0) {
			String line = input.nextLine();
			char piece = line.charAt(0);
			int row = Integer.parseInt(line.split(" ")[1]);
			int column = Integer.parseInt(line.split(" ")[2]);
			processProblem(piece, row, column);
			nbr_of_problems--;
		}
	}

	private void processProblem(char piece, int row, int column) {
		if (piece == 'r')
			processRook(row, column);
		else if (piece == 'k')
			processKnight(row, column);
		else if (piece == 'Q')
			processQueen(row, column);
		else
			processKing(row, column);
	}

	private void processKing(int row, int column) {
		if ((row & 1) != 0) // if odd integer
			row++;
		if ((column & 1) != 0)
			column++;
		int r = row / 2;
		int c = column / 2;
		System.out.println(r * c);
	}

	private void processQueen(int row, int column) {
		if (row < column)
			System.out.println(row);
		else
			System.out.println(column);
	}

	private void processKnight(int row, int column) {
		System.out.println(Math.round((double) (row * column) / 2));
	}

	private void processRook(int row, int column) {
		if (row < column)
			System.out.println(row);
		else
			System.out.println(column);
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.start();
	}
}
