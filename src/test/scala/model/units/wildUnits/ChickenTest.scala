package cl.uchile.dcc.citric
package model.units.wildUnits

import model.units.classes.wildUnits.Chicken

class ChickenTest extends munit.FunSuite {
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
  private var stars = 0
  private var currentHP = maxHp
  /*
  This is the object under test.
  We initialize it in the beforeEach method so we can reuse it in all the tests.
  This is a good practice because it will reset the object before each test, so you don't have
  to worry about the state of the object between tests.
  */
  private var chicken: Chicken = _  // <- x = _ is the same as x = null
  /* Add any other variables you need here... */

  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
    chicken = new Chicken(maxHp, attack, defense, evasion)
  }

  test("A Chicken should have correctly set their attributes") {
    assertEquals(chicken.maxHp, maxHp)
    assertEquals(chicken.attack, attack)
    assertEquals(chicken.defense, defense)
    assertEquals(chicken.evasion, evasion)
    assertEquals(chicken.currentHP, currentHP)
    assertEquals(chicken.stars, stars)
  }

  test("A Chicken should be able to change its currentHP using his getter and setter"){
    val expected = chicken.currentHP
    assertEquals(chicken.currentHP, expected)
    chicken.currentHP_=(expected+1)
    assertEquals(chicken.currentHP, expected+1)
  }

  // Two ways to test randomness (you can use any of them):

  // 1. Test invariant properties, e.g. the result is always between 1 and 6.
  test("A Chicken should be able to roll a dice without a default/fixed seed") {
    for (_ <- 1 to 10) {
      assert(chicken.rollDice() >= 1 && chicken.rollDice() <= 6)
    }
  }

  // 2. Set a seed and test the result is always the same.
  // A seed sets a fixed succession of random numbers, so you can know that the next numbers
  // are always the same for the same seed.
  test("A Chicken should be able to roll a dice with a fixed seed") {
    val other =
      new Chicken(maxHp,attack,defense,evasion)
    for (_ <- 1 to 10) {
      assertEquals(chicken.rollDice(11), other.rollDice(11))
    }
  }

  test("Defense method"){
    val other = new Chicken(maxHp, attack, defense, evasion)
    val ref = chicken.currentHP
    val value = chicken.defendMove(other)
    println(value,ref,chicken.currentHP)
    assert(chicken.currentHP == ref - 1 || chicken.currentHP == ref - value)
  }

  test("Evade method"){
    val other = new Chicken(maxHp, attack, defense, evasion)
    val ref = chicken.currentHP
    val value = chicken.evadeMove(other)
    //println(value,ref,other.attack)
    assert(chicken.currentHP == ref || chicken.currentHP == ref - value)
  }

}

