package model;

import java.awt.Color;

public interface MazeSquareModel {

  /**
   * Determines if a square is white
   * @return true if the square is white, false otherwise.
   */

  public boolean isWhite();

  /**
   * Sets the color of the square.
   * @param c Color.
   */

  public void setColor(Color c);

  /**
   * Gets the previous square in the path to the end.
   * @return previous square in end path.
   */

  public MazeSquare getPrev();

  /**
   * Sets the square above the current square.
   * @param up square above the current square.
   */

  public void setUp(MazeSquare up);

  /**
   * Sets the square below the current square.
   * @param down the square below the current square.
   */

  public void setDown(MazeSquare down);

  /**
   * Sets the square to the left of the current square.
   * @param left square to the left of the current square.
   */

  public void setLeft(MazeSquare left);

  /**
   * Sets the square to the right of the current square.
   * @param right square to the right of the current square.
   */

  public void setRight(MazeSquare right);

  /**
   * Sets the previous square in the path to the end.
   * @param prev the previous square in the path to the end.
   */

  public void setPrev(MazeSquare prev);

  /**
   * sets the up right square.
   * @param m the square to set.
   */

  public void setUpRight(MazeSquare m);

  /**
   * sets the up left square.
   * @param m the square to set.
   */

  public void setUpLeft(MazeSquare m);

  /**
   * sets the down right square.
   * @param m the square to set.
   */

  public void setDownRight(MazeSquare m);

  /**
   * sets the down left square.
   * @param m the square to set.
   */

  public void setDownLeft(MazeSquare m);
  /**
   * Gets the color of the current square.
   * @return color of the current square.
   */

  public Color getColor();

  /**
   * gets the square above the current square.
   * @return the square above the current square.
   */

  public MazeSquare getUp();

  /**
   * gets the square below the current square.
   * @return the square below the current square.
   */

  public MazeSquare getDown();

  /**
   * gets the square to the left of the current square.
   * @return the square to the left of the current square.
   */

  public MazeSquare getLeft();

  /**
   * gets the square to the right of the current square.
   * @return the square to the right of the current squre.
   */

  public MazeSquare getRight();

  /**
   * gets the square to the down left of the current square.
   * @return the square to the right of the current square.
   */

  public MazeSquare getDownLeft();

  /**
   * gets the square to the down right of the current square.
   * @return the square to the right of the current square.
   */

  public MazeSquare getDownRight();

  /**
   * gets the square to the up left of the current square.
   * @return the square to the right of the current square.
   */

  public MazeSquare getUpLeft();

  /**
   * gets the square to the up right of the current square.
   * @return the square to the right of the current square.
   */

  public MazeSquare getUpRight();

  /**
   * gets the row the square is on in the maze.
   * @return the row the square is on.
   */

  public int getRow();
  /**
   * gets the column that the square is on in the maze.
   * @return the column the square is on.
   */

  public int getCol();

  /**
   * gets the distance to the end from the current square down.
   * @return distance to the end.
   */

  public int getDistToEndDown();

  /**
   * gets the distance to the end from the current square horizontally.
   * @return distance to the end.
   */

  public int getDistToEndLeftRight();

  /**
   * gets the manhattan distance from the square to the end.
   * @return the manhattan distance to the end.
   */

  public int getManhattanDist();

}
