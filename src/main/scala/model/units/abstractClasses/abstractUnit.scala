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
  var stars: Int = 0
  var currentHP: Int = maxHp
}
