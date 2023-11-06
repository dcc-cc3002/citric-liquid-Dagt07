package cl.uchile.dcc.citric
package model.norm.traits

import model.units.classes.PlayerCharacter

trait NormTrait {

  /** The number of stars required to upgrade the norma */
  val stars: Int
  /** The number of wins required to upgrade the norma */
  val wins: Int
  /** The player character who owns this norma */
  val owner: PlayerCharacter

  /** Method that upgrades the norma of the player */
  def upgrade(): Unit
}
