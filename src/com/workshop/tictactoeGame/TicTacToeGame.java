package com.workshop.tictactoeGame;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {

	// Class Variables
	private static final char ROUND = 'O';
	private static final char CROSS = 'X';
	private static final char BLANK = ' ';
	private char[] board;
	private static int boardSize = 10;
	private char playerChar;
	private char opponentChar;
	private int playerCurrentPosition;
	private final Scanner scannerObj = new Scanner(System.in);

	// Class Functions

	// instantiate char board with board_size
	private void board() {
		board = new char[boardSize];
		Arrays.fill(board, BLANK);
	}

	// Take Input from user for Token (i.e X or O)
	private void playerChoiceChar() {
		System.out.println("Please Choose X or O");

		playerChar = scannerObj.next().toUpperCase().charAt(0);
		opponentChar = (playerChar == CROSS) ? ROUND : CROSS;
	}

	// Showing the Token for Player and Opponent
	private void showPlayer() {
		System.out.println("Player Token " + playerChar + " Opponent Token " + opponentChar);
	}

	// Printing the Current State of the Game Board
	private void showBoard() {
		System.out.println("\n**** Board ****");
		for (int i = 1; i < boardSize; i = i + 3) {
			System.out.println("  " + board[i] + " | " + board[i + 1] + " | " + board[i + 2]);
			System.out.println("-------------");
		}
	}

	// Check the availability in board for the currentPos
	private boolean getStatus(int currentPos) {
		if (currentPos >= boardSize || currentPos < 1)
			return false;
		return (board[currentPos] == BLANK) ? true : false;
	}

	// Taking Choice of User to get the Current Position in Board
	private int choosePosition() {
		System.out.println("Choose any Number Between 1 and 9 ");
		int currentPos = scannerObj.nextInt();
		while ((currentPos >= boardSize || currentPos < 1) && !getStatus(currentPos)) {
			System.out.println("Choose any Number Between 1 and 9 ");
			currentPos = scannerObj.nextInt();
		}
		return currentPos;
	}

	private void playerCurrentPosSetter(int cur) {
		this.playerCurrentPosition = cur;
	}

	private void playMove() {
		board[playerCurrentPosition] = playerChar;
		showBoard();
	}

	public static void main(String args[]) {
		System.out.println("Game");
		TicTacToeGame game = new TicTacToeGame();
		game.board();

		game.playerChoiceChar();
		game.showPlayer();
		game.showBoard();
		game.playerCurrentPosSetter(game.choosePosition());
		game.playMove();
	}

}
