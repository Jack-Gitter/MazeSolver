import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 * An action listener for the right button.
 */

public class RightButtonController implements ActionListener {

  Controller c;

  /**
   * Instantiates the action listener.
   * @param c the {@code Controller} instance.
   */

  public RightButtonController(Controller c) {
    this.c = c;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.c.rightButtonClicked();
  }
}
