package cl.uchile.dcc.citric
package model.units.abstractClasses

import model.units.traits.unitTrait

abstract class abstractUnit(val maxHp: Int, val attack: Int, val defense: Int, val evasion: Int) extends unitTrait{
  var stars: Int = 0
  var currentHP: Int = maxHp
}
