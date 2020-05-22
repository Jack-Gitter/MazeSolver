package view;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Handles graphics for the program.
 */

public class View implements ViewModel {

  private JFrame frame;
  private JPanel panel;
  private JButton findImage;
  private JButton downloadImage;
  private JButton solveMaze;
  private Box box1;
  private Box box2;
  private Box box3;
  private JLabel lLabel;
  private JLabel rLabel;
  private JFileChooser jfc;
  private Image scaledUnsolvedMaze;
  private Image scaledSolvedMaze;
  private final JComboBox<String> algos;

  public View() {


    // initialize frame
    this.frame = new JFrame("Model.Maze Solver");
    this.frame.setSize(800, 500);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setResizable(false);

    // initialize panel
    this.panel = new JPanel();

    // scale images to fit in jFrame
    this.scaledUnsolvedMaze  = null;
    this.scaledSolvedMaze = null;

    // initialize buttons
    this.solveMaze = new JButton("solve");
    this.findImage = new JButton("import maze");
    this.downloadImage = new JButton("download solved");

    // initialize labels for images
    this.lLabel = new JLabel();
    this.rLabel = new JLabel();

    // initialize JComboBox
    this.algos = new JComboBox<String>(new String[]{"A* Modification", "BFS", "DFS"});

    // parameters for right button
    this.downloadImage.addActionListener(new RightButtonController(new Controller(this)));

    // initialize file chooser
    this.jfc = new JFileChooser();
    this.jfc.setCurrentDirectory(null);
    this.jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

    // initialize box layouts
    this.box2 = Box.createHorizontalBox();
    this.box1 = Box.createHorizontalBox();
    this.box3 = Box.createHorizontalBox();

    // set box1 parameters
    this.box1.add(Box.createRigidArea(new Dimension(0, 5)));
    this.box1.add(Box.createHorizontalStrut(125));
    this.box1.add(this.findImage);
    this.box1.add(Box.createHorizontalStrut(105));
    this.box1.add(this.solveMaze);
    this.box1.add(Box.createHorizontalStrut(95));
    this.box1.add(this.downloadImage);
    this.frame.add(this.box1, BorderLayout.SOUTH);

    // set box2 parameters
    this.box2.add(Box.createHorizontalStrut(40));
    this.box2.add(lLabel);
    this.box2.add(Box.createHorizontalStrut(80));
    this.box2.add(rLabel);
    this.frame.add(this.box2, BorderLayout.WEST);

    // set box3 parameters
    this.box3.add(Box.createRigidArea(new Dimension(0, 15)));
    this.box3.add(this.algos);
    this.frame.add(this.box3, BorderLayout.NORTH);

    // set action listener for the find maze button
    this.findImage.addActionListener(new LeftButtonController(new Controller(this)));

    // set action listener for the solve maze button
    this.solveMaze.addActionListener(new MiddleButtonController(new Controller(this)));

    // display the frame.
    this.frame.setVisible(true);

  }

  /**
   * returns the file chooser.
   * @return the file chooser.
   */

  public JFileChooser getJfc() {
    return this.jfc;
  }

  /**
   * gets the algorithms supported by the program.
   * @return the list of algorithms in a box.
   */

  public JComboBox getAlgos() {
    return this.algos;
  }

  /**
   * updates the left image.
   */

  public void updateLeftImage(BufferedImage image) {
        this.scaledUnsolvedMaze  = (image.getScaledInstance(
            (int) (this.frame.getWidth() / 2.5), (int) (this.frame.getHeight() / 1.5),
            Image.SCALE_SMOOTH));
        this.lLabel.setIcon(new ImageIcon(this.scaledUnsolvedMaze));
        this.frame.revalidate();
        this.frame.repaint();
    }

  /**
   * updates the right image.
   * @param image the solved maze img.
   */

  public void updateRightImage(BufferedImage image) {
    this.scaledSolvedMaze = (image.getScaledInstance(
        (int) (this.frame.getWidth() / 2.5), (int) (this.frame.getHeight() / 1.5),
        Image.SCALE_SMOOTH));
    this.rLabel.setIcon(new ImageIcon(this.scaledSolvedMaze));
    this.frame.revalidate();
    this.frame.repaint();
  }


}


