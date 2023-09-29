package cl.uchile.dcc.citric
package model.units.classes

import model.norm.traits.normTrait
import model.units.traits.unitTrait
import model.units.abstractClasses.abstractUnit
import model.norm.classes.NormaClass

import scala.math.{min, floorDiv}
import scala.util.Random

/** The `PlayerCharacter` class represents a character or avatar in the game, encapsulating
  * several attributes such as health points, attack strength, defense capability,
  * and evasion skills. Each player has a unique name, and throughout the game,
  * players can collect stars, roll dice, and progress in levels known as 'norma'.
  * This class not only maintains the state of a player but also provides methods
  * to modify and interact with these attributes.
  *
  * For instance, players can:
 *
  * - Increase or decrease their star count.
 *
  * - Roll a dice, a common action in many board games.
 *
  * - Advance their norma level.
  *
  * Furthermore, the `Player` class has a utility for generating random numbers,
  * which is primarily used for simulating dice rolls. By default, this utility is
  * an instance of the `Random` class but can be replaced if different random
  * generation behaviors are desired.
  *
  * @param name The name of the player. This is an identifier and should be unique.
  * @param maxHp The maximum health points a player can have. It represents the player's endurance.
  * @param attack The player's capability to deal damage to opponents.
  * @param defense The player's capability to resist or mitigate damage from opponents.
  * @param evasion The player's skill to completely avoid certain attacks.
  * @param randomNumberGenerator A utility to generate random numbers. Defaults to a new `Random`
  *                              instance.
  *
  * @author [[https://github.com/Dagt07/ David García T.]]
  * @author [[https://github.com/danielRamirezL/ Daniel Ramírez L.]]
  * @author [[https://github.com/joelriquelme/ Joel Riquelme P.]]
  * @author [[https://github.com/r8vnhill/ Ignacio Slater M.]]
  * @author [[https://github.com/Seivier/ Vicente González B.]]
  */

class PlayerCharacter(val name: String,
                      maxHp: Int,
                      attack: Int,
                      defense: Int,
                      evasion: Int,
                      val randomNumberGenerator: Random = new Random(),
                      var wins: Int,
                      val defaultNorm: Int = 1,
                      var currentNorm: Int,
                      var normObjective: String)
  extends abstractUnit(maxHp, attack, defense, evasion) with normTrait {

  /** getters and setters for PlayerCharacter */
  //private var _currentHP: Int = maxHp
  //private var _stars: Int = 0

  /*
  /** getter for currentHP
   * @return the current HP of the player
   */
  def currentHP: Int = _currentHP

  /** getter for stars
   * @return the current star count of the player
   */
  def stars: Int = _stars


  /** Setter for CurrentHP */
  def currentHP_= (newAmount: Int): Unit = _currentHP

  /** Setter for stars */
  def stars_= (newAmount: Int): Unit = _stars

  */

  /** A instance of normaClass that will help checking player requirements to level up his norm */
  val playerNorm = new NormaClass(defaultNorm, currentNorm, normObjective)

  /** Rolls a dice and returns a value between 1 to 6. */
  def rollDice(): Int = {
    randomNumberGenerator.nextInt(6) + 1
  }

  /* PANEL DEPENDENT METHODS */
  def regenerateHP(): Unit = {
    //currentHP += 10
    //_currentHP = currentHP + 10
    currentHP_=(10 + currentHP)
  }

  /** Increases the player's star count by the given amount. */
  def increaseStarsByPanel(): Unit = {
    stars += min(rollDice() * currentNorm, rollDice() * 3)
    //_stars = stars + min(rollDice() * currentNorm, rollDice() * 3)
  }

  def decreaseStarsByPanel(): Unit = {
    stars -= rollDice() * currentNorm
    //_stars = stars - rollDice() * currentNorm

  }


  /* ROUND DEPENDENT METHODS, will be implemented properly later */

  def increaseStarsByRound(amount: Int): Unit = {
    stars += amount
    //_stars = stars + amount
  }


  /* COMBAT DEPENDENT METHODS, will be implemented properly later */
  /** Increases the player's star count by the given amount(carried by the enemy). */
  def increaseStarsByCombat(amount: Int): Unit = {
    stars += amount
    //_stars = stars + amount
  }

  def decreaseStarsByCombat(amount: Int): Unit = {
    stars -= amount
    //_stars = stars + amount
  }

  // Two ways to gain wins for the player:


  //1. By defeating a wild unit
  def increaseVictories(wildUnit: unitTrait): Unit = {
    wins += 1
    stars += wildUnit.stars
    //_stars = stars + wildUnit._stars
  }


  //using overloading to separate the way victories are achieved

  //2. By defeating a player
  def increaseVictories(opponent: PlayerCharacter): Unit = {
    wins += 2
    val rival_Stars : Int = opponent.stars
    val value: Int = rival_Stars/2
    stars += value
    //_stars = stars + value
  }

  /** Norm dependent methods */
  var IntNormObjective: Int = currentNorm + 1
  def normCheck(): Unit = {
    playerNorm.getRequirements(IntNormObjective) match {
      case Some((starsRequired, winsRequired)) =>
        if (stars >= starsRequired || wins >= winsRequired) {
          currentNorm += 1
          IntNormObjective += 1
          //_stars = 0
          stars = 0
          wins = 0
        }
      case None => ()
    }
  }

}
