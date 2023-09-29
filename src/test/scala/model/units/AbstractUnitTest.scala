/* AUN SIN IMPLEMENTAR BIEN PORQUE QUIERO PROBAR LOS GETTERS Y SETTERS
DE LAS UNIDADES DEL ABSTRACT UNIT YA QUE ES LO QUE TODOS USAN
SIN EMBARGO NO LO HARE FINALMENTE ACÁ POR EL MOMENTO YA QUE NO SE DEBE INSTANCIAR UN ABSTRACT UNIT
POR ELLO LO PROBARE EN LAS UNIDADES QUE HEREDAN DE ESTE ABSTRACT UNIT
*/

/*
package cl.uchile.dcc.citric
package model.units

import model.units.traits.unitTrait
import model.units.abstractClasses.AbstractUnit

class AbstractUnitTest extends munit.FunSuite {

  /** Some default stats for a random AbstractUnit */
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
  private var abstractUnit: unitTrait = _  // <- x = _ is the same as x = null
  /* Add any other variables you need here... */

  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
    abstractUnit = new unitTrait() {
      override val maxHp: Int = AbstractUnitTest.this.maxHp
      override val attack: Int = AbstractUnitTest.this.attack
      override val defense: Int = AbstractUnitTest.this.defense
      override val evasion: Int = AbstractUnitTest.this.evasion
      override var currentHP: Int = AbstractUnitTest.this.currentHP
      override var stars: Int = AbstractUnitTest.this.stars
    }
  }

  test("A abstractUnit should have correctly set their attributes") {
    assertEquals(abstractUnit.maxHp, maxHp)
    assertEquals(abstractUnit.attack, attack)
    assertEquals(abstractUnit.defense, defense)
    assertEquals(abstractUnit.evasion, evasion)
    assertEquals(abstractUnit.currentHP, currentHP)
    assertEquals(abstractUnit.stars, stars)
  }
}
*/