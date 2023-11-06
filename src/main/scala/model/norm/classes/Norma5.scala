package cl.uchile.dcc.citric
package model.norm.classes

import model.norm.abstractc.AbstractNorma
import model.units.classes.PlayerCharacter

class Norma5(owner: PlayerCharacter) extends AbstractNorma(200, 14, owner){

  override def upgrade(): Unit ={
    if(owner.stars >= stars || owner.wins >= wins){
      owner.intNorm = 6
      owner.norma = new Norma6(owner)
      owner.norma.upgrade()
    }
  }
}
