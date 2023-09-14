package cl.uchile.dcc.citric
package model.Panels.AbstractClasses

import scala.collection.mutable.ArrayBuffer
import model.Panels.Traits.Panel
import model.Units.Classes.PlayerCharacter

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
