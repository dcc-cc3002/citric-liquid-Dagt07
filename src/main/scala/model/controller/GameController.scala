package cl.uchile.dcc.citric
package model.controller

import model.controller.states.traits.GameState
import model.controller.states.classes._
import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

class GameController {

  private var state: GameState = _
  private var observers = ArrayBuffer.empty[Observer]



  def changeState(st: GameState) = {
    state = st
    st.controller = this
  }

  def startGame() = {
    changeState(new InitialState())

  }

  def addObserver(obs: Observer) = {
    this.observers += obs
  }

  def play() = {
    state.doAction()
  }

  def rollDice(): Unit = {
    /* ... */
  }

  def doAction(): Unit = {
    /* ... */
  }


}
