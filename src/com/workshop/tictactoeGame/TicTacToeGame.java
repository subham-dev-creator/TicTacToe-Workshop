package com.workshop.tictactoeGame;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {
	
	//Class Variables
	private char[] board ;
	private static int board_size=10;
	private String player_char;
	private String opponent_char;
	
	
	
	//Class Functions
	private void board()
	{
		//instantiate char board with board_size
		board = new char[board_size];
		Arrays.fill(board, ' ');
	}
	private void player_choice_XO() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Please Choose X or O");
		
		//Take Input from user for Token (i.e X or O)
		player_char=sc.next();
		if(player_char=="X")
			opponent_char="O";
		else
			opponent_char="X";
	}
	
	private void showPlayer() {
		System.out.println("Player Token " + player_char + " Opponent Token " + opponent_char);
	}
	public static void main(String args[]) {
		System.out.println("Game");
		TicTacToeGame game1 = new TicTacToeGame();
		game1.board();
		
		game1.player_choice_XO();
		game1.showPlayer();
	}
	
	

}
