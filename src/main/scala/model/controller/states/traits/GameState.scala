package cl.uchile.dcc.citric
package model.controller.states.traits

import model.controller.GameController

trait GameState {
  var controller: GameController
  def doAction(): Unit
}
