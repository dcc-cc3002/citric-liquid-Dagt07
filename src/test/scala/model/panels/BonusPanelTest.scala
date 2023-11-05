package cl.uchile.dcc.citric
package model.panels

import cl.uchile.dcc.citric.model.panels.traits.Panel
import cl.uchile.dcc.citric.model.panels.classes.{BonusPanel, DropPanel}
import cl.uchile.dcc.citric.model.units.classes.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

class BonusPanelTest extends munit.FunSuite{

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

  //initialize array buffer of characters and panels to create the bonusPanel

  private var bonusPanel: BonusPanel = _

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

    //Init the panel
    bonusPanel = new BonusPanel(characters, nextPanels) {
    }
  }

  // 1. Test invariant properties
  test("A BonusPanel should have correctly set their attributes") {
    assertEquals(bonusPanel.characters, ArrayBuffer[PlayerCharacter](player1, player2, player3))
    assertEquals(bonusPanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3))
  }

  // 2. Test methods
  test("A BonusPanel should add a character to the list of characters currently on this panel") {
    bonusPanel.addCharacter(player1)
    assertEquals(bonusPanel.characters, ArrayBuffer[PlayerCharacter](player1, player2, player3, player1))
  }

  test("A BonusPanel should remove a character to the list of characters currently on this panel") {
    assertEquals(bonusPanel.characters, ArrayBuffer[PlayerCharacter](player1, player2, player3))
    bonusPanel.removeCharacter(player1)
    assertEquals(bonusPanel.characters, ArrayBuffer[PlayerCharacter](player2, player3))
  }

  test("A BonusPanel should add a panel to the list of NextPanels if necessary to build/modify the board") {
    assertEquals(bonusPanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3))
    val panel4: Panel = new BonusPanel(ArrayBuffer[PlayerCharacter](player1, player2), ArrayBuffer[Panel](panel1, panel2))
    bonusPanel.addPanel(panel4)
    assertNotEquals(bonusPanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3))
    assertEquals(bonusPanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3, panel4))
  }

  test("A BonusPanel should remove a panel from the list of NextPanels if necessary to build/modify the board") {
    assertEquals(bonusPanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3))
    //remove a panel that already exist in the list of nextPanels of the panel itself
    bonusPanel.removePanel(panel3)
    assertEquals(bonusPanel.nextPanels, ArrayBuffer[Panel](panel1, panel2))
    assertNotEquals(bonusPanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3))
    //remove a panel that doesn't exist in the list of nextPanels of the panel itself
    val panel4: Panel = new BonusPanel(ArrayBuffer[PlayerCharacter](player1, player2), ArrayBuffer[Panel](panel1, panel2))
    bonusPanel.removePanel(panel4)
    assertEquals(bonusPanel.nextPanels, ArrayBuffer[Panel](panel1, panel2))
    assertNotEquals(bonusPanel.nextPanels, ArrayBuffer[Panel](panel1))
  }

  test("Not more than one same type panel removed") {
    val panel5: Panel = new BonusPanel(ArrayBuffer[PlayerCharacter](player1, player2), ArrayBuffer[Panel]())
    val panel6: Panel = new DropPanel(ArrayBuffer[PlayerCharacter](player1, player2), ArrayBuffer[Panel]())
    val panel7: Panel = new DropPanel(ArrayBuffer[PlayerCharacter](player1, player2), ArrayBuffer[Panel]())
    val bonusPanel2: BonusPanel = new BonusPanel(ArrayBuffer[PlayerCharacter](player1, player2, player3), ArrayBuffer[Panel](panel5, panel6, panel7))
    assertEquals(bonusPanel2.nextPanels, ArrayBuffer[Panel](panel5, panel6, panel7))
    bonusPanel2.removePanel(panel7)
    assertEquals(bonusPanel2.nextPanels, ArrayBuffer[Panel](panel5, panel6))
  }

  test("Cant remove a panel from a isolated Panel(empty nextPanels list)") {
    //This case its included in the test above, when trying to remove a panel that doesn't exist in the list of nextPanels
    //but for a more sensitive test, its being tested
    val bonusPanel2: BonusPanel = new BonusPanel(ArrayBuffer[PlayerCharacter](player1), ArrayBuffer[Panel]())
    val otherPanel: BonusPanel = new BonusPanel(ArrayBuffer[PlayerCharacter](player1), ArrayBuffer[Panel](panel1, panel2))
    bonusPanel2.removePanel(otherPanel)
    assertEquals(bonusPanel2.nextPanels, ArrayBuffer[Panel]())
  }

  test("A character should be able to increase their stars by landing in a BonusPanel") {
    val other = new PlayerCharacter(name, maxHp, attack, defense, evasion,
      wins, defaultNorm, currentNorm, normObjective)
    assertEquals(player1.stars, other.stars)
    bonusPanel.apply(player1)
    assert(player1.stars > other.stars)
  }

}
