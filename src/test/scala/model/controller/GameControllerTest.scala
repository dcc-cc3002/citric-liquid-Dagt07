package cl.uchile.dcc.citric
package model.controller

import model.controller.GameController
import model.controller.states.traits.GameState
import model.controller.states.classes._
import model.controller.observers.traits.Observer
import model.controller.observers.classes._
import model.units.classes.PlayerCharacter

import com.sun.tools.javac.util.Assert
import org.junit.Assert.assertThrows

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

  /*
  test("A GameController can add observers"){
    val observer1 = new TurnObserver()
    testController.addObserver(observer1)
    assert(testController.observers.contains(observer1))
  }*/

  test("set random turns to play"){
    testController.setTurns(2) //player 2 got the first turn, the rest will follow in order
    assert(testController.shiftList.length == 4)
    assert(testController.shiftList.containsSlice(ArrayBuffer(2,3,0,1)))
    testController.shiftList.clear()
    testController.addPlayer(new PlayerCharacter("Player5",10,1,1,1))
    testController.setTurns(3) //player 3 got the first turn, the rest will follow in order
    assert(testController.shiftList.length == 5)
    assert(testController.shiftList.containsSlice(ArrayBuffer(3,4,0,1,2)))
  }

  /** Tests for the transition methods of the State Pattern */
  test("A InitialState only can change to ChapterState") {
    //assertEquals(classOf[InitialState], classOf[testController.state])
    //assert(classOf[InitialState], () => testController.chapterState())
    //Assert.assertThrows(classOf[InvalidActionException], () => testController.transition())
    println(s"current state of the controller: ${testController.state}") //Initial state
    testController.chapterState()
    println(s"current state of the controller: ${testController.state}") //Chapter state
  }

  test("a") {
    val st = new InitialState()
    st.controller = testController
    println(s"current state of the controller: ${testController.state}") //Initial state
    st.doAction()
    println(s"current state of the controller: ${testController.state}") //Chapter state
  }
}
