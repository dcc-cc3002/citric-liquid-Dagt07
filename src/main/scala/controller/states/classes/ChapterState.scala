package cl.uchile.dcc.citric
package controller.states.classes

import controller.states.abstractc.AbstractGameState

class ChapterState extends AbstractGameState{

  override def doAction(): Unit = {
    /* si ya no hay jugadores con turno en este chapter, hacer new chapter para volver a partir */
    if (controller.turn > controller.playersLength()-1){
      controller.turn = 0
      controller.chapter += 1
    }

    /* ver si alguien ta con norma6 */
    if (controller.finish()) {
      //controller.promptWin()
      controller.changeState(new GameOverState())
      return
    }
    /*ver recovery */
    controller.selectPlayer()
    if (controller.playerSelected().isKO) { /* si esta KO, hacer recovery */
      controller.changeState(new RecoveryState())
    }
    else{ /* jugar turno, sino estaba KO*/
      //controller.canPlay() o controller.playsTurn()
      controller.changeState(new PlayerTurnState())
    }

  }
}
