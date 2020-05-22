import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 * Controller that handles inputs to buttons on the view.
 */

public class Controller {

  View v;

  /**
   * creates an {@code Controller} instance.
   * @param v the view that the controller has access to.
   */

  public Controller(View v) {
    this.v = v;
  }

  public void go() {
    this.v = new View();
  }
  /**
   * Determines what to do if the left button is clicked.
   */

  public void lefButtonClicked() {
    if (v.getJfc().showOpenDialog(v.getFindImage()) == JFileChooser.APPROVE_OPTION) {
      try {
        this.v.setUnsolvedMaze(ImageIO.read(new File(this.v.getJfc().getSelectedFile().getAbsolutePath())));
        this.v.setScaledUnsolvedMaze(v.getUnsolvedMaze().getScaledInstance(
            (int) (this.v.getFrame().getWidth() / 2.5), (int) (this.v.getFrame().getHeight() / 1.5),
            Image.SCALE_SMOOTH));
        this.v.getlLabel().setIcon(new ImageIcon(this.v.getScaledUnsolvedMaze()));
        this.v.setPathToUnsolvedMaze(v.getJfc().getSelectedFile().getAbsolutePath());
        this.v.revalidateFrame();
        this.v.repaint();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }

  /**
   * Determines what to do if the rigth button is clicked.
   */

  public void rightButtonClicked() {
    if (v.getJfc().showOpenDialog(v.getFindImage()) == JFileChooser.APPROVE_OPTION) {
      File f = new File(v.getJfc().getCurrentDirectory() + "/SolvedMaze.png");
      try {
        ImageIO.write((RenderedImage) v.getSolvedMaze(), "png", f);
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }

  /**
   * Determines what to do if the middle button is clicked.
   */

  public void middleButtonClicked() {
    try {
      Maze m = new Maze(v.getPathToUnsolvedMaze());
      if (this.v.getAlgos().getSelectedItem().toString().equals("A* Modification")) {
        m.solveMazePriorityQueue();
      } else if (this.v.getAlgos().getSelectedItem().toString().equals("BFS")){
        m.solveMazeBFS();
      } else if (this.v.getAlgos().getSelectedItem().toString().equals("DFS")) {
        m.solveMazeDFS();
      }
      this.v.setSolvedMaze(m.retrieveSolvedMazeImg());
      this.v.setScaledSolvedMaze(v.getSolvedMaze().getScaledInstance(
          (int) (this.v.getFrame().getWidth() / 2.5), (int) (this.v.getFrame().getHeight() / 1.5),
          Image.SCALE_SMOOTH));
      this.v.getRLabel().setIcon(new ImageIcon(this.v.getScaledUnsolvedMaze()));
      this.v.revalidateFrame();
      this.v.repaint();
    } catch (IOException ioException) {
      return;
    } catch (IllegalArgumentException iaException) {
      return;
    }
  }
}
