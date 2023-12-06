package cl.uchile.dcc.citric
package controller

import controller.states.classes._
import controller.states.traits.GameState
import model.units.classes.PlayerCharacter
import model.units.traits.UnitTrait
import controller.observers.Observer

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class GameController {

  /** ---------------------- Attributes ----------------------- */
  private var _state: GameState = _
  private var observers = ArrayBuffer.empty[Observer]
  var players: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter] // hacerle getter
  private var _selected: Option[UnitTrait] = None
  private var _selectedPlayer: Option[PlayerCharacter] = None
  private var _target: Option[UnitTrait] = None
  //val shiftList : ArrayBuffer[Int] = ArrayBuffer.empty[Int]
  var chapter: Int = 1
  var turn: Int = 0
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
  /*
  def setTurns(value: Int): Unit = {
    val length: Int = players.length
    for(i <- 0 to (length-1)){
      shiftList += (value+i)%length
    }
    selectedPlayer()
  }
  */
  def setTurns(): Unit = {
    players = Random.shuffle(players)
    println(players)
  }

  def unitSelected(): UnitTrait = {
    if (_selected.isDefined) {
      _selected.get
    } else {
      throw new AssertionError("Unit not defined")
    }
  }

  def playerSelected(): PlayerCharacter = {
    if (_selectedPlayer.isDefined) {
      _selectedPlayer.get
    } else {
      throw new AssertionError("Player not defined")
    }
  }

  def target(): UnitTrait = {
    if (_target.isDefined) {
      _target.get
    } else {
      throw new AssertionError("Enemy not defined")
    }
  }

  def selectUnit(u: UnitTrait): Unit = {
    _selected = Some(u)
  }

  def selectPlayer(): Unit = {
    _selectedPlayer = Some(players(turn))
  }

  /*
  def selectAlly(id: Int) = {
    _selected = Some(allies(id))
  }

  def selectAllyTarget(id: Int) = {
    _target = Some(enemies(id))
  }

  def selectEnemy(id: Int) = {
    _selected = Some(enemies(id))
  }

  def selectEnemyTarget(id: Int) = {
    _target = Some(allies(id))
  }
  */

  /** Adds a player to the game, per default there will be 4 players */
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
    val attacker = unitSelected()
    attacker.attackMove(target)
    notifyAttack(attacker, target)
  }

  /** ---------------------- States transition methods ----------------------- */
  def chapterState(): Unit = {

  }

  def playsTurn(): Int = {
    players(turn).increaseStarsByRound(chapter/5 + 1) //increase stars by round, integer division
    players(turn).rollDice() //return roll dice
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
