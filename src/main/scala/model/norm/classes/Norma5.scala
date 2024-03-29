package cl.uchile.dcc.citric
package model.norm.classes

import model.norm.abstractc.AbstractNorma
import model.units.classes.PlayerCharacter

/** Class representing the first Norma (Norma5) in the game.
 * @param owner The player character who owns this Norma.
 */
class Norma5(owner: PlayerCharacter) extends AbstractNorma(200, 14, owner){

  /** Upgrades the Norma if the player meets the requirements.
   * If the player has enough stars or wins, the Norma is upgraded to Norma5.
   */
  override def upgrade(): Unit ={
    if(owner.stars >= stars || owner.wins >= wins){
      owner.intNorm = 6
      owner.norma = new Norma6(owner)
      owner.norma.upgrade()
    }
  }
}
