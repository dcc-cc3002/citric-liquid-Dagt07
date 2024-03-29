package cl.uchile.dcc.citric
package controller.states.classes

import controller.states.abstractc.AbstractGameState

class MovingState(var roll: Int) extends AbstractGameState{
  override def doAction(): Unit = {

    /* choose while it has remaining movements path */
    while (roll !=0) {
      controller.choosePath(1) // for testing we define now the path as always 1 (right)
      roll -= 1
      /* stop at panel, will not be implemented because depends on input */
    }
    /* outside while means out of movements */
    controller.changeState(new CombatState())
  }
}
