/**
 *  Written By Brandon Wade
 *
 *  This class main purpose is to implement the functions need to display the pinball game
 */

package Pinball;

import javafx.scene.text.Text;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.util.Random;
import java.util.List;


class Display
{
    private final int WIDTH = 201;
    private final int HEIGHT = 385;

    private final int ROWS;
    private final int COLS;


    protected List<Cell> YellowTiles;

    public Text scoreText = new Text();

    Display()
    {
        ROWS = 8;
        COLS = 5;
    }


    public Display(int rows, int cols)
    {
        ROWS = rows;
        COLS = cols;
    }

    /**
     * Makes three random tiles on the game board yellow
     */
    void setRandomOrangeTiles(Cell[][] Board)
    {
        Random rand = new Random();
        for(int i = 0; i < 5; i++)
        {

            int random_row = rand.nextInt((7 - 0) + 1) + 0;
            int random_col = rand.nextInt((4 - 0) + 1) + 0;
            setTileColor(random_row,random_col, Color.ORANGE,Board);
        }
    }


    /**
     *
     * @return the width of the game board
     */
    public int getBoardWidth()
    {
        return WIDTH;
    }

    /**
     *
     * @return the height of the game board
     */
    public int getBoardHeight()
    {
        return HEIGHT;
    }

    /**
     *
     * @return the number of rows
     */
    public int getBoardRows()
    {
        return ROWS;
    }

    /**
     *
     * @return the number of columns the board has
     */
    public int getBoardColumns()
    {
        return COLS;
    }

    /**
     *
     */
    public void setBallAt(int x, int y, Circle circle)
    {
        circle.relocate(x,y);
    }

    /**
     *
     * @param row
     * @param col
     */
    void setTileColor(int row, int col, javafx.scene.paint.Color c, Cell[][] Board)
    {

        if(c == Color.ORANGE)
        {
            Board[row][col].cell_state = true;
            //YellowTiles[tileCounter] = Board[row][col];
            //tileCounter++;
            YellowTiles.add(Board[row][col]);
        }
       Board[row][col].cell_color = c;
    }

    /**
     *
     * @param state
     *
     *  sets the play button to either on or off and possibly position?
     */
    public void setPlayButton(boolean state, javafx.scene.control.Button button)
    {
        //deactivate button
        if(!state)
        {
            //get reference to reset button
            // set the play button to on or off
            button.setStyle("-fx-background-color: #D3D3D3");
        }

        else if(state)
        {
            button.setStyle("-fx-background-color: #ffff00");
        }
    }

    /**
     *
     * @param state
     */
    public void setResetButton(boolean state, javafx.scene.control.Button button)
    {
        //deactivate button
        if(!state)
        {
            //get reference to reset button
            // set the play button to on or off
            button.setStyle("-fx-background-color: #D3D3D3");
        }

        else if(state)
        {
            button.setStyle("-fx-background-color: #ffff00");
        }
    }

    /**
     *
     * @param value
     */
    public void setScoreValue(int value)
    {
        scoreText.setFill(Color.RED);
        scoreText.setText(Integer.toString(value));
    }
}
