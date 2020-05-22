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


  public void initActionListeners() {
    JButton[] buttons = this.v.getButtons();
    for (JButton b : buttons) {
      this.setButtonActionListener(b);
    }
  }

  @Override
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

  @Override
  public void rightButtonClicked() {
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

  @Override
  public void middleButtonClicked() {
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

  @Override
  public void setButtonActionListener(JButton b) {
    if (b.getText().equals("import maze")) {
      b.addActionListener(new Controller.LeftButtonController(this));
    } else if (b.getText().equals("solve")) {
      b.addActionListener(new Controller.MiddleButtonController(this));
    } else if (b.getText().equals("download solved")) {
      b.addActionListener(new Controller.RightButtonController(this));
    }
  }

  /**
   * represents a action listener for the left button.
   */

  static class LeftButtonController implements ActionListener {

    Controller c;

    /**
     * Instantiates the action listener.
     * @param c the controller that the action listener has access to.
     */

    public LeftButtonController(Controller c) {
      this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      this.c.lefButtonClicked();
    }
  }

  /**
   * represents an action listener for the middle button.
   */

  static class MiddleButtonController implements ActionListener {

    Controller c;

    /**
     * Instantiates the action listener
     * @param c the {@code Controller.Controller} instance.
     */

    public MiddleButtonController(Controller c) {
      this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      c.middleButtonClicked();
    }
  }

  /**
   * represents an action listener for the right button.
   */

  static class RightButtonController implements ActionListener {

    Controller c;

    /**
     * Instantiates the action listener.
     * @param c the {@code Controller.Controller} instance.
     */

    public RightButtonController(Controller c) {
      this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      this.c.rightButtonClicked();
    }
  }


}
