package cl.uchile.dcc.citric
package model.panels.classes

import model.units.classes.PlayerCharacter
import model.panels.traits.Panel
import model.panels.abstractc.AbstractPanel

import scala.collection.mutable.ArrayBuffer

/** A class that represents a bonus panel in the board.
 *
 * This type of panel gives stars to the player character that lands on it based on the current norm of the player
 * and the roll of the dice that the player obtains.
 *
 * @param characters The list of characters currently on this panel.
 * @param nextPanels The list of panels that are directly connected to this one.
 *
 * @constructor create a new bonus panel with the specified characters and next panels
 *
 * @author [[https://github.com/Dagt07 David Garcia T.]]
 */

class BonusPanel(characters: ArrayBuffer[PlayerCharacter], nextPanels: ArrayBuffer[Panel])
  extends AbstractPanel(characters, nextPanels){

  /** Gives stars to the player character that lands on this panel.
   *
   * It needs to know the current norm of the player and the roll of the dice that the player obtains to calculate
   * the amount of stars that the player will receive with the following criteria:
   * min(roll * Norma, roll * 3).
   *
   * @param player a player from the player characters array currently in the panel
   */
  def giveStars (player: PlayerCharacter): Unit = {
    if (characters.contains(player)) {
      player.increaseStarsByPanel()
    }
  }

}
