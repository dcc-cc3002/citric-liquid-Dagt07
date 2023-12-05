package cl.uchile.dcc.citric
package model.controller.states.classes

import model.controller.states.abstractc.AbstractGameState
import model.controller.GameController

import scala.util.Random

class InitialState extends AbstractGameState {

  override def doAction(): Unit = {
    controller.promptStart()
    controller.setTurns()
    controller.changeState(new ChapterState())
  }

}