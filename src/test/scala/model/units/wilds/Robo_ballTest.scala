package cl.uchile.dcc.citric
package model.units.wilds

import model.units.classes.wilds.Robo_ball
class Robo_ballTest extends munit.FunSuite {
  /*
  REMEMBER: It is a good practice to use constants for the values that are used in multiple
  tests, so you can change them in a single place.
  This will make your tests more readable, easier to maintain, and less error-prone.
  */
  private val maxHp = 3
  private val attack = -1
  private val defense = -1
  private val evasion = +1
  /* Add any other constants you need here... */
  private val stars = 0
  private val currentHP = maxHp
  /*
  This is the object under test.
  We initialize it in the beforeEach method so we can reuse it in all the tests.
  This is a good practice because it will reset the object before each test, so you don't have
  to worry about the state of the object between tests.
  */
  private var robo_ball: Robo_ball = _  // <- x = _ is the same as x = null
  /* Add any other variables you need here... */

  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
    robo_ball = new Robo_ball(maxHp, attack, defense, evasion)
  }

  test("A Robo_ball should have correctly set their attributes") {
    assertEquals(robo_ball.maxHp, maxHp)
    assertEquals(robo_ball.attack, attack)
    assertEquals(robo_ball.defense, defense)
    assertEquals(robo_ball.evasion, evasion)
    assertEquals(robo_ball.currentHP, currentHP)
    assertEquals(robo_ball.stars, stars)
  }

  // Two ways to test randomness (you can use any of them):

  // 1. Test invariant properties, e.g. the result is always between 1 and 6.
  test("A Robo_Ball should be able to roll a dice without a default/fixed seed") {
    for (_ <- 1 to 10) {
      assert(robo_ball.rollDice() >= 1 && robo_ball.rollDice() <= 6)
    }
  }

  // 2. Set a seed and test the result is always the same.
  // A seed sets a fixed succession of random numbers, so you can know that the next numbers
  // are always the same for the same seed.
  test("A Robo_ball should be able to roll a dice with a fixed seed") {
    val other =
      new Robo_ball(maxHp, attack, defense, evasion)
    for (_ <- 1 to 10) {
      assertEquals(robo_ball.rollDice(11), other.rollDice(11))
    }
  }

  test("Attack method") {
    assertNotEquals(robo_ball.attackMove(robo_ball), robo_ball.attack)
    assert(robo_ball.attack < robo_ball.attackMove(robo_ball))
    assert(robo_ball.attackMove(robo_ball) > -1) //Attack should never be negative
    robo_ball.isKO = true
    assertEquals(robo_ball.attackMove(robo_ball), 0) //If KO, attack should be 0

  }

  test("Defense method") {
    val other = new Robo_ball(maxHp, attack, defense, evasion)
    val ref = robo_ball.currentHP
    val value = robo_ball.defendMove(other)
    assert(robo_ball.currentHP == ref - 1 || robo_ball.currentHP == ref - value)
    val megaRobo_ball = new Robo_ball(maxHp, 10000, defense, evasion)
    robo_ball.defendMove(megaRobo_ball)
    assertEquals(robo_ball.currentHP, expected = 0)
    assertEquals(robo_ball.isKO, expected = true)
  }

  test("Evade method") {
    val other = new Robo_ball(maxHp, attack, defense, evasion)
    val ref = robo_ball.currentHP
    val value = robo_ball.evadeMove(other)
    //println(value,ref,other.attack)
    assert(robo_ball.currentHP == ref || robo_ball.currentHP == ref - value)
  }
}

