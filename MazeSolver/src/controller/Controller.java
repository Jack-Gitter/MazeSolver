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
  MazeModel m;
  String currentUnsolved;
  /**
   * creates an {@code Controller.Controller} instance.
   * @param v the view that the controller has access to.
   */

  public Controller(View v) {
    this.v = v;
    this.m = null;
    this.currentUnsolved = "";
  }

  /**
   * Determines what to do if the left button is clicked.
   */

  public void lefButtonClicked() {
    if (this.v.getJfc().showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      try {
        this.currentUnsolved = this.v.getJfc().getSelectedFile().getAbsolutePath().toString();
        BufferedImage um = (ImageIO
            .read(new File(this.v.getJfc().getSelectedFile().getAbsolutePath())));
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
    if (m != null) {
      if (v.getJfc().showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        File f = new File(v.getJfc().getCurrentDirectory() + "/SolvedMaze.png");
        try {
          ImageIO.write((RenderedImage) m.retrieveSolvedMazeImg(), "png", f);
        } catch (IOException ioException) {
          return;
        }
      }
    }
  }

  /**
   * Determines what to do if the middle button is clicked.
   */

  // need two file choosers.
  public void middleButtonClicked() {
    // this actual functionality belongs in the view in a method

    try {
      this.v.getJfc().setSelectedFile(new File(this.currentUnsolved));
      this.m = new Maze(v.getJfc().getSelectedFile().getAbsolutePath());
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
}
