package com.capgemini.tictactoe;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class TicTacToeGame {

	private static final Random ran = new Random();
	private static final Scanner SC = new Scanner(System.in);
	private static final int HEAD = 1;
	private static final int TAIL = 0;
	private static char[] board = new char[10];
	private static char player;
	private static char computer;

	public static void main(String[] args) {
		System.out.println("Welcome to Tic Tac Toe Game!");
		board = createBoard();
		player = chooseLetter();
		computer = (player == 'o') ? 'x' : 'o';
		System.out.println("Player letter is '" + player + "' and computer letter is '" + computer + "'");
		displayBoard(board);
		int firstChance = toss(); 								// Determining first turn using a toss
		boolean chance = true;
		switch (firstChance) {
		case HEAD:
			System.out.println("\nComputer plays first.");
			displayBoard(computerMove(board));
			chance = true;
			break;
		case TAIL:
			System.out.println("\nPlayer plays first.");
			displayBoard(playerMove(board, playerIndex(board)));
			chance = false;
			break;
		}
		int status = 0;
		while (status == 0) { 									// Player plays when chance == true and computer plays when chance == false
			if (chance) {
				System.out.println("\nPlayer plays.");
				board = playerMove(board, playerIndex(board));
				chance = false;
			} else {
				System.out.println("\nComputer plays.");
				board = computerMove(board);
				chance = true;
			}
			displayBoard(board);
			status = gameStatus(board); 						// status == 1 in tie situation and 2 in win situation
		}
		switch (status) {
		case 1:
			System.out.println("It's a tie.");
			break;
		case 2:
			if (chance) 										// if chance == true, last chance was of computer
				System.out.println("\n*****Computer wins!*****");
			else 												// if chance == false, last chance was of player
				System.out.println("\n*****Player wins!*****");
			break;
		}
	}

	// Creating empty board character array
	private static char[] createBoard() {
		for (int index = 0; index < board.length; index++)
			board[index] = '\0';
		return board;
	}

	// Player choosing a letter
	private static char chooseLetter() {
		char player = '\0';
		while (true) {
			System.out.println("Choose player letter (x or o):");
			char input = SC.next().toLowerCase().charAt(0);
			if (input == 'x' || input == 'o') {
				player = input;
				break;
			} else
				System.out.println("You can only choose between x and o.");
		}
		return player;
	}

	// Displaying board
	private static void displayBoard(char[] board) {
		System.out.println("\n" + board[1] + "  |  " + board[2] + "  |  " + board[3]);
		System.out.println("--------------");
		System.out.println(board[4] + "  |  " + board[5] + "  |  " + board[6]);
		System.out.println("--------------");
		System.out.println(board[7] + "  |  " + board[8] + "  |  " + board[9]);

	}

	// Tossing a coin
	private static int toss() {
		return ran.nextInt(2);
	}

	// Player selects an index
	private static int playerIndex(char[] board) {
		int index = 0;
		while (true) {
			System.out.println("Enter the desired location index:");
			int input = SC.nextInt();
			if (input >= 1 && input <= 9) {
				if (isFree(board, input)) {
					index = input;
					break;
				} else
					System.out.println("This location is already occupied.");
			} else
				System.out.println("Enter number between 1-9");
		}
		return index;
	}

	// Checking if index position is free
	private static boolean isFree(char[] board, int index) {
		return board[index] == '\0';
	}

	// Player plays
	private static char[] playerMove(char[] board, int index) {
		if (isFree(board, index))
			board[index] = player;
		return board;
	}

	// Computer plays
	private static char[] computerMove(char[] board) {
		int winIndex = checkMyWin(board);
		if (winIndex == 0) {
			int blockIndex = blockPlayerWin(board);
			if (blockIndex == 0) {
				int emptyCorner = chooseCorners(board);
				if (emptyCorner == 0) {
					int place=chooseCentreOrSide(board);
					board[place]=computer;
				} else
					board[emptyCorner] = computer;
			} else
				board[blockIndex] = computer;
		} else
			board[winIndex] = computer;
		return board;
	}

	// Checking game status
	private static int gameStatus(char[] board) {
		int check = 0;
		// Tie scenario
		boolean tieCheck = true;
		for (int i = 1; i <= 9; i++)
			if (board[i] == '\0')
				tieCheck = false;
		if (tieCheck)
			check = 1;
		// Winning scenarios
		if ((board[1] == board[2] && board[2] == board[3] && board[1] != '\0')
				|| (board[4] == board[5] && board[5] == board[6] && board[4] != '\0')
				|| (board[7] == board[8] && board[8] == board[9] && board[7] != '\0'))
			check = 2;
		if ((board[1] == board[4] && board[4] == board[7] && board[1] != '\0')
				|| (board[2] == board[5] && board[5] == board[8] && board[2] != '\0')
				|| (board[3] == board[6] && board[6] == board[9] && board[3] != '\0'))
			check = 2;
		if ((board[1] == board[5] && board[5] == board[9] && board[1] != '\0')
				|| (board[3] == board[5] && board[5] == board[7] && board[3] != '\0'))
			check = 2;

		return check;
	}

	// Check computer's win and play
	private static int checkMyWin(char[] board) {
		int[][] arr = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 }, { 1, 5, 9 },
				{ 3, 5, 7 } };
		TreeSet<Integer> list = new TreeSet<Integer>();
		for (int i = 0; i < 8; i++) {
			if (board[arr[i][0]] == board[arr[i][1]] && board[arr[i][0]] == computer && board[arr[i][2]] == '\0')
				list.add(arr[i][2]);
			if (board[arr[i][0]] == board[arr[i][2]] && board[arr[i][0]] == computer && board[arr[i][1]] == '\0')
				list.add(arr[i][1]);
			if (board[arr[i][1]] == board[arr[i][2]] && board[arr[i][1]] == computer && board[arr[i][0]] == '\0')
				list.add(arr[i][0]);
		}
		if (list.isEmpty())
			return 0;
		else {
			int randomIndex = ran.nextInt(list.size());
			Integer[] playIndices = list.toArray(new Integer[list.size()]);
			return playIndices[randomIndex].intValue();
		}
	}

	// Check player's win and block it
	private static int blockPlayerWin(char[] board) {
		int[][] arr = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 }, { 1, 5, 9 },
				{ 3, 5, 7 } };
		TreeSet<Integer> list = new TreeSet<Integer>();
		for (int i = 0; i < 8; i++) {
			if (board[arr[i][0]] == board[arr[i][1]] && board[arr[i][0]] == player && board[arr[i][2]] == '\0')
				list.add(arr[i][2]);
			if (board[arr[i][0]] == board[arr[i][2]] && board[arr[i][0]] == player && board[arr[i][1]] == '\0')
				list.add(arr[i][1]);
			if (board[arr[i][1]] == board[arr[i][2]] && board[arr[i][1]] == player && board[arr[i][0]] == '\0')
				list.add(arr[i][0]);
		}
		if (list.isEmpty())
			return 0;
		else {
			int randomIndex = ran.nextInt(list.size());
			Integer[] blockIndices = list.toArray(new Integer[list.size()]);
			return blockIndices[randomIndex].intValue();
		}
	}

	// Choose available corners
	private static int chooseCorners(char[] board) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (board[1] == '\0')
			list.add(1);
		if (board[3] == '\0')
			list.add(3);
		if (board[7] == '\0')
			list.add(7);
		if (board[9] == '\0')
			list.add(9);
		if (list.isEmpty())
			return 0;
		else {
			int randomIndex = ran.nextInt(list.size());
			Integer[] emptyCorners = list.toArray(new Integer[list.size()]);
			return emptyCorners[randomIndex].intValue();
		}
	}
	
	// Choose centre if empty, else a side
	private static int chooseCentreOrSide(char[] board) {
		if(board[5]=='\0')
			return 5;
		else {
			ArrayList<Integer> list = new ArrayList<Integer>();
			if (board[2] == '\0')
				list.add(2);
			if (board[4] == '\0')
				list.add(4);
			if (board[6] == '\0')
				list.add(6);
			if (board[8] == '\0')
				list.add(8);
			int randomIndex = ran.nextInt(list.size());
			Integer[] emptySides = list.toArray(new Integer[list.size()]);
			return emptySides[randomIndex].intValue();
		}
	}
}