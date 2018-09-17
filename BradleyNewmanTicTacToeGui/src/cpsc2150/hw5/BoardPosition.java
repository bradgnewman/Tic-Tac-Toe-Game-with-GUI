/**
 * Bradley Newman
 * CPSC 2150 Section 1
 * CPSC 2151 Section 3
 * Homework 5
 * Tic Tac Toe
 */
package cpsc2150.hw5;



/**
 * @invariant 3 <= row <= MAX_SIZE
 * @invariant 3 <= col <= MAX_SIZE
 */
public class BoardPosition {
    int row = 0;
    int col = 0;
    char player = ' ';

    /**
     * Constructor
     * @requires r to be type int, c to be type int, and p to be type char
     * @ensures row = r, col = c, player = p
     * @param r, c, p
     */
    public BoardPosition(int r, int c, char p)
    {
        row = r;
        col = c;
        player = p;
    }


    /**
     * @ensures getRow = row
     * @return row
     */
    public int getRow()
    {
        return row;
    }

    /**
     * @ensures getColumn = col
     * @return col
     */
    public int getColumn()
    {
        return col;
    }


    /**
     * @requires player == 'X' or player == 'O'
     * @ensures getPlayer = player
     * @return player
     */
    public char getPlayer()
    {
        return player;
    }

    /**
     * @ensures obj is of type BoardPosition
     * @param obj
     * @return true is obj is type BoardPosition
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof BoardPosition))
        {
            return false;
        }
        BoardPosition bPos = (BoardPosition) obj;
        if(this.getRow() != bPos.getRow())
        {
            return false;
        }
        if(this.getColumn() != bPos.getColumn())
        {
            return false;
        }
        if(this.getPlayer() != bPos.getPlayer())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
