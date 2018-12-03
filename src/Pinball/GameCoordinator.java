package Pinball;


/**
 * Created By Brandon Wade
 */

/*********************************************************************************/
/**
 *     Libaries
 */
/*********************************************************************************/
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Group;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*********************************************************************************/
/**       Start of GameCoordinator.java
/*********************************************************************************/
public class GameCoordinator extends Application
{

    /***** References to other classes ******/
    private  Display gameDisplay;  // Display

    private  Ball gameBall;  // Ball

    private  Mouse gameMouse; // Mouse

    private  Board gameBoard; // Board

    private Controls gameController; // Controller

    private Score scoreBoard; // Score

    /**************** Global Variables *************************************/

    private GraphicsContext gc;

    private  Canvas canvas;

    private  int cols; // number cols specified in Display class
    private  int rows; // number of rows specified in Display class

    private int WIDTH;  // Width of the game screen specified in the Display Class
    private int HEIGHT; // Height of the game screen specified in the Display Class


    private boolean isPlayButtonActive = true; // set to true when play button is active
    private boolean isResetButtonActive = false; // set to true when reset button is clicked

    private boolean shootBall = false; // gets set to true when the user presses play

    private Button resetButton; // used to reset the game
    private Button playButton; // used to start the game

    /*****************************************************************************************/

    /********** Initializing the constructor for game coordinator ***************************/
    public GameCoordinator()
    {

        gameDisplay = new Display(8,5);
        gameBall = new Ball();
        gameMouse = new Mouse();
        gameBoard = new Board();
        scoreBoard = new Score();
        gameController = new Controls();

        // Board Height specified in Display class
        HEIGHT = gameDisplay.getBoardHeight();

        // Board width specified in Display class
        WIDTH = gameDisplay.getBoardWidth();

        cols = gameDisplay.getBoardColumns();
        rows =  gameDisplay.getBoardRows();


        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
    }
      /**********************************************************************************/

    /************************ MAIN *****************************************************/
    public static void main(String[] args)
    {
        GameCoordinator gameCoordinator = new GameCoordinator();
        launch(args);
    }
     /**********************************************************************************/

    /**********************************************************************************/
     /**
     /** @param primaryStage
     /*********************************************************************************/
    @Override
    public void start(Stage primaryStage) {


        /** BASIC SETUP  **/
        primaryStage.setTitle("Pinball Machine");

        Bounds bounds = canvas.getBoundsInLocal();

        canvas.setOnMouseMoved(gameMouse.getMouseMovedHandler);

        /** Combine all the UI Components and draw the board **/
        createGameBoard();

        /** Add UI to the scene **/
        Group root = new Group ();
        root.getChildren().add(canvas);
        root.getChildren().add(gameBall.ball);
        root.getChildren().add(playButton);
        root.getChildren().add(resetButton);
        root.getChildren().add(gameDisplay.scoreText);


        /** Create a instance of the scene using group created above and display it **/
        primaryStage.setScene(new Scene(new Group(root)));
        primaryStage.setResizable(false);
        primaryStage.show();

         /******** START ANIMATION FUNCTIONALITY ***********************/
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t)
                    {
                        /** If the reset button is active check and see if the mouse is within the grey boxx */
                        /** if it is, allow movement of the game ball. **/
                        if(isResetButtonActive)
                        {
                            if(gameMouse.getX() < bounds.getMaxX() && gameMouse.getX() > bounds.getMinX()
                                    && gameMouse.getX() < 190.0)
                            {
                                gameBall.Move(gameMouse.getX());
                            }
                        }

                        /** shootball gets set to true when the play button is pressed **/
                        if(shootBall)
                        {

                            int currentScore = gameBoard.checkTouch(); // the number of times a orange tile was hit

                            gameBall.setInPlay(bounds);  // lauches the ball in a random direction

                            gameBoard.Touch(gameBall.getLocation()); // check if the ball touches a orangle tile

                            int newScore = gameBoard.checkTouch();  // the number times a orange tile is hit after launch

                            /** let the game cordinator know a point was scored, so the number of times **/
                            /** the ball hits the wall can be reset **/
                            if(newScore > currentScore)
                            {
                                gameBall.wallCounter = 0;
                            }
                            /**  If the ball hasnt hit a orangle tile by the time it bounces off three **/
                            /** walls the game is lose, and reseted **/
                            if(currentScore == newScore && gameBall.wallCounter >= 3)
                            {
                                gameBall.setOffPlay(); // place the ball back in the grey bar

                                scoreBoard.Reset();  // reset the score

                                gameBall.wallCounter = 0; // reset the wall hit count

                                shootBall = false;
                            }

                            /** Update the board **/
                            drawScoreBoard(); // re draw scoreboard
                            drawBoard(); // re draw the game board
                            gameDisplay.setScoreValue(currentScore * 10); // 10 points per orange tile hit

                        }

                    }
    }));
        //number of times to play animation
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
    /*********************************************************************************/

    /*********************************************************************************/
    /**
     *      Create all the UI componets
     */
    /*********************************************************************************/
    private void createGameBoard()
    {
        //create graphics for the gameboard i.e draw blue tiles
        drawBoard();

        // create graphics for the grey bar (where the ball will be shot from)
        drawGreyBar();

        // create graphics for the scoreboard
        drawScoreBoard();

        //create graphics for play button
        createPlayButton();

        //create graphics for reset button
        createResetButton();

    }
    /*********************************************************************************/

    /*********************************************************************************/
    /**   Draws the tile's on the game board
     *   Based on rows and cols specifed in Display class
    /*********************************************************************************/
    private void drawBoard()
    {
        for(int i = 0; i < cols; i++)
        {
            for(int j = 0; j < rows; j++)
            {
                gc.setLineWidth(3); // outline of cell

                gc.strokeRect(i*40,j*40,40,40);

                gc.setStroke(Color.BLACK); // color of outline of cell
                gc.setFill(gameBoard.GameBoard[j][i].cell_color);// color of cell
                gc.fillRect(i*40,j*40,40,40);

            }
        }
    }
    /*********************************************************************************/

    /*********************************************************************************/
    /**
     *   Creates the grey bar that will house the game ball
     */
    /*********************************************************************************/
    private void drawGreyBar()
    {
        //draw the grey bar
        gc.setLineWidth(3); // outline of cell
        gc.strokeRect(0,322,310,20);
        gc.setStroke(Color.BLACK); // color of outline of cell
        gc.setFill(Color.GRAY);
        gc.fillRect(0,322,310,20);

    }
    /*********************************************************************************/
    /*********************************************************************************/
    /**
     *     Draw the scoreboard, defined in Display class
     */
    /*********************************************************************************/
    private void drawScoreBoard()
    {
        // draw the scoreboard
        gc.setLineWidth(3); // outline of cell
        gc.strokeRect(80,347,35,35);
        gc.setStroke(Color.BLACK); // color of outline of cell
        gc.setFill(Color.BLACK);
        gc.fillRect(80,347,35,35);

        //gameDisplay.setScoreValue(1);
        gameDisplay.scoreText.setX(91);
        gameDisplay.scoreText.setY(370);
    }
    /*********************************************************************************/

    /*********************************************************************************/
    /**
     *       Create the Reset button graphics, Action Event Handler for the button is
     *       also defined here
     */
    /*********************************************************************************/
    private void createResetButton()
    {
        /** Create the reset button GUI **/

        //create the reset button
        resetButton = new Button();

        //button text modifications
        resetButton.setText("Reset");
        resetButton.setWrapText(true);

        //button position modifcations
        resetButton.setLayoutX(0);
        resetButton.setLayoutY(344);

        //button size modifications
        resetButton.setStyle("-fx-background-color: #D3D3D3");
        resetButton.setMinSize(77,40);


        /** Handle when the reset button is clicked **/
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e)
            {

                // the reset button was initially active
                if(gameController.getMode(isResetButtonActive) == 1)
                {
                    // do nothing
                }
                // the reset button was inactive when it was clicked
                // set the reset button to active
                // set the play button to inactive
                // the ball will be taken out of play
                //call the necessary functions to reset the board
                else
                {
                    isResetButtonActive = true;
                    isPlayButtonActive = false;
                    shootBall = false;

                    //reset the gameboard
                    gameBoard.Reset();
                    scoreBoard.Reset();
                    drawBoard();

                }
                // Handle the colors of active and inactive buttons
                gameDisplay.setResetButton(isResetButtonActive, resetButton);
                gameDisplay.setPlayButton(isPlayButtonActive, playButton);
            }
        });

    }
    /*********************************************************************************/
    /**
     *       Create the Play button graphics, Action Event Handler for the button is
     *       also specified here.
     */
    /*********************************************************************************/
    private void createPlayButton()
    {
        /** Create the play button GUI **/

        // create the play button
        playButton = new Button();

        //button text modifications
        playButton.setText("Play");
        playButton.setWrapText(true);

        //button position modifcations
        playButton.setLayoutX(118);
        playButton.setLayoutY(344);

        //button size modifications
        playButton.setStyle("-fx-background-color: #ffff00");
        playButton.setMinSize(80, 40);


        /** Handle when the play button is clicked **/
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e)
            {
                // the play button was clicked and was initially active
                // the mode wont change
                if(gameController.getMode(isPlayButtonActive) == 1)
                {
                    // do nothing
                }
                // the play button was inactive when it was clicked
                // set the play button to active
                // set the reset button to inactive
                // the ball will be set into play
                else
                {
                    isResetButtonActive = false;
                    isPlayButtonActive = true;
                    shootBall = true;

                }

                //modify the colors of the buttons
                gameDisplay.setPlayButton(isPlayButtonActive, playButton);
                gameDisplay.setResetButton(isResetButtonActive, resetButton);
            }
        });

    }

}
/*********************************************************************************/
/**
 *                    End of Coordinator.java
 */
/*********************************************************************************/