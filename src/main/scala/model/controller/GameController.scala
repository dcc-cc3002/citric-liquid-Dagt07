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
  val players: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter]
  private var _selected: Option[UnitTrait] = None
  val shiftList : ArrayBuffer[Int] = ArrayBuffer.empty[Int]
  var chapter: Int = 1
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

  }

  /** Set the random turns for the available players
   * starting from the player with the given value
   * */
  def setTurns(value: Int): Unit = {
    val length: Int = players.length
    for(i <- 0 to (length-1)){
      shiftList += (value+i)%length
    }
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
    val attacker = selected() //revisar si esto tiene sentido ya que en combatState se hace un selected pa quien defiende
    attacker.attackMove(target)
    notifyAttack(attacker, target)
  }

  /** ---------------------- States transition methods ----------------------- */
  def chapterState(): Unit = {

  }

  def playsTurn(): Int = {
    this.players(shiftList(i)).increaseStarsByRound(chapter/5 + 1) //increase stars by round, integer division
    this.players(shiftList(i)).rollDice() //return roll dice
  }

  def stayAtPanel(): Unit = {

  }

  def outOfMovements(): Unit = {

  }


  def norma6Reached(): Unit = {

  }

  def recovery(player: PlayerCharacter): Boolean = {
    val luck = player.rollDice()
    var necessaryToRecover = 6 - chapter
    if (necessaryToRecover <= 0) necessaryToRecover = 0
    if (luck >= necessaryToRecover){
      return true //Enough luck to recover
    }
    false //not Enough luck to recover
  }

  def fightAnotherPlayer(): Unit = {

  }

  def tryEncounterPanel(): Unit = {

  }

  def combatEnded(): Unit = {

  }

  def panelEffect(): Unit = {

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
