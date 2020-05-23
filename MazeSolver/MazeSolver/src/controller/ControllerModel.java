package controller;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import model.Maze;
import model.MazeModel;
import view.View;

public interface ControllerModel {


  /**
   * Determines what to do if the left button is clicked.
   */

  public void lefButtonClicked();

  /**
   * Determines what to do if the right button is clicked.
   */

  public void rightButtonClicked();

  /**
   * Determines what to do if the middle button is clicked.
   */

  public void middleButtonClicked();

  /**
   * Sets the action listener of the views button for a specific button.
   * @param b the button given from the view.
   */

  public void setButtonActionListener(JButton b);

  /**
   * Initializes the action listeners for the view.
   */
  public void initActionListeners();

  /**
   * Initializes the file chooser for the view.
   */

  public void initFileChooser();
}
