package cl.uchile.dcc.citric
package model.units.classes.wilds

import model.units.traits.{UnitTrait, WildUnit}
import model.units.abstractc.AbstractUnit
import model.units.classes.PlayerCharacter
import model.units.classes.wilds.{Chicken, RoboBall, Seagull}

/**
 * Class that represents the Robo_ball wild unit.
 * @param maxHp the maximum hp of the unit.
 * @param attack the attack of the unit.
 * @param defense the defense of the unit.
 * @param evasion the evasion of the unit.
 *
 * @author [[https://github.com/Dagt07/ David García T.]]
 */
class RoboBall(maxHp: Int, attack: Int, defense: Int, evasion: Int) extends AbstractUnit(maxHp, attack, defense, evasion) with WildUnit{

  /** Double dispatch methods, related to the combat */

  /** Increases the stars of a unit after defeating a Robo_ball wild unit.
   * @param unit The unit that defeated the Robo_ball.
   */
  def increaseStars(unit: UnitTrait): Unit = {
    unit.increaseStarsRoboBall(this)
  }

  /** Increases the stars of a player character after defeating a Robo_ball wild unit.
   * @param unit The player character who defeated the Robo_ball.
   */
  def increaseStarsPlayer(unit: PlayerCharacter): Unit = {
    unit.stars += this.stars + 2 //Robo_ball bonus = 3 stars
    this.stars = 0
    unit.increaseVictoriesVsWildUnit()
  }

}
