package cl.uchile.dcc.citric
package model.panels

import model.units.classes.PlayerCharacter
import model.panels.classes.{DropPanel, HomePanel}
import model.panels.traits.Panel

import scala.collection.mutable.ArrayBuffer

class HomePanelTest extends munit.FunSuite{

  /* Values and variables for the test*/

  /* Default player for testing */
  private val name = "testPlayer"
  private val maxHp = 10
  private val attack = 1
  private val defense = 1
  private val evasion = 1
  /* Add any other constants you need here... */
  private var stars = 0
  private var wins = 0
  private var currentHP = maxHp
  private val defaultNorm = 1
  private var currentNorm = 1
  private var normObjective = "stars"
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

  //initialize array buffer of characters and panels to create the HomePanel

  private var homePanel: HomePanel = _

  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
    player1 = new PlayerCharacter(name, maxHp, attack, defense, evasion,
                                  wins, defaultNorm, currentNorm, normObjective)
    player2 = new PlayerCharacter(name, maxHp, attack, defense, evasion,
                                  wins, defaultNorm, currentNorm, normObjective)
    player3 = new PlayerCharacter(name, maxHp, attack, defense, evasion,
                                  wins, defaultNorm, currentNorm, normObjective)

    val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter](player1, player2, player3)
    val nextPanels: ArrayBuffer[Panel] = ArrayBuffer[Panel](panel1, panel2, panel3)

    homePanel = new HomePanel(characters, nextPanels, owner= player1){
    }
  }

  test("A HomePanel should have correctly set their attributes") {
    assertEquals(homePanel.characters, ArrayBuffer[PlayerCharacter](player1, player2, player3))
    assertEquals(homePanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3))
    assertEquals(homePanel.owner, player1)
  }

  test("A HomePanel should add a panel to the list of NextPanels if necessary to build/modify the board") {
    assertEquals(homePanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3))
    val panel4: Panel = new HomePanel(ArrayBuffer[PlayerCharacter](player1, player2), ArrayBuffer[Panel](panel1, panel2), owner=player2)
    homePanel.addPanel(panel4)
    assertNotEquals(homePanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3))
    assertEquals(homePanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3, panel4))
  }

  test("A HomePanel should remove a panel from the list of NextPanels if necessary to build/modify the board") {
    assertEquals(homePanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3))
    //remove a panel that already exist in the list of nextPanels of the panel itself
    homePanel.removePanel(panel3)
    assertEquals(homePanel.nextPanels, ArrayBuffer[Panel](panel1, panel2))
    assertNotEquals(homePanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3))
    //remove a panel that doesn't exist in the list of nextPanels of the panel itself
    val panel4: Panel = new HomePanel(ArrayBuffer[PlayerCharacter](player1, player2), ArrayBuffer[Panel](panel1, panel2), owner = player2)
    homePanel.removePanel(panel4)
    assertEquals(homePanel.nextPanels, ArrayBuffer[Panel](panel1, panel2))
    assertNotEquals(homePanel.nextPanels, ArrayBuffer[Panel](panel1))
  }

  test("Not more than one same type panel removed") {
    val panel5: Panel = new HomePanel(ArrayBuffer[PlayerCharacter](player1, player2), ArrayBuffer[Panel](), owner = player2)
    val panel6: Panel = new DropPanel(ArrayBuffer[PlayerCharacter](player1, player2), ArrayBuffer[Panel]())
    val panel7: Panel = new DropPanel(ArrayBuffer[PlayerCharacter](player1, player2), ArrayBuffer[Panel]())
    val homePanel2: HomePanel = new HomePanel(ArrayBuffer[PlayerCharacter](player1, player2, player3), ArrayBuffer[Panel](panel5, panel6, panel7), owner = player3)
    assertEquals(homePanel2.nextPanels, ArrayBuffer[Panel](panel5, panel6, panel7))
    homePanel2.removePanel(panel7)
    assertEquals(homePanel2.nextPanels, ArrayBuffer[Panel](panel5, panel6))
  }

  test("Cant remove a panel from a isolated Panel(empty nextPanels list)") {
    //This case its included in the test above, when trying to remove a panel that doesn't exist in the list of nextPanels
    //but for a more sensitive test, its being tested
    val homePanel2: HomePanel = new HomePanel(ArrayBuffer[PlayerCharacter](player1), ArrayBuffer[Panel](), owner = player3)
    val otherPanel: HomePanel = new HomePanel(ArrayBuffer[PlayerCharacter](player1), ArrayBuffer[Panel](panel1, panel2), owner = player1)
    homePanel2.removePanel(otherPanel)
    assertEquals(homePanel2.nextPanels, ArrayBuffer[Panel]())
  }

  test("A HomePanel can trigger the player to regenerate HP") {
    var healthPoints = player1.currentHP
    homePanel.regenerateHP(player1)
    assertEquals(player1.currentHP, healthPoints + 10)
  }
}
