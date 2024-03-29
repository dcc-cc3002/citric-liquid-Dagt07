package cl.uchile.dcc.citric
package controller.states.classes

import controller.states.abstractc.AbstractGameState

class LandingPanelState extends AbstractGameState{
  override def doAction(): Unit = {

    //se llega desde CombatState

    //se hace el efecto
    controller.panelEffect()
    controller.changeState(new ChapterState())
  }
}
