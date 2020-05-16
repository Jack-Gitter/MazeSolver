import java.awt.Color;

/**
 * Represents one square in a maze.
 */

public class MazeSquare {

  private MazeSquare up, down, left, right, prev;
  private int row, col, distToEnd;
  private Color c;

  /**
   * Constructs a maze square.
   * @param up square above.
   * @param down square below.
   * @param left square to the left.
   * @param right square to the right.
   * @param row the row which the square is in.
   * @param col the column which the square is in.
   * @param c the color of the square.
   */

  public MazeSquare(MazeSquare up, MazeSquare down, MazeSquare left, MazeSquare right, int row, int col, int distToEnd, Color c) {
    this.up = up;
    this.down = down;
    this.left = left;
    this.right = right;
    this.row = row;
    this.col = col;
    this.c = c;
    this.prev = null;
    this.distToEnd = distToEnd;
  }

  /**
   * Determines if a square is white
   * @return true if the square is white, false otherwise.
   */

  public boolean isWhite() {
    return this.c.equals(Color.WHITE);
  }

  /**
   * Sets the color of the square.
   * @param c Color.
   */

  public void setColor(Color c) {
    this.c = c;
  }

  /**
   * Gets the previous square in the path to the end.
   * @return previous square in end path.
   */

  public MazeSquare getPrev() {
    return this.prev;
  }

  /**
   * Sets the square above the current square.
   * @param up square above the current square.
   */

  public void setUp(MazeSquare up) {
    this.up = up;
  }

  /**
   * Sets the square below the current square.
   * @param down the square below the current square.
   */

  public void setDown(MazeSquare down) {
    this.down = down;
  }

  /**
   * Sets the square to the left of the current square.
   * @param left square to the left of the current square.
   */

  public void setLeft(MazeSquare left) {
    this.left = left;
  }

  /**
   * Sets the square to the right of the current square.
   * @param right square to the right of the current square.
   */

  public void setRight(MazeSquare right) {
    this.right = right;
  }

  /**
   * Sets the previous square in the path to the end.
   * @param prev the previous square in the path to the end.
   */

  public void setPrev(MazeSquare prev) {
    this.prev = prev;
  }

  /**
   * Gets the color of the current square.
   * @return color of the current square.
   */

  public Color getColor() {
    return this.c;
  }

  /**
   * gets the square above the current square.
   * @return the square above the current square.
   */

  public MazeSquare getUp() {
    return this.up;
  }

  /**
   * gets the square below the current square.
   * @return the square below the current square.
   */

  public MazeSquare getDown() {
    return this.down;
  }

  /**
   * gets the square to the left of the current square.
   * @return the square to the left of the current square.
   */

  public MazeSquare getLeft() {
    return this.left;
  }

  /**
   * gets the square to the right of the current square.
   * @return the square to the right of the current squre.
   */

  public MazeSquare getRight() {
    return this.right;
  }

  /**
   * gets the row the square is on in the maze.
   * @return the row the square is on.
   */

  public int getRow() {
    return this.row;
  }

  /**
   * gets the column that the square is on in the maze.
   * @return the column the square is on.
   */

  public int getCol() {
    return this.col;
  }

  /**
   * gets the distance to the end from the current square.
   * @return distance to the end.
   */

  public int getDistToEnd() {
    return this.distToEnd;
  }
}
