package cl.uchile.dcc.citric
package model.norm.classes

import model.norm.abstractc.AbstractNorma
import model.units.classes.PlayerCharacter

class Norma3(owner: PlayerCharacter) extends AbstractNorma(70, 6, owner){

  override def upgrade(): Unit ={
    if(owner.stars >= stars || owner.wins >= wins){
      owner.intNorm = 4
      owner.norma = new Norma4(owner)
      owner.norma.upgrade()
    }
  }
}
