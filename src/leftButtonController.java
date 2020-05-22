import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 * Action Listener for the left button.
 */

class leftButtonController implements ActionListener {

  Controller c;

  /**
   * Instantiates the action listener.
   * @param c the controller that the action listener has access to.
   */

  public leftButtonController(Controller c) {
    this.c = c;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.c.lefButtonClicked();
  }
}