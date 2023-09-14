package cl.uchile.dcc.citric
package model.Panels

class NeutralPanelTest extends munit.FunSuite{
  /*
  REMEMBER: It is a good practice to use constants for the values that are used in multiple
  tests, so you can change them in a single place.
  This will make your tests more readable, easier to maintain, and less error-prone.
  */
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
  private var neutralpanel: new NeutralPanel() = _

  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
     player1 = new PlayerCharacter()
     player2 = new PlayerCharacter()
     player3 = new PlayerCharacter()

    //inicializar array buffer de personajes y paneles para poder crear el NeutralPanel
     neutralpanel = new NeutralPanel()
  }
}
