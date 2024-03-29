package cl.uchile.dcc.citric
package controller

import controller.states.classes._
import controller.states.traits.GameState
import model.units.classes.PlayerCharacter
import model.units.traits.UnitTrait
import controller.observers.Observer
import model.panels.Board
import model.panels.traits.Panel

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class GameController {

  /** ---------------------- Attributes ----------------------- */
  private var _state: GameState = _
  var observers: ArrayBuffer[Observer] = ArrayBuffer.empty[Observer]
  var players: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter] // hacerle getter
  private var _selected: Option[UnitTrait] = None
  private var _selectedPlayer: Option[PlayerCharacter] = None
  private var _target: Option[UnitTrait] = None
  var chapter: Int = 1
  var turn: Int = 0
  var board: Board = _
  var roll: Int = _

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
    this.setTurns()
    board = new Board(players, ArrayBuffer.empty[Panel])
    changeState(new InitialState())
  }

  /** Uses any UnitTrait rollDice method and returns it */
  def rollDice(u:UnitTrait): Int = {
    selectUnit(u)
    unitSelected().rollDice()
  }

  /** Set the random turns for the available players
   * doing a shuffle of the list of players
   * */
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


  def selectAllyTarget(u: UnitTrait): Unit = {
    _target = Some(u)
  }

  def selectEnemy(u: UnitTrait): Unit = {
    _selected = Some(u)
  }

  def selectEnemyTarget(u: UnitTrait): Unit = {
    _target = Some(u)
  }


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

  def getPanel: Panel = board.board(0)(0) //for quick testing

  /** ---------------------- States transition methods ----------------------- */
  def chapterState(): Unit = {

  }

  def playsTurn(): Int = {
    players(turn).increaseStarsByRound(chapter/5 + 1) //increase stars by round, integer division
    players(turn).rollDice() //return roll dice
  }

  def choosePath(dir: Int): Unit = {
    //panel 0,0 for quick testing
    selectPlayer()
    if (dir == 0) { //left or previousPanel
      board.prevPanel(0,0).characters += playerSelected() //add player to previous panel
      board.board(0)(0).characters -= playerSelected() //remove player from current panel
    } else { //right or followingPanel
      board.followingPanel(0,0).characters += playerSelected() //add player to following panel
      board.board(0)(0).characters -= playerSelected() //remove player from current panel
    }
  }

  /*
  def stayAtPanel(): Unit = {

  }

  def outOfMovements(): Unit = {

  }


  def norma6Reached(): Unit = {

  }
  */


  def recovery(player: PlayerCharacter): Boolean = {
    val luck = player.rollDice()
    var necessaryToRecover = 6 - chapter
    if (necessaryToRecover <= 0) necessaryToRecover = 0
    if (luck >= necessaryToRecover){
      return true //Enough luck to recover
    }
    false //not Enough luck to recover
  }


  def tryEncounterPanel(): Unit = {

  }

  def combatEnded(): Unit = {

  }

  def panelEffect(): Unit = {
    getPanel.apply()
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
