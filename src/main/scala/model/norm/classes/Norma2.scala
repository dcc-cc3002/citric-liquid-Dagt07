package cl.uchile.dcc.citric
package model.norm.classes

import model.norm.abstractc.AbstractNorma
import model.units.classes.PlayerCharacter

/**Class representing the second Norma (Norma2) in the game.
 * @param owner The player character who owns this Norma.
 */
class Norma2(owner: PlayerCharacter) extends AbstractNorma(30, 3, owner){

  /** Upgrades the Norma if the player meets the requirements.
   * If the player has enough stars or wins, the Norma is upgraded to Norma3.
   */
  override def upgrade(): Unit ={
    if(owner.stars >= stars || owner.wins >= wins){
      owner.intNorm = 3
      owner.norma = new Norma3(owner)
      owner.norma.upgrade()
    }
  }
}
