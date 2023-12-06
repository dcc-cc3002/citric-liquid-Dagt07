package cl.uchile.dcc.citric
package controller.states.classes

import controller.states.abstractc.AbstractGameState
import controller.GameController

import scala.util.Random

class InitialState extends AbstractGameState {

  override def doAction(): Unit = {
    controller.promptStart()
    controller.changeState(new ChapterState())
  }

}