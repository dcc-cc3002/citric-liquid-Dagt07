package cl.uchile.dcc.citric
package model.units.classes.wilds

import model.units.traits.{UnitTrait, WildUnit}
import model.units.abstractc.AbstractUnit
import model.units.classes.PlayerCharacter
import model.units.classes.wilds.{Chicken, RoboBall, Seagull}

/**
 * Class that represents the Seagull wild unit.
 * @param maxHp the maximum hp of the unit.
 * @param attack the attack of the unit.
 * @param defense the defense of the unit.
 * @param evasion the evasion of the unit.
 *
 * @author [[https://github.com/Dagt07/ David García T.]]
 */
class Seagull(maxHp: Int, attack: Int, defense: Int, evasion: Int) extends AbstractUnit(maxHp, attack, defense, evasion) with WildUnit{

  /** Double dispatch methods, related to the combat */

  /** Increases the stars of a unit after defeating a Seagull wild unit.
   * @param unit The unit that defeated the Seagull.
   */
  def increaseStars(unit: UnitTrait): Unit = {
    unit.increaseStarsSeagull(this)
  }

  /** Increases the stars of a player character after defeating a Seagull wild unit.
   * @param unit The player character who defeated the Seagull.
   */
  def increaseStarsPlayer(unit: PlayerCharacter): Unit = {
    unit.stars += this.stars + 2 //Seagull bonus = 2 stars
    this.stars = 0
    unit.increaseVictoriesVsWildUnit()
  }


  /** No stars are increased when a Seagull wild unit defeats another wild unit (assumed to be disallowed).
   * @param unit The Chicken unit defeated by the Seagull wild unit. */
  def increaseStarsChicken(unit: Chicken): Unit = {}

  /** No stars are increased when a Seagull wild unit defeats a Robo_ball wild unit (assumed to be disallowed).
   * @param unit The Robo_ball unit defeated by the Seagull wild unit. */
  def increaseStarsRoboBall(unit: RoboBall): Unit = {}

  /** No stars are increased when a Seagull wild unit defeats another Seagull wild unit (assumed to be disallowed).
   * @param unit The Seagull unit defeated by the Seagull wild unit. */
  def increaseStarsSeagull(unit: Seagull): Unit = {}

}
