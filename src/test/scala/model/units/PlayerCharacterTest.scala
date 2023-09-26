package cl.uchile.dcc.citric
package model.units

import model.units.classes.PlayerCharacter
import model.units.traits.unitTrait
import model.units.classes.wildUnits.{Chicken, Robo_ball, Seagull}

import cl.uchile.dcc.citric.model.units.classes.wildUnits.Chicken

import scala.util.Random

class PlayerCharacterTest extends munit.FunSuite {
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
  private var randomNumberGenerator: Random = _
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
  private var character: PlayerCharacter = _  // <- x = _ is the same as x = null
  /* Add any other variables you need here... */

  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
    randomNumberGenerator = new Random(11)
    character = new PlayerCharacter(
      name,
      maxHp,
      attack,
      defense,
      evasion,
      randomNumberGenerator: Random,
      wins,
      defaultNorm,
      currentNorm,
      normObjective
    )
  }

  test("A character should have correctly set their attributes") {
    assertEquals(character.name, name)
    assertEquals(character.maxHp, maxHp)
    assertEquals(character.attack, attack)
    assertEquals(character.defense, defense)
    assertEquals(character.evasion, evasion)
    assertEquals(character.randomNumberGenerator, randomNumberGenerator)
    assertEquals(character.stars, stars)
    assertEquals(character.wins, wins)
    assertEquals(character.currentHP, currentHP)
    assertEquals(character.defaultNorm, defaultNorm)
    assertEquals(character.currentNorm, currentNorm)
    assertEquals(character.normObjective, normObjective)
  }

  // Two ways to test randomness (you can use any of them):

  // 1. Test invariant properties, e.g. the result is always between 1 and 6.
  test("A character should be able to roll a dice") {
    for (_ <- 1 to 10) {
      assert(character.rollDice >= 1 && character.rollDice <= 6)
    }
  }

  // 2. Set a seed and test the result is always the same.
  // A seed sets a fixed succession of random numbers, so you can know that the next numbers
  // are always the same for the same seed.
  test("A character should be able to roll a dice with a fixed seed") {
    val other =
      new PlayerCharacter(name, maxHp, attack, defense, evasion, new Random(11),
                          wins, defaultNorm, currentNorm, normObjective)
    for (_ <- 1 to 10) {
      assertEquals(character.rollDice(), other.rollDice())
    }
  }

  test("A character should be able to regenerate their HP by landing in their HomePanel"){
    assertEquals(character.currentHP, maxHp)
    character.regenerateHP()
    assertEquals(character.currentHP, maxHp + 10)
  }

  test("A character should be able to increase their stars by landing in a BonusPanel"){
    val other = new PlayerCharacter(name, maxHp, attack, defense, evasion, new Random(11),
                                    wins, defaultNorm, currentNorm, normObjective)
    assertEquals(character.stars,other.stars)
    character.increaseStarsByPanel()
    assert(character.stars > other.stars)
  }

  test("A character should be able to decrease their stars by landing in a DropPanel") {
    val other = new PlayerCharacter(name, maxHp, attack, defense, evasion, new Random(11),
                                    wins, defaultNorm, currentNorm, normObjective)
    assertEquals(character.stars, other.stars)
    character.decreaseStarsByPanel()
    assert(character.stars < other.stars)
  }

  test("A character should increase their stars by winning a combat against other player") {
    val other = new PlayerCharacter(name, maxHp, attack, defense, evasion, new Random(11),
                                    wins, defaultNorm, currentNorm, normObjective)
    assertEquals(character.stars, other.stars)
    character.increaseStarsByCombat(2)
    assert(character.stars > other.stars)
    assertEquals(character.stars, other.stars + 2)
  }

  test("A character should decrease their stars by losing a combat against another player") {
    val other = new PlayerCharacter(name, maxHp, attack, defense, evasion, new Random(11),
                                    wins, defaultNorm, currentNorm, normObjective)
    assertEquals(character.stars, other.stars)
    character.decreaseStarsByCombat(3)
    assert(character.stars < other.stars)
    assertEquals(character.stars, other.stars - 3)
  }

  test("A character should increase their stars by winning a combat against a wildUnit") {
    /*For example here we will use a Chicken as a wildUnit (because all wildUnits are the same but with different stats*/
    val chicken: unitTrait = new Chicken(maxHp, attack, defense, evasion)
    assertEquals(character.stars, chicken.stars)
    character.increaseStarsByCombat(5)
    assert(character.stars > chicken.stars)
    assertEquals(character.stars, chicken.stars + 5)
  }

  test("A character should increase their victories by winning a combat against a wildUnit") {
    /*For example here we will use a Chicken as a wildUnit (because all wildUnits are the same but with different stats*/
    //Here is necessary to declare chicken a type Chicken, because we are using overloading in the PlayerCharacter class
    val chicken: Chicken = new Chicken(maxHp, attack, defense, evasion)
    //they both are initialized with 0 stars
    assertEquals(character.stars, chicken.stars)
    character.increaseVictories(chicken)
    assertEquals(character.stars, chicken.stars) //both are 0
    chicken.stars = 2 //now the wildUnit has 2 stars that the character can steal by winning
    character.increaseVictories(chicken)
    //wildUnit losing stars not implemented yet
    assertEquals(character.stars, chicken.stars)
  }


  test("A character should increase their victories by winning a combat against another player") {
    //Here is necessary to declare chicken a type Chicken, because we are using overloading in the PlayerCharacter class
    val opponent: PlayerCharacter = new PlayerCharacter(name, maxHp, attack, defense, evasion, new Random(11),
                                                     wins, defaultNorm, currentNorm, normObjective)
    //they both are initialized with 0 stars
    assertEquals(character.stars, opponent.stars)
    character.increaseVictories(opponent)
    assertEquals(character.stars, opponent.stars) //both are 0
    opponent.stars = 2 //now the opponent has 2 stars that the character can steal by winning
    character.increaseVictories(opponent)
    //opponent losing stars not implemented yet
    opponent.stars /= 2
    assertEquals(character.stars, opponent.stars)
  }

}
