package cl.uchile.dcc.citric
package model.units.classes.wilds

import model.units.traits.{UnitTrait, WildUnit}
import model.units.abstractc.AbstractUnit
import model.units.classes.PlayerCharacter
import model.units.classes.wilds.{Chicken, Robo_ball, Seagull}

/**
 * Class that represents the Chicken wild unit.
 * @param maxHp the maximum hp of the unit.
 * @param attack the attack of the unit.
 * @param defense the defense of the unit.
 * @param evasion the evasion of the unit.
 *
 * @author [[https://github.com/Dagt07/ David García T.]]
 */
class Chicken (maxHp: Int, attack: Int, defense: Int, evasion: Int) extends AbstractUnit(maxHp, attack, defense, evasion) with WildUnit {

  def increaseStars(unit: UnitTrait, value: Int): Unit = {
    unit.increaseStarsChicken(this, value)
  }

  def increaseStarsPlayer(unit: PlayerCharacter, value: Int): Unit = {
    unit.stars += this.stars + value + 3 //Chicken bonus = 3 stars
  }

  def increaseStarsChicken(unit: Chicken, value: Int): Unit = {} //we asume that WildUnit vs WildUnit combat isn't allowed

  def increaseStarsRobo_ball(unit: Robo_ball, value: Int): Unit = {} //we asume that WildUnit vs WildUnit combat isn't allowed

  def increaseStarsSeagull(unit: Seagull, value: Int): Unit = {} //we asume that WildUnit vs WildUnit combat isn't allowed

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