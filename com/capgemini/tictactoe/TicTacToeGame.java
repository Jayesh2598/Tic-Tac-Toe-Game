package com.capgemini.tictactoe;

import java.util.Scanner;

public class TicTacToeGame {
	
	public static void main(String[] args) {
		System.out.println("Welcome to Tic Tac Toe Game!");
		char[] board= createBoard();
	}
	
	//creating empty board character array
	private static char[] createBoard() {
		char[] board = new char[10];
		for(int index=0;index<10;index++)
			board[index]='\0';
		return board;
	}
}
