/**
 * Bradley Newman
 * CPSC 2150 Section 1
 * CPSC 2151 Section 3
 * Homework 5
 * Tic Tac Toe
 */
package cpsc2150.hw5;



/**
 * IGameBoard represents a 2-dimensional gameboard that has characters
 * on it as markers (X,O). No space on the board can have multiple
 * players, and there can be a clear winner. Board is NUM_ROWS x
 * NUM_COLS in size
 *
 * Initialization ensures: the Board does not have any markers on it
 * Defines: NUM_ROWS: Z
 * Defines: NUM_COLUMNS: Z
 * Constraints: 0< NUM_ROWS <= MAX_SIZE
 *                    0< NUM_COLS <= MAX_SIZE
 */
public interface IGameBoard {
    int MAX_SIZE = 100;

    /**
     * @requires player == 'X' or player == 'O'
     * @ensures the move is valid before marker is placed
     * @param pos
     * @return true if space is available
     */
    boolean checkSpace(BoardPosition pos);

    /**
     * @requires player == 'X' or player == 'O'
     * @ensures marker is placed at lastPos
     * @param lastPos
     */
    void placeMarker(BoardPosition lastPos);

    /**
     * @requires player == 'X' or player == 'O'
     * @ensures the player will win if they have enough in a row horizontally, vertically, or diagonally
     * @param lastPos
     * @return true if player won
     */
    boolean checkForWinner(BoardPosition lastPos);
}
