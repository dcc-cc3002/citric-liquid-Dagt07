package cl.uchile.dcc.citric
package controller.states.classes

import controller.states.abstractc.AbstractGameState

import model.units.traits.UnitTrait

class CombatState extends AbstractGameState{

  override def doAction(): Unit = {

    /* pensar caso en que no hace nada como decia el Aux por implementación sencilla */

    //caso no hay nadie en el panel
    if(controller.getPanel.characters.isEmpty) {
      controller.changeState(new LandingPanelState())
      return
    }
    //caso hay alguien en el panel ----> pelear, por ahora solo atacar al primero
    controller.selectAllyTarget(controller.getPanel.characters.head)
    controller.doAttack(controller.target())
    controller.changeState(new LandingPanelState()) //ya peleo
  }

}
