import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// things left to do :
// why is it taking so long to solve the maze in comparison to my other one with no gui?
// why is there a discrepancy in time between this program and the other maze solver with no gui?

/**
 * Handles graphics for the program.
 */

public class View {

  private JFrame frame;
  private JPanel panel;
  private JButton findImage;
  private JButton downloadImage;
  private JButton solveMaze;
  private Box box1;
  private Box box2;
  private BufferedImage unsolvedMaze;
  private BufferedImage solvedMaze;
  private JLabel lLabel;
  private JLabel rLabel;
  private JFileChooser jfc;
  private Image scaledUnsolvedMaze;
  private Image scaledSolvedMaze;
  private String pathToUnsolvedMaze;

  public View() {

    this.pathToUnsolvedMaze = "";

    // initialize frame
    this.frame = new JFrame("Maze Solver");
    this.frame.setSize(800, 500);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setResizable(false);

    // initialize panel
    this.panel = new JPanel();

    // what should the initial inputs for the images be?
    this.unsolvedMaze = null;
    this.solvedMaze = null;

    // scale images to fit in jframe
    this.scaledUnsolvedMaze  = null;
    this.scaledSolvedMaze = null;
    // initialize buttons
    this.solveMaze = new JButton("solve maze");
    this.findImage = new JButton("import maze");
    this.downloadImage = new JButton("download solved");

    // initialize labels for images
    this.lLabel = new JLabel();
    this.rLabel = new JLabel();

    // parameters for right button
    this.downloadImage.addActionListener(new RightButtonController(this));

    // initialize file chooser
    this.jfc = new JFileChooser();
    this.jfc.setCurrentDirectory(null);
    this.jfc.setDialogTitle("pick a maze");
    this.jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

    // initialize box layouts
    this.box2 = Box.createHorizontalBox();
    this.box1 = Box.createHorizontalBox();

    // set box1 parameters
    this.box1.add(Box.createRigidArea(new Dimension(0, 20)));
    this.box1.add(Box.createHorizontalStrut(110));
    this.box1.add(this.findImage);
    this.box1.add(Box.createHorizontalStrut(110));
    this.box1.add(this.solveMaze);
    this.box1.add(Box.createHorizontalStrut(110));
    this.box1.add(this.downloadImage);
    this.frame.add(this.box1, BorderLayout.SOUTH);

    // set box2 parameters
    this.box2.add(Box.createHorizontalStrut(40));
    this.box2.add(lLabel);
    this.box2.add(Box.createHorizontalStrut(80));
    this.box2.add(rLabel);
    this.frame.add(this.box2, BorderLayout.WEST);

    // set action listener for the find maze button
    this.findImage.addActionListener(new leftButtonController(this));

    // set action listener for the solve maze button
    this.solveMaze.addActionListener(new MiddleButtonController(this));

    // display the frame.
    this.frame.setVisible(true);

  }

  public JFileChooser getJfc() {
    return this.jfc;
  }

  public JButton getFindImage() {
    return this.findImage;
  }

  public JLabel getlLabel() {
    return this.lLabel;
  }

  public JFrame getFrame() {
    return this.frame;
  }

  public Image getUnsolvedMaze() {
    return this.unsolvedMaze;
  }

  public void setUnsolvedMaze(BufferedImage i) {
    this.unsolvedMaze = i;
  }

  public void setScaledUnsolvedMaze(Image i) {
    this.scaledUnsolvedMaze = i;
  }

  public void setSolvedMaze(BufferedImage i) {
    this.solvedMaze = i;
  }

  public Image getSolvedMaze() {
    return this.solvedMaze;
  }

  public void setScaledSolvedMaze(Image i) {
    this.scaledUnsolvedMaze = i;
  }

  public Image getScaledUnsolvedMaze() {
    return this.scaledUnsolvedMaze;
  }

  public void revalidateFrame() {
    this.frame.revalidate();
  }

  public void repaint() {
    this.frame.repaint();
  }

  public String getPathToUnsolvedMaze() {
    return this.pathToUnsolvedMaze;
  }

  public JLabel getrLabel() {
    return this.rLabel;
  }

  public void setPathToUnsolvedMaze(String s) {
    this.pathToUnsolvedMaze = s;
  }


}


