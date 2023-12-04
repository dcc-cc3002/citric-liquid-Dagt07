package cl.uchile.dcc.citric
package model.controller.states.classes

import model.controller.states.abstractc.AbstractGameState

class InitialState extends AbstractGameState {

  override def doAction(): Unit = {
    controller.promptStart()
    controller.changeState(new ChapterState)
  }

}