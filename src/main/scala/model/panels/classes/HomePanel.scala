package cl.uchile.dcc.citric
package model.panels.classes

import model.panels.abstractClasses.AbstractPanel
import model.panels.traits.Panel
import model.units.classes.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

/** A class that represents a home panel in the board.
 *  It is a special panel, necessary to level up the player's currentNorm level (condition to win)
 *  it also regenerates the HP of the player that lands on it
 *
 *  not implemented yet:
 *  -If the player passing by is the owner of this home panel, he can choose to activate the panel ending his turn here
 *  regenerating 10 HP per default and doing a normCheck to see if he can level up his currentNorm level.
 *  -If the player passing by is not the owner of this home panel, he cannot activate the panel.
 *  -If the player its not the owner of the panel, he can only activate it if he lands exactly on it, regenerating 10 HP
 *  per default and doing a normCheck to see if he can level up his currentNorm level.
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

  def regenerateHP(player: PlayerCharacter): Unit = {
    if (characters.contains(player)){
      player.regenerateHP()
    }
  }

  /** Check if the specified player meets the requirements to level up his currentNorm level
   *
   *  Those requirements are in the following table:
   *  Norm Estrellas Victorias
   *   1      10         1
   *   2      30         3
   *   3      70         6
   *   4     120        10
   *   5     200        14
   *
   * @param player the player to check
   */
  def normCheck(player: PlayerCharacter): Unit ={
    if (player.normObjective == "stars"){
      if (player.currentNorm == 1 && player.stars >= 10 ) player.currentNorm +=1
      else if (player.currentNorm == 2 && player.stars >= 30) player.currentNorm += 1
      else if (player.currentNorm == 3 && player.stars >= 70) player.currentNorm += 1
      else if (player.currentNorm == 4 && player.stars >= 120) player.currentNorm += 1
      else if (player.currentNorm == 5 && player.stars >= 200) player.currentNorm += 1
    }
    else if (player.normObjective == "wins"){
      if (player.currentNorm == 1 && player.wins >= 1 ) player.currentNorm +=1
      else if (player.currentNorm == 2 && player.wins >= 3) player.currentNorm += 1
      else if (player.currentNorm == 3 && player.wins >= 6) player.currentNorm += 1
      else if (player.currentNorm == 4 && player.wins >= 10) player.currentNorm += 1
      else if (player.currentNorm == 5 && player.wins >= 14) player.currentNorm += 1
    }
  }

}
