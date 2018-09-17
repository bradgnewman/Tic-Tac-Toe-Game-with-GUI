/**
 * Bradley Newman
 * CPSC 2150 Section 1
 * CPSC 2151 Section 3
 * Homework 5
 * Tic Tac Toe
 */
package cpsc2150.hw5;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 *
 * This is where you will write code
 *
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and one of the IGameBoard implementations from Homework 4
 * You can choose which IGameBoard implementation to use
 * If your code was correct you will not need to make any changes to your IGameBoard implementation class besides the package name
 */
public class TicTacToeController {
    //our current game that is being played
    private IGameBoard curGame;
    //to track who's turn it is
    private char curPlayer;
    //The screen that provides our view
    private TicTacToeView screen;
    // no other class variables are needed or should be declared


    // add the code and contracts for the constructor of our tic tac toe game

    /**
     * Constructor
     * @requires model to be type IGameBoard, view to be type TicTacToeView
     * @ensures curGame = model, screen = view, curPlayer = 'X'
     * @param model the implementation of the tic tac toe memory efficient game
     * @param view the screen interface with row and columns for the user to click to place their marker
     */
    TicTacToeController(IGameBoard model, TicTacToeView view)
    {
        curGame = model;
        screen = view;
        curPlayer = 'X';
    }

    //Add the code to respond to a user clicking on a button to try to claim a space
    //User will click on the button at row, col
    //User may click on a space that is not available, they do not get that space
    //We no longer have a main function that controls the flow of the game. Users will click buttons, anf
    //Will handle the events here
    // Make sure to make any changes to the screen needed through publicly available functions
    // When a player wins, display a message. You do not need to reset the game or close the window
    // The players can close the window and restart the game themselves
    // remember your javadoc comments and contracts

    /**
     * @ensures player marker placed on space they select
     * @ensures invalid moves can't be placed
     * @ensures player wins if they have the win number in a row horizontally, diagonally, or vertically
     * @requires row and col are within bounds of the game board, player == 'X' or player == 'O'
     * @param row the row where the user has clicked
     * @param col the col where the user has clicked
     */
    public void processButtonClick(int row, int col)
    {
        BoardPosition guiGame = new BoardPosition(row, col, curPlayer);
        //checks for valid space selected
        if (curGame.checkSpace(guiGame)) {
            //places marker in game
            curGame.placeMarker(guiGame);
            screen.setMarker(row, col, curPlayer);
            //alternates players
            if (curPlayer == 'X') {
                curPlayer = 'O';
                screen.setMessage("It is " + curPlayer + "'s turn.");
            }
            else {
                curPlayer = 'X';
                screen.setMessage("It is " + curPlayer + "'s turn.");
            }
        }
        //prints error if space already filled
        else {
            screen.setMessage("That space is unavailable, please select an available space");
        }
        //checks to see if a winning move has occurred
        if (curGame.checkForWinner(guiGame)) {
            if (curPlayer == 'X') {
                curPlayer = 'O';
            }
            else {
                curPlayer = 'X';
            }
            screen.setMessage("Congratulations! Player " + curPlayer + " has won the game!");
            if (curPlayer == 'X') {
                curPlayer = 'O';
            }
            else {
                curPlayer = 'X';
            }
        }
    }
}
