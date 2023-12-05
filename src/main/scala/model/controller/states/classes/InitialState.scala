package cl.uchile.dcc.citric
package model.controller.states.classes

import model.controller.states.abstractc.AbstractGameState
import model.controller.GameController

import scala.util.Random

class InitialState extends AbstractGameState {

  override def doAction(): Unit = {
    controller.promptStart()
    val randomNumberGenerator: Random = new Random()
    val random_value = randomNumberGenerator.nextInt(4)
    controller.setTurns(random_value)
    controller.changeState(new ChapterState(1))
  }

}