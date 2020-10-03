package com.capgemini.tictactoe;

import java.util.Scanner;

public class TicTacToeGame {
	
	static Scanner sc=new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Welcome to Tic Tac Toe Game!");
		char[] board= createBoard();
		char player=chooseLetter();
		char computer='x';
		if(player=='x')
			computer='o';
		System.out.println("Player letter is '"+player+"' and computer letter is '"+computer+"'");
	}
	
	//creating empty board character array
	private static char[] createBoard() {
		char[] board = new char[10];
		for(int index=0;index<10;index++)
			board[index]='\0';
		return board;
	}
	
	//Player choosing a letter
	private static char chooseLetter() {
		char player='\0';
		while(true) {
			System.out.println("Choose a letter (x or o):");
			char input = sc.next().toLowerCase().charAt(0);
			if(input=='x' || input=='o' ) {
				player=input;
				break;
			}	
			else
				System.out.println("You can only choose between x and o.");	
		}
		return player;
	}
}
