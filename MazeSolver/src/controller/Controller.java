package controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import view.*;
import model.*;
/**
 * Controller.Controller that handles inputs to buttons on the view.
 */

public class Controller implements ControllerModel {

  View v;

  /**
   * creates an {@code Controller.Controller} instance.
   * @param v the view that the controller has access to.
   */

  public Controller(View v) {
    this.v = v;
  }

  /**
   * Determines what to do if the left button is clicked.
   */

  public void lefButtonClicked() {
    if (this.v.getJfc().showOpenDialog(this.v.getFindImage()) == JFileChooser.APPROVE_OPTION) {
      try {
        BufferedImage um = (ImageIO
            .read(new File(this.v.getJfc().getSelectedFile().getAbsolutePath())));
        System.out.println(um == null);
        this.v.updateLeftImage(um);
      } catch (IOException e) {
        return;
      }
    }
  }

  /**
   * Determines what to do if the right button is clicked.
   */

  public void rightButtonClicked() {
    // what class does this belong in?
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
    // this actual functionality belongs in the view in a method
    try {
      MazeModel m = new Maze(v.getPathToUnsolvedMaze());
      if (this.v.getAlgos().getSelectedItem().toString().equals("A* Modification")) {
        m.solveMazePriorityQueue();
      } else if (this.v.getAlgos().getSelectedItem().toString().equals("BFS")){
        m.solveMazeBFS();
      } else if (this.v.getAlgos().getSelectedItem().toString().equals("DFS")) {
        m.solveMazeDFS();
      }
      this.v.updateRightImage(m.retrieveSolvedMazeImg());
    } catch (IOException ioException) {
      return;
    } catch (IllegalArgumentException iaException) {
      return;
    }
  }
}
