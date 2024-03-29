package cl.uchile.dcc.citric
package controller.states.traits

import controller.GameController

trait GameState {
  var controller: GameController
  def doAction(): Unit
}
