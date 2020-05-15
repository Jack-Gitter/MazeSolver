import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * Represents a maze.
 */

public class Maze {

  private MazeSquare[][] maze;
  private BufferedImage mazeImg;

  /**
   * Instantiates a maze.
   * @param filePath the path to the maze to replicate.
   * @throws IOException if the file path is invalid.
   */

  public Maze(String filePath) throws IOException {
    File f = new File(filePath);
    this.mazeImg = ImageIO.read(f);
    this.maze = new MazeSquare[mazeImg.getHeight()][mazeImg.getWidth()];
    this.initMaze();
  }

  /**
   * Initializes the maze to be processed.
   */

  private void initMaze() {
    for (int i = 0; i < this.mazeImg.getHeight(); i++) {
      for (int j = 0; j < this.mazeImg.getWidth(); j++) {
        maze[i][j] = new MazeSquare(null, null, null, null, i, j, i - mazeImg.getHeight(), new Color(mazeImg.getRGB(j,i)));
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
      }
    }
  }

  /**
   * solves the maze.
   */

  public void solveMazeFloodFill() {
    MazeSquare startSquare = this.findStartSquare();
    Deque<MazeSquare> dq = new LinkedList<MazeSquare>();
    List<MazeSquare> visited = new ArrayList<MazeSquare>();
    if (startSquare == null) {
      throw new IllegalArgumentException("You have given an invalid maze");
    }
    dq.offerLast(startSquare);
    while (dq.size() != 0) {
      MazeSquare curr = dq.pollFirst();
      visited.add(curr);
      if (curr.getRow() == this.mazeImg.getHeight() - 1 && curr.getColor().equals(Color.WHITE)) {
        this.setAllPrevToRed(curr);
        break;
      }
      if (curr.getUp() != null && !visited.contains(curr.getUp()) && curr.getColor().equals(Color.WHITE)) {
        dq.offerLast(curr.getUp());
        visited.add(curr.getUp());
        curr.getUp().setPrev(curr);
      }
      if (curr.getDown() != null && !visited.contains(curr.getDown()) && curr.getColor().equals(Color.WHITE)) {
        dq.offerLast(curr.getDown());
        visited.add(curr.getDown());
        curr.getDown().setPrev(curr);
      }
      if (curr.getLeft() != null && !visited.contains(curr.getLeft()) && curr.getColor().equals(Color.WHITE)) {
        dq.offerLast(curr.getLeft());
        visited.add(curr.getLeft());
        curr.getLeft().setPrev(curr);
      }
      if (curr.getRight() != null && !visited.contains(curr.getRight()) && curr.getColor().equals(Color.WHITE)) {
        dq.offerLast(curr.getRight());
        visited.add(curr.getRight());
        curr.getRight().setPrev(curr);
      }
    }

    this.getSolvedMaze();
  }

  public void solveMazePriorityQueue() {
    MazeSquare startSquare = this.findStartSquare();
    Comparator<MazeSquare> c = (m1, m2) -> m2.getDistToEnd() - m1.getDistToEnd();
    PriorityQueue<MazeSquare> pq = new PriorityQueue<>(c);
    List<MazeSquare> visited = new ArrayList<MazeSquare>();
    if (startSquare == null) {
      throw new IllegalArgumentException("You have given an invalid maze");
    }
    pq.add(startSquare);
    while (pq.size() != 0) {
      MazeSquare curr = pq.poll();
      visited.add(curr);
      System.out.println("row: " + curr.getRow());
      System.out.println("col: " + curr.getCol());
      System.out.println("disttoend: " + curr.getDistToEnd());
      if (curr.getRow() == this.mazeImg.getHeight() - 1 && curr.getColor().equals(Color.WHITE)) {
        this.setAllPrevToRed(curr);
        break;
      }
      if (curr.getUp() != null && !visited.contains(curr.getUp()) && curr.getColor().equals(Color.WHITE)) {
        pq.add(curr.getUp());
        visited.add(curr.getUp());
        curr.getUp().setPrev(curr);
      }
      if (curr.getDown() != null && !visited.contains(curr.getDown()) && curr.getColor().equals(Color.WHITE)) {
        pq.add(curr.getDown());
        visited.add(curr.getDown());
        curr.getDown().setPrev(curr);
      }
      if (curr.getLeft() != null && !visited.contains(curr.getLeft()) && curr.getColor().equals(Color.WHITE)) {
        pq.add(curr.getLeft());
        visited.add(curr.getLeft());
        curr.getLeft().setPrev(curr);
      }
      if (curr.getRight() != null && !visited.contains(curr.getRight()) && curr.getColor().equals(Color.WHITE)) {
        pq.add(curr.getRight());
        visited.add(curr.getRight());
        curr.getRight().setPrev(curr);
      }
    }

    this.getSolvedMaze();
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
        return this.maze[0][col];
      }
    }
    return null;
  }

  /**
   * displays the solved maze in image form.
   */

  private void getSolvedMaze() {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(this.mazeImg.getWidth() + 50, this.mazeImg.getHeight() + 50);
    frame.add(new JLabel(new ImageIcon(this.mazeImg)));
    frame.setVisible(true);
  }

}
