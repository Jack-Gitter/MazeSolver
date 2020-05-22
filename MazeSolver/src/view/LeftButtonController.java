package view;

import controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action Listener for the left button.
 */

class LeftButtonController implements ActionListener {

  Controller c;

  /**
   * Instantiates the action listener.
   * @param c the controller that the action listener has access to.
   */

  public LeftButtonController(Controller c) {
    this.c = c;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.c.lefButtonClicked();
  }
}