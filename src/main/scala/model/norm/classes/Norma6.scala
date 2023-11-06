package cl.uchile.dcc.citric
package model.norm.classes

import model.norm.abstractc.AbstractNorma
import model.units.classes.PlayerCharacter

class Norma6(owner: PlayerCharacter) extends AbstractNorma(10000, 10000, owner){

  override def upgrade(): Unit = {
    owner.intNorm = 6
  }
}
