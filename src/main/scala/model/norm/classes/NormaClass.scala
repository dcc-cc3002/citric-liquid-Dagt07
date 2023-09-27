package cl.uchile.dcc.citric
package model.norm.classes

import model.norm.traits.normTrait

/** NormaCLass is a class that represents all the valids norms for the game, aka, the level system.
 *  @param defaultNorm the default norm of the player.
 *  @param currentNorm the current norm of the player.
 *  @param normObjective the objective of the norm.
 *  @constructor create a new norma class with the specified default norm, current norm and norm objective because of the trait.
 *
 *  @author [[https://github.com/Dagt07 David Garcia T.]]
 */

class NormaClass(val defaultNorm: Int = 1, var currentNorm: Int, var normObjective: String) extends normTrait {

  /** Valid norms using a map with the level as key and a tuple (x,y) where x: stars needed to level up, y: victories needed to level up. */
  val norms: Map[Int, (Int, Int)] = Map(
      2 -> (10, 1),
      3 -> (30, 3),
      4 -> (70, 6),
      5 -> (120, 10),
      6 -> (200, 14)
  )

  /** Method that the player will use with his current norm level to ask the map with valid norms the requirement to level up.
   * @param level the current norm level of the player.
   */
  def getRequirements(level: Int): Option[(Int, Int)] = {
    norms.get(level)
  }
}
