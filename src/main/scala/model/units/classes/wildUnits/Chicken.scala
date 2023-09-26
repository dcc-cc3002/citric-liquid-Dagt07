package cl.uchile.dcc.citric
package model.units.classes.wildUnits

import model.units.abstractClasses.abstractUnit


class Chicken (maxHp: Int, attack: Int, defense: Int, evasion: Int) extends abstractUnit(maxHp, attack, defense, evasion){

  //Chicken as a wild unit will have the ability to steal stars form the player if it wins, but right now that involves
  //combat, so it will be implemented later.
}
