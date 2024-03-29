package cl.uchile.dcc.citric
package model.units.classes

import model.norm.traits.NormTrait
import model.units.traits.{UnitTrait, WildUnit}
import model.units.abstractc.AbstractUnit
import model.norm.classes.Norma1

import model.units.classes.wilds.{Chicken, RoboBall, Seagull}

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
  var norma:NormTrait = new Norma1(this)
  var intNorm = 1
  var normObjective: String = "stars"
  var wins : Int = 0

  /**-------------------------- PANEL DEPENDENT METHODS --------------------------*/

  /** Regenerates HP for the player character. */
  def regenerateHP(): Unit = {
    currentHP_=(10 + currentHP)
  }

  /** Increases the player character's star count based on panel effects. */
  def increaseStarsByPanel(): Unit = {
    stars += min(rollDice() * intNorm, rollDice() * 3)
  }

  /** Decreases the player character's star count based on panel effects. */
  def decreaseStarsByPanel(): Unit = {
    stars -= rollDice() * intNorm
  }

  /** -------- ROUND DEPENDENT METHODS, will be implemented properly later -------- */

  /** Increases the player character's star count based on round effects. */
  def increaseStarsByRound(amount: Int): Unit = {
    stars += amount
  }

  /**-------------------------- COMBAT DEPENDENT METHODS --------------------------*/

  /** Increases the stars of a unit after defeating a PlayerCharacter.
   * @param unit The unit that defeated the PlayerCharacter.
   */
  def increaseStars(unit: UnitTrait): Unit = {
    unit.increaseStarsPlayer(this)
  }

  /** Increases the stars of a player character after defeating a PlayerCharacter.
   * @param unit The player character who defeated the PlayerCharacter.
   */
  def increaseStarsPlayer(unit: PlayerCharacter): Unit = {
    unit.stars += this.stars/2
    this.stars = floorDiv(this.stars, 2)
    unit.increaseVictoriesVsPlayer()
  }

  /** Increases the stars of a Chicken after defeating a PlayerCharacter.
   * @param unit The chicken who defeated the PlayerCharacter.
   */
  override def increaseStarsChicken(unit: Chicken): Unit = {
    unit.stars += this.stars/2 //losing as a Player vs any WildUnit will drop our stars by half
    this.stars = floorDiv(this.stars, 2)
  }

  /** Increases the stars of a Robo_ball after defeating a PlayerCharacter.
   * @param unit The robo_ball who defeated the PlayerCharacter.
   */
  override def increaseStarsRoboBall(unit: RoboBall): Unit = {
    unit.stars += this.stars/2
    this.stars = floorDiv(this.stars, 2)
  }

  /** Increases the stars of a Seagull after defeating a PlayerCharacter.
   * @param unit The seagull who defeated the PlayerCharacter.
   */
  override def increaseStarsSeagull(unit: Seagull): Unit = {
    unit.stars += this.stars/2
    this.stars = floorDiv(this.stars, 2)
  }

  // Two ways to gain wins for the player:

  //1. By defeating a wild unit
  /** Increases the wins of a player character after defeating a wild unit. */
  def increaseVictoriesVsWildUnit(): Unit = {
    wins += 1
  }

  //2. By defeating a player
  /** Increases the wins of a player character after defeating another player. */
  private def increaseVictoriesVsPlayer(): Unit = {
    wins += 2
  }

}
