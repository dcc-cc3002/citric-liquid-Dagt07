package cl.uchile.dcc.citric
package model.norm.classes

import model.norm.abstractc.AbstractNorma
import model.units.classes.PlayerCharacter

class Norma1 (owner: PlayerCharacter) extends AbstractNorma(10, 1, owner){

  override def upgrade(): Unit = {
    if(owner.stars >= stars || owner.wins >= wins){
      owner.intNorm = 2
      owner.norma = new Norma2(owner)
      owner.norma.upgrade()
    }
  }
}
