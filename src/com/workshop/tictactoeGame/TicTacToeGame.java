package com.workshop.tictactoeGame;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {
	
	//Class Variables
	private char[] board ;
	private static int boardSize=10;
	private char playerChar;
	private char opponentChar;
	
	
	
	//Class Functions
	private void board()
	{
		//instantiate char board with board_size
		board = new char[boardSize];
		Arrays.fill(board, ' ');
	}
	private void player_choice_XO() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Please Choose X or O");
		
		//Take Input from user for Token (i.e X or O)
		playerChar=sc.next().charAt(0); 
		opponentChar = (playerChar == 'X') ? 'O' : 'X';
		sc.close();
	}
	
	private void showPlayer() {
		System.out.println("Player Token " + playerChar + " Opponent Token " + opponentChar);
	}
	
	private void showBoard() {
		System.out.println("\n**** Board ****");
	    System.out.println("  " + board[1] + " | " + board[2] + " | " + board[3]);  
	    System.out.println("-------------");
	    System.out.println("  " + board[4] + " | " + board[5] + " | " + board[6]);  
	    System.out.println("-------------");
	    System.out.println("  " + board[7] + " | " + board[8] + " | " + board[9]); 
	}
	public static void main(String args[]) {
		System.out.println("Game");
		TicTacToeGame game1 = new TicTacToeGame();
		game1.board();
		
		game1.player_choice_XO();
		game1.showPlayer();
		game1.showBoard();
	}
	
	
	

}
