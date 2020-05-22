package view;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public interface ViewModel {

  /**
   * returns the file chooser.
   * @return the file chooser.
   */

  public JFileChooser getJfc();
  /**
   * returns the find image button.
   * @return the find image button.
   */

  public JButton getFindImage();
  /**
   * gets the solved maze image.
   * @return the solved maze image.
   */
  public Image getSolvedMaze();


  /**
   * gets the path to the unsolved maze image file.
   * @return the path to the unsolved maze image file.
   */

  public String getPathToUnsolvedMaze();


  /**
   * gets the algorithms supported by the program.
   * @return the list of algorithms in a box.
   */


  public JComboBox getAlgos();

  /**
   * updates the left image.
   */

  public void updateLeftImage();

  /**
   * updates the right image.
   * @param solvedMaze the solved maze img.
   */
  public void updateRightImage(BufferedImage solvedMaze);

}
