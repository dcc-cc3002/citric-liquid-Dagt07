package cl.uchile.dcc.citric
package model.panels.classes

import model.panels.abstractc.AbstractPanel
import model.panels.traits.Panel
import model.units.classes.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

/** A class that represents a home panel in the board.
 *  It is a special panel, necessary to level up the player's currentNorm level (condition to win)
 *  it also regenerates the HP of the player that lands on it
 *
 *  not implemented yet:
 *  -If the player passing by is the owner of this home panel, he can choose to activate the panel ending his turn here
 *  -If the player passing by is not the owner of this home panel, he cannot activate the panel.
 *  -If the player its not the owner of the panel, he can only activate it if he lands exactly on it, regenerating 10 HP
 *
 * @param characters The list of characters currently on this panel.
 * @param nextPanels The list of panels that are directly connected to this one.
 * @param owner the player character that owns this home panel.
 * @constructor create a new home panel with the specified characters, next panels and owner.
 *
 * @author [[https://github.com/Dagt07 David Garcia T.]]
 */

class HomePanel(characters: ArrayBuffer[PlayerCharacter], nextPanels: ArrayBuffer[Panel], val owner: PlayerCharacter)
  extends AbstractPanel(characters, nextPanels) {

  /** Regenerate the HP of the specified player
   *
   *  everytime a player lands on this panel, can activate it and regenerate 10 HP per default
   *
   * @param player the player to regenerate HP
   */
  def apply(player: PlayerCharacter): Unit = {
    if (characters.contains(player)){
      player.regenerateHP()
    }
  }

  /** Check if the specified player meets the requirements to level up his current norma level
   *
   *  Those requirements are in the following table:
   *  Norma  Stars Victories
   *   1      10       1
   *   2      30       3
   *   3      70       6
   *   4     120      10
   *   5     200      14
   *
   * @param player the player to check
   */
  def normaCheck(player: PlayerCharacter): Unit = {
    if (characters.contains(player)){
      player.norma.upgrade()
    }
  }

}
