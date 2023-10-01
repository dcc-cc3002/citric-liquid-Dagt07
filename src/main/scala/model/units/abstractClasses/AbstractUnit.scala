package cl.uchile.dcc.citric
package model.units.abstractClasses

import model.units.traits.unitTrait
import scala.util.Random
import scala.math.max

/**
 * Abstract class that represents a unit.
 * @param CMaxHp the maximum hp of the unit.
 * @param CAttack the attack of the unit.
 * @param CDefense the defense of the unit.
 * @param CEvasion the evasion of the unit.
 */
abstract class AbstractUnit(val CMaxHp: Int, val CAttack: Int, val CDefense: Int, val CEvasion: Int) extends unitTrait{

  /** Security for values */
  private val _maxHp: Int = CMaxHp
  private val _attack: Int = CAttack
  private val _defense: Int = CDefense
  private val _evasion: Int = CEvasion

  /** Security for variables */
  private var _stars: Int = 0
  private var _currentHP: Int = CMaxHp

  val randomNumberAttack: Random = new Random()
  val randomNumberDefense: Random = new Random()
  val randomNumberEvasion: Random = new Random()

  /** Getters for values */

  /** getter for maxHP
   * @return the max HP of the player
   */
  def maxHp: Int = _maxHp

  /** getter for attack
   * @return the attack of the player
   */
  def attack: Int = _attack

  /** getter for defense
   * @return the defense of the player
   */
  def defense: Int = _defense

  /** getter for evasion
   * @return the evasion of the player
   */
  def evasion: Int = _evasion

  /** Getters/Setters for variables */

  /** getter for currentHP
   * @return the current HP of the player
   */
  def currentHP: Int = _currentHP

  /** getter for stars
   * @return the current star count of the player
   */
  def stars: Int = _stars

  /** Setter for CurrentHP
   * @param newAmount an Int amount for update the healthPoints of the player
   * @return the new HP value of the player
   */
  def currentHP_=(newAmount: Int): Unit = _currentHP = newAmount

  /** Setter for stars
   *
   * @param newAmount an Int amount for update the star count of the player
   * @return the new stars value of the player
   */
  def stars_=(newAmount: Int): Unit = _stars = newAmount

  /*roll dice 2.0*/
  def rollDiceAttack(): Int = {
    randomNumberGenerator2.nextInt(6) + 1
  }

  def attackMove(): Unit = {
  }

  def defendMove(): Unit = {
    luck = max(1,rollDice2()+attack-(rollDice2()))
  }

  def evadeMove(): Unit = {
  }

}
