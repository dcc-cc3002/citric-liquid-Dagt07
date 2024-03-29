package cl.uchile.dcc.citric

import controller.GameController
import controller.observers.InfoObserver

object Main {
  def main(args: Array[String]): Unit = {
    val game = new GameController()
    game.addObserver(new InfoObserver())
    game.startGame()
    /* Cuando este listo, basta con descomentar esto para simular el juego
    while(!game.finish()) {
      game.play()
    }
    */
  }
}