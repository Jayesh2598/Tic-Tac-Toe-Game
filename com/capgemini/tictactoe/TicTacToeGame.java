package com.capgemini.tictactoe;

import java.util.Scanner;

public class TicTacToeGame {

	private static final Scanner SC = new Scanner(System.in);
	public static char[] board=new char[10];
	public static char player;
	public static char computer;
	
	public static void main(String[] args) {
		System.out.println("Welcome to Tic Tac Toe Game!");
		board = createBoard();
		player = chooseLetter();
		computer = (player == 'o')?'x':'o';
		System.out.println("Player letter is '" + player + "' and computer letter is '" + computer + "'");
		displayBoard(board);
		int index= playerIndex();
		if(isFree(board,index))
			displayBoard(playerMove(board, index));
		
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
	
	//Player makes a move
	private static int playerIndex() {
		int index=0;
		while (true) {
			System.out.println("Enter the desired location index:");
			int input = SC.nextInt();
			if(input>=1 && input<=9) {
				index=input;
				break;
			}	
			else
				System.out.println("Enter number between 1-9");
		}
		return index;
	}
	
	//Checking if index position is free
	private static boolean isFree(char[] board, int index) {
		return board[index]=='\0';
	}
	
	//Making move if index is free
	private static char[] playerMove(char[] board, int index) {
		if(isFree(board,index))
			board[index]=player;
		return board;
	}
}
