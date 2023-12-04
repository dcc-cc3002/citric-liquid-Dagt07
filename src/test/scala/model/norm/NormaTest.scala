package cl.uchile.dcc.citric
package model.norm

import model.panels.traits.Panel
import model.units.classes.PlayerCharacter
import model.norm.classes.{Norma1, Norma2, Norma3, Norma4, Norma5, Norma6}

class NormaTest extends munit.FunSuite{

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

  override def beforeEach(context: BeforeEach): Unit = {
    player1 = new PlayerCharacter(name, maxHp, attack, defense, evasion)
  }

  /* Tests for Norma1 */
  test("Norma1 should be created correctly"){
    val norma1 = new Norma1(player1)
    assertEquals(norma1.stars, 10)
    assertEquals(norma1.wins, 1)
    assertEquals(norma1.owner, player1)
  }

  test("Norma1 should upgrade correctly"){
    val norma1 = new Norma1(player1)
    player1.stars = 10
    player1.wins = 1
    norma1.upgrade()
    assertEquals(player1.intNorm, 2)
    assertEquals(player1.norma.owner, player1)
  }

  /* Tests for Norma2 */
  test("Norma2 should be created correctly"){
    val norma2 = new Norma2(player1)
    assertEquals(norma2.stars, 30)
    assertEquals(norma2.wins, 3)
    assertEquals(norma2.owner, player1)
  }

  test("Norma2 should upgrade correctly"){
    val norma2 = new Norma2(player1)
    player1.stars = 30
    player1.wins = 3
    norma2.upgrade()
    assertEquals(player1.intNorm, 3)
    assertEquals(player1.norma.owner, player1)
  }

  /* Tests for Norma3 */
  test("Norma3 should be created correctly"){
    val norma3 = new Norma3(player1)
    assertEquals(norma3.stars, 70)
    assertEquals(norma3.wins, 6)
    assertEquals(norma3.owner, player1)
  }

  test("Norma3 should upgrade correctly"){
    val norma3 = new Norma3(player1)
    player1.stars = 70
    player1.wins = 6
    norma3.upgrade()
    assertEquals(player1.intNorm, 4)
    assertEquals(player1.norma.owner, player1)
  }

  /* Tests for Norma4 */
  test("Norma4 should be created correctly"){
    val norma4 = new Norma4(player1)
    assertEquals(norma4.stars, 120)
    assertEquals(norma4.wins, 10)
    assertEquals(norma4.owner, player1)
  }

  test("Norma4 should upgrade correctly"){
    val norma4 = new Norma4(player1)
    player1.stars = 120
    player1.wins = 10
    norma4.upgrade()
    assertEquals(player1.intNorm, 5)
    assertEquals(player1.norma.owner, player1)
  }

  /* Tests for Norma5 */
  test("Norma5 should be created correctly"){
    val norma5 = new Norma5(player1)
    assertEquals(norma5.stars, 200)
    assertEquals(norma5.wins, 14)
    assertEquals(norma5.owner, player1)
  }

  test("Norma5 should upgrade correctly"){
    val norma5 = new Norma5(player1)
    player1.stars = 200
    player1.wins = 14
    norma5.upgrade()
    assertEquals(player1.intNorm, 6)
    assertEquals(player1.norma.owner, player1)
  }

  /* Tests for Norma6 */
  test("Norma6 should be created correctly"){
    val norma6 = new Norma6(player1)
    assertEquals(norma6.stars, 10000)
    assertEquals(norma6.wins, 10000)
    assertEquals(norma6.owner, player1)
  }

  test("Norma6 shouldn't upgrade"){
    val norma6 = new Norma6(player1)
    player1.stars = 10000
    player1.wins = 10000
    norma6.upgrade()
    assertEquals(player1.intNorm, 6)
    assertEquals(player1.norma.owner, player1)
  }

}


