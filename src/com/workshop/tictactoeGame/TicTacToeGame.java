package com.workshop.tictactoeGame;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {

	// Class Variables
	private static final char ROUND = 'O';
	private static final char CROSS = 'X';
	private static final char BLANK = ' ';
	private static final String TAIL = "TAIL";
	private static final String HEAD = "HEAD";
	private char[] board;
	private static int boardSize = 10;
	private char playerChar;
	private char opponentChar;
	private int playerCurrentPosition;
	private final Scanner scannerObj = new Scanner(System.in);
	private String playerTossChoice;
	private boolean isPlayerWinsToss;
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
		while (!(playerChar == 'X' || playerChar == 'O')) {
			System.out.println("Please Choose X or O");
			playerChar = scannerObj.next().toUpperCase().charAt(0);
		}
		opponentChar = (playerChar == CROSS) ? ROUND : CROSS;
	}

	// Showing the Token for Player and Opponent
	private void showPlayer() {
		System.out.println("Player Token is " + playerChar + "\nOpponent Token is " + opponentChar);
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
	
	//Players move in Board
	private void playMove() {
		board[playerCurrentPosition] = playerChar;
		showBoard();
	}
	
	//Player Choose its Choice in TOSS
	private void playerTossChoice() {
		System.out.println("Enter Your Choice Press \n 1.Head \n 2.Tail ");
		int choice = scannerObj.nextInt();
		if (choice == 1)
			playerTossChoice = HEAD;
		else
			playerTossChoice = TAIL;
	}
	
	//TOSS JUDGEMENT
	private void tossWin() {
		int randomNum = (int) (Math.random() * 20 % 2);
		String tossResult;
		if (randomNum == 0)
			tossResult = TAIL;
		else
			tossResult = HEAD;
		System.out.println("Coin Result is " + tossResult);

		if (tossResult == playerTossChoice) {
			System.out.println("Player Wins the Toss Player Moves First ");
			isPlayerWinsToss = true;
		} else {
			System.out.println("Opponent Wins the Toss Opponent Moves First");
			isPlayerWinsToss = false;
		}
	}

	public static void main(String args[]) {
		System.out.println("Game");
		TicTacToeGame game = new TicTacToeGame();
		game.board();

		game.playerChoiceChar();
		game.showPlayer();
		game.playerTossChoice();
		game.tossWin();
		game.showBoard();
		game.playerCurrentPosSetter(game.choosePosition());
		game.playMove();
	}

}
