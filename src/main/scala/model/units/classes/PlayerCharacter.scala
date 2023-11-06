package cl.uchile.dcc.citric
package model.units.classes

import model.norm.traits.NormTrait
import model.units.traits.{UnitTrait, WildUnit}
import model.units.abstractc.AbstractUnit
import model.norm.classes.Norma1

import model.units.classes.wilds.{Chicken, Robo_ball, Seagull}

import scala.math.{floorDiv, min}

/** The `PlayerCharacter` class represents a character or avatar in the game, encapsulating
  * several attributes such as health points, attack strength, defense capability,
  * and evasion skills. Each player has a unique name, and throughout the game,
  * players can collect stars, roll dice, and progress in levels known as 'norma'.
  * This class not only maintains the state of a player but also provides methods
  * to modify and interact with these attributes.
  *
  * For instance, players can:
  *
  * - Increase or decrease their star/victories count.
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
  * @param wins The number of victories the player has.
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
                      evasion: Int)
  extends AbstractUnit(maxHp, attack, defense, evasion) {

  /** getters and setters for PlayerCharacter are implemented in abstractUnit */

  /** A instance of normaClass that will help checking player requirements to level up his norm */
  //val playerNorm = new NormaClass(defaultNorm, currentNorm, normObjective)
  var norma:NormTrait = new Norma1(this)
  var intNorm = 1
  var normObjective: String = "stars"
  var wins : Int = 0

  /* PANEL DEPENDENT METHODS */
  def regenerateHP(): Unit = {
    currentHP_=(10 + currentHP)
  }

  /** Increases the player's star count by the given amount. */
  def increaseStarsByPanel(): Unit = {
    stars += min(rollDice() * intNorm, rollDice() * 3)
  }

  def decreaseStarsByPanel(): Unit = {
    stars -= rollDice() * intNorm
  }

  /* ROUND DEPENDENT METHODS, will be implemented properly later */

  def increaseStarsByRound(amount: Int): Unit = {
    stars += amount
  }

  /* COMBAT DEPENDENT METHODS */
  def increaseStars(unit: UnitTrait): Unit = {
    unit.increaseStarsPlayer(this)
  }

  def increaseStarsPlayer(unit: PlayerCharacter): Unit = {
    unit.stars += this.stars/2
    this.stars = floorDiv(this.stars, 2)
    unit.increaseVictoriesVsPlayer()
  }

  def increaseStarsChicken(unit: Chicken): Unit = {
    unit.stars += this.stars/2 //losing as a Player vs any WildUnit will drop our stars by half
    this.stars = floorDiv(this.stars, 2)
  }

  def increaseStarsRobo_ball(unit: Robo_ball): Unit = {
    unit.stars += this.stars/2
    this.stars = floorDiv(this.stars, 2)
  }

  def increaseStarsSeagull(unit: Seagull): Unit = {
    unit.stars += this.stars/2
    this.stars = floorDiv(this.stars, 2)
  }

  // Two ways to gain wins for the player:

  //1. By defeating a wild unit
  def increaseVictoriesVsWildUnit(): Unit = {
    wins += 1
  }

  //2. By defeating a player
  private def increaseVictoriesVsPlayer(): Unit = {
    wins += 2
  }

}
