/**
 * Bradley Newman
 * CPSC 2150 Section 1
 * CPSC 2151 Section 3
 * Homework 5
 * Tic Tac Toe
 */
package cpsc2150.hw5;

import java.util.*;
/**
 * GameBoardMem is an implementation of IGameBoard in which a game of tic tac toe is played using 2 ArrayLists for memory
 * efficiency
 */

/**
 * @invariant 3 <= rowNum <= MAX_SIZE
 * @invariant 3 <= colNum <= MAX_SIZE
 * @invariant numWin >= 2
 * Correspondence: NUM_ROWS = rowNum
 * Correspondence: NUM_COLS = colNum
 * Correspondence: this = XList[0...rowNum-1][0...colNum-1]
 * Correspondence: this = OList[0...rowNum-1][0...colNum-1]
 */
public class GameBoardMem implements IGameBoard {
    private int rowNum = 0;
    private int colNum = 0;
    private int numWin = 0;
    private List<BoardPosition> XList = new ArrayList<>();
    private List<BoardPosition> OList = new ArrayList<>();

    /**
     * Constructor
     * @requires boardsizeRows is type int, boardSizeCols is type int, and winNumber is type int
     * @ensures rowNum = boardSizeRows, colNum = boardSizeCols, numWin = winNumber
     * @param boardSizeRows, boardSizeCols, winNumber
     */
    GameBoardMem(int boardSizeRows, int boardSizeCols, int winNumber) {
        rowNum = boardSizeRows;
        colNum = boardSizeCols;
        numWin = winNumber;
    }

    //checks if space is available
    @Override
    public boolean checkSpace(BoardPosition pos) {
        boolean X;
        boolean O;
        BoardPosition newPos;
        if(pos.getPlayer() == 'X') {
            newPos = new BoardPosition(pos.getRow(), pos.getColumn(),'O');
            X = !(XList.contains(pos));
            O = !(OList.contains(newPos));
        }
        else {
            newPos = new BoardPosition(pos.getRow(), pos.getColumn(), 'X');
            X = !(XList.contains(newPos));
            O = !(OList.contains(pos));
        }
        return X && O;
    }

    //places marker in the users specified location
    @Override
    public void placeMarker(BoardPosition lastPos) {
        char player = lastPos.getPlayer();
        BoardPosition placeMarker = new BoardPosition(lastPos.getRow(), lastPos.getColumn(), lastPos.getPlayer());
        if(player == 'X') {
            XList.add(placeMarker);
        }
        if(player == 'O') {
            OList.add(placeMarker);
        }
    }

    //checks to see if the last move resulted in a winner
    @Override
    public boolean checkForWinner(BoardPosition lastPos) {
        if (checkHorizontalWin(lastPos) || checkVerticalWin(lastPos) || checkDiagonalWin(lastPos)) {
            return true;
        }
        return false;
    }

    //checks to see if a player won horizontally
    /**
     * @requires player == 'X' or player == 'O'
     * @ensures player is a winner horizontally if they have enough in a row and numInARow = numWin
     * @param lastPos
     * @return true if player is a winner horizontally
     */
    private boolean checkHorizontalWin(BoardPosition lastPos) {
        char marker = lastPos.getPlayer();
        int playerRow = lastPos.getRow();
        int playerCol = lastPos.getColumn();
        int numInARow = 1;
        for(int i = 1; i <= numWin; i++) {
            int col = playerCol - i;
            BoardPosition horizontalMarker = new BoardPosition(playerRow, col, marker);
            if(col < 0) {
                break;
            }
            if(XList.contains(horizontalMarker) || OList.contains(horizontalMarker)) {
                numInARow++;
            }
            else {
                break;
            }
        }
        for(int i = 1; i <= numWin; i++) {
            int col = playerCol + i;
            BoardPosition horizontalMarker = new BoardPosition(playerRow, col, marker);
            if(col > MAX_SIZE - 1) {
                break;
            }
            if(XList.contains(horizontalMarker) || OList.contains(horizontalMarker)) {
                numInARow++;
            }
            else {
                break;
            }
        }
        return(numInARow >= numWin);
    }

    //checks to see if a player won vertically
    /**
     * @requires player == 'X' or player == 'O'
     * @ensures player is a winner vertically if they have enough in a row and numInARow = numWin
     * @param lastPos
     * @return true if player is a winner vertically
     */
    private boolean checkVerticalWin(BoardPosition lastPos) {
        char marker = lastPos.getPlayer();
        int playerRow = lastPos.getRow();
        int playerCol = lastPos.getColumn();
        int numInARow = 1;
        for(int i = 1; i <= numWin; i++) {
            int row = playerRow - i;
            BoardPosition verticalMarker = new BoardPosition(row, playerCol, marker);
            if(row < 0) {
                break;
            }
            if(XList.contains(verticalMarker) || OList.contains(verticalMarker)) {
                numInARow++;
            }
            else {
                break;
            }
        }
        for(int i = 1; i <= numWin; i++) {
            int row = playerRow + i;
            BoardPosition verticalMarker = new BoardPosition(row, playerCol, marker);
            if(row > MAX_SIZE - 1) {
                break;
            }
            if(XList.contains(verticalMarker) || OList.contains(verticalMarker)) {
                numInARow++;
            }
            else {
                break;
            }
        }
        return(numInARow >= numWin);
    }

    //checks to see if a player won diagonally
    /**
     * @requires player == 'X' or player == 'O'
     * @ensures player is a winner diagonally if they have enough in a row and numInARow = numWin
     * @param lastPos
     * @return true if player is a winner diagonally
     */
    private boolean checkDiagonalWin(BoardPosition lastPos) {
        char marker = lastPos.getPlayer();
        int playerRow = lastPos.getRow();
        int playerCol = lastPos.getColumn();
        int numInARow = 1;
        for(int i = 1; i < numWin; i++) {
            int row = playerRow - i;
            int col = playerCol - i;
            BoardPosition diagonalMarker = new BoardPosition(row, col, marker);
            if(row < 0 || col < 0) {
                break;
            }
            if(XList.contains(diagonalMarker) || OList.contains(diagonalMarker)) {
                numInARow++;
            }
            else {
                break;
            }
        }
        for(int i = 1; i < numWin; i++) {
            int row = playerRow + i;
            int col = playerCol + i;
            BoardPosition diagonalMarker = new BoardPosition(row, col, marker);
            if(row > MAX_SIZE - 1 || col > MAX_SIZE - 1) {
                break;
            }
            if(XList.contains(diagonalMarker) || OList.contains(diagonalMarker)) {
                numInARow++;
            }
            else {
                break;
            }
        }
        if(numInARow >= numWin) {
            return true;
        }
        else {
            numInARow = 1;
        }
        for(int i = 1; i < numWin; i++) {
            int row = playerRow + i;
            int col = playerCol - i;
            BoardPosition diagonalMarker = new BoardPosition(row, col, marker);
            if(row > MAX_SIZE - 1 || col < 0) {
                break;
            }
            if(XList.contains(diagonalMarker) || OList.contains(diagonalMarker)) {
                numInARow++;
            }
            else {
                break;
            }
        }
        for(int i = 1; i < numWin; i++) {
            int row = playerRow - i;
            int col = playerCol + i;
            BoardPosition diagonalMarker = new BoardPosition(row, col, marker);
            if(row < 0 || col > MAX_SIZE - 1) {
                break;
            }
            if(XList.contains(diagonalMarker) || OList.contains(diagonalMarker)) {
                numInARow++;
            }
            else {
                break;
            }
        }
        return(numInARow >= numWin);
    }

    //prints out the game board
    /**
     * @ensures the game board is stored in the string builtString
     * @return builtString
     */
    @Override
    public String toString() {
        StringBuffer builtString = new StringBuffer();
        builtString.append("  |");
        for (int num = 0; num < colNum; num++) {
            if (num < 10) {
                builtString.append("  ");
                builtString.append(num);
                builtString.append("|");
            }
            else {
                builtString.append(" ");
                builtString.append(num);
                builtString.append("|");
            }
        }
        builtString.append("\n");
        for (int row = 0; row < rowNum; row++) {
            if (row < 10) {
                builtString.append(row);
                builtString.append(" | ");
            }
            else {
                builtString.append(row);
                builtString.append("| ");
            }
            for (int col = 0; col < colNum; col++) {
                BoardPosition XMarker = new BoardPosition(row, col, 'X');
                BoardPosition OMarker = new BoardPosition(row, col, 'O');
                if(XList.contains(XMarker)) {
                    builtString.append("X");
                }
                else if (OList.contains(OMarker)) {
                    builtString.append("O");
                }
                else {
                    builtString.append(" ");
                }
                builtString.append(" | ");
            }
            builtString.append("\n");
        }
        return builtString.toString();
    }
}
