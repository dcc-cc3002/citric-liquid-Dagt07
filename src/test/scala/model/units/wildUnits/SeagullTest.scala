package cl.uchile.dcc.citric
package model.units.wildUnits

import model.units.classes.wildUnits.Seagull

class SeagullTest extends munit.FunSuite {
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
  private var seagull: Seagull = _  // <- x = _ is the same as x = null
  /* Add any other variables you need here... */

  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
    seagull = new Seagull(
      maxHp,
      attack,
      defense,
      evasion,
      currentHP,
      stars)
  }

  test("A Chicken should have correctly set their attributes") {
    assertEquals(seagull.maxHp, maxHp)
    assertEquals(seagull.attack, attack)
    assertEquals(seagull.defense, defense)
    assertEquals(seagull.evasion, evasion)
    assertEquals(seagull.currentHP, currentHP)
    assertEquals(seagull.stars, stars)
  }
}
