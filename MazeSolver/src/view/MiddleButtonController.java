package view;

import controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener for the middle button.
 */

public class MiddleButtonController implements ActionListener {

  Controller c;

  /**
   * Instantiates the action listener
   * @param c the {@code Controller.Controller} instance.
   */

  public MiddleButtonController(Controller c) {
    this.c = c;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    c.middleButtonClicked();
  }
}
