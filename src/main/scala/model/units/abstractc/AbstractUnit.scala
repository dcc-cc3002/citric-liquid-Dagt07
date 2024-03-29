package cl.uchile.dcc.citric
package model.units.abstractc

import model.units.traits.UnitTrait
import model.units.classes.wilds.{Chicken, RoboBall, Seagull}

import scala.util.Random
import scala.math.max

/**
 * Abstract class that represents a unit.
 * @param CMaxHp the maximum hp of the unit.
 * @param CAttack the attack of the unit.
 * @param CDefense the defense of the unit.
 * @param CEvasion the evasion of the unit.
 * @author [[https://github.com/Dagt07/ David García T.]]
 */
abstract class AbstractUnit(val CMaxHp: Int, val CAttack: Int, val CDefense: Int, val CEvasion: Int) extends UnitTrait {

  /** Security for values */
  private val _maxHp: Int = CMaxHp
  private val _attack: Int = CAttack
  private val _defense: Int = CDefense
  private val _evasion: Int = CEvasion

  /** Security for variables */
  private var _stars: Int = 0
  private var _currentHP: Int = CMaxHp
  private var _isKO: Boolean = false
  private var _decision: String = "defense" //"defense" will be the default state, "evade" its the other possibility

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

  /** getter for isKO
   * @return the KO status of the player
   */
  def isKO: Boolean = _isKO

  /** getter for decision
   * @return the decision string of the unit
   * */
  def decision: String = _decision

  /** Setter for currentHP
   * @param newAmount an Int amount for update the healthPoints of the player
   * @return the new HP value of the player
   */
  def currentHP_=(newAmount: Int): Unit = {
    if (newAmount>0) _currentHP = newAmount else _currentHP = 0
  }

  /** Setter for stars
   * @param newAmount an Int amount for update the star count of the player
   * @return the new stars value of the player
   */
  def stars_=(newAmount: Int): Unit = _stars = newAmount

  /** Setter for isKO
   * @param newStatus a Boolean value for update the KO status of the player
   * @return the new KO status of the player
   */
  def isKO_=(newStatus: Boolean): Unit = _isKO = newStatus

  /** Setter for decision
   * @param newDecision a string for update the decision state
   * @return the new decision state of a unit
   * */
  def decision_=(newDecision: String): Unit = _decision = newDecision

  /** ------------------------------ Other methods ---------------------------------------*/

  /** Rolls a dice and returns a value between 1 to 6. */
  def rollDice(seed: Int = 0): Int = {
    if (seed != 0){
      val randomNumberGenerator: Random = new Random(seed)
      return randomNumberGenerator.nextInt(6) + 1
    }
    val randomNumberGenerator: Random = new Random()
    val dice = randomNumberGenerator.nextInt(6) + 1
    dice
  }

  /**------------------------ COMBAT METHODS (using double dispatch)---------------------- */

  /** Method that a unit call to attack another unit
   *
   * @param opponent the unit that will receive the attack
   * @return the damage done by the unit
   */
  def attackMove(opponent: UnitTrait): Int = {
    opponent.receiveAttack(this)
  }

  /** Method that the attacking unit calls to calculate his damage
   *
   * @param attackingUnit the unit that will attack
   * @return the damage done by the unit
   */
  def attackCalculator(attackingUnit: UnitTrait): Int = {
    if (attackingUnit.isKO){
      return 0 // an KO unit can't attack
    }
    var damage = attackingUnit.rollDice() + attackingUnit.attack
    if (damage<0){ // damage done by a unit cant be negative
      damage=0
    }
    damage // returns the damage of the attack
  }

  /** Method that a unit that is being attack calls to protect itself
   *
   * @param attackingUnit The unit that is attacking.
   * @return The damage taken by the unit.
   */
  def receiveAttack(attackingUnit: UnitTrait): Int = {
    if (this.isKO){
      return 0 // an KO unit can't be attacked
    }
    val damageToReceive = attackingUnit.attackCalculator(attackingUnit)
    if (decision == "defense"){ // unit has decide to defend
      this.defendMove(damageToReceive,attackingUnit)
    } else if(decision == "evade"){ // else case, unit has decide to evade
      this.evadeMove(damageToReceive,attackingUnit)
    } else { // invalid tactic
      0
    }
  }

  /** Method that a unit uses to defend itself against an attack.
   *
   * @param damageToReceive The damage to receive from the attacker.
   * @param attackingUnit   The unit that is attacking.
   * @return The damage taken by the unit after defending.
   */
  def defendMove(damageToReceive: Int, attackingUnit: UnitTrait): Int = {
    val damage_taken = max(1, damageToReceive - (rollDice() + defense))
    currentHP -= damage_taken
    if (currentHP <= 0) {
      currentHP = 0
      isKO = true
      attackingUnit.increaseStars(this)
    }
    damage_taken // returns the damage taken
  }

  /** Method that a unit uses to evade an attack.
   *
   * @param damageToReceive The damage to potentially receive from the attacker.
   * @param attackingUnit   The unit that is attacking.
   * @return The damage taken by the unit after attempting to evade.
   */
  def evadeMove(damageToReceive: Int, attackingUnit: UnitTrait): Int = {
    val selfLuck = rollDice()+ evasion
    if (selfLuck <= damageToReceive){
      //case when it will take damage
      currentHP -= damageToReceive
      if (currentHP <= 0) {
        isKO = true
        currentHP = 0
        attackingUnit.increaseStars(this)
      }
      return damageToReceive // returns the damage taken
    }
    0 // returns 0 damage taken
  }

  /** Methods needed for stars Double Dispatch  */
  /* Some of them are empty because they will be override if necessary, other cases like WildUnit vs WildUnit its not allowed */
  /** @example No stars are increased when a Chicken wild unit defeats a Seagull wild unit */
  def increaseStarsChicken(unit: Chicken): Unit = {}
  /** @example No stars are increased when a RoboBall wild unit defeats another RoboBall wild unit */
  def increaseStarsRoboBall(unit: RoboBall): Unit = {}
  /** @example No stars are increased when a Seagull wild unit defeats another Seagull wild unit */
  def increaseStarsSeagull(unit: Seagull): Unit = {}


}