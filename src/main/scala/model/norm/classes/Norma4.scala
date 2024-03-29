package cl.uchile.dcc.citric
package model.norm.classes

import model.norm.abstractc.AbstractNorma
import model.units.classes.PlayerCharacter

/** Class representing the first Norma (Norma4) in the game.
 * @param owner The player character who owns this Norma.
 */
class Norma4(owner: PlayerCharacter) extends AbstractNorma(120, 10, owner){

  /** Upgrades the Norma if the player meets the requirements.
   * If the player has enough stars or wins, the Norma is upgraded to Norma5.
   */
  override def upgrade(): Unit ={
    if(owner.stars >= stars || owner.wins >= wins){
      owner.intNorm = 5
      owner.norma = new Norma5(owner)
      owner.norma.upgrade()
    }
  }
}
