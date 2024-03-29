package cl.uchile.dcc.citric
package model.norm.classes

import model.norm.abstractc.AbstractNorma
import model.units.classes.PlayerCharacter

/** Class representing the first Norma (Norma3) in the game.
 * @param owner The player character who owns this Norma.
 */
class Norma3(owner: PlayerCharacter) extends AbstractNorma(70, 6, owner){

  /** Upgrades the Norma if the player meets the requirements.
   * If the player has enough stars or wins, the Norma is upgraded to Norma4.
   */
  override def upgrade(): Unit ={
    if(owner.stars >= stars || owner.wins >= wins){
      owner.intNorm = 4
      owner.norma = new Norma4(owner)
      owner.norma.upgrade()
    }
  }
}
