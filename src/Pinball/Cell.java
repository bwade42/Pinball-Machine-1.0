package Pinball;

import javafx.scene.paint.Color;

/**
 *  This class constitues what defines a "tile" on the board or in this case a "cell"
 */
public class Cell
{
    public Color cell_color;

    public int cell_row;

    public int cell_col;

    public boolean cell_state;

    public Cell(int x, int y, Color c, boolean state)
    {
       cell_row = x;
       cell_col = y;
       cell_color = c;
       cell_state = state;
    }

}
