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
	private static final String USER = "User";
	private static final String COMPUTER = "Computer";
	private char[] board;
	private static int boardSize = 10;
	private char playerChar;
	private char opponentChar;
	private int playerCurrentPosition;
	private final Scanner scannerObj = new Scanner(System.in);
	private String playerTossChoice;
	private String currentPlayer;
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

	// returns dynamic position to block user to win
	private int blockGeneratedPosition() {
		int position=1;
		int max=0;
		for(int i=1;i<boardSize;i+=3){
			int ct=0;
			if(board[i]==playerChar && board[i]!=opponentChar)
				ct++;
			if(board[i+1]==playerChar && board[i+1]!=opponentChar)
				ct++;
			if(board[i+2]==playerChar && board[i+2]!=opponentChar)
				ct++;

			if(ct!=3 && ct>max)
			{
				if(board[i]!=playerChar && board[i]!=opponentChar)
					position=i;
				if(board[i+1]!=playerChar && board[i+1]!=opponentChar)
					position=i+1;
				if(board[i+2]!=playerChar && board[i+2]!=opponentChar)
					position=i+2;
				max=ct;
			}
		}

		for(int i=1;i<=3;i+=1){
			int ct=0;
			if(board[i]==playerChar && board[i]!=opponentChar)
				ct++;
			if(board[i+3]==playerChar && board[i+3]!=opponentChar)
				ct++;
			if(board[i+6]==playerChar && board[i+6]!=opponentChar)
				ct++;

			if(ct!=3 && ct>=max)
			{
				if(board[i]!=playerChar && board[i]!=opponentChar)
					position=i;
				else if(board[i+3]!=playerChar && board[i+3]!=opponentChar)
					position=i+3;
				else if(board[i+6]!=playerChar && board[i+6]!=opponentChar)
					position=i+6;
				max=ct;
			}
		}
		System.out.println("pos " + position);
		if(position==1)
			return availableCorner();
		return position;
	}

	// Finds the corner positions available , if not then generates a randon position
	private int availableCorner() {
		if(board[1]==BLANK)
			return  1;
		if(board[3]==BLANK)
			return 3;
		if(board[7]==BLANK)
			return 7;
		if(board[9]==BLANK)
			return 9;
		return availableCenter();
	}

	private int availableCenter() {
		if(board[5]==BLANK)
			return 5;
		return availbleSides();
	}

	private int availbleSides() {
		if(board[2]==BLANK)
			return 2;
		if(board[4]==BLANK)
			return 4;
		if(board[6]==BLANK)
			return 6;
		if(board[8]==BLANK)
			return 8;
		return randomGeneratedPosition();
	}


	// Taking Choice of User to get the Current Position in Board
	private int choosePosition() {
		if(currentPlayer==COMPUTER)
			return blockGeneratedPosition();
		System.out.println(currentPlayer + " Choose any Number Between 1 and 9 ");
		int currentPos = scannerObj.nextInt();
		while ((currentPos >= boardSize || currentPos < 1) && !getStatus(currentPos)) {
			System.out.println(currentPlayer + " Choose any Number Between 1 and 9 ");
			currentPos = scannerObj.nextInt();
		}
		return currentPos;
	}

	//Random generated Position for Board
	private int randomGeneratedPosition() {
		int randomNum = (int) (Math.random() * 20 % 9);
		randomNum++;
		
		while(!getStatus(randomNum)) {
			randomNum = (int) (Math.random() * 20 % 9);
			randomNum++;
		}
		return randomNum;
	}

	private void playerCurrentPosSetter(int cur) {
		this.playerCurrentPosition = cur;
	}

	// Players move in Board
	private void playMove() {
		if (currentPlayer == USER)
			board[playerCurrentPosition] = playerChar;
		else
			board[playerCurrentPosition] = opponentChar;
		showBoard();

		currentPlayer = (currentPlayer == USER) ? COMPUTER : USER;
	}

	// Player Choose its Choice in TOSS
	private void playerTossChoice() {
		System.out.println("Enter Your Choice Press \n 1.Head \n 2.Tail ");
		int choice = scannerObj.nextInt();
		if (choice == 1)
			playerTossChoice = HEAD;
		else
			playerTossChoice = TAIL;
	}

	// TOSS JUDGEMENT
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
			currentPlayer = USER;
		} else {
			System.out.println("Opponent Wins the Toss Opponent Moves First");
			currentPlayer = COMPUTER;
		}
	}

	//Board Winning Condition 
	private boolean winningCondition() {
		char checkChar;
		if (currentPlayer == USER)
			checkChar = playerChar;
		else
			checkChar = opponentChar;

		// horizontal check
		for (int i = 1; i < boardSize; i += 3) {
			if (board[i] == checkChar && board[i + 1] == checkChar && board[i + 2] == checkChar)
				return true;
		}

		// vertical check
		for (int i = 1; i <= 3; i++) {
			if (board[i] == checkChar && board[i + 3] == checkChar && board[i + 6] == checkChar)
				return true;
		}

		if ((board[1] == board[5]) && (board[5] == board[9]) && (board[9] == checkChar))
			return true;

		if ((board[3] == board[5]) && (board[5] == board[7]) && (board[7] == checkChar))
			return true;

		return false;
	}

	//Game Starts HERE
	private void start() {
		board();
		playerChoiceChar();
		showPlayer();
		playerTossChoice();
		tossWin();
		showBoard();
		while (!isBoardFill()) {
			playerCurrentPosSetter(choosePosition());
			playMove();
			if (winningCondition()) {
				System.out.println(currentPlayer + " Wins the game \n GAME OVER");
				break;
			}
		}
	}
	
	//Checking Board is FULL or NOT
	private boolean isBoardFill() {
		for (int i = 1; i < boardSize; i++)
			if (board[i] == BLANK)
				return false;
		return true;
	}

	public static void main(String args[]) {
		System.out.println("Game");
		TicTacToeGame game = new TicTacToeGame();
		game.start();
	}
}
