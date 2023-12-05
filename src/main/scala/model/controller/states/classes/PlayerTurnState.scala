package cl.uchile.dcc.citric
package model.controller.states.classes

import model.controller.states.abstractc.AbstractGameState

class PlayerTurnState extends AbstractGameState{
  override def doAction(): Unit = {
    val roll = controller.playsTurn()
    controller.changeState(new MovingState(roll))
  }
}
