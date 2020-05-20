import java.awt.Color;
import java.io.IOException;
import java.util.Scanner;

/*
  Runs the solver
 */

public class Main {
  public static void main(String[] args) {
    Maze m = null;
    Scanner s = new Scanner(System.in);
    String algo = "";
    try {
      System.out.println("please enter a file path for your maze: Ensure that it is a png and that "
          + "the bottom and top rows are all black pixels besides the starting and ending zones");
      m = new Maze(s.next());
    } catch(IOException ioe) {
      System.out.println("invalid input");
      return;
    }

    while (!algo.equals("PVDTE") && !algo.equals("FF")) {
      System.out.println("Which algorithm would you like to use? options are : "
          + "PVDTE (Prioritize Vertical Distance To End) or FF (Flood Fill)");
      algo = s.next();
    }
    System.out.println("Solving...");
    if (algo.equals("PVDTE")) {
      m.solveMazePriorityQueue();
    } else {
      m.solveMazeFloodFill();
    }
    System.out.println("solved! please check the directory where your original image was");


  }
}
