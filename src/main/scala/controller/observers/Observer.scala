package cl.uchile.dcc.citric
package controller.observers

import model.units.traits.UnitTrait

trait Observer {
  def updateAttack(from: UnitTrait, to: UnitTrait): Unit
}
