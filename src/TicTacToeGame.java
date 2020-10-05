import java.util.Arrays;

public class TicTacToeGame {
	private char[] board ;
	private void board()
	{
		board = new char[10];
		Arrays.fill(board, ' ');
	}
	public static void main(String args[]) {
		TicTacToeGame game1 = new TicTacToeGame();
		game1.board();
	}

}
