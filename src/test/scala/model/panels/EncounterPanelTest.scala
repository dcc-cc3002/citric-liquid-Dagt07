package cl.uchile.dcc.citric
package model.panels

import model.panels.classes.{DropPanel, EncounterPanel}
import model.panels.traits.Panel
import model.units.classes.PlayerCharacter
import model.units.traits.UnitTrait
import model.units.classes.wilds.Chicken

import scala.collection.mutable.ArrayBuffer

class EncounterPanelTest extends munit.FunSuite{

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

  /*
  This is the object under test.
  We initialize it in the beforeEach method so we can reuse it in all the tests.
  This is a good practice because it will reset the object before each test, so you don't have
  to worry about the state of the object between tests.
  */
  private var player1: PlayerCharacter = _ // <- x = _ is the same as x = null
  private var player2: PlayerCharacter = _
  private var player3: PlayerCharacter = _

  private var wildUnit: UnitTrait = _

  private var panel1: Panel = _
  private var panel2: Panel = _
  private var panel3: Panel = _

  //initialize array buffer of characters and panels to create the EncounterPanel

  private var encounterPanel: EncounterPanel = _

  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
    player1 = new PlayerCharacter(name, maxHp, attack, defense, evasion)
    player2 = new PlayerCharacter(name, maxHp, attack, defense, evasion)
    player3 = new PlayerCharacter(name, maxHp, attack, defense, evasion)

    var wildUnit: UnitTrait = new Chicken (maxHp, attack, defense, evasion)

    val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter](player1, player2, player3)
    val nextPanels: ArrayBuffer[Panel] = ArrayBuffer[Panel](panel1, panel2, panel3)

    //Init the panel
    encounterPanel = new EncounterPanel(characters, nextPanels, wildUnit) {
    }
  }

  // 1. Test invariant properties
  test("A EncounterPanel should have correctly set their attributes") {
    //val other_chicken = new Chicken(maxHp, attack, defense, evasion)
    assertEquals(encounterPanel.characters, ArrayBuffer[PlayerCharacter](player1, player2, player3))
    assertEquals(encounterPanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3))
  }

  // 2. Test methods
  test("A EncounterPanel should add a character to the list of characters currently on this panel") {
    encounterPanel.addCharacter(player1)
    assertEquals(encounterPanel.characters, ArrayBuffer[PlayerCharacter](player1, player2, player3, player1))
  }

  test("A EncounterPanel should remove a character to the list of characters currently on this panel") {
    assertEquals(encounterPanel.characters, ArrayBuffer[PlayerCharacter](player1, player2, player3))
    encounterPanel.removeCharacter(player1)
    assertEquals(encounterPanel.characters, ArrayBuffer[PlayerCharacter](player2, player3))
  }

  test("A EncounterPanel should add a panel to the list of NextPanels if necessary to build/modify the board") {
    assertEquals(encounterPanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3))
    val panel4: Panel = new EncounterPanel(ArrayBuffer[PlayerCharacter](player1, player2), ArrayBuffer[Panel](panel1, panel2), wildUnit)
    encounterPanel.addPanel(panel4)
    assertNotEquals(encounterPanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3))
    assertEquals(encounterPanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3, panel4))
  }

  test("A EncounterPanel should remove a panel from the list of NextPanels if necessary to build/modify the board") {
    assertEquals(encounterPanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3))
    //remove a panel that already exist in the list of nextPanels of the panel itself
    encounterPanel.removePanel(panel3)
    assertEquals(encounterPanel.nextPanels, ArrayBuffer[Panel](panel1, panel2))
    assertNotEquals(encounterPanel.nextPanels, ArrayBuffer[Panel](panel1, panel2, panel3))
    //remove a panel that doesn't exist in the list of nextPanels of the panel itself
    val panel4: Panel = new EncounterPanel(ArrayBuffer[PlayerCharacter](player1, player2), ArrayBuffer[Panel](panel1, panel2), wildUnit)
    encounterPanel.removePanel(panel4)
    assertEquals(encounterPanel.nextPanels, ArrayBuffer[Panel](panel1, panel2))
    assertNotEquals(encounterPanel.nextPanels, ArrayBuffer[Panel](panel1))
  }

  test("Not more than one same type panel removed") {
    val panel5: Panel = new EncounterPanel(ArrayBuffer[PlayerCharacter](player1, player2), ArrayBuffer[Panel](), wildUnit)
    val panel6: Panel = new DropPanel(ArrayBuffer[PlayerCharacter](player1, player2), ArrayBuffer[Panel]())
    val panel7: Panel = new DropPanel(ArrayBuffer[PlayerCharacter](player1, player2), ArrayBuffer[Panel]())
    val encounterPanel2: EncounterPanel = new EncounterPanel(ArrayBuffer[PlayerCharacter](player1, player2, player3), ArrayBuffer[Panel](panel5, panel6, panel7), wildUnit)
    assertEquals(encounterPanel2.nextPanels, ArrayBuffer[Panel](panel5, panel6, panel7))
    encounterPanel2.removePanel(panel7)
    assertEquals(encounterPanel2.nextPanels, ArrayBuffer[Panel](panel5, panel6))
  }

  test("Cant remove a panel from a isolated Panel(empty nextPanels list)") {
    //This case its included in the test above, when trying to remove a panel that doesn't exist in the list of nextPanels
    //but for a more sensitive test, its being tested
    val encounterPanel2: EncounterPanel = new EncounterPanel(ArrayBuffer[PlayerCharacter](player1), ArrayBuffer[Panel](), wildUnit)
    val otherPanel: EncounterPanel = new EncounterPanel(ArrayBuffer[PlayerCharacter](player1), ArrayBuffer[Panel](panel1, panel2), wildUnit)
    encounterPanel2.removePanel(otherPanel)
    assertEquals(encounterPanel2.nextPanels, ArrayBuffer[Panel]())
  }

  test("apply method doesnt do anything") {
    val healthPoints = player1.currentHP
    encounterPanel.apply()
    assertEquals(player1.currentHP, healthPoints)
  }

}
