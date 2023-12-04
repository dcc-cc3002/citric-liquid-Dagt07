package cl.uchile.dcc.citric
package model.controller.states.abstractc

import model.controller.GameController
import model.controller.states.traits.GameState

abstract class AbstractGameState extends GameState{
  private var _controller: Option[GameController] = None

  /** Getter for controller */
  def controller: GameController = {
    if (_controller.isDefined) {
      _controller.get
    } else {
      throw new AssertionError("Controller not defined")
    }
  }

  /** Setter for controller */
  def controller_=(cont: GameController): Unit = {
    _controller = Some(cont)
  }

  def doAction(): Unit = throw new AssertionError("Wrong State")
}