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
   * gets the algorithms supported by the program.
   * @return the list of algorithms in a box.
   */


  public JComboBox getAlgos();

  /**
   * updates the left image.
   */

  public void updateLeftImage(BufferedImage image);

  /**
   * updates the right image.
   * @param solvedMaze the solved maze img.
   */

  public void updateRightImage(BufferedImage solvedMaze);

  /**
   * gets the buttons for initialization of action listeners by the controller.
   * @return the buttons of the view.
   */

  public JButton[] getButtons();

}
