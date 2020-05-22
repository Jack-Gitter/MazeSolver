package model;

import java.awt.image.BufferedImage;

public interface MazeModel {

  void solveMazePriorityQueue();

  void solveMazeDFS();

  void solveMazeBFS();

  BufferedImage retrieveSolvedMazeImg();

}
