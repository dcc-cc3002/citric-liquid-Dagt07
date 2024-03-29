package cl.uchile.dcc.citric
package model.units.wilds

import model.units.classes.wilds.{Chicken, RoboBall, Seagull}
import model.units.classes.PlayerCharacter
class SeagullTest extends munit.FunSuite {
  /*
  REMEMBER: It is a good practice to use constants for the values that are used in multiple
  tests, so you can change them in a single place.
  This will make your tests more readable, easier to maintain, and less error-prone.
  */
  private val maxHp = 3
  private val attack = +1
  private val defense = -1
  private val evasion = -1
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
    seagull = new Seagull(maxHp, attack, defense, evasion)
  }

  test("A Seagull should have correctly set their attributes") {
    assertEquals(seagull.maxHp, maxHp)
    assertEquals(seagull.attack, attack)
    assertEquals(seagull.defense, defense)
    assertEquals(seagull.evasion, evasion)
    assertEquals(seagull.currentHP, currentHP)
    assertEquals(seagull.stars, stars)
  }

  test("A Seagull should be able to change its currentHP using his getter and setter") {
    val expected = seagull.currentHP
    assertEquals(seagull.currentHP, expected)
    seagull.currentHP_=(expected + 1)
    assertEquals(seagull.currentHP, expected + 1)
  }

  test("A Seagull should be able to get and change his decision to defend or evade if someone attacks him") {
    assertEquals(seagull.decision, "defense")
    seagull.decision = "evade"
    assertEquals(seagull.decision, "evade")
  }
  
  // Two ways to test randomness (you can use any of them):

  // 1. Test invariant properties, e.g. the result is always between 1 and 6.
  test("A Seagull should be able to roll a dice without a default/fixed seed") {
    for (_ <- 1 to 10) {
      assert(seagull.rollDice() >= 1 && seagull.rollDice() <= 6)
    }
  }

  // 2. Set a seed and test the result is always the same.
  // A seed sets a fixed succession of random numbers, so you can know that the next numbers
  // are always the same for the same seed.
  test("A Seagull should be able to roll a dice with a fixed seed") {
    val other =
      new Seagull(maxHp, attack, defense, evasion)
    for (_ <- 1 to 10) {
      assertEquals(seagull.rollDice(11), other.rollDice(11))
    }
  }

  /** ------------------------------------- COMBAT METHODS -------------------------------------- */
  /* for properly test the double dispatch for the combat system, we will test the methods from inside to outside the scope
    or in other words, by the runtime stack with the most deep method that has been called
   */

  test("AttackCalculator method should return a damage value") {
    val ref = seagull.attack // -1
    val damageToDeal = seagull.attackCalculator(seagull)
    assert(damageToDeal > -1) // damageToDeal cant be negative
    // in this case, seagull attack is -1, so the maximum attack depends on the rollDice() getting a 6 ---> dmg = 6+(-1) = 5
    assert(damageToDeal == 0 || damageToDeal <= (attack + 6))
    assert(damageToDeal > ref) // it can be (0 to 5) > -1
    val lazySeagull = new Seagull(maxHp, -1000, defense, evasion) // a Unit with no attack
    val damage = lazySeagull.attackCalculator(lazySeagull)
    assertEquals(damage, 0) // for a negative attack, it should return damage = 0
  }

  test("AttackCalculator for a KO unit should return 0") {
    val otherUnit = new Seagull(maxHp, attack, defense, evasion)
    otherUnit.currentHP = 0
    otherUnit.isKO = true
    val damage = otherUnit.attackCalculator(otherUnit)
    assertEquals(damage, 0)
  }

  test("Defense method") {
    val ref = seagull.currentHP
    val opponent = new Seagull(maxHp, attack, defense, evasion)
    val damageToReceive = opponent.attackCalculator(opponent) //we already test this method
    assertEquals(opponent.currentHP, ref)
    seagull.defendMove(damageToReceive,opponent)
    // Minimum damage is 1, or Max damage 5 ---> it will defeat the seagull and his HP would be 0, or currentHP between 1 to maxHP(3 in this case)
    assert(seagull.currentHP == ref - 1 || seagull.currentHP == 0 || (seagull.currentHP > 0 && seagull.currentHP <= maxHp))
  }

  test("Defense method vs a OverPowered unit") {
    val ref = seagull.currentHP
    val megaSeagull = new Seagull(maxHp, 1000, defense, evasion)
    val damageToReceive = megaSeagull.attackCalculator(megaSeagull) //we already test this method, could be 1001 to 1006
    assert(1000 < damageToReceive && damageToReceive <= 1006)
    seagull.defendMove(damageToReceive,megaSeagull)
    assert(seagull.currentHP < ref)
    assertEquals(seagull.currentHP, 0)
    assertEquals(seagull.isKO, expected = true)
  }

  test("Evade method") {
    val ref = seagull.currentHP
    val opponent = new Seagull(maxHp, attack, defense, evasion)
    val damageToReceive = opponent.attackCalculator(opponent) // Minimum damage = 1, Max damage = 5
    assertEquals(opponent.currentHP, ref)
    val damageReceived = seagull.evadeMove(damageToReceive,opponent)
    //evade the attack = damageReceived = 0, take all damage = damageReceived == damageToReceive
    assert(damageReceived == 0 || damageReceived == damageToReceive)
    assert(seagull.currentHP > -1) //after the attack, currentHP cant be negative
    // 1) SameHP because evade successfully the attack, 2) take all the damage and survives, 3) take all damage and not survives
    assert(seagull.currentHP == ref || seagull.currentHP == ref - damageReceived || seagull.currentHP == 0)
  }

  test("Evade method vs a non dangerous Unit") {
    val ref = seagull.currentHP
    val lazySeagull = new Seagull(maxHp, -1000, defense, evasion) // a Unit with no attack
    val damageToReceive = lazySeagull.attackCalculator(lazySeagull)
    assertEquals(damageToReceive, 0) // for a negative attack, it should return damage = 0
    seagull.evadeMove(damageToReceive,lazySeagull)
    assertEquals(seagull.currentHP, ref) // for a negative attack, it should always evade it ---> return damage = 0
  }

  test("Evade method vs a OverPowered unit") {
    // By the evade idea, it always take all damage or nothing, then this will have the same effect as test("Evade method")
    val ref = seagull.currentHP
    val megaSeagull = new Seagull(maxHp, 1000, defense, evasion)
    val damageToReceive = megaSeagull.attackCalculator(megaSeagull) //we already test this method, could be 1001 to 1006
    assert(1000 < damageToReceive && damageToReceive <= 1006)
    val damageReceived = seagull.evadeMove(damageToReceive,megaSeagull)
    assert(damageReceived == 0 || damageReceived == damageToReceive)
    assert(seagull.currentHP > -1) //after the attack, currentHP cant be negative
    // 1) SameHP because evade successfully the attack, 2) take all the damage (OP unit KO the seagull)
    assert(seagull.currentHP == ref || seagull.currentHP == 0)
  }

  test("ReceiveAttack method: First test, a seagull not in KO state and with 'defense' tactic") {
    val ref = seagull.currentHP
    val attackingUnit = new Seagull(maxHp, attack, defense, evasion)
    assertEquals(seagull.isKO, false)
    assertEquals(seagull.decision, "defense")
    // First, test a seagull not in KO state and with defense tactic
    val damageReceived = seagull.receiveAttack(attackingUnit)
    // Minimum damage is 1, or Max damage 5 ---> it will defeat the seagull and his HP would be 0, or currentHP between 1 to maxHP(3 in this case)
    assert(seagull.currentHP == ref - 1 || seagull.currentHP == 0 || (seagull.currentHP > 0 && seagull.currentHP <= maxHp))
  }

  test("ReceiveAttack method: Second test, a seagull not in KO state and with 'evade' tactic") {
    val ref = seagull.currentHP
    val attackingUnit = new Seagull(maxHp, 1, defense, evasion) // With attack equal to 1 we can test both scenarios
    assertEquals(seagull.isKO, false)
    seagull.decision = "evade"
    assertEquals(seagull.decision, "evade")
    // First, test a seagull not in KO state and with defense tactic
    val damageReceived = seagull.receiveAttack(attackingUnit)
    // 1) SameHP because evade successfully the attack, 2) take all the damage and survives, 3) take all damage and not survives
    assert(seagull.currentHP == ref || seagull.currentHP == ref - damageReceived || seagull.currentHP == 0)
  }

  test("ReceiveAttack method: Third test, a seagull in KO state with any tactic") {
    val ref = seagull.currentHP
    val attackingUnit = new Seagull(maxHp, 1, defense, evasion)
    seagull.isKO = true
    assertEquals(seagull.isKO, true)
    val damageReceived = seagull.receiveAttack(attackingUnit)
    assertEquals(damageReceived, 0)
    assertEquals(seagull.currentHP, ref)
  }

  test("ReceiveAttack method: Fourth test, a seagull with an invalid tactic") {
    val ref = seagull.currentHP
    val attackingUnit = new Seagull(maxHp, 1, defense, evasion) // With attack equal to 1 we can test both scenarios
    seagull.decision = "something"
    assertEquals(seagull.decision, "something")
    val damageReceived = seagull.receiveAttack(attackingUnit)
    assertEquals(damageReceived, 0)
    assertEquals(seagull.currentHP, ref)
  }

  test("Attack method, with 'defense' tactic") {
    //our seagull will defend, his opponent will attack
    val ref = seagull.currentHP
    val attackingUnit = new Seagull(maxHp, attack, defense, evasion)
    assertEquals(seagull.isKO, false)
    assertEquals(seagull.decision, "defense")
    attackingUnit.attackMove(seagull)
    // Minimum damage is 1, or Max damage 5 ---> it will defeat the seagull and his HP would be 0, or currentHP between 1 to maxHP(3 in this case)
    assert(seagull.currentHP == ref - 1 || seagull.currentHP == 0 || seagull.currentHP > 0)
  }

  test("Attack method, with 'evade' tactic") {
    //our seagull will defend, his opponent will attack
    val ref = seagull.currentHP
    val attackingUnit = new Seagull(maxHp, 1, defense, evasion)
    assertEquals(seagull.isKO, false)
    seagull.decision = "evade"
    assertEquals(seagull.decision, "evade")
    val damageReceived = attackingUnit.attackMove(seagull)
    assert(seagull.currentHP > -1) //after the attack, currentHP cant be negative
    // 1) SameHP because evade successfully the attack, 2) take all the damage and survives, 3) take all damage and not survives
    assert(seagull.currentHP == ref || seagull.currentHP == ref - damageReceived || seagull.currentHP == 0)
  }

  test("Increase Stars method: Vs PlayerCharacter") {
    seagull.stars = 4
    val opponent = new PlayerCharacter("john", maxHp, attack, defense, evasion)
    opponent.stars = 4
    assertEquals(seagull.stars, opponent.stars)
    seagull.increaseStars(opponent)
    assertEquals(seagull.stars, 6)
    assertNotEquals(seagull.stars, opponent.stars)
  }

  test("Increase Stars method: Vs any WildUnit type should not increase stars") {
    seagull.stars = 4
    val opponent = new Chicken(maxHp, attack, defense, evasion)
    val opponent2 = new RoboBall(maxHp, attack, defense, evasion)
    val opponent3 = new Seagull(maxHp, attack, defense, evasion)
    opponent.stars = 4
    opponent2.stars = 4
    opponent3.stars = 4
    assertEquals(seagull.stars, opponent.stars)
    assertEquals(seagull.stars, opponent2.stars)
    assertEquals(seagull.stars, opponent3.stars)
    seagull.increaseStars(opponent) // it should not increase stars
    assertEquals(seagull.stars, 4)
    seagull.increaseStars(opponent2) // it should not increase stars
    assertEquals(seagull.stars, 4)
    seagull.increaseStars(opponent3) // it should not increase stars
    assertEquals(seagull.stars, 4)
  }

}
