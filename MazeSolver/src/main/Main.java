package main;

import controller.Controller;
import view.View;

/*
  Runs the solver
 */

public class Main {
  public static void main(String[] args) {
    Controller c = new Controller(new View());
  }
}
