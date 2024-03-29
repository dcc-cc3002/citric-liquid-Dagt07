package cl.uchile.dcc.citric
package model.norm.classes

import model.norm.abstractc.AbstractNorma
import model.units.classes.PlayerCharacter

/** Class representing the first Norma (Norma6) in the game.
 * @param owner The player character who owns this Norma.
 */
class Norma6(owner: PlayerCharacter) extends AbstractNorma(10000, 10000, owner){

  /** Not upgrade the Norma by any chance, max Norma level */
  override def upgrade(): Unit = {
    owner.intNorm = 6
  }
}
