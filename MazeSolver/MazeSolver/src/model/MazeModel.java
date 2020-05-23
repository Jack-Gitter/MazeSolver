package model;

import java.awt.image.BufferedImage;

public interface MazeModel {

  /**
   * Solves a maze in a variation of A*.
   */

  void solveMazePriorityQueue();

  /**
   * Solves a maze using DFS.
   */

  void solveMazeDFS();

  /**
   * Solves a maze using BFS.
   */

  void solveMazeBFS();

  /**
   * Retrieves the solved maze image.
   * @return the solved maze image.
   */
  
  BufferedImage retrieveSolvedMazeImg();

}
