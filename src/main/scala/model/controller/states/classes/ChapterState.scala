package cl.uchile.dcc.citric
package model.controller.states.classes

import model.controller.states.abstractc.AbstractGameState

class ChapterState(val currentTurn: Int) extends AbstractGameState{
  /* currentTurn es pa saber cuantas veces hacer playsTurn (hasta la cantidad de players)*/


  override def doAction(): Unit = {

    /* ver si alguien ta con norma6 */
    if (controller.finish()) {
      //controller.promptWin()
      controller.changeState(new GameOverState())
    }
    /*ver recovery */
    if (player.isKO()) {
      controller.changeState(new RecoveryState())
    }
    else{ /* jugar turno, depende del recovery*/
      //controller.canPlay() o controller.playsTurn()
      controller.changeState(new PlayerTurnState())
    }



    //controller.players.foreach(player => player.playTurn()) sirve pero no tiene en cuenta turnos

    /* si ya no hay jugadores con turno en este chapter, hacer new chapter para volver a partir */
  }
}
