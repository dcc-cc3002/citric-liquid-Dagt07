package cl.uchile.dcc.citric
package model.units.classes

import model.norm.traits.normTrait
import model.units.traits.unitTrait
import model.units.abstractClasses.AbstractUnit
import model.norm.classes.NormaClass

import scala.math.{min, floorDiv}

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
  * @param defaultNorm The default norma level of the player.
  * @param currentNorm The current norma level of the player.
  * @param normObjective The objective of the player's current norma level.
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
                      var wins: Int,
                      val defaultNorm: Int = 1,
                      var currentNorm: Int,
                      var normObjective: String)
  extends AbstractUnit(maxHp, attack, defense, evasion) with normTrait {

  /** getters and setters for PlayerCharacter are implemented in abstractUnit */

  /** A instance of normaClass that will help checking player requirements to level up his norm */
  val playerNorm = new NormaClass(defaultNorm, currentNorm, normObjective)



  /* PANEL DEPENDENT METHODS */
  def regenerateHP(): Unit = {
    currentHP_=(10 + currentHP)
  }

  /** Increases the player's star count by the given amount. */
  def increaseStarsByPanel(): Unit = {
    stars += min(rollDice() * currentNorm, rollDice() * 3)
  }

  def decreaseStarsByPanel(): Unit = {
    stars -= rollDice() * currentNorm
  }


  /* ROUND DEPENDENT METHODS, will be implemented properly later */

  def increaseStarsByRound(amount: Int): Unit = {
    stars += amount
  }


  /* COMBAT DEPENDENT METHODS, will be implemented properly later */
  /** Increases the player's star count by the given amount(carried by the enemy). */
  def increaseStarsByCombat(amount: Int): Unit = {
    stars += amount
  }

  def decreaseStarsByCombat(amount: Int): Unit = {
    stars -= amount
  }

  // Two ways to gain wins for the player:
  //using overloading to separate the way victories are achieved

  //1. By defeating a wild unit
  def increaseVictories(wildUnit: unitTrait): Unit = {
    wins += 1
    stars += wildUnit.stars
  }

  //2. By defeating a player
  def increaseVictories(opponent: PlayerCharacter): Unit = {
    wins += 2
    val rival_Stars : Int = opponent.stars
    val value: Int = rival_Stars/2
    stars += value
  }

  /** Norm dependent methods */
  var IntNormObjective: Int = currentNorm + 1
  def normCheck(): Unit = {
    playerNorm.getRequirements(IntNormObjective) match {
      case Some((starsRequired, winsRequired)) =>
        if (stars >= starsRequired || wins >= winsRequired) {
          currentNorm += 1
          IntNormObjective += 1
          stars = 0
          wins = 0
        }
      case None => ()
    }
  }

}
