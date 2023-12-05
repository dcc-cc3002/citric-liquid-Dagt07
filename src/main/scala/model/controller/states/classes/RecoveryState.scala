package cl.uchile.dcc.citric
package model.controller.states.classes

import model.controller.states.abstractc.AbstractGameState
import model.units.classes.PlayerCharacter

class RecoveryState extends AbstractGameState{

  override def doAction(): Unit = {
    if (controller.recovery(player: PlayerCharacter)){
      controller.changeState(new PlayerTurnState())
    }
    else{
      controller.changeState(new ChapterState(i+1))
    }

  }
}
