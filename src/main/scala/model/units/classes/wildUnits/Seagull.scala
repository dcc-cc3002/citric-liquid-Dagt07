package cl.uchile.dcc.citric
package model.units.classes.wildUnits

import model.units.abstractClasses.abstractUnit

class Seagull(maxHp: Int = 3, attack: Int = 1, defense: Int = -1, evasion: Int = -1) extends abstractUnit(maxHp, attack, defense, evasion){

  //Seagull as a wild unit will have the ability to steal stars form the player if it wins, but right now that involves
  //combat, so it will be implemented later.
}
