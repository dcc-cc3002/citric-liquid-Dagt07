package cl.uchile.dcc.citric
package model.norm

import model.norm.classes.NormaClass
import model.panels.traits.Panel
import model.units.classes.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class normaTest extends munit.FunSuite{

  /* Values and variables for the test*/

  /* Default player for testing */
  private val name = "testPlayer"
  private val maxHp = 10
  private val attack = 1
  private val defense = 1
  private val evasion = 1
  //private var randomNumberGenerator: Random = _
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

  //initialize array buffer of characters and panels to create the NeutralPanel

  private var normaClass: NormaClass = _

  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
    //randomNumberGenerator = new Random(11)
    player1 = new PlayerCharacter(name, maxHp, attack, defense, evasion,
      wins, defaultNorm, currentNorm, normObjective)
  }

  test("NormClass has correctly set their valid norms"){
    normaClass = new NormaClass(defaultNorm, currentNorm, normObjective)
    assertEquals(normaClass.norms, Map(
      2 -> (10, 1),
      3 -> (30, 3),
      4 -> (70, 6),
      5 -> (120, 10),
      6 -> (200, 14))
    )
  }

  test("getRequirements with each valid norm level"){
    normaClass = new NormaClass(defaultNorm, currentNorm, normObjective)
    assertEquals(normaClass.getRequirements(2), Some((10, 1)))
    assertEquals(normaClass.getRequirements(3), Some((30, 3)))
    assertEquals(normaClass.getRequirements(4), Some((70, 6)))
    assertEquals(normaClass.getRequirements(5), Some((120, 10)))
    assertEquals(normaClass.getRequirements(6), Some((200, 14)))
    assertEquals(normaClass.getRequirements(7), None)
  }
}
