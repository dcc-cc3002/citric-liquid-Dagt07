package cl.uchile.dcc.citric
package controller.observers

import model.units.traits.UnitTrait

class InfoObserver extends Observer {

  def updateAttack(from: UnitTrait, to: UnitTrait): Unit = {
    println(s"Defending unit HP reduced to ${to.currentHP}")
  }

}
