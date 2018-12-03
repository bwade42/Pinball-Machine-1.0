package Pinball;

import javafx.scene.paint.Color;
import javafx.geometry.Point2D;

import java.util.ArrayList;

class Board extends Display
{
    public  Cell[][] GameBoard;

    //public Cell[] Tiles;

    private int hitCounter = 0;

    public int counter = 0;

    public boolean isTouch = false;


    public Board()
    {
        GameBoard = new Cell[8][5];

        YellowTiles = new ArrayList<>();
        //tileCounter = 0;
        initGameBoard(8,5);
    }


    /**
     * Initializes the game board with a Cell, with a Cell being an object containing a row, col,
     * color, and state
     */
    private void initGameBoard(int rows, int cols)
    {
        //GameBoard = new Cell[ROWS][COLS];

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                GameBoard[i][j] = new Cell(i,j, Color.BLUE,false);
            }

        }
    }

   public void Reset()
   {
       initGameBoard(8,5);
       //tileCounter = 0;
       //YellowTiles = new Cell[5];
       YellowTiles = new ArrayList<>();
       setRandomOrangeTiles(GameBoard);
       hitCounter = 0;
   }

   //pass in location of the ball
    public int Touch(Point2D location)
    {
        /** Ball coordinates **/
         double ball_xpos = (int)location.getY() / 40;
         double ball_ypos = (int)location.getX() / 40;

        for(int i = 0; i < YellowTiles.size();i++)
        {

            if((int)ball_xpos == YellowTiles.get(i).cell_row && (int)ball_ypos == YellowTiles.get(i).cell_col)
            {
                setTileColor(YellowTiles.get(i).cell_row,YellowTiles.get(i).cell_col,Color.BLUE,GameBoard);
                YellowTiles.remove(i);
                hitCounter++;

            }

            else
            {

            }

        }

        return hitCounter;

    }

    public int checkTouch()
    {
        return hitCounter;
    }

}
