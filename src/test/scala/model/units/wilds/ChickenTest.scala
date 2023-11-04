package cl.uchile.dcc.citric
package model.units.wilds

import model.units.classes.wilds.Chicken

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

  test("A Chicken should be able to change its currentHP using his getter and setter"){
    val expected = chicken.currentHP
    assertEquals(chicken.currentHP, expected)
    chicken.currentHP_=(expected+1)
    assertEquals(chicken.currentHP, expected+1)
  }

  test("A Chicken should be able to get and change his decision to defend or evade if someone attacks him") {
    assertEquals(chicken.decision, "defense")
    chicken.decision = "evade"
    assertEquals(chicken.decision, "evade")
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

  /** ------------------------------------- COMBAT METHODS --------------------------------------*/
  /* for properly test the double dispatch for the combat system, we will test the methods from inside to outside the scope
    or in other words, by the runtime stack with the most deep method that has been called
   */

  test("AttackCalculator method should return a damage value"){
    val ref = chicken.attack // -1
    val damageToDeal = chicken.attackCalculator(chicken)
    assert(damageToDeal > -1) // damageToDeal cant be negative
    // in this case, chicken attack is -1, so the maximum attack depends on the rollDice() getting a 6 ---> dmg = 6+(-1) = 5
    assert(damageToDeal == 0 || damageToDeal < 6)
    assert(damageToDeal > ref) // it can be (0 to 5) > -1
    val lazyChicken = new Chicken(maxHp, -1000, defense, evasion) // a Unit with no attack
    val damage = lazyChicken.attackCalculator(lazyChicken)
    assertEquals(damage,0) // for a negative attack, it should return damage = 0
  }

  test("AttackCalculator for a KO unit should return 0"){
    val otherUnit = new Chicken(maxHp,attack,defense,evasion)
    otherUnit.currentHP = 0
    otherUnit.isKO = true
    val damage = otherUnit.attackCalculator(otherUnit)
    assertEquals(damage,0)
  }

  test("Defense method"){
    val ref = chicken.currentHP
    val opponent = new Chicken(maxHp, attack, defense, evasion)
    val damageToReceive = opponent.attackCalculator(opponent) //we already test this method
    assertEquals(opponent.currentHP, ref)
    chicken.defendMove(damageToReceive)
    // Minimum damage is 1, or Max damage 5 ---> it will defeat the chicken and his HP would be 0, or currentHP between 1 to maxHP(3 in this case)
    assert(chicken.currentHP == ref - 1 || chicken.currentHP == 0 || (chicken.currentHP > 0  && chicken.currentHP <= maxHp) )
  }

  test("Defense method vs a OverPowered unit"){
    val ref = chicken.currentHP
    val megaChicken = new Chicken(maxHp, 1000, defense, evasion)
    val damageToReceive = megaChicken.attackCalculator(megaChicken) //we already test this method, could be 1001 to 1006
    assert(1000 < damageToReceive && damageToReceive <= 1006)
    chicken.defendMove(damageToReceive)
    assert(chicken.currentHP < ref)
    assertEquals(chicken.currentHP,0)
    assertEquals(chicken.isKO, expected = true)
  }

  test("Evade method"){
    val ref = chicken.currentHP
    val opponent = new Chicken(maxHp, attack, defense, evasion)
    val damageToReceive = opponent.attackCalculator(opponent) // Minimum damage = 1, Max damage = 5
    assertEquals(opponent.currentHP, ref)
    val damageReceived = chicken.evadeMove(damageToReceive)
    //evade the attack = damageReceived = 0, take all damage = damageReceived == damageToReceive
    assert(damageReceived == 0 || damageReceived == damageToReceive)
    assert(chicken.currentHP > -1) //after the attack, currentHP cant be negative
    // 1) SameHP because evade successfully the attack, 2) take all the damage and survives, 3) take all damage and not survives
    assert(chicken.currentHP == ref || chicken.currentHP == ref - damageReceived || chicken.currentHP == 0)
  }

  test("Evade method vs a non dangerous Unit"){
    val ref = chicken.currentHP
    val lazyChicken = new Chicken(maxHp, -1000, defense, evasion) // a Unit with no attack
    val damageToReceive = lazyChicken.attackCalculator(lazyChicken)
    assertEquals(damageToReceive, 0) // for a negative attack, it should return damage = 0
    chicken.evadeMove(damageToReceive)
    assertEquals(chicken.currentHP, ref) // for a negative attack, it should always evade it ---> return damage = 0
  }

  test("Evade method vs a OverPowered unit") {
    // By the evade idea, it always take all damage or nothing, then this will have the same effect as test("Evade method")
    val ref = chicken.currentHP
    val megaChicken = new Chicken(maxHp, 1000, defense, evasion)
    val damageToReceive = megaChicken.attackCalculator(megaChicken) //we already test this method, could be 1001 to 1006
    assert(1000 < damageToReceive && damageToReceive <= 1006)
    val damageReceived = chicken.evadeMove(damageToReceive)
    assert(damageReceived == 0 || damageReceived == damageToReceive)
    assert(chicken.currentHP > -1) //after the attack, currentHP cant be negative
    // 1) SameHP because evade successfully the attack, 2) take all the damage (OP unit KO the chicken)
    assert(chicken.currentHP == ref || chicken.currentHP == 0)
  }

  test("ReceiveAttack method: First test, a chicken not in KO state and with 'defense' tactic"){
    val ref = chicken.currentHP
    val attackingUnit = new Chicken(maxHp, attack, defense, evasion)
    assertEquals(chicken.isKO, false)
    assertEquals(chicken.decision, "defense")
    // First, test a chicken not in KO state and with defense tactic
    val damageReceived = chicken.receiveAttack(attackingUnit)
    // Minimum damage is 1, or Max damage 5 ---> it will defeat the chicken and his HP would be 0, or currentHP between 1 to maxHP(3 in this case)
    assert(chicken.currentHP == ref - 1 || chicken.currentHP == 0 || (chicken.currentHP > 0  && chicken.currentHP <= maxHp) )
  }

  test("ReceiveAttack method: Second test, a chicken not in KO state and with 'evade' tactic") {
    val ref = chicken.currentHP
    val attackingUnit = new Chicken(maxHp, 1, defense, evasion) // With attack equal to 1 we can test both scenarios
    assertEquals(chicken.isKO, false)
    chicken.decision = "evade"
    assertEquals(chicken.decision, "evade")
    // First, test a chicken not in KO state and with defense tactic
    val damageReceived = chicken.receiveAttack(attackingUnit)
    // 1) SameHP because evade successfully the attack, 2) take all the damage and survives, 3) take all damage and not survives
    assert(chicken.currentHP == ref || chicken.currentHP == ref - damageReceived || chicken.currentHP == 0)
  }

  test("ReceiveAttack method: Third test, a chicken in KO state with any tactic"){
    val ref = chicken.currentHP
    val attackingUnit = new Chicken(maxHp, 1, defense, evasion)
    chicken.isKO = true
    assertEquals(chicken.isKO, true)
    val damageReceived = chicken.receiveAttack(attackingUnit)
    assertEquals(damageReceived,0)
    assertEquals(chicken.currentHP, ref)
  }

  test("ReceiveAttack method: Fourth test, a chicken with an invalid tactic"){
    val ref = chicken.currentHP
    val attackingUnit = new Chicken(maxHp, 1, defense, evasion) // With attack equal to 1 we can test both scenarios
    chicken.decision = "something"
    assertEquals(chicken.decision, "something")
    val damageReceived = chicken.receiveAttack(attackingUnit)
    assertEquals(damageReceived, 0)
    assertEquals(chicken.currentHP, ref)
  }

  test("Attack Method, with 'defense' tactic"){
    //our chicken will defend, his opponent will attack
    val ref = chicken.currentHP
    val attackingUnit = new Chicken(maxHp, attack, defense, evasion)
    assertEquals(chicken.isKO, false)
    assertEquals(chicken.decision, "defense")
    val damageReceived = attackingUnit.attackMove(chicken)
    // Minimum damage is 1, or Max damage 5 ---> it will defeat the chicken and his HP would be 0, or currentHP between 1 to maxHP(3 in this case)
    assert(chicken.currentHP == ref - 1 || chicken.currentHP == 0 || chicken.currentHP > 0)
  }

  test("Attack Method, with 'evade' tactic") {
    //our chicken will defend, his opponent will attack
    val ref = chicken.currentHP
    val attackingUnit = new Chicken(maxHp, 1, defense, evasion)
    assertEquals(chicken.isKO, false)
    chicken.decision = "evade"
    assertEquals(chicken.decision, "evade")
    val damageReceived = attackingUnit.attackMove(chicken)
    assert(chicken.currentHP > -1) //after the attack, currentHP cant be negative
    // 1) SameHP because evade successfully the attack, 2) take all the damage and survives, 3) take all damage and not survives
    assert(chicken.currentHP == ref || chicken.currentHP == ref - damageReceived || chicken.currentHP == 0)
  }

}

