package cl.uchile.dcc.citric
package model.Panels.Classes

import model.Units.Classes.PlayerCharacter
import model.Panels.Traits.Panel
import model.Panels.AbstractClasses.AbstractPanel

import scala.collection.mutable.ArrayBuffer

/** A class that represents a neutral panel in the board.
 *
 * This type of panel has no special effects on the player characters that land on it.
 *
 * @param characters The list of characters currently on this panel.
 * @param nextPanels The list of panels that are directly connected to this one.
 * @constructor create a new neutral panel with the specified characters and next panels
 *
 * @author [[https://github.com/Dagt07 David Garcia T.]]
 */

class NeutralPanel(characters: ArrayBuffer[PlayerCharacter], nextPanels: ArrayBuffer[Panel])
  extends AbstractPanel(characters, nextPanels){

}
