package cl.uchile.dcc.citric
package model.units.traits

import model.units.classes.wilds.{Chicken, Robo_ball, Seagull}
import cl.uchile.dcc.citric.model.units.classes.PlayerCharacter

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
  def defendMove(damageToReceive: Int): Int
  def evadeMove(damageToReceive: Int): Int

}
