package cl.uchile.dcc.citric
package model.panels.abstractClasses

import scala.collection.mutable.ArrayBuffer
import model.panels.traits.Panel
import model.units.classes.PlayerCharacter

/** AbstractPanel will help us to create the different types of panels that we will use in the game.
 *
 * This class will be extended by the different types of panels that we will use in the game.
 *
 * @param characters The list of characters currently on this panel.
 * @param nextPanels The list of panels that are directly connected to this one.
 *
 * @constructor create a new abstract panel with the specified characters and next panels
 *
 * @author [[https://github.com/Dagt07 David Garcia T.]]
 */

abstract class AbstractPanel(val characters: ArrayBuffer[PlayerCharacter], var nextPanels: ArrayBuffer[Panel])
  extends Panel{

  /** Adds a character to the list of characters currently on this panel.
   *
   * This might be invoked when a player moves to this panel or starts their turn on it.
   *
   * @param player The player character to add to this panel.
   * @return the panel characters array with the new player
   *
   * @example {{{
   *  val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]()
   *  addCharacter(player)
   *  #It has added a new player to the current panel characters array, isn't necessary to know the index of the player
   *  characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter](player
   * }}}
   */
  def addCharacter(player: PlayerCharacter): Unit = {
    characters += player
  }

  /** Removes a character from the list of characters currently on this panel.
   *
   * This might be invoked when a player moves off this panel.
   *
   * @param player The player character to remove from this panel.
   * @return the panel characters array without the specified player
   *
   * @example
   * {{{
   *   val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]()
   *   #Supose that the panel array characters has 3 players
   *   removeCharacter(characters(2))
   *   #It has removed the third player from the panel, it would be necessary to know the index of the player
   *   characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter](player1, player2)
   * }}}
   */
  def removeCharacter(player: PlayerCharacter): Unit = {
    characters -= player
  }

}
