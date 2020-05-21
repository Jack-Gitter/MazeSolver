import java.awt.Color;
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
  private String filePath;

  /**
   * Instantiates a maze.
   * @param filePath the path to the maze to replicate.
   * @throws IOException if the file path is invalid.
   */

  public Maze(String filePath) throws IOException {
    this.filePath = filePath;
    File f = new File(this.filePath);
    this.mazeImg = ImageIO.read(f);
    this.solutionFound = false;
    this.maze = new MazeSquare[mazeImg.getHeight()][mazeImg.getWidth()];
    this.initMaze();
  }

  /**
   * Initializes the maze to be processed.
   */

  private void initMaze() {
    for (int i = 0; i < this.mazeImg.getHeight(); i++) {
      for (int j = 0; j < this.mazeImg.getWidth(); j++) {
        maze[i][j] = new MazeSquare(null, null, null, null, null, null, null, null, i, j, i - mazeImg.getHeight(), new Color(mazeImg.getRGB(j,i)));
      }
    }
    for (int i = 0; i < this.mazeImg.getHeight(); i++) {
      for (int j = 0; j < this.mazeImg.getWidth(); j++) {
        if (i > 0) {
          this.maze[i][j].setUp(maze[i-1][j]);
        } else {
          this.maze[i][j].setUp(null);
        }
        if (i < this.mazeImg.getHeight() - 1) {
          this.maze[i][j].setDown(maze[i+1][j]);
        } else {
          this.maze[i][j].setDown(null);
        }
        if (j > 0) {
          this.maze[i][j].setLeft(maze[i][j-1]);
        } else {
          this.maze[i][j].setLeft(null);
        }
        if (j < this.mazeImg.getWidth() - 1) {
          this.maze[i][j].setRight(maze[i][j+1]);
        } else {
          this.maze[i][j].setRight(null);
        }
        if (i > 0 && j < this.mazeImg.getWidth() - 1) {
          this.maze[i][j].setUpRight(maze[i-1][j+1]);
        } else {
          this.maze[i][j].setUpRight(null);
        }
        if (i > 0 && j > 0) {
          this.maze[i][j].setUpLeft(maze[i-1][j-1]);
        } else {
          this.maze[i][j].setUpLeft(null);
        }
        if (i < this.mazeImg.getHeight() - 1 && j > 0) {
          this.maze[i][j].setDownLeft(maze[i+1][j-1]);
        } else {
          this.maze[i][j].setDownLeft(null);
        }
       if (i < this.mazeImg.getHeight() - 1 && j < this.mazeImg.getWidth() - 1) {
         this.maze[i][j].setDownRight(maze[i+1][j+1]);
       } else {
         this.maze[i][j].setDownRight(null);
       }
      }
    }
  }

  /**
   * solves the maze.
   */

  public void solveMazeFloodFill() {
    MazeSquare startSquare = this.findStartSquare();
    Deque<MazeSquare> dq = new LinkedList<MazeSquare>();
    Set<MazeSquare> visited = new HashSet<>();
    if (startSquare == null) {
      throw new IllegalArgumentException("You have given an invalid maze");
    }
    dq.offerLast(startSquare);
    while (dq.size() != 0) {
      MazeSquare curr = dq.pollFirst();
      visited.add(curr);
      if (curr.getRow() == this.mazeImg.getHeight() - 1 && curr.getColor().equals(Color.WHITE)) {
        this.setAllPrevToRed(curr);
        this.solutionFound = true;
        break;
      }
      if (curr.getUp() != null && !visited.contains(curr.getUp())
          && curr.getColor().equals(Color.WHITE)
          && !this.xPixelsWithinBlack(curr.getUp(), (int) (this.getStartWidth() / 2))) {
        dq.offerLast(curr.getUp());
        visited.add(curr.getUp());
        curr.getUp().setPrev(curr);
      }
      if (curr.getDown() != null && !visited.contains(curr.getDown())
          && curr.getColor().equals(Color.WHITE)
          && !this.xPixelsWithinBlack(curr.getDown(), (int) (this.getStartWidth() / 2))) {
        dq.offerLast(curr.getDown());
        visited.add(curr.getDown());
        curr.getDown().setPrev(curr);
      }
      if (curr.getLeft() != null && !visited.contains(curr.getLeft())
          && curr.getColor().equals(Color.WHITE)
          && !this.xPixelsWithinBlack(curr.getLeft(), (int) (this.getStartWidth() / 2))) {
        dq.offerLast(curr.getLeft());
        visited.add(curr.getLeft());
        curr.getLeft().setPrev(curr);
      }
      if (curr.getRight() != null && !visited.contains(curr.getRight())
          && curr.getColor().equals(Color.WHITE)
          && !this.xPixelsWithinBlack(curr.getRight(), (int) (this.getStartWidth() / 2))) {
        dq.offerLast(curr.getRight());
        visited.add(curr.getRight());
        curr.getRight().setPrev(curr);
      }
    }
  }

  public void solveMazePriorityQueue() {
    MazeSquare startSquare = this.findStartSquare();
    Comparator<MazeSquare> c = (m1, m2) -> m2.getDistToEnd() - m1.getDistToEnd();
    PriorityQueue<MazeSquare> pq = new PriorityQueue<>(c);
    Set<MazeSquare> visited = new HashSet<>();
    if (startSquare == null) {
      throw new IllegalArgumentException("You have given an invalid maze");
    }
    pq.add(startSquare);
    while (pq.size() != 0) {
      MazeSquare curr = pq.poll();
      this.mazeImg.setRGB(curr.getCol(), curr.getRow(), new Color(3, 252, 211).getRGB());
      visited.add(curr);
      if (curr.getRow() == this.mazeImg.getHeight() - 1 && curr.getColor().equals(Color.WHITE)) {
        this.setAllPrevToRed(curr);
        this.solutionFound = true;
        break;
      }
      if (curr.getUp() != null && !visited.contains(curr.getUp())
          && curr.getColor().equals(Color.WHITE)
          && !this.xPixelsWithinBlack(curr.getUp(), (int) (this.getStartWidth() / 2))) {
        pq.add(curr.getUp());
        visited.add(curr.getUp());
        curr.getUp().setPrev(curr);
      }
      if (curr.getDown() != null && !visited.contains(curr.getDown())
          && curr.getColor().equals(Color.WHITE)
          && !this.xPixelsWithinBlack(curr.getDown(), (int) (this.getStartWidth() / 2))) {
        pq.add(curr.getDown());
        visited.add(curr.getDown());
        curr.getDown().setPrev(curr);
      }
      if (curr.getLeft() != null && !visited.contains(curr.getLeft())
          && curr.getColor().equals(Color.WHITE)
          && !this.xPixelsWithinBlack(curr.getLeft(), (int) (this.getStartWidth() / 2))) {
        pq.add(curr.getLeft());
        visited.add(curr.getLeft());
        curr.getLeft().setPrev(curr);
      }
      if (curr.getRight() != null && !visited.contains(curr.getRight())
          && curr.getColor().equals(Color.WHITE)
          && !this.xPixelsWithinBlack(curr.getRight(), (int) (this.getStartWidth() / 2))) {
        pq.add(curr.getRight());
        visited.add(curr.getRight());
        curr.getRight().setPrev(curr);
      }
    }
  }

  private int getStartWidth() {
    int res = 0;
    for (int col = 0; col < this.mazeImg.getWidth(); col++) {
      if (this.mazeImg.getRGB(col, 0) == Color.white.getRGB()) {
        res +=1;
      }
    }
    return res;
  }

  private boolean xPixelsWithinBlack(MazeSquare curr, int x) {
    Deque<MazeSquare> dq = new LinkedList<>();
    Set<MazeSquare> visited = new HashSet<>();
    if (curr.getColor().equals(Color.black)) {
      return true;
    }
    dq.offerLast(curr);
    for (int i = 0; i < x-1; i++) {
      int currentlen = dq.size();
      for(int j = 0; j < currentlen; j++) {
        MazeSquare current = dq.pollFirst();
        if (current.getColor().equals(Color.black)) {
          return true;
        }
        visited.add(current);
        if (current.getUp() != null && !visited.contains(current.getUp())) {
          if (current.getUp().getColor().equals(Color.BLACK)) {
            return true;
          } else {
            dq.offerLast(current.getUp());
            visited.add(current.getUp());
          }
        }
        if (current.getLeft() != null && !visited.contains(current.getLeft())) {
          if (current.getLeft().getColor().equals(Color.BLACK)) {
            return true;
          } else {
            dq.offerLast(current.getLeft());
            visited.add(current.getLeft());
          }
        }
        if (current.getRight() != null && !visited.contains(current.getRight())) {
          if (current.getRight().getColor().equals(Color.BLACK)) {
            return true;
          } else {
            dq.offerLast(current.getRight());
            visited.add(current.getRight());
          }
        }
        if (current.getDown() != null && !visited.contains(current.getDown())) {
          if (current.getDown().getColor().equals(Color.BLACK)) {
            return true;
          } else {
            dq.offerLast(current.getDown());
            visited.add(current.getDown());
          }
        }
        if (current.getUpRight() != null && !visited.contains(current.getUpRight())) {
          if (current.getUpRight().getColor().equals(Color.black)) {
            return true;
          }
          else {
            dq.offerLast(current.getUpRight());
            visited.add(current.getUpRight());
          }
        }
        if (current.getUpLeft() != null && !visited.contains(current.getUpLeft())) {
          if (current.getUpLeft().getColor().equals(Color.black)) {
            return true;
          } else {
            dq.offerLast(current.getUpLeft());
            visited.add(current.getUpLeft());
          }
        }
        if (current.getDownLeft() != null && !visited.contains(current.getDownLeft())) {
          if (current.getDownLeft().getColor().equals(Color.black)) {
            return true;
          } else {
            dq.offerLast(current.getDownLeft());
            visited.add(current.getDownLeft());
          }
        }
        if (current.getDownRight() != null && !visited.contains(current.getDownRight())) {
          if (current.getDownRight().getColor().equals(Color.black)) {
            return true;
          } else {
            dq.offerLast(current.getDownRight());
            visited.add(current.getDownRight());
          }
        }
      }
    }
      return false;
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
        return this.maze[2][col + this.getStartWidth() / 2 - 1];
      }
    }
    return null;
  }

  /**
   * displays the solved maze in image form.
   */

  public BufferedImage retrieveSolvedMazeImg() {
    return this.mazeImg;
  }

}