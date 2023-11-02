package cl.uchile.dcc.citric
package model.units.classes.wilds

import model.units.abstractc.AbstractUnit
import model.units.traits.WildUnit

/**
 * Class that represents the Robo_ball wild unit.
 * @param maxHp the maximum hp of the unit.
 * @param attack the attack of the unit.
 * @param defense the defense of the unit.
 * @param evasion the evasion of the unit.
 *
 * @author [[https://github.com/Dagt07/ David García T.]]
 */
class Robo_ball(maxHp: Int, attack: Int, defense: Int, evasion: Int) extends AbstractUnit(maxHp, attack, defense, evasion) with WildUnit{

  //Robo_ball as a wild unit will have the ability to steal stars form the player if it wins, but right now that involves
  //combat, so it will be implemented later.
}
