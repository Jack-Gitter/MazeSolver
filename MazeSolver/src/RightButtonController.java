import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class RightButtonController implements ActionListener {

  View v;

  public RightButtonController(View v) {
    this.v = v;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (v.getJfc().showOpenDialog(v.getFindImage()) == JFileChooser.APPROVE_OPTION) {
      File f = new File(v.getJfc().getCurrentDirectory() + "/SolvedMaze.png");
      try {
        ImageIO.write((RenderedImage) v.getSolvedMaze(), "png", f);
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }
}
