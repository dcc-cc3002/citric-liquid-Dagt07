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
  private val defense = +1
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

  test("A Robo_ball should be able to change its currentHP using his getter and setter") {
    val expected = robo_ball.currentHP
    assertEquals(robo_ball.currentHP, expected)
    robo_ball.currentHP_=(expected + 1)
    assertEquals(robo_ball.currentHP, expected + 1)
  }
  
  test("A Robo_ball should be able to get and change his decision to defend or evade if someone attacks him") {
    assertEquals(robo_ball.decision, "defense")
    robo_ball.decision = "evade"
    assertEquals(robo_ball.decision, "evade")
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

  /** ------------------------------------- COMBAT METHODS -------------------------------------- */
  /* for properly test the double dispatch for the combat system, we will test the methods from inside to outside the scope
    or in other words, by the runtime stack with the most deep method that has been called
   */

  test("AttackCalculator method should return a damage value") {
    val ref = robo_ball.attack // -1
    val damageToDeal = robo_ball.attackCalculator(robo_ball)
    assert(damageToDeal > -1) // damageToDeal cant be negative
    // in this case, robo_ball attack is -1, so the maximum attack depends on the rollDice() getting a 6 ---> dmg = 6+(-1) = 5
    assert(damageToDeal == 0 || damageToDeal <= (attack + 6))
    assert(damageToDeal > ref) // it can be (0 to 5) > -1
    val lazyRobo_ball = new Robo_ball(maxHp, -1000, defense, evasion) // a Unit with no attack
    val damage = lazyRobo_ball.attackCalculator(lazyRobo_ball)
    assertEquals(damage, 0) // for a negative attack, it should return damage = 0
  }

  test("AttackCalculator for a KO unit should return 0") {
    val otherUnit = new Robo_ball(maxHp, attack, defense, evasion)
    otherUnit.currentHP = 0
    otherUnit.isKO = true
    val damage = otherUnit.attackCalculator(otherUnit)
    assertEquals(damage, 0)
  }

  test("Defense method") {
    val ref = robo_ball.currentHP
    val opponent = new Robo_ball(maxHp, attack, defense, evasion)
    val damageToReceive = opponent.attackCalculator(opponent) //we already test this method
    assertEquals(opponent.currentHP, ref)
    robo_ball.defendMove(damageToReceive)
    // Minimum damage is 1, or Max damage 5 ---> it will defeat the robo_ball and his HP would be 0, or currentHP between 1 to maxHP(3 in this case)
    assert(robo_ball.currentHP == ref - 1 || robo_ball.currentHP == 0 || (robo_ball.currentHP > 0 && robo_ball.currentHP <= maxHp))
  }

  test("Defense method vs a OverPowered unit") {
    val ref = robo_ball.currentHP
    val megaRobo_ball = new Robo_ball(maxHp, 1000, defense, evasion)
    val damageToReceive = megaRobo_ball.attackCalculator(megaRobo_ball) //we already test this method, could be 1001 to 1006
    assert(1000 < damageToReceive && damageToReceive <= 1006)
    robo_ball.defendMove(damageToReceive)
    assert(robo_ball.currentHP < ref)
    assertEquals(robo_ball.currentHP, 0)
    assertEquals(robo_ball.isKO, expected = true)
  }

  test("Evade method") {
    val ref = robo_ball.currentHP
    val opponent = new Robo_ball(maxHp, attack, defense, evasion)
    val damageToReceive = opponent.attackCalculator(opponent) // Minimum damage = 1, Max damage = 5
    assertEquals(opponent.currentHP, ref)
    val damageReceived = robo_ball.evadeMove(damageToReceive)
    //evade the attack = damageReceived = 0, take all damage = damageReceived == damageToReceive
    assert(damageReceived == 0 || damageReceived == damageToReceive)
    assert(robo_ball.currentHP > -1) //after the attack, currentHP cant be negative
    // 1) SameHP because evade successfully the attack, 2) take all the damage and survives, 3) take all damage and not survives
    assert(robo_ball.currentHP == ref || robo_ball.currentHP == ref - damageReceived || robo_ball.currentHP == 0)
  }

  test("Evade method vs a non dangerous Unit") {
    val ref = robo_ball.currentHP
    val lazyRobo_ball = new Robo_ball(maxHp, -1000, defense, evasion) // a Unit with no attack
    val damageToReceive = lazyRobo_ball.attackCalculator(lazyRobo_ball)
    assertEquals(damageToReceive, 0) // for a negative attack, it should return damage = 0
    robo_ball.evadeMove(damageToReceive)
    assertEquals(robo_ball.currentHP, ref) // for a negative attack, it should always evade it ---> return damage = 0
  }

  test("Evade method vs a OverPowered unit") {
    // By the evade idea, it always take all damage or nothing, then this will have the same effect as test("Evade method")
    val ref = robo_ball.currentHP
    val megaRobo_ball = new Robo_ball(maxHp, 1000, defense, evasion)
    val damageToReceive = megaRobo_ball.attackCalculator(megaRobo_ball) //we already test this method, could be 1001 to 1006
    assert(1000 < damageToReceive && damageToReceive <= 1006)
    val damageReceived = robo_ball.evadeMove(damageToReceive)
    assert(damageReceived == 0 || damageReceived == damageToReceive)
    assert(robo_ball.currentHP > -1) //after the attack, currentHP cant be negative
    // 1) SameHP because evade successfully the attack, 2) take all the damage (OP unit KO the robo_ball)
    assert(robo_ball.currentHP == ref || robo_ball.currentHP == 0)
  }

  test("ReceiveAttack method: First test, a robo_ball not in KO state and with 'defense' tactic") {
    val ref = robo_ball.currentHP
    val attackingUnit = new Robo_ball(maxHp, attack, defense, evasion)
    assertEquals(robo_ball.isKO, false)
    assertEquals(robo_ball.decision, "defense")
    // First, test a robo_ball not in KO state and with defense tactic
    val damageReceived = robo_ball.receiveAttack(attackingUnit)
    // Minimum damage is 1, or Max damage 5 ---> it will defeat the robo_ball and his HP would be 0, or currentHP between 1 to maxHP(3 in this case)
    assert(robo_ball.currentHP == ref - 1 || robo_ball.currentHP == 0 || (robo_ball.currentHP > 0 && robo_ball.currentHP <= maxHp))
  }

  test("ReceiveAttack method: Second test, a robo_ball not in KO state and with 'evade' tactic") {
    val ref = robo_ball.currentHP
    val attackingUnit = new Robo_ball(maxHp, 1, defense, evasion) // With attack equal to 1 we can test both scenarios
    assertEquals(robo_ball.isKO, false)
    robo_ball.decision = "evade"
    assertEquals(robo_ball.decision, "evade")
    // First, test a robo_ball not in KO state and with defense tactic
    val damageReceived = robo_ball.receiveAttack(attackingUnit)
    // 1) SameHP because evade successfully the attack, 2) take all the damage and survives, 3) take all damage and not survives
    assert(robo_ball.currentHP == ref || robo_ball.currentHP == ref - damageReceived || robo_ball.currentHP == 0)
  }

  test("ReceiveAttack method: Third test, a robo_ball in KO state with any tactic") {
    val ref = robo_ball.currentHP
    val attackingUnit = new Robo_ball(maxHp, 1, defense, evasion)
    robo_ball.isKO = true
    assertEquals(robo_ball.isKO, true)
    val damageReceived = robo_ball.receiveAttack(attackingUnit)
    assertEquals(damageReceived, 0)
    assertEquals(robo_ball.currentHP, ref)
  }

  test("ReceiveAttack method: Fourth test, a robo_ball with an invalid tactic") {
    val ref = robo_ball.currentHP
    val attackingUnit = new Robo_ball(maxHp, 1, defense, evasion) // With attack equal to 1 we can test both scenarios
    robo_ball.decision = "something"
    assertEquals(robo_ball.decision, "something")
    val damageReceived = robo_ball.receiveAttack(attackingUnit)
    assertEquals(damageReceived, 0)
    assertEquals(robo_ball.currentHP, ref)
  }

  test("Attack Method, with 'defense' tactic") {
    //our robo_ball will defend, his opponent will attack
    val ref = robo_ball.currentHP
    val attackingUnit = new Robo_ball(maxHp, attack, defense, evasion)
    assertEquals(robo_ball.isKO, false)
    assertEquals(robo_ball.decision, "defense")
    val damageReceived = attackingUnit.attackMove(robo_ball)
    // Minimum damage is 1, or Max damage 5 ---> it will defeat the robo_ball and his HP would be 0, or currentHP between 1 to maxHP(3 in this case)
    assert(robo_ball.currentHP == ref - 1 || robo_ball.currentHP == 0 || robo_ball.currentHP > 0)
  }

  test("Attack Method, with 'evade' tactic") {
    //our robo_ball will defend, his opponent will attack
    val ref = robo_ball.currentHP
    val attackingUnit = new Robo_ball(maxHp, 1, defense, evasion)
    assertEquals(robo_ball.isKO, false)
    robo_ball.decision = "evade"
    assertEquals(robo_ball.decision, "evade")
    val damageReceived = attackingUnit.attackMove(robo_ball)
    assert(robo_ball.currentHP > -1) //after the attack, currentHP cant be negative
    // 1) SameHP because evade successfully the attack, 2) take all the damage and survives, 3) take all damage and not survives
    assert(robo_ball.currentHP == ref || robo_ball.currentHP == ref - damageReceived || robo_ball.currentHP == 0)
  }


}

