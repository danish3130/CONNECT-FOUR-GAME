import java.util.Scanner;

public class ConnectFourGame {

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static char[][] board = new char[ROWS][COLUMNS];

    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        displayBoard();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Player " + currentPlayer + ", enter your column (1-7): ");
            int column = scanner.nextInt() - 1;

            if (isValidMove(column)) {
                dropPiece(column);
                displayBoard();

                if (checkWin()) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                }

                if (isBoardFull()) {
                    System.out.println("It's a draw!");
                    break;
                }

                switchPlayer();
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void displayBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-----------------------------");
    }

    private static boolean isValidMove(int column) {
        return column >= 0 && column < COLUMNS && board[0][column] == ' ';
    }

    private static void dropPiece(int column) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][column] == ' ') {
                board[i][column] = currentPlayer;
                break;
            }
        }
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static boolean checkWin() {
        // Check for a win in rows, columns, and diagonals
        return checkRows() || checkColumns() || checkDiagonals();
    }

    private static boolean checkRows() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                if (board[i][j] == currentPlayer &&
                        board[i][j + 1] == currentPlayer &&
                        board[i][j + 2] == currentPlayer &&
                        board[i][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkColumns() {
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == currentPlayer &&
                        board[i + 1][j] == currentPlayer &&
                        board[i + 2][j] == currentPlayer &&
                        board[i + 3][j] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkDiagonals() {
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                if (board[i][j] == currentPlayer &&
                        board[i + 1][j + 1] == currentPlayer &&
                        board[i + 2][j + 2] == currentPlayer &&
                        board[i + 3][j + 3] == currentPlayer) {
                    return true;
                }

                if (board[i][j + 3] == currentPlayer &&
                        board[i + 1][j + 2] == currentPlayer &&
                        board[i + 2][j + 1] == currentPlayer &&
                        board[i + 3][j] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < COLUMNS; i++) {
            if (board[0][i] == ' ') {
                return false;
            }
        }
        return true;
    }
}