package cl.uchile.dcc.citric
package model.panels.classes

import model.panels.abstractClasses.AbstractPanel
import model.panels.traits.Panel
import model.units.classes.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

/** A class that represents a penalty panel in the board.
 *
 * This type of panel penalty the player character that lands on it based, removing
 * a certain amount of stars based on the current norm of the player * the roll of the dice that the player obtains.
 *
 * @param characters The list of characters currently on this panel.
 * @param nextPanels The list of panels that are directly connected to this one.
 *
 * @constructor create a new Drop panel with the specified characters and next panels
 *
 * @author [[https://github.com/Dagt07 David Garcia T.]]
 */

class DropPanel(characters: ArrayBuffer[PlayerCharacter], nextPanels: ArrayBuffer[Panel])
  extends AbstractPanel(characters, nextPanels) {

  /** Penalty the player character that lands on this panel, removing some of his stars.
   *
   * It needs to know the current norm of the player and the roll of the dice that the player obtains to calculate
   * the amount of stars that the player will lose with the following criteria:
   * rollDice() * currentNorm
   *
   * @param player a player from the player characters array currently in the panel
   */

  def dropStars(player: PlayerCharacter): Unit = {
    if (characters.contains(player)){
      player.decreaseStarsByPanel()
    }
  }

}
