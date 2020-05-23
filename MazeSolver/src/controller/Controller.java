package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import view.*;
import model.*;
/**
 * Controller.Controller that handles inputs to buttons on the view.
 */

public class Controller implements ControllerModel {

  private View v;
  private MazeModel m;
  private String currentUnsolved;
  private JFileChooser jfcUnsolvedMazeChooser;

  /**
   * creates an {@code Controller.Controller} instance.
   * @param v the view that the controller has access to.
   */

  public Controller(View v) {
    this.v = v;
    this.m = null;
    this.currentUnsolved = "";
  }


  @Override
  public void initActionListeners() {
    JButton[] buttons = this.v.getButtons();
    for (JButton b : buttons) {
      this.setButtonActionListener(b);
    }
  }

  @Override
  public void initFileChooser() {
    this.jfcUnsolvedMazeChooser = new JFileChooser();
    this.jfcUnsolvedMazeChooser.setCurrentDirectory(null);
    this.jfcUnsolvedMazeChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
  }

  @Override
  public void lefButtonClicked() {
    if (jfcUnsolvedMazeChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      this.currentUnsolved = jfcUnsolvedMazeChooser.getSelectedFile().getAbsolutePath().toString();
      try {
        BufferedImage um = (ImageIO
            .read(new File(jfcUnsolvedMazeChooser.getSelectedFile().getAbsolutePath())));
        this.v.updateLeftImage(um);
      } catch (IOException e) {
        return;
      }
    }
  }

  @Override
  public void rightButtonClicked() {
    if (m != null) {
      if (jfcUnsolvedMazeChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        File f = new File(jfcUnsolvedMazeChooser.getCurrentDirectory() + "/SolvedMaze.png");
        try {
          ImageIO.write((RenderedImage) m.retrieveSolvedMazeImg(), "png", f);
        } catch (IOException ioException) {
          return;
        }
      }
    }
  }

  @Override
  public void middleButtonClicked() {
    try {
      this.jfcUnsolvedMazeChooser.setSelectedFile(new File(this.currentUnsolved));
      this.m = new Maze(jfcUnsolvedMazeChooser.getSelectedFile().getAbsolutePath());
      if (this.v.getAlgos().getSelectedItem().toString().equals("A* Modification")) {
        this.m.solveMazePriorityQueue();
      } else if (this.v.getAlgos().getSelectedItem().toString().equals("BFS")){
        this.m.solveMazeBFS();
      } else if (this.v.getAlgos().getSelectedItem().toString().equals("DFS")) {
        this.m.solveMazeDFS();
      }
      this.v.updateRightImage(this.m.retrieveSolvedMazeImg());
    } catch (IOException ioException) {
      return;
    } catch (IllegalArgumentException iaException) {
      return;
    }
  }

  @Override
  public void setButtonActionListener(JButton b) {
    if (b.getText().equals("import maze")) {
      b.addActionListener((ActionEvent e) -> lefButtonClicked());
    } else if (b.getText().equals("solve")) {
      b.addActionListener((ActionEvent e) -> middleButtonClicked());
    } else if (b.getText().equals("download solved")) {
      b.addActionListener((ActionEvent e) -> rightButtonClicked());
    }
  }

}
