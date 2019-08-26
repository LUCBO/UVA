package _101;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Solution for UVa problem 101 The Blocks Problem
 * 
 * @author Lucas Borg 2019-08-
 */
public class Main {

	private static Scanner input;
	private LinkedList<LinkedList<Integer>> blockLayout;

	public void start() {
		input = new Scanner(System.in);
		int nbrOfBlocks = Integer.parseInt(input.nextLine());
		blockLayout = new LinkedList<LinkedList<Integer>>();
		for (int i = 0; i < nbrOfBlocks; i++) {
			blockLayout.addLast(new LinkedList<Integer>());
			blockLayout.getLast().add(i);
		}
		String command = "";
		while (!command.equals("quit")) {
			command = input.nextLine();
			if (command.contains("move") || command.contains("pile")) {
				int a = Integer.parseInt(command.split(" ")[1]);
				int b = Integer.parseInt(command.split(" ")[3]);
				if (a != b) {
					if (command.contains("move")) {
						if (command.contains("onto"))
							moveAontoB(a, b);
						else
							moveAoverB(a, b);
					} else {
						if (command.contains("onto"))
							pileAontoB(a, b);
						else
							pileAoverB(a, b);
					}
				}
			}
		}
		printResult();
	}

	private void pileAoverB(int a, int b) {
		int[] posA = getPositionOfBlockNbr(a);
		int[] posB = getPositionOfBlockNbr(b);
		LinkedList<Integer> blocksToMove = getBlocksToMove(posA);
		while (!blocksToMove.isEmpty()) {
			blockLayout.get(posB[0]).addLast(blocksToMove.removeLast());
		}
	}

	private void pileAontoB(int a, int b) {
		int[] posB = getPositionOfBlockNbr(b);
		returnBlocksOverPos(posB);
		int[] posA = getPositionOfBlockNbr(a);
		LinkedList<Integer> blocksToMove = new LinkedList<Integer>();
		if (posA[0] != posB[0])
			blocksToMove = getBlocksToMove(posA);
		else {
			int counter = 0;
			while (counter < posB[1] - posA[1]) {
				blocksToMove.addLast(blockLayout.get(posA[0]).remove(posA[1]));
				counter++;
			}
		}

		while (!blocksToMove.isEmpty()) {
			blockLayout.get(posB[0]).addLast(blocksToMove.removeLast());
		}
	}

	private void moveAoverB(int a, int b) {
		int[] posA = getPositionOfBlockNbr(a);
		int[] posB = getPositionOfBlockNbr(b);
		returnBlocksOverPos(posA);
		if (posA[0] == posB[0])
			posB = getPositionOfBlockNbr(b);
		blockLayout.get(posA[0]).removeLast();
		blockLayout.get(posB[0]).addLast(a);
	}

	private void moveAontoB(int a, int b) {
		int[] posA = getPositionOfBlockNbr(a);
		int[] posB = getPositionOfBlockNbr(b);
		returnBlocksOverPos(posB);
		returnBlocksOverPos(posA);
		if (posA[0] == posB[0])
			posB = getPositionOfBlockNbr(b);
		blockLayout.get(posA[0]).removeLast();
		blockLayout.get(posB[0]).addLast(a);
	}

	private void returnBlocksOverPos(int[] pos) {
		while (blockLayout.get(pos[0]).size() > pos[1]) {
			int blockToMove = blockLayout.get(pos[0]).removeLast();
			blockLayout.get(blockToMove).addFirst(blockToMove);
		}
	}

	private LinkedList<Integer> getBlocksToMove(int[] pos) {
		LinkedList<Integer> blocks = new LinkedList<Integer>();
		while (blockLayout.get(pos[0]).size() >= pos[1]) {
			blocks.addLast(blockLayout.get(pos[0]).removeLast());
		}
		return blocks;
	}

	/**
	 * 
	 * @param blockNbr
	 *            The number of the block (a or b)
	 * @return {position, order from bottom}
	 */
	private int[] getPositionOfBlockNbr(int blockNbr) {
		int pos = -1, hight = 0;
		for (int i = 0; i < blockLayout.size(); i++) {
			for (int j = 0; j < blockLayout.get(i).size(); j++) {
				if (blockLayout.get(i).get(j) == blockNbr) {
					pos = i;
					hight = j + 1;
					break;
				}
			}
			if (pos != -1)
				break;
		}
		return new int[] { pos, hight };

	}

	private void printResult() {
		for (int i = 0; i < blockLayout.size(); i++) {
			System.out.print(i + ":");
			for (int j = 0; j < blockLayout.get(i).size(); j++) {
				System.out.print(" " + blockLayout.get(i).get(j));
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.start();
	}
}
