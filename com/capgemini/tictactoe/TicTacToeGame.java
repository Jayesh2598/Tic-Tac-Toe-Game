package com.capgemini.tictactoe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {

	private static final Random ran = new Random();
	private static final Scanner SC = new Scanner(System.in);
	private static final int HEAD = 1;
	private static final int TAIL = 0;
	public static char[] board = new char[10];
	public static char player;
	public static char computer;

	public static void main(String[] args) {
		System.out.println("Welcome to Tic Tac Toe Game!");
		board = createBoard();
		player = chooseLetter();
		computer = (player == 'o') ? 'x' : 'o';
		System.out.println("Player letter is '" + player + "' and computer letter is '" + computer + "'");
		displayBoard(board);
		int firstChance = toss(); 									// Determining first turn using a toss
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
		while (status == 0) { 										// Player plays when chance == true and computer plays when chance == false
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
			status = gameStatus(board); 							// status == 1 in tie situation and 2 in win situation
		}
		switch (status) {
		case 1:
			System.out.println("It's a tie.");
			break;
		case 2:
			if (chance) 											// if chance == true, last chance was of computer
				System.out.println("*****Computer wins!*****");
			else 													// if chance == false, last chance was of player
				System.out.println("*****Player wins!*****");
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
		while (true) {
			int computerIndex = ran.nextInt(9) + 1;
			if (isFree(board, computerIndex)) {
				board[computerIndex] = computer;
				break;
			}
		}
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
				|| (board[2] == board[5] && board[5] == board[7] && board[2] != '\0')
				|| (board[3] == board[6] && board[6] == board[9] && board[3] != '\0'))
			check = 2;
		if ((board[1] == board[5] && board[5] == board[9] && board[1] != '\0')
				|| (board[3] == board[5] && board[5] == board[7] && board[3] != '\0'))
			check = 2;

		return check;
	}
}
