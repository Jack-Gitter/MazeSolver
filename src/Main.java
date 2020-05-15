import java.awt.Color;
import java.io.IOException;
import java.util.Scanner;

/*
Issues:
  how to get the red line to draw in the middle of the rows and columns instead of on the edges.
  implement another solve algorithm, which uses a priority queue and the factor for the q is the distance
  from the bottom row that the current square is at, so i would have to add a dist from bottom parameter
  to the mazeSquare class
  to fix the issue of the red line not begin in the middle, just do a check to see what side the black
  line is on, and then move it like 10 pixels the other way, to get it off the black line. That should do for now to
  at least give it some space
 */

public class Main {
  public static void main(String[] args) {
    Maze m = null;
    Scanner s = new Scanner(System.in);
    try {
      System.out.println("please enter a file path for your maze");
      m = new Maze(s.next());
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
    m.solveMazePriorityQueue();
  }
}
