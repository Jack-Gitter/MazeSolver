import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;
import javax.imageio.ImageIO;

/**
 * Represents a maze.
 */

// assumption that the maze's start width is the same as the other widths is wrong, find a good balance
public class Maze {

  private MazeSquare[][] maze;
  private BufferedImage mazeImg;
  private boolean solutionFound;
  private boolean isInvalidPixel;

  /**
   * Instantiates a maze.
   * @param filePath the path to the maze to replicate.
   * @throws IOException if the file path is invalid.
   */

  public Maze(String filePath) throws IOException {
    this.isInvalidPixel = false;
    String fPath = filePath;
    File f = new File(fPath);
    this.mazeImg = ImageIO.read(f);
    this.solutionFound = false;
    this.maze = new MazeSquare[mazeImg.getHeight()][mazeImg.getWidth()];
    this.initMaze();
  }

  /**
   * solves the maze.
   */

  public void solveMazeBFS() {
    Deque<MazeSquare> dq = new LinkedList<MazeSquare>();
    MazeSquare startSquare = this.findStartSquare();
    Set<MazeSquare> visited = new HashSet<>();
    if (startSquare == null) {
      return;
    }
    dq.offerLast(startSquare);
    while (dq.size() != 0) {
      MazeSquare curr = dq.pollFirst();
      this.mazeImg.setRGB(curr.getCol(), curr.getRow(), new Color(3, 252, 211).getRGB());
      visited.add(curr);
      if (this.checkForSolution(curr)) {
        this.setAllPrevToRed(curr);
        this.solutionFound = true;
        break;
      }
      if (this.squareIsValid(curr.getUp(), visited)) {
        dq.offerLast(curr.getUp());
        visited.add(curr.getUp());
        this.setPrev(curr.getUp(), curr);
      }
      this.isInvalidPixel = false;
      if (this.squareIsValid(curr.getDown(), visited)) {
        dq.offerLast(curr.getDown());
        visited.add(curr.getDown());
        this.setPrev(curr.getDown(), curr);
      }
      this.isInvalidPixel = false;
      if (this.squareIsValid(curr.getLeft(), visited)) {
        dq.offerLast(curr.getLeft());
        visited.add(curr.getLeft());
        this.setPrev(curr.getLeft(), curr);
      }
      this.isInvalidPixel = false;
      if (this.squareIsValid(curr.getRight(), visited)) {
        dq.offerLast(curr.getRight());
        visited.add(curr.getRight());
        this.setPrev(curr.getRight(), curr);
      }
      this.isInvalidPixel = false;
    }
  }

  /**
   * solves the maze.
   */

  public void solveMazeDFS() {
    Deque<MazeSquare> dq = new LinkedList<MazeSquare>();
    MazeSquare startSquare = this.findStartSquare();
    Set<MazeSquare> visited = new HashSet<>();
    if (startSquare == null) {
      return;
    }
    dq.offerFirst(startSquare);
    while (dq.size() != 0) {
      MazeSquare curr = dq.pollFirst();
      this.mazeImg.setRGB(curr.getCol(), curr.getRow(), new Color(3, 252, 211).getRGB());
      visited.add(curr);
      if (this.checkForSolution(curr)) {
        this.setAllPrevToRed(curr);
        this.solutionFound = true;
        break;
      }
      if (this.squareIsValid(curr.getUp(), visited)) {
        dq.offerFirst(curr.getUp());
        visited.add(curr.getUp());
        this.setPrev(curr.getUp(), curr);
      }
      this.isInvalidPixel = false;
      if (this.squareIsValid(curr.getDown(), visited)) {
        dq.offerFirst(curr.getDown());
        visited.add(curr.getDown());
        this.setPrev(curr.getDown(), curr);
      }
      this.isInvalidPixel = false;
      if (this.squareIsValid(curr.getLeft(), visited)) {
        dq.offerFirst(curr.getLeft());
        visited.add(curr.getLeft());
        this.setPrev(curr.getLeft(), curr);
      }
      this.isInvalidPixel = false;
      if (this.squareIsValid(curr.getRight(), visited)) {
        dq.offerFirst(curr.getRight());
        visited.add(curr.getRight());
        this.setPrev(curr.getRight(), curr);
      }
      this.isInvalidPixel = false;
    }
  }

  /**
   * Solves the maze.
   */

  public void solveMazePriorityQueue() {
    Comparator<MazeSquare> c = (m1, m2) -> m2.getDistToEnd() - m1.getDistToEnd();
    PriorityQueue<MazeSquare> pq = new PriorityQueue<>(c);
    MazeSquare startSquare = this.findStartSquare();
    Set<MazeSquare> visited = new HashSet<>();
    if (startSquare == null) {
      return;
    }
    pq.add(startSquare);
    while (pq.size() != 0) {
      MazeSquare curr = pq.poll();
      this.mazeImg.setRGB(curr.getCol(), curr.getRow(), new Color(3, 252, 211).getRGB());
      visited.add(curr);
      if (this.checkForSolution(curr)) {
        this.setAllPrevToRed(curr);
        this.solutionFound = true;
        break;
      }
      if (this.squareIsValid(curr.getUp(), visited)) {
        pq.add(curr.getUp());
        visited.add(curr.getUp());
        this.setPrev(curr.getUp(), curr);
      }
      this.isInvalidPixel = false;
      if (this.squareIsValid(curr.getDown(), visited)) {
        pq.add(curr.getDown());
        visited.add(curr.getDown());
        this.setPrev(curr.getDown(), curr);
      }
      this.isInvalidPixel = false;
      if (this.squareIsValid(curr.getLeft(), visited)) {
        pq.add(curr.getLeft());
        visited.add(curr.getLeft());
        this.setPrev(curr.getLeft(), curr);
      }
      this.isInvalidPixel = false;
      if (this.squareIsValid(curr.getRight(), visited)) {
        pq.add(curr.getRight());
        visited.add(curr.getRight());
        this.setPrev(curr.getRight(), curr);
      }
      this.isInvalidPixel = false;
    }
  }

  /**
   * Sets the previous element of a {@code MazeSquare}.
   * @param next the element to have the previous field set.
   * @param current the element to occupy the previous field.
   */

  private void setPrev(MazeSquare next, MazeSquare current) {
    next.setPrev(current);
  }

  /**
   * Gets the width of the entrance of the maze.
   * @return the width of the entrace.
   */

  private int getStartWidth() {
    int res = 0;
    for (int col = 0; col < this.mazeImg.getWidth(); col++) {
      if (this.mazeImg.getRGB(col, 0) == Color.white.getRGB()) {
        res +=1;
      }
    }
    return res;
  }

  /**
   * Determines if a square is in bounds and not processed by the algorithm yet.
   * @param square the square to check.
   * @param visited the {@code Set<MazeSquare>} of all visited {@code MazeSquares}
   * @return true if the square is in bounds and not processed, false otherwise.
   */

  private boolean squareIsInBoundsAndNotProcessed(MazeSquare square, Set<MazeSquare> visited) {
    return square != null && !visited.contains(square);
  }

  /**
   * Determines how close a square is to the walls of the maze.
   * @param curr the current square to process.
   * @param x the distance parameter.
   * @return true if the square is within x pixels to the walls of the maze.
   */

  private boolean xPixelsWithinBlack(MazeSquare curr, int x) {
    Deque<MazeSquare> dq = new LinkedList<>();
    Set<MazeSquare> visited = new HashSet<>();
    if (curr.getColor().equals(Color.black)) {
      return true;
    }
    dq.offerLast(curr);
    for (int i = 0; i < x-1; i++) {
      int currentLen = dq.size();
      for(int j = 0; j < currentLen; j++) {
        MazeSquare current = dq.pollFirst();
        if (current.getColor().equals(Color.black)) {
          return true;
        }
        visited.add(current);
        this.continuePixelCheck(dq, current.getUp(), visited);
        this.continuePixelCheck(dq, current.getRight(), visited);
        this.continuePixelCheck(dq, current.getLeft(), visited);
        this.continuePixelCheck(dq, current.getDown(), visited);
        this.continuePixelCheck(dq, current.getUpRight(), visited);
        this.continuePixelCheck(dq, current.getUpLeft(), visited);
        this.continuePixelCheck(dq, current.getDownRight(), visited);
        this.continuePixelCheck(dq, current.getDownLeft(), visited);
        if (this.isInvalidPixel) return true;
      }
    }
      return false;
  }

  /**
   * Determines if a square is black or not. Assists the {@code xPixelsWithinBlack} function.
   * @param dq the list of squares to process.
   * @param square the current square to process.
   * @param visited the {@code Set<MazeSquare>} of {@code MazeSquare} objects previously processed.
   */

  private void continuePixelCheck(Deque<MazeSquare> dq, MazeSquare square, Set<MazeSquare> visited) {
    if (this.squareIsInBoundsAndNotProcessed(square, visited)) {
      if (square.getColor().equals(Color.black)) {
        this.isInvalidPixel = true;
      } else {
        dq.offerLast(square);
        visited.add(square);
      }
    }
  }

  /**
   * Sets the path from start to finish to red pixels.
   * @param curr the current maze square to set red.
   */

  private void setAllPrevToRed(MazeSquare curr) {

    while(curr != null) {
      this.mazeImg.setRGB(curr.getCol(), curr.getRow(), Color.red.getRGB());
      curr = curr.getPrev();
    }

  }

  /**
   * finds the start square in the maze.
   * @return the start square in the maze.
   */

  private MazeSquare findStartSquare() {
    for (int col = 0; col < this.maze[0].length; col++) {
      if (this.maze[0][col].isWhite()) {
        return this.maze[0][col + this.getStartWidth() / 2 - 1];
      }
    }
    return null;
  }

  /**
   * displays the solved maze in image form.
   */

  public BufferedImage retrieveSolvedMazeImg() {
    if (!solutionFound) {
      throw new IllegalArgumentException("maze image was not valid");
    }
      return this.mazeImg;
  }

  /**
   * Determines if a square is valid to continue moving through the maze.
   * @param square the square to check.
   * @param visited the {@code Set<MazeSquare>} of {@code MazeSquare} objects previously processed.
   * @return true if the square is valid, false otherwise.
   */

  private boolean squareIsValid(MazeSquare square, Set<MazeSquare> visited) {
    return square != null && !visited.contains(square) && !square.getColor().equals(Color.black)
        && !this.xPixelsWithinBlack(square, this.getStartWidth() / 2 + 1);
  }

  /**
   * Determines if the current square is at the end of the maze.
   * @param curr the current square to process.
   * @return true if the maze has been solved, false otherwise.
   */

  private boolean checkForSolution(MazeSquare curr) {
    return (curr.getRow() == this.mazeImg.getHeight() - 1 && curr.getColor().equals(Color.WHITE));
  }

  /**
   * Initializes the maze to be processed.
   */

  private void initMaze() {

    this.setColors();

    this.setNeighbors();
  }

  /**
   * Sets all of the fields other than color in each {@code MazeSquare} object in the maze.
   */

  private void setNeighbors() {
    for (int i = 0; i < this.mazeImg.getHeight(); i++) {
      for (int j = 0; j < this.mazeImg.getWidth(); j++) {
        if (i > 0) {
          this.maze[i][j].setUp(maze[i-1][j]);
        }
        if (i < this.mazeImg.getHeight() - 1) {
          this.maze[i][j].setDown(maze[i+1][j]);
        }
        if (j > 0) {
          this.maze[i][j].setLeft(maze[i][j-1]);
        }
        if (j < this.mazeImg.getWidth() - 1) {
          this.maze[i][j].setRight(maze[i][j+1]);
        }
        if (i > 0 && j < this.mazeImg.getWidth() - 1) {
          this.maze[i][j].setUpRight(maze[i-1][j+1]);
        }
        if (i > 0 && j > 0) {
          this.maze[i][j].setUpLeft(maze[i-1][j-1]);
        }
        if (i < this.mazeImg.getHeight() - 1 && j > 0) {
          this.maze[i][j].setDownLeft(maze[i+1][j-1]);
        }
        if (i < this.mazeImg.getHeight() - 1 && j < this.mazeImg.getWidth() - 1) {
          this.maze[i][j].setDownRight(maze[i+1][j+1]);
        }
      }
    }
  }

  /**
   * Initializes only the colors of the {@code MazeSquare} objects in the maze.
   */

  private void setColors() {
    for (int i = 0; i < this.mazeImg.getHeight(); i++) {
      for (int j = 0; j < this.mazeImg.getWidth(); j++) {
        maze[i][j] = new MazeSquare(null, null, null, null, null,
            null, null, null, i, j,
            i - mazeImg.getHeight(), new Color(mazeImg.getRGB(j,i)));
      }
    }
  }

}