package cl.uchile.dcc.citric
package model.norm.abstractc

import model.norm.traits.NormTrait
import model.units.classes.PlayerCharacter

abstract class AbstractNorma (val stars: Int, val wins: Int, val owner: PlayerCharacter) extends NormTrait{

  owner.norma = this
  def upgrade(): Unit = {}

}
