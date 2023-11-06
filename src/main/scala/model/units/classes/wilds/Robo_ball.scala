package cl.uchile.dcc.citric
package model.units.classes.wilds

import model.units.traits.{UnitTrait, WildUnit}
import model.units.abstractc.AbstractUnit
import model.units.classes.PlayerCharacter
import model.units.classes.wilds.{Chicken, Robo_ball, Seagull}

/**
 * Class that represents the Robo_ball wild unit.
 * @param maxHp the maximum hp of the unit.
 * @param attack the attack of the unit.
 * @param defense the defense of the unit.
 * @param evasion the evasion of the unit.
 *
 * @author [[https://github.com/Dagt07/ David García T.]]
 */
class Robo_ball(maxHp: Int, attack: Int, defense: Int, evasion: Int) extends AbstractUnit(maxHp, attack, defense, evasion) with WildUnit{

  /** Double dispatch methods, related to the combat */

  /** Increases the stars of a unit after defeating a Robo_ball wild unit.
   * @param unit The unit that defeated the Robo_ball.
   */
  def increaseStars(unit: UnitTrait): Unit = {
    unit.increaseStarsRobo_ball(this)
  }

  /** Increases the stars of a player character after defeating a Robo_ball wild unit.
   * @param unit The player character who defeated the Robo_ball.
   */
  def increaseStarsPlayer(unit: PlayerCharacter): Unit = {
    unit.stars += this.stars + 2 //Robo_ball bonus = 3 stars
    this.stars = 0
    unit.increaseVictoriesVsWildUnit()
  }

  /** No stars are increased when a Robo_ball wild unit defeats another wild unit (assumed to be disallowed).
   * @param unit The Chicken unit defeated by the Robo_ball wild unit. */
  def increaseStarsChicken(unit: Chicken): Unit = {}

  /** No stars are increased when a Robo_ball wild unit defeats another Robo_ball wild unit (assumed to be disallowed).
   * @param unit The Robo_ball unit defeated by the Robo_ball wild unit. */
  def increaseStarsRobo_ball(unit: Robo_ball): Unit = {}

  /** No stars are increased when a Robo_ball wild unit defeats a Seagull wild unit (assumed to be disallowed).
   * @param unit The Seagull unit defeated by the Robo_ball wild unit. */
  def increaseStarsSeagull(unit: Seagull): Unit = {}

}
