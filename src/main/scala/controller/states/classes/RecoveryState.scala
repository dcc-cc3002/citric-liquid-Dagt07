package cl.uchile.dcc.citric
package controller.states.classes

import controller.states.abstractc.AbstractGameState
import model.units.classes.PlayerCharacter

class RecoveryState extends AbstractGameState{

  override def doAction(): Unit = {
    controller.selectPlayer()
    if (controller.recovery(controller.playerSelected())){ //Se logró recuperar
      controller.changeState(new PlayerTurnState())
    }
    else{ //No se logró recuperar
      controller.turn += 1
      controller.changeState(new ChapterState())
    }

  }
}
