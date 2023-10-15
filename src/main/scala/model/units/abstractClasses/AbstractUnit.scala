package cl.uchile.dcc.citric
package model.units.abstractClasses

import model.units.traits.UnitTrait

import scala.util.Random
import scala.math.max

/**
 * Abstract class that represents a unit.
 * @param CMaxHp the maximum hp of the unit.
 * @param CAttack the attack of the unit.
 * @param CDefense the defense of the unit.
 * @param CEvasion the evasion of the unit.
 * @param randomNumberGenerator A utility to generate random numbers. Defaults to a new `Random` instance.
 */
abstract class AbstractUnit(val CMaxHp: Int, val CAttack: Int, val CDefense: Int, val CEvasion: Int) extends UnitTrait{

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

  /** Other methods */
  /** Rolls a dice and returns a value between 1 to 6. */
  def rollDice(seed: Int = 0): Int = {
    if (seed != 0){
      val randomNumberGenerator: Random = new Random(seed)
      return randomNumberGenerator.nextInt(6) + 1
    }
    val randomNumberGenerator: Random = new Random()
    randomNumberGenerator.nextInt(6) + 1
  }

  /*
  def attackMove(): Unit = {
  }
  */
  def defendMove(opponent: UnitTrait): Int = {
    val luck = max(1, opponent.rollDice() + opponent.attack - (rollDice() + defense))
    //println(luck,opponent.attack)
    currentHP -= luck
    luck //returns the damage taken
  }

  def evadeMove(opponent: UnitTrait): Int = {
    val selfLuck = rollDice()+ evasion
    val opponentLuck = opponent.rollDice() + opponent.attack
    //println(currentHP, selfLuck, opponentLuck, opponent.attack)
    if (selfLuck <= opponentLuck){
      //case when it will take damage
      currentHP -= opponentLuck
      //println("Gano el oponente")
      //println(currentHP)
      return opponentLuck //returns the damage taken
    }
    //println("Gano evadir")
    //println(currentHP)
    selfLuck //returns the damage taken
  }
}
