package cl.uchile.dcc.citric
package model.norm.classes

import model.norm.abstractc.AbstractNorma
import model.units.classes.PlayerCharacter

class Norma2(owner: PlayerCharacter) extends AbstractNorma(30, 3, owner){

  override def upgrade(): Unit ={
    if(owner.stars >= stars || owner.wins >= wins){
      owner.intNorm = 3
      owner.norma = new Norma3(owner)
      owner.norma.upgrade()
    }
  }
}
