package cl.uchile.dcc.citric
package model.controller.states.classes

import model.controller.states.abstractc.AbstractGameState

class InitialState extends AbstractGameState {

  override def doAction(): Unit = {
    controller.promptStart()
    // agregar set de turnos random
    controller.changeState(new ChapterState)
  }

}