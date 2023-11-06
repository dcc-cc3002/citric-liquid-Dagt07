package cl.uchile.dcc.citric
package model.panels.traits

import model.units.classes.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** Represents a single cell on a board, known as a Panel.
  *
  * Each panel has its own effect, which can be applied to a character.
  * In the context of the board game, a panel represents a tile or space that a character lands on
  * and experiences an effect (e.g., losing stars, fighting an enemy, etc.).
  * Panels can also be connected to other panels, allowing for the formation of complex board
  * structures.
  *
  * @author [[https://github.com/Dagt07 David Garcia T.]]
  * @author [[https://github.com/r8vnhill Ignacio Slater M.]]
  */
trait Panel {
  /** Array of the characters currently positioned on this panel.
   * In the game, multiple characters might be on the same panel at once, e.g., if multiple players
   * land on the same space.
   * @return a List of PlayerCharacter instances that are currently on this panel.
   */
  val characters: ArrayBuffer[PlayerCharacter]

  /** An array of panels that are directly connected to this one.
   * In the context of the game, multiple routes or paths may exist, this could represent the
   * possible next steps a player might take after being on this panel.
   * @return a List of Panel instances that are adjacent or connected to this panel.
   */
  var nextPanels: ArrayBuffer[Panel]


  /** Adds a character to the list of characters currently on this panel.
    *
    * This might be invoked when a player moves to this panel or starts their turn on it.
    *
    * @param player The player character to add to this panel.
    */
  def addCharacter(player: PlayerCharacter): Unit


  /** Removes a character from the list of characters currently on this panel.
    *
    * This might be invoked when a player moves off this panel.
    *
    * @param player The player character to remove from this panel.
    */
  def removeCharacter(player: PlayerCharacter): Unit


  /** Adds a panel to the list of panels that are directly connected to this one.
   *
   * This might be invoked when a player moves to this panel or starts their turn on it.
   *
   * @param panel The panel to add to this panel.
   * @return the panel nextPanels array with the new panel
   * @example {{{
   *  val nextPanels: ArrayBuffer[Panel] = ArrayBuffer[Panel]()
   *  addPanel(panel)
   *  #It has added a new panel to the current panel nextPanels array, isn't necessary to know the index of the panel
   *  nextPanels: ArrayBuffer[Panel] = ArrayBuffer[Panel](panel)
   * }}}
   */
  def addPanel(panel: Panel): Unit


  /** Removes a panel from the list of panels that are directly connected to this one.
   *
   * This might be invoked when a player moves off this panel.
   *
   * @param panel The panel to remove from this panel.
   * @return the panel nextPanels array without the specified panel
   * @example
   * {{{
   *   #Supose that the panel array nextPanels has 3 panels
   *   val nextPanels: ArrayBuffer[Panel] = ArrayBuffer[Panel](panel1, panel2, panel3)
   *   removePanel(nextPanels(3))
   *   #It has removed the third panel from the panel, it would be necessary to know the index of the panel
   *   nextPanels: ArrayBuffer[Panel] = ArrayBuffer[Panel](panel1, panel2)
   * }}}
   */
  def removePanel(panel: Panel): Unit


  /** Method that applies the effect of the panel to the player.
   * This method will be reimplemented (override) in the different types of panels that we will use in the game. */
  def apply(): Unit

}
