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
abstract class abstractUnit(val maxHp: Int, val attack: Int, val defense: Int, val evasion: Int) extends unitTrait{
  private var _stars: Int = 0
  private var _currentHP: Int = maxHp

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
  def stars_=(newAmount: Int): Unit = {
    _stars = newAmount
    println(s"cambiando estrellas $newAmount")
  }
}
