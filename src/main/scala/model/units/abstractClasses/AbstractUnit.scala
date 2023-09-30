package cl.uchile.dcc.citric
package model.units.abstractClasses

import model.units.traits.unitTrait

/**
 * Abstract class that represents a unit.
 * @param maxHp the maximum hp of the unit.
 * @param attack the attack of the unit.
 * @param defense the defense of the unit.
 * @param evasion the evasion of the unit.
 */
abstract class AbstractUnit(val maxHp: Int, val attack: Int, val defense: Int, val evasion: Int) extends unitTrait{

  /** Security for values */
  private val _maxHp: Int = maxHp
  private val _attack: Int = attack
  private val _defense: Int = defense
  private val _evasion: Int = evasion

  /** Security for variables */
  private var _stars: Int = 0
  private var _currentHP: Int = maxHp

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
