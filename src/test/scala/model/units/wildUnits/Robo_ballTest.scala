package cl.uchile.dcc.citric
package model.units.wildUnits

import model.units.classes.wildUnits.Robo_ball
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
}

