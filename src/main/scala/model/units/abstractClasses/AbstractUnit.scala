package cl.uchile.dcc.citric
package model.units.abstractClasses

import model.units.traits.unitTrait

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

  /** Getters for values */
  def maxHp: Int = _maxHp
  def attack: Int = _attack
  def defense: Int = _defense
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
}
