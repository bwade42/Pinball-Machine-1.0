package Pinball;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
/*******************************************************************************************/
/**
 *  Mouse main purpose is to retrieve the X and Y coordinates of the mouse and send it to
 *  the GameCoordinator  Class to be evaulated.
 */
/*******************************************************************************************/
class Mouse
{
    private double x; // used to store the x coordinate of the mouse
    private double y; // used to store y coordinate of the mouse


    /**
     *  Event Handle to capture the movement of the mouse
     */
    public final EventHandler<MouseEvent> getMouseMovedHandler = new EventHandler<MouseEvent>()
    {

        @Override
        public void handle(MouseEvent e)
        {
            x = e.getX();
            y = e.getY();

        }
    };

    /**
     *
     * @return x coordinate of mouse
     */
    public double getX()
    {
        return x;
    }

    /**
     *
     * @return  y coordinate of mouse
     */
    public double getY()
    {
        return y;
    }
}


