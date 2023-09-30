package cl.uchile.dcc.citric
package model.units.traits

/**
* @param maxHp The maximum health points a player can have. It represents the player's endurance.
* @param attack The player's capability to deal damage to opponents.
* @param defense The player's capability to resist or mitigate damage from opponents.
* @param evasion The player's skill to completely avoid certain attacks.
*/

trait unitTrait {
  /*
  val maxHp: Int
  val attack: Int
  val defense: Int
  val evasion: Int
  var currentHP: Int
  var stars: Int
  //var _currentHP: Int
  //var _stars: Int
  */

  /** getters/setters to be implemented for variables*/
  def currentHP: Int
  def currentHP_=(newAmount: Int): Unit
  def stars: Int
  def stars_=(newAmount: Int): Unit

  /** getters to be implemented for values */
  def maxHp: Int
  def attack: Int
  def defense: Int
  def evasion: Int

}
