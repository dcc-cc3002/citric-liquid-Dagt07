package cl.uchile.dcc.citric
package model.controller

import model.controller.states.traits.GameState
import model.controller.states.classes._
import model.controller.observers.traits.Observer
import model.units.traits.UnitTrait
import model.units.classes.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

class GameController {

  /** ---------------------- Attributes ----------------------- */
  private var _state: GameState = _
  private var observers = ArrayBuffer.empty[Observer]
  private val players = ArrayBuffer.empty[PlayerCharacter]
  private var _selected: Option[UnitTrait] = None

  /** ----------------------- General controller methods ----------------------- */

  /** getter for state
   * @return the current state of the controller
   */
  def state: GameState = _state

  /** setter for state
   * @param st the new state of the controller
   */
  def changeState(st: GameState): Unit = {
    _state = st
    st.controller = this
  }

  def play(): Unit = {
    state.doAction()
  }

  def finish(): Boolean = {
    for(player <- players){
      if(player.intNorm == 6) return true
    }
    false
  }

  def startGame(): Unit = {
    println("Welcome to 99.7% Orange Juice")
    addPlayer(new PlayerCharacter("Player1",10,1,1,1))
    addPlayer(new PlayerCharacter("Player2",10,1,1,1))
    addPlayer(new PlayerCharacter("Player3",10,1,1,1))
    addPlayer(new PlayerCharacter("Player4",10,1,1,1))
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

  /** Adds a player to the game, per default there will be 4 players*/
  def addPlayer(player: PlayerCharacter): Unit = {
    players += player
  }

  /** Removes a player from the game */
  def removePlayer(player: PlayerCharacter): Unit = {
    players -= player
  }

  /** Returns the number of players in the game */
  def playersLength(): Int = {
    players.length
  }

  def doAttack(target: UnitTrait): Unit  = {
    val attacker = selected()
    attacker.attackMove(target)
    notifyAttack(attacker, target)
  }

  /** ---------------------- States transition methods ----------------------- */
  def chapterState(): Unit = {

  }

  def playsTurn(): Unit = {

  }

  def stayAtPanel(): Unit = {

  }

  def outOfMovements(): Unit = {

  }

  def isKO(): Unit = {

  }

  def norma6Reached(): Unit = {

  }

  def enoughRoll(): Unit = {

  }

  def fightAnotherPlayer(): Unit = {

  }

  def tryEncounterPanel(): Unit = {

  }

  def combatEnded(): Unit = {

  }

  /** -------------------- Observer pattern methods -------------------- */
  def addObserver(obs: Observer): ArrayBuffer[Observer] = {
    this.observers += obs
  }

  def removeObserver(obs: Observer): ArrayBuffer[Observer] = {
    this.observers -= obs
  }

  def notifyAttack(from: UnitTrait, to: UnitTrait): Unit = {
    for (o <- observers) {
      o.updateAttack(from, to)
    }
  }

  def promptStart(): Unit = {
    println("Player turn")
  }

}
