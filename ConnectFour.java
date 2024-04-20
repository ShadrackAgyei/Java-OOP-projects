/**
 * This program is a Connect Four game. Connect four is a two-player board game in which the players
 * alternately drop colored disks into a seven-column, six-row vertically suspended grid.
 * The objective of the game is to connect four same-colored disks in a row, a column, or a
 * diagonal before your opponent can do likewise.
 *
 * @author: Shadrack Agyei Nti
 */

import java.util.Scanner;

public class ConnectFour {
    final static String playerRed = "red";
    final static String playerYellow = "yellow";

    // variable to track player turn
    static String playerTurn = playerRed;
    final static char player1 = 'R';
    final static char player2 = 'Y';

    // dimensions for the connect four board
    final static int noOfRows = 6;
    final static int noOfColumns = 7;


    public static void main(String[] args){
        // create the connect four board
        char[][] connectBoard = new char[noOfRows][noOfColumns];

        createConnectBoard(connectBoard);
        playGame(connectBoard);
    }
    public static void playGame(char[][] connectBoard){
        // create a scanner for taking input
        Scanner input = new Scanner(System.in);

        boolean gameWon = false;
        while (!gameWon){

            // check for draw by checking if board is full
            boolean draw = isBoardFull(connectBoard);
            if (draw){
                System.out.println("The game ends in a draw");
                break;
            }

            // prompt the current player for input
            System.out.printf("Drop a %s at column (0-6): ", playerTurn);
            int column = input.nextInt();

            // condition to check if input is within column range
            if (column > 6 || column < 0){
                System.out.println("Invalid Input.");
                continue;
            }

            // drop disk into column player chooses
            dropDisks(connectBoard, column);

            // print updated board after each disk drop
            printBoardStatus(connectBoard);

            // check if game has been won after each disk drop
            gameWon = checkGameStatus(connectBoard);

            // update player turn
            playerTurn = (playerTurn == playerRed) ? playerYellow : playerRed;
        }
    }

    /**
     * This method creates the connect board and initialize it with empty spaces
     * @param connectBoard the game board represented by a 2D array of size 6 x 7
     */
    public static void createConnectBoard(char[][] connectBoard){
        // intialize board with empty spaces
        for (int i = 0; i<connectBoard.length; i++){
            for (int j =0; j < connectBoard[i].length;j++){
                connectBoard[i][j] = ' ';
            }
        }
    }

    /**
     * This method handles the dropping of disk into the column chosen by the player
     * @param connectBoard the game board represented by a 2D array of size 6 x 7
     * @param column index of column chosen by player.
     */
    public static void dropDisks(char[][] connectBoard,int column){
        // disk postion management
        char player = 0;
        if (playerTurn == playerRed) player = player1;
        else if (playerTurn == playerYellow) player = player2;


        for (int row = noOfRows-1; row >= 0; row--){
            if (connectBoard[row][column] == ' '){
                connectBoard[row][column] = player;
                break;
            } else if (connectBoard[0][column] != ' '){ // check if column is full
                System.out.println("Column is full.");
                playerTurn = (playerTurn == playerRed) ? playerYellow : playerRed;
                break;
            }
        }
    }

    /**
     * This method checks the game status and determines if the game has been won
     * @param connectBoard the game board represented by a 2D array of size 6 x 7
     * @return True if game won, and false otherwise
     */
    public static boolean checkGameStatus(char[][] connectBoard){
        // intialize gameWon status to false
        boolean gameWon = false;
        int consecutiveDisksForWin = 4;

        // check for horizontal win
        gameWon = checkHorizontalWin(connectBoard, consecutiveDisksForWin, gameWon);

        // check for vertical win
        gameWon = checkVerticalWin(connectBoard, consecutiveDisksForWin, gameWon);

        // check for forward direction of diagonal win
        gameWon = checkForwardDiagonalWin(connectBoard, gameWon);

        // check for reverse direction of diagonal win
        gameWon = checkReverseDiagonalWin(connectBoard, gameWon);

        if (gameWon) System.out.println("The " + playerTurn + " player won.");
        return gameWon;
    }

    /**
     * This method checks if the board is full which indicates a draw
     * @param connectBoard the game board represented by a 2D array of size 6 x 7
     * @return True if the board is full, indicating draw and false otherwise
     */
    public static boolean isBoardFull(char[][] connectBoard){
        // check if every column on board is full
        boolean isBoardFull = true;
        for (int row = 0; row < connectBoard.length; row++){
            for (int col = 0; col < connectBoard[row].length; col++){
                if (connectBoard[row][col] == ' '){
                    isBoardFull = false;
                    return isBoardFull;
                }
            }
        }
        return isBoardFull;
    }

    /**
     * This method prints the status of the board
     * @param connectBoard the game board represented by a 2D array of size 6 x 7
     */
    public static void printBoardStatus(char[][] connectBoard){
        // print board status
        for (int i = 0; i<connectBoard.length; i++){
            for (int j =0; j < connectBoard[i].length;j++){
                System.out.print("|" + connectBoard[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------------");
    }

    /**
     * This method checks if the game has been won by 4 consecutive matches horizontally
     * @param connectBoard the game board represented by a 2D array of size 6 x 7
     * @param consecutiveDisksForWin number of consecutive disks needed to win (4)
     * @param gameWon boolean status for game won
     * @return True if a horizontal win is found, otherwise false
     */
    public static boolean checkHorizontalWin(char[][] connectBoard,int consecutiveDisksForWin,boolean gameWon){
        for (int row = 0; row < noOfRows; row++){
            int counterRed = 0;
            int counterYellow = 0;
            for (int col = 0; col <noOfColumns; col++){
                if (connectBoard[row][col] == 'R' ){
                    counterYellow = 0;
                    counterRed++;
                } else if (connectBoard[row][col] == 'Y') {
                    counterRed  = 0;
                    counterYellow++;
                } else if (connectBoard[row][col] == ' ') {
                    counterRed = 0;
                    counterYellow = 0;
                }
                if (counterYellow == consecutiveDisksForWin || counterRed == consecutiveDisksForWin) {
                    gameWon = true;
                    break;
                }
            }
        }
        return gameWon;
    }

    /**
     * This method checks if the game has been won by 4 consecutive matches vertically
     * @param connectBoard the game board represented by a 2D array of size 6 x 7
     * @param consecutiveDisksForWin number of consecutive disks needed to win (4)
     * @param gameWon boolean status for game won
     * @return True if a vertical win is found, otherwise false
     */
    public static boolean checkVerticalWin(char[][] connectBoard,int consecutiveDisksForWin,boolean gameWon){
        // check for vertical win
        for (int col = 0; col< noOfColumns; col++){
            int counterRed = 0;
            int counterYellow = 0;
            for (int row = 0; row < noOfRows; row++){
                if (connectBoard[row][col] != ' '){
                    if (connectBoard[row][col] == 'R'){
                        counterRed++;
                        counterYellow = 0;

                    } else if (connectBoard[row][col] == 'Y') {
                        counterYellow++;
                        counterRed  = 0;

                    }
                    if (counterYellow == consecutiveDisksForWin|| counterRed == consecutiveDisksForWin) {
                        gameWon = true;
                        break;
                    }
                }
            }
        }
        return gameWon;
    }

    /**
     * This method checks if the game has been won by 4 consecutive diagonal matches in the forward direction
     * @param connectBoard the game board represented by a 2D array of size 6 x 7
     * @param gameWon boolean status for game won
     * @return True if a vertical win is found, otherwise false
     */
    public static boolean checkForwardDiagonalWin(char[][] connectBoard,boolean gameWon){
        try {
            for (int row = 0; row < connectBoard.length; row++) {
                for (int col = 0; col < connectBoard[0].length; col++) {
                    if (connectBoard[row][col] == 'R' && connectBoard[row + 1][col + 1] == 'R' &&
                            connectBoard[row + 2][col + 2] == 'R' && connectBoard[row + 3][col + 3] == 'R') {
                        gameWon = true;
                        break;
                    } else if (connectBoard[row][col] == 'Y' && connectBoard[row + 1][col + 1] == 'Y' &&
                            connectBoard[row + 2][col + 2] == 'Y' && connectBoard[row + 3][col + 3] == 'Y') {
                        gameWon = true;
                        break;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored){}

        return gameWon;
    }
    /**
     * This method checks if the game has been won by 4 consecutive diagonal matches in the reverse direction
     * @param connectBoard the game board represented by a 2D array of size 6 x 7
     * @param gameWon boolean status for game won
     * @return True if a vertical win is found, otherwise false
     */
    public static boolean checkReverseDiagonalWin(char[][] connectBoard,boolean gameWon){
        try {
            for (int row = 0; row < connectBoard.length; row++) {
                for (int col = 0; col < connectBoard[0].length; col++) {
                    if (connectBoard[row][col] == 'R' && connectBoard[row + 1][col - 1] == 'R' &&
                            connectBoard[row + 2][col - 2] == 'R' && connectBoard[row + 3][col - 3] == 'R') {
                        gameWon = true;
                        break;
                    } else if (connectBoard[row][col] == 'Y' && connectBoard[row + 1][col - 1] == 'Y' &&
                            connectBoard[row + 2][col - 2] == 'Y' && connectBoard[row + 3][col - 3] == 'Y') {
                        gameWon = true;
                        break;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored){}
        return gameWon;
    }

}