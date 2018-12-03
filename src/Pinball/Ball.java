package Pinball;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.geometry.Point2D;
import javafx.geometry.Bounds;

/*******************************************************************************************/
/**
 *     Ball creates the graphics and functionality of the pinball being used in the game
 */
/*******************************************************************************************/
class Ball
{
    protected final Circle ball;

    private GameCoordinator gameCoordinator;

    private double x_velocity = 2; // velocity of the ball in the x direction
    private double y_velocity = 2; //velcoity of the ball in the y direction

    public int wallCounter = -1;  // set to -1 to offset if the ball goes south first

    public Ball()
    {
        //draw the ball
        ball = new Circle(8, Color.RED);

        Reset(); // place the ball in the proper position
    }

    /**
     *  Reset The ball back in the middle of the grey bar
     */
    private void Reset()
    {
        //gameCoordinator.gameDisplay.setBallAt(100,322,ball);
        ball.relocate(90,325);
    }

    /**
     *  Active only after setInPlay
     *  has been invoked; new position stops at the wall
     */
    public void Move(double x)
    {
        //gameCoordinator.gameDisplay.setBallAt((int)x,322,ball);
        ball.relocate((int)x, 325);
    }

    /**
     *  get the location of the ball
     */
    public Point2D getLocation()
    {
       Point2D point;

       point = new Point2D (ball.getLayoutX(), ball.getLayoutY());

       return point;
    }

    /**
     *   SetInPlay Shoots the ball in a random direction
     */
    public void setInPlay(Bounds bounds)
    {
        double angle = Math.toRadians(Math.random() * 360); // choose a random angle between 1-360
        double amount = 1;

        /** Basic trig to retrieve the x and y position of the random angle **/
        /** i.e Cos(180) = -1  Cos(90) = 0  **/
        /** Sin(180) = 0  Sin(90) = 1 **/
        int random_x = (int)ball.getLayoutX() + (int)(amount * Math.cos(angle));
        int y2 = (int)ball.getLayoutY() + (int)(amount * Math.sin(angle));

        /** place the ball in new random x and y position **/
        ball.setLayoutX(random_x + x_velocity);
        ball.setLayoutY(y2 + y_velocity);

        /** If the ball's position is equal to the left or right border/ top down border **/
        /** send the ball in the opposite direction and increment a counter **/
        /** that will be used in the GameCoordinator class **/
        if(ball.getLayoutX() <= (bounds.getMinX() + ball.getRadius()) ||
                ball.getLayoutX() >= (bounds.getMaxX() - ball.getRadius()) )
        {

            x_velocity = -x_velocity;
            wallCounter++;

        }

        //Ball has reached top or bottom border
        // "-42" is assuming the border is  the grey bar the ball is shot from
        // to make the last tile the border, it is done simply by multipling the last row 5
        // by 40 (the size of the box) which gives 200 our border
        if((ball.getLayoutY() >= ((bounds.getMaxY() - 42)  - ball.getRadius())) ||
                (ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius()))){

            y_velocity = -y_velocity;
            wallCounter++;

        }

    }

    /**
     * This method is invoked when:
     * 1. The player presses the reset ball
     * 2. The game is won
     * 3. return a boolean?
     */
    public void setOffPlay()
    {
       Reset();
    }

}
/*******************************************************************************************/
/*******************************************************************************************/