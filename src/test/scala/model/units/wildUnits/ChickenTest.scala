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
  private val stars = 0
  private val currentHP = maxHp
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

  /*PARA NO SOBRECARGAR DE MOMENTO AL PLAYER Y TODOS LOS TEST QUE YA TIENE,
  PROBEMOS ACÁ LOS GETTERS Y SETTERS DE ABSTRACT UNIT*/

  test("A Chicken should be able to change its currentHP using his getter and setter"){
    val expected = chicken.currentHP
    assertEquals(chicken.currentHP, expected)
    chicken.currentHP_=(expected+1)
    assertEquals(chicken.currentHP, expected+1)
  }
}

