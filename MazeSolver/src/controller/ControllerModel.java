package controller;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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

}
