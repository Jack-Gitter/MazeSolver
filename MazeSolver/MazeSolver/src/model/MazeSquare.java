package model;

import java.awt.Color;

/**
 * Represents one square in a maze.
 */

public class MazeSquare implements MazeSquareModel {

  private MazeSquare up, down, left, right, upRight, upLeft, downRight, downLeft, prev;
  private int row, col, distToEndDown, distToEndLeftRight;
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

  public MazeSquare(MazeSquare up, MazeSquare down, MazeSquare left, MazeSquare right,
      MazeSquare upRight, MazeSquare upLeft, MazeSquare downRight, MazeSquare downLeft, int row,
      int col, int distToEndDown, int distToEndLeftRight, Color c) {
    this.up = up;
    this.down = down;
    this.left = left;
    this.right = right;
    this.row = row;
    this.col = col;
    this.c = c;
    this.prev = null;
    this.distToEndDown = distToEndDown;
    this.distToEndLeftRight = distToEndLeftRight;
    this.upRight = upRight;
    this.upLeft = upLeft;
    this.downRight = downRight;
    this.downLeft = downLeft;
  }

  @Override
  public boolean isWhite() {
    return this.c.equals(Color.WHITE);
  }

  @Override
  public void setColor(Color c) {
    this.c = c;
  }

  @Override
  public MazeSquare getPrev() {
    return this.prev;
  }

  @Override
  public void setUp(MazeSquare up) {
    this.up = up;
  }

  @Override
  public void setDown(MazeSquare down) {
    this.down = down;
  }

  @Override
  public void setLeft(MazeSquare left) {
    this.left = left;
  }

  @Override
  public void setRight(MazeSquare right) {
    this.right = right;
  }

  @Override
  public void setPrev(MazeSquare prev) {
    this.prev = prev;
  }

  @Override
  public void setUpRight(MazeSquare m) {
    this.upRight = m;
  }

  @Override
  public void setUpLeft(MazeSquare m) {
    this.upLeft = m;
  }

  @Override
  public void setDownRight(MazeSquare m) {
    this.downRight = m;
  }

  @Override
  public void setDownLeft(MazeSquare m) {
    this.downLeft = m;
  }

  @Override
  public Color getColor() {
    return this.c;
  }

  @Override
  public MazeSquare getUp() {
    return this.up;
  }

  @Override
  public MazeSquare getDown() {
    return this.down;
  }

  @Override
  public MazeSquare getLeft() {
    return this.left;
  }

  @Override
  public MazeSquare getRight() {
    return this.right;
  }

  @Override
  public MazeSquare getDownLeft() {
    return downLeft;
  }

  @Override
  public MazeSquare getDownRight() {
    return downRight;
  }

  @Override
  public MazeSquare getUpLeft() {
    return upLeft;
  }

  @Override
  public MazeSquare getUpRight() {
    return upRight;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getCol() {
    return this.col;
  }

  @Override
  public int getDistToEndDown() {
    return this.distToEndDown;
  }

  @Override
  public int getDistToEndLeftRight() {
    return this.distToEndLeftRight;
  }

  @Override
  public int getManhattanDist() {
    return this.distToEndDown + this.distToEndLeftRight;
  }

}