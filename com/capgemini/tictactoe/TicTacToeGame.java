package com.capgemini.tictactoe;

import java.util.Scanner;

public class TicTacToeGame {

	private static final Scanner SC = new Scanner(System.in);
	public static char[] board=new char[10];
	
	public static void main(String[] args) {
		System.out.println("Welcome to Tic Tac Toe Game!");
		board = createBoard();
		char player = chooseLetter();
		char computer = 'x';
		if (player == 'x')
			computer = 'o';
		System.out.println("Player letter is '" + player + "' and computer letter is '" + computer + "'");
		displayBoard(board);

	}

	// creating empty board character array
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
		System.out.println("\n" + board[1] +"  |  "+ board[2]+"  |  "+ board[3]);
		System.out.println("--------------");
		System.out.println(board[4] +"  |  "+ board[5]+"  |  "+ board[6]);
		System.out.println("--------------");
		System.out.println(board[7] +"  |  "+ board[8]+"  |  "+ board[9]);
		
	}
}
