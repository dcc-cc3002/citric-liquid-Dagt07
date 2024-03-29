package cl.uchile.dcc.citric
package model.panels.classes

import model.panels.abstractc.AbstractPanel
import model.panels.traits.Panel
import model.units.traits.UnitTrait
import model.units.classes.PlayerCharacter
import model.units.classes.wilds.{Chicken, RoboBall, Seagull}
import scala.util.Random

import scala.collection.mutable.ArrayBuffer

/**A class that represents a panel where combats with wildUnits take place in the board.
 * @param characters The list of characters currently on this panel.
 * @param nextPanels The list of panels that are directly connected to this one.
 * @constructor create a new encounter panel with the specified characters and next panels
 *
 * @author [[https://github.com/Dagt07 David Garcia T.]]
 */

class EncounterPanel(characters: ArrayBuffer[PlayerCharacter], nextPanels: ArrayBuffer[Panel])
  extends AbstractPanel(characters, nextPanels){

  var wildUnit : UnitTrait = _

  /** random wildUnits that can spawn in this panel */
  val wilds : Map[Int, UnitTrait] = Map(1 -> new Chicken(3,-1,-1,1),
                                        2 -> new RoboBall(3,-1,1,-1),
                                        3 -> new Seagull(3,1,-1,-1))

  def spawnWildUnit(): Unit = {
    val randomWild = new Random()
    wildUnit = wilds(randomWild.nextInt(3) + 1)
  }

  override def apply(): Unit = {}
}
