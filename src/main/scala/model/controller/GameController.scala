package cl.uchile.dcc.citric
package model.controller

import model.controller.states.traits.GameState
import model.controller.states.classes._
import model.controller.observers.traits.Observer
import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

class GameController {

  private var state: GameState = _
  private var observers = ArrayBuffer.empty[Observer]

  def play(): Unit = {
    state.doAction()
  }

  def addObserver(obs: Observer): ArrayBuffer[Observer] = {
    this.observers += obs
  }

  def removeObserver(obs: Observer): ArrayBuffer[Observer] = {
    this.observers -= obs
  }

  def changeState(st: GameState): Unit = {
    state = st
    st.controller = this
  }

  def startGame(): Unit = {
    changeState(new InitialState())

  }

  def rollDice(): Unit = {
    changeState(new MovingState())
  }

  def doAction(): Unit = {
    /* ... */
  }

  def chapterState(): Unit = {
    changeState(new ChapterState())
  }

  def playsTurn(): Unit = {
    changeState(new PlayerTurnState())
  }

  def stayAtPanel(): Unit = {
    changeState(new LandingPanelState())
  }

  def outOfMovements(): Unit = {
    changeState(new LandingPanelState())
  }

  def isKO(): Unit = {
    changeState(new RecoveryState())
  }

  def norma6Reached(): Unit = {
    changeState(new GameOverState())
  }

  def enoughRoll(): Unit = {
    changeState(new ChapterState())
  }

  def fightAnotherPlayer(): Unit = {
    changeState(new CombatState())
  }

  def tryEncounterPanel(): Unit = {
    changeState(new CombatState())
  }

  def combatEnded(): Unit = {
    changeState(new LandingPanelState())
  }

}
