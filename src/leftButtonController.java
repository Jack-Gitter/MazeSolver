import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

class leftButtonController implements ActionListener {

  View v;

  public leftButtonController(View v) {
    this.v = v;
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    if (v.getJfc().showOpenDialog(v.getFindImage()) == JFileChooser.APPROVE_OPTION) {
      try {
        this.v.setUnsolvedMaze(ImageIO.read(new File(this.v.getJfc().getSelectedFile().getAbsolutePath())));
        this.v.setScaledUnsolvedMaze(v.getUnsolvedMaze().getScaledInstance(
            (int) (this.v.getFrame().getWidth() / 2.5), (int) (this.v.getFrame().getHeight() / 1.5),
            Image.SCALE_SMOOTH));
        this.v.getlLabel().setIcon(new ImageIcon(this.v.getScaledUnsolvedMaze()));
        this.v.setPathToUnsolvedMaze(v.getJfc().getSelectedFile().getAbsolutePath());
        this.v.revalidateFrame();
        this.v.repaint();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }
}