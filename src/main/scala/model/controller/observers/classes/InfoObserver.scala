package cl.uchile.dcc.citric
package model.controller.observers.classes

import model.controller.observers.traits.Observer
import model.units.traits.UnitTrait

class InfoObserver extends Observer {

  def updateAttack(from: UnitTrait, to: UnitTrait): Unit = {
    println(s"Defending unit HP reduced to ${to.currentHP}")
  }

}
