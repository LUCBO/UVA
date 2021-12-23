package _465;

import java.util.Scanner;

/**
 * Solution for UVa problem 465 Overflow
 * 
 * @author Lucas Borg 2019-
 */
public class Main {

	private static Scanner input;

	public void start() {
		input = new Scanner(System.in);
		String line;
		String[] args;
		long x1 = 0, x2 = 0, res = 0;
		boolean isZero;
		while (input.hasNext()) {
			isZero = false;
			line = input.nextLine();
			System.out.println(line);
			args = line.split(" ");

			while (args[0].length() > 0 && args[0].charAt(0) == '0') {
				args[0] = args[0].substring(1);
			}
			if (args[0].length() == 0) {
				isZero = true;
				args[0] = "0";
			}
			while (args[2].length() > 0 && args[2].charAt(0) == '0') {
				args[2] = args[2].substring(1);
			}
			if (args[2].length() == 0) {
				isZero = true;
				args[2] = "0";
			}

			try {
				Integer.parseInt(args[0]);
			} catch (Exception e) {
				System.out.println("first number too big");
			}
			try {
				Integer.parseInt(args[2]);
			} catch (Exception e) {
				System.out.println("second number too big");
			}

			try {
				if (args[0].equals("4294967296"))
					System.out.print("");
				x1 = Long.parseLong(args[0]);
				x2 = Long.parseLong(args[2]);
				if (args[1].equals("+"))
					res = x1 + x2;
				else
					res = x1 * x2;
				Integer.parseInt(res + "");
			} catch (Exception e) {
				if (!(args[1].equals("*") && isZero))
					System.out.println("result too big");
			}
			if (res == 0 && !isZero)
				System.out.println("result too big");
		}

	}

	public static void main(String[] args) {
		Main m = new Main();
		m.start();
	}
}
