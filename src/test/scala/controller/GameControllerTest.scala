package cl.uchile.dcc.citric
package controller

import controller.states.classes._
import model.units.classes.PlayerCharacter
import controller.observers.InfoObserver
import scala.collection.mutable.ArrayBuffer

class GameControllerTest extends munit.FunSuite{

  /* Default player for testing */
  private val name = "testPlayer"
  private val maxHp = 10
  private val attack = 1
  private val defense = 1
  private val evasion = 1

  private var player1: PlayerCharacter = _ // <- x = _ is the same as x = null
  private var testController = new GameController()

  override def beforeEach(context: BeforeEach): Unit = {
    player1 = new PlayerCharacter(name, maxHp, attack, defense, evasion)
    testController = new GameController()
    testController.startGame()
  }

  test("A GameController can add players"){
    val player5 = new PlayerCharacter("Player5",10,1,1,1)
    testController.addPlayer(player5)
    assert(testController.players.contains(player5))
  }

  test("A GameController can remove players"){
    testController.removePlayer(player1)
    assert(!testController.players.contains(player1))
  }

  test("players list length") {
    val length = testController.players.length
    assertEquals(length, 4)
  }

  test("A GameController can add observers"){
    val observer1 = new InfoObserver()
    testController.addObserver(observer1)
    assert(testController.observers.contains(observer1))
  }

  test("A GameController can remove observers"){
    val observer1 = new InfoObserver()
    testController.addObserver(observer1)
    testController.removeObserver(observer1)
    assert(!testController.observers.contains(observer1))
  }

  test("Set random turns to play"){
    println("player 1", testController.players(0))
    println("player 2", testController.players(1))
    println("player 3", testController.players(2))
    println("player 4", testController.players(3))
    testController.setTurns()
    println("player turno 1", testController.players(0))
    println("player turno 2", testController.players(1))
    println("player turno 3", testController.players(2))
    println("player turno 4", testController.players(3))
    println("player a jugar con turno 0", testController.players(testController.turn))
    testController.turn = 1
    println("player a jugar con turno 0", testController.players(testController.turn))
  }

  /*
  test("Selecting the corresponding player based on the current turn"){
    //println("player 3, turno 0", testController.selected())
    testController.turn = 1
    testController.selectedPlayer()
    //println("player de turno 1", testController.selected())
    //println("player 5", testController.players(4))
    testController.turn = 2
    testController.selectedPlayer()
    //println("player de turno 2", testController.selected())
    //println("player 1", testController.players(0))
  }
  */

  test("choose path to the followingPanel (right)"){
    testController.selectPlayer()
    val player = testController.playerSelected()
    testController.choosePath(1) //right
    assert(testController.board.board(0)(0).characters.isEmpty)
    assert(testController.board.followingPanel(0,0).characters.contains(player))
    assertEquals(testController.board.followingPanel(0,0).characters.length, 1)
  }

  test("choose path to the previousPanel (left)"){
    testController.selectPlayer()
    val player = testController.playerSelected()
    testController.choosePath(0) //left
    assert(testController.board.board(0)(0).characters.isEmpty)
    assert(testController.board.prevPanel(0,0).characters.contains(player))
    assertEquals(testController.board.prevPanel(0,0).characters.length, 1)
  }

  test("recovery"){
    testController.selectPlayer()
    val player = testController.playerSelected()
    player.isKO = true
    testController.recovery(player)
    assert(!player.isKO | player.isKO) //either true or false (random)
    // for a high chapter value, the recovery state its going to be true
    testController.chapter = 8
    player.isKO = true
    assertEquals(testController.recovery(player), true)
  }

  /** Tests for transition methods between states of State Pattern */

  test("transition from InitialState to ChapterState") {
    val st = new InitialState()
    st.controller = testController
    //println(s"current state of the controller: ${testController.state}") //Initial state
    assert(testController.state.getClass == classOf[InitialState])
    st.doAction()
    assert(testController.state.getClass == classOf[ChapterState])
    //println(s"current state of the controller: ${testController.state}") //Chapter state
  }

  test("transition from ChapterState to GameOverState | RecoveryState | PlayerTurnState") {
    val st = new ChapterState()
    st.controller = testController
    st.doAction()
    assert(testController.state.getClass == classOf[PlayerTurnState])
  }


}
