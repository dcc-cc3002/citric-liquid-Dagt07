package cl.uchile.dcc.citric
package model.norm.classes

import model.norm.abstractc.AbstractNorma
import model.units.classes.PlayerCharacter

class Norma4(owner: PlayerCharacter) extends AbstractNorma(120, 10, owner){

  override def upgrade(): Unit ={
    if(owner.stars >= stars || owner.wins >= wins){
      owner.intNorm = 5
      owner.norma = new Norma5(owner)
      owner.norma.upgrade()
    }
  }
}
