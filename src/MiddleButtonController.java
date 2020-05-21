import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;

public class MiddleButtonController implements ActionListener {

  View v;

  public MiddleButtonController(View v) {
    this.v = v;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      Maze m = new Maze(v.getPathToUnsolvedMaze());
      if (this.v.getAlgos().getSelectedItem().toString().equals("A* Modification")) {
        m.solveMazePriorityQueue();
      } else if (this.v.getAlgos().getSelectedItem().toString().equals("BFS")){
        m.solveMazeFloodFill();
      }
      this.v.setSolvedMaze(m.retrieveSolvedMazeImg());
      this.v.setScaledSolvedMaze(v.getSolvedMaze().getScaledInstance(
          (int) (this.v.getFrame().getWidth() / 2.5), (int) (this.v.getFrame().getHeight() / 1.5),
          Image.SCALE_SMOOTH));
      this.v.getrLabel().setIcon(new ImageIcon(this.v.getScaledUnsolvedMaze()));
      this.v.revalidateFrame();
      this.v.repaint();
    } catch (IOException ioException) {
      return;
    } catch (IllegalArgumentException iaException) {
      return;
    }
  }
}
