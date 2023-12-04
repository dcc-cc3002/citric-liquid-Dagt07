package cl.uchile.dcc.citric
package model.controller.observers.traits

import model.units.traits.UnitTrait

trait Observer {
  def updateAttack(from: UnitTrait, to: UnitTrait): Unit
}
