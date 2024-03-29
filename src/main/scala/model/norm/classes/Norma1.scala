package cl.uchile.dcc.citric
package model.norm.classes

import model.norm.abstractc.AbstractNorma
import model.units.classes.PlayerCharacter

/** Class representing the first Norma (Norma1) in the game.
 * @param owner The player character who owns this Norma.
 */

class Norma1 (owner: PlayerCharacter) extends AbstractNorma(10, 1, owner){

  /** Upgrades the Norma if the player meets the requirements.
   * If the player has enough stars or wins, the Norma is upgraded to Norma2.
   */
  override def upgrade(): Unit = {
    if(owner.stars >= stars || owner.wins >= wins){
      owner.intNorm = 2
      owner.norma = new Norma2(owner)
      owner.norma.upgrade()
    }
  }
}
