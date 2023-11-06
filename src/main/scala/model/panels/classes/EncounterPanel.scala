package cl.uchile.dcc.citric
package model.panels.classes

import model.panels.abstractc.AbstractPanel
import model.panels.traits.Panel
import model.units.traits.UnitTrait
import model.units.classes.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

/**A class that represents a panel where combats with wildUnits take place in the board.
 * @param characters The list of characters currently on this panel.
 * @param nextPanels The list of panels that are directly connected to this one.
 * @constructor create a new encounter panel with the specified characters and next panels
 *
 * @author [[https://github.com/Dagt07 David Garcia T.]]
 */

class EncounterPanel(characters: ArrayBuffer[PlayerCharacter], nextPanels: ArrayBuffer[Panel], var wildUnit: UnitTrait)
  extends AbstractPanel(characters, nextPanels){

  override def apply(): Unit = {}
}
