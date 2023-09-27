package cl.uchile.dcc.citric
package model.norm.classes

import model.norm.traits.normTrait

class NormaClass(val defaultNorm: Int = 1, var currentNorm: Int, var normObjective: String) extends normTrait {
  val norms: Map[Int, (Int, Int)] = Map(
      2 -> (10, 1),
      3 -> (30, 3),
      4 -> (70, 6),
      5 -> (120, 10),
      6 -> (200, 14)
  )

  def getRequirements(level: Int): Option[(Int, Int)] = {
    norms.get(level)
  }
}
