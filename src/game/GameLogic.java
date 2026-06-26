package game;

import java.util.Random;

public class GameLogic {

    private char[] board;

    public GameLogic() {
        board = new char[9];
        resetBoard();
    }

    public void resetBoard() {

        for (int i = 0; i < board.length; i++) {
            board[i] = ' ';
        }
    }

    public boolean makeMove(int index, char symbol) {

        if (index < 0 || index >= 9) {
            return false;
        }

        if (board[index] != ' ') {
            return false;
        }

        board[index] = symbol;

        return true;
    }

    public boolean checkWinner(char symbol) {

        int[][] patterns = {
                {0,1,2},
                {3,4,5},
                {6,7,8},
                {0,3,6},
                {1,4,7},
                {2,5,8},
                {0,4,8},
                {2,4,6}
        };

        for (int[] p : patterns) {

            if (board[p[0]] == symbol &&
                    board[p[1]] == symbol &&
                    board[p[2]] == symbol) {

                return true;
            }
        }

        return false;
    }

    public boolean isDraw() {

        for (char c : board) {

            if (c == ' ') {
                return false;
            }
        }

        return true;
    }

    public int computerMove() {

        Random random = new Random();

        while (true) {

            int move = random.nextInt(9);

            if (board[move] == ' ') {

                board[move] = 'O';

                return move;
            }
        }
    }

    public char[] getBoard() {
        return board;
    }
}