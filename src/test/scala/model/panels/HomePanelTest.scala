package cl.uchile.dcc.citric
package model.panels

import model.units.classes.PlayerCharacter
import model.panels.classes.HomePanel
import model.panels.traits.Panel

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class HomePanelTest extends munit.FunSuite{

  /* Values and variables for the test*/

  /* Default player for testing */
  private val name = "testPlayer"
  private val maxHp = 10
  private val attack = 1
  private val defense = 1
  private val evasion = 1
  private val randomNumberGenerator = new Random(11)
  /* Add any other constants you need here... */
  private val stars = 0
  private val wins = 0
  private val currentHP = maxHp
  private val defaultNorm = 1
  private val currentNorm = 1
  private val normObjective = "stars"
  /*
  This is the object under test.
  We initialize it in the beforeEach method so we can reuse it in all the tests.
  This is a good practice because it will reset the object before each test, so you don't have
  to worry about the state of the object between tests.
  */
  private var player1: PlayerCharacter = _ // <- x = _ is the same as x = null
  private var player2: PlayerCharacter = _
  private var player3: PlayerCharacter = _

  private var panel1: Panel = _
  private var panel2: Panel = _
  private var panel3: Panel = _

  //initialize array buffer of characters and panels to create the NeutralPanel

  private var homepanel: HomePanel = _

  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
    player1 = new PlayerCharacter(name, maxHp, attack, defense, evasion, randomNumberGenerator, stars,
      wins, currentHP, defaultNorm, currentNorm, normObjective)
    player2 = new PlayerCharacter(name, maxHp, attack, defense, evasion, randomNumberGenerator, stars,
      wins, currentHP, defaultNorm, currentNorm, normObjective)
    player3 = new PlayerCharacter(name, maxHp, attack, defense, evasion, randomNumberGenerator, stars,
      wins, currentHP, defaultNorm, currentNorm, normObjective)

    val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter](player1, player2, player3)
    val nextPanels: ArrayBuffer[Panel] = ArrayBuffer[Panel](panel1, panel2, panel3)

    homepanel = new HomePanel(characters, nextPanels, owner= player1){
    }
  }

  test("A HomePanel should have correctly set their attributes") {
    assertEquals(homepanel.characters, ArrayBuffer[PlayerCharacter](player1, player2, player3))
    assertEquals(homepanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3))
    assertEquals(homepanel.owner, player1)
  }

  test("A HomePanel can trigger the player to regenerate HP") {
    var healthPoints = player1.currentHP
    homepanel.regenerateHP(player1)
    assertEquals(player1.currentHP, healthPoints + 10)
  }
}