import java.awt.Color;
import java.io.IOException;
import java.util.Scanner;

/*
Issues:
  getting red line in the middle --> do a computation between the closest black line and the
  corrisponding row / col of the previous square(depnding if the black line is to the left/right or up/down),
   go in the middle of the two, then place the pixel there
 */

public class Main {
  public static void main(String[] args) {
    Maze m = null;
    Scanner s = new Scanner(System.in);
    String algo = "";
    try {
      System.out.println("please enter a file path for your maze");
      m = new Maze(s.next());
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }

    while (!algo.equals("PDTE") && !algo.equals("FF")) {
      System.out.println("Which algorithm would you like to use? options are : PDTE or FF");
      algo = s.next();
    }
    if (algo.equals("PDTE")) {
      m.solveMazePriorityQueue();
    } else {
      m.solveMazeFloodFill();
    }
    System.out.println("Solving...");

  }
}
