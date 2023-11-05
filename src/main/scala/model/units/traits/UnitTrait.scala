package cl.uchile.dcc.citric
package model.units.traits

import model.units.classes.wilds.{Chicken, Robo_ball, Seagull}
import model.units.classes.PlayerCharacter

trait UnitTrait {

  /** getters/setters to be implemented for variables*/
  def currentHP: Int
  def currentHP_=(newAmount: Int): Unit
  def stars: Int
  def stars_=(newAmount: Int): Unit
  def isKO: Boolean
  def isKO_=(newState: Boolean): Unit

  /** getters to be implemented for values */
  def maxHp: Int
  def attack: Int
  def defense: Int
  def evasion: Int

  /** Other methods */
  def rollDice(seed: Int = 0): Int
  def attackMove(opponent: UnitTrait): Int
  def attackCalculator(attackingUnit: UnitTrait): Int
  def receiveAttack(attackingUnit: UnitTrait): Int
  def defendMove(damageToReceive: Int, attackingUnit: UnitTrait): Int
  def evadeMove(damageToReceive: Int, attackingUnit: UnitTrait): Int


  def increaseStars(unit: UnitTrait,value: Int): Unit
  def increaseStarsPlayer(unit: PlayerCharacter,value: Int): Unit
  def increaseStarsChicken(unit: Chicken,value: Int): Unit
  def increaseStarsRobo_ball(unit: Robo_ball,value: Int): Unit
  def increaseStarsSeagull(unit: Seagull,value: Int): Unit

  /*
  def decreaseStars(unit: UnitTrait): Int
  def decreaseStarsPlayer(unit: PlayerCharacter): Int
  def decreaseStarsWildUnit(unit: WildUnit): Int
  */
}
