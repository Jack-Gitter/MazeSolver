import java.awt.Color;
import java.io.IOException;
import java.util.Scanner;

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
    m.solveMaze();


  }
}
