package cl.uchile.dcc.citric
package model.controller

import model.controller.states.traits.GameState
import model.controller.states.classes._
import model.controller.observers.traits.Observer
import model.units.traits.UnitTrait

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

class GameController {

  /** ---------------------- Attributes ----------------------- */
  private var _state: GameState = _
  private var observers = ArrayBuffer.empty[Observer]
  private var _selected: Option[UnitTrait] = None

  /** ----------------------- General controller methods ----------------------- */

  /** getter for state
   *
   * @return the current state of the controller
   */
  def state: GameState = _state

  /** setter for state
   *
   * @param st the new state of the controller
   */
  def changeState(st: GameState): Unit = {
    _state = st
    st.controller = this
  }

  def play(): Unit = {
    state.doAction()
  }

  def addObserver(obs: Observer): ArrayBuffer[Observer] = {
    this.observers += obs
  }

  def removeObserver(obs: Observer): ArrayBuffer[Observer] = {
    this.observers -= obs
  }


  def startGame(): Unit = {
    changeState(new InitialState())
  }

  def rollDice(): Unit = {
    changeState(new MovingState())
  }

  def selected(): UnitTrait = {
    if (_selected.isDefined) {
      _selected.get
    } else {
      throw new AssertionError("Player not defined")
    }
  }

  def doAttack(target: UnitTrait): Unit  = {
    val attacker = selected()
    attacker.attackMove(target)
    notifyAttack(attacker, target)
  }

  /** ---------------------- States transition methods ----------------------- */
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

  /** -------------------- Observer pattern methods -------------------- */

  def notifyAttack(from: UnitTrait, to: UnitTrait): Unit = {
    for (o <- observers) {
      o.updateAttack(from, to)
    }
  }

  def promptStart(): Unit = {
    println("Players turn")
  }

}
