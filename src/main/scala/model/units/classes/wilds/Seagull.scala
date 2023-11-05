package cl.uchile.dcc.citric
package model.units.classes.wilds

import model.units.traits.{UnitTrait, WildUnit}
import model.units.abstractc.AbstractUnit
import model.units.classes.PlayerCharacter
import model.units.classes.wilds.{Chicken, Robo_ball, Seagull}

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

  def increaseStars(unit: UnitTrait): Unit = {
    unit.increaseStarsSeagull(this)
  }

  def increaseStarsPlayer(unit: PlayerCharacter): Unit = {
    unit.stars += this.stars + 2 //Seagull bonus = 2 stars
    this.stars = 0
  }

  def increaseStarsChicken(unit: Chicken): Unit = {} //we asume that WildUnit vs WildUnit combat isn't allowed

  def increaseStarsRobo_ball(unit: Robo_ball): Unit = {} //we asume that WildUnit vs WildUnit combat isn't allowed

  def increaseStarsSeagull(unit: Seagull): Unit = {} //we asume that WildUnit vs WildUnit combat isn't allowed

  /*
  def decreaseStars(unit: UnitTrait): Int = {
    unit.decreaseStarsWildUnit(this)
  }

  def decreaseStarsPlayer(losingUnit: PlayerCharacter): Int = {
    val starsLost = this.stars
    this.stars = 0
    starsLost //return all the stars carried by this unit
  }

  def decreaseStarsWildUnit(losingUnit: WildUnit): Int = {
    0
  }
   */
}
