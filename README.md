# Project Title

Pinball Assignment for CS 351

The game consists of a 8x5 gameboard with blue tiles, with 5 tiles being orange and randomly placed throught the board.
The ball is initially placed at the center of the gray bar. The reset and play buttons control the game. When the reset button is
highlighted the user can move the ball anywhere in the grey bar by using the mouse. When the user presses play the ball will shoot in
a random direction. The goal is to get as many yellow tiles as possible before the ball bounces against 3 walls.

Class Heiarchy follows the design as described in the PDF in this repository.


## Getting Started
The jar file for the game is located here:
https://csgit.cs.unm.edu/bwade/CS351_Assignment1_Pinball/blob/master/out/artifacts/CS351_Assignment1_Pinball_jar/Pinball_BrandonWade.jar

Download the Jar and the game should run.

### Prerequisites

This game was created with Java SDK 9.

## Versioning

Commit one i.e Version 1.0
- Simple Hello World Application to get used to using Git

Commit Two i.e Version 1.1
- implemented Display for pinball game added a constructor for a cell, as well the Display class
- Overall got the basic skeleton for the code as well GUI set up for the game

Commit Three i.e Version 1.2
1. Ball : Missing functionality for methods setStartLocation and setOffPlay 

2. Board : Functionality includes reseting the Game Board, Initizating the Board, and detecting if the
     game ball has touched a orange tile on the board

3. Cell : Constructor for Board Tiles includes color, and position   
  
4. Display : Constructor for Display, Main functionaility is to update the display, with tile color changes based on
     game states.

5. Mouse : Main functionality is to return the position of the mouse
 
6. Score : Update the scores value, or return it as an int.

Commit Four i.e Version 1.3

- The game as well as code is considered completed

## Bugs And Other Known Issues
- The class Controls.java is currently not being used, although it does have a functional method that return's a 0 or 1 based
  on the given state.

- The method in the clas Score.java called "incrementBy", is currently dead code. I left it in just for following the rules
   of the proposed design
 
- Currently the bottom wall or 'boundary' after the ball is set in play is the top of the grey. There wasnt any specification on what the
- top or bottom boundaries are. But if this were to change, it can be done simply by going to the Ball.java class, going into
- the setInPlay method, and navigating to the second if statement. From here as the comment in the code mentions, we change the
 "42" to 200.
- 42" is assuming the border is  the grey bar the ball is shot from
- 200 comes from multipling the max row(5) by the size of the tile 40
       

## Authors

* **Brandon Wade** - *Initial work* - [CS351](https://csgit.cs.unm.edu/bwade/CS351_Assignment1_Pinball.gith)


