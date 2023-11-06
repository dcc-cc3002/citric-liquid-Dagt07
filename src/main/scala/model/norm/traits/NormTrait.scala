package cl.uchile.dcc.citric
package model.norm.traits

import model.units.classes.PlayerCharacter

trait NormTrait {

  val stars: Int
  val wins: Int
  val owner: PlayerCharacter

  /** Method that upgrades the norm of the player */
  def upgrade(): Unit
}
