package cl.uchile.dcc.citric
package controller.states.classes

import controller.states.abstractc.AbstractGameState

class PlayerTurnState extends AbstractGameState{
  override def doAction(): Unit = {
    val roll = controller.playsTurn()
    controller.changeState(new MovingState(roll))
  }
}
