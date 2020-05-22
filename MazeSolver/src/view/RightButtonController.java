package view;

import controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An action listener for the right button.
 */

public class RightButtonController implements ActionListener {

  Controller c;

  /**
   * Instantiates the action listener.
   * @param c the {@code Controller.Controller} instance.
   */

  public RightButtonController(Controller c) {
    this.c = c;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.c.rightButtonClicked();
  }
}
