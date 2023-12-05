package cl.uchile.dcc.citric
package model.panels

import model.panels.traits.Panel
import model.panels.abstractc.AbstractPanel
import model.panels.classes._
import model.units.classes.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

class Board(characters: ArrayBuffer[PlayerCharacter], nextPanels: ArrayBuffer[Panel])
  extends AbstractPanel(characters, nextPanels) {

  private val board: Array[Array[Panel]] = Array.ofDim[Panel](6, 6)
  private val defaultNeutralPanel: Panel = new NeutralPanel(ArrayBuffer[PlayerCharacter](), ArrayBuffer[Panel]())

  // Función para obtener el panel en una posición específica
  def getPanelAt(x: Int, y: Int): Panel = board(x)(y)

  // Función para obtener el panel a la izquierda de una posición
  def izq(x: Int, y: Int): Panel = board(x)((y - 1 + 6) % 6)

  // Función para obtener el panel a la derecha de una posición
  def der(x: Int, y: Int): Panel = board(x)((y + 1) % 6)

  /** Lists of characters and nextPanels necessary for each Panel */
  val hc1: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]();
  val hc2: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]()
  val hc3: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]();
  val hc4: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]()
  val hn1: ArrayBuffer[Panel] = ArrayBuffer[Panel]();
  val hn2: ArrayBuffer[Panel] = ArrayBuffer[Panel]()
  val hn3: ArrayBuffer[Panel] = ArrayBuffer[Panel]();
  val hn4: ArrayBuffer[Panel] = ArrayBuffer[Panel]()
  val nc1: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]();
  val nc2: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]()
  val nc3: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]();
  val nc4: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]()
  val nn1: ArrayBuffer[Panel] = ArrayBuffer[Panel]();
  val nn2: ArrayBuffer[Panel] = ArrayBuffer[Panel]()
  val nn3: ArrayBuffer[Panel] = ArrayBuffer[Panel]();
  val nn4: ArrayBuffer[Panel] = ArrayBuffer[Panel]()
  val bc1: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]();
  val bc2: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]()
  val bc3: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]();
  val bc4: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]()
  val bn1: ArrayBuffer[Panel] = ArrayBuffer[Panel]();
  val bn2: ArrayBuffer[Panel] = ArrayBuffer[Panel]()
  val bn3: ArrayBuffer[Panel] = ArrayBuffer[Panel]();
  val bn4: ArrayBuffer[Panel] = ArrayBuffer[Panel]()
  val dc1: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]();
  val dc2: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]()
  val dc3: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]();
  val dc4: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]()
  val dn1: ArrayBuffer[Panel] = ArrayBuffer[Panel]();
  val dn2: ArrayBuffer[Panel] = ArrayBuffer[Panel]()
  val dn3: ArrayBuffer[Panel] = ArrayBuffer[Panel]();
  val dn4: ArrayBuffer[Panel] = ArrayBuffer[Panel]()
  val ec1: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]();
  val ec2: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]()
  val ec3: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]();
  val ec4: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter]()
  val en1: ArrayBuffer[Panel] = ArrayBuffer[Panel]();
  val en2: ArrayBuffer[Panel] = ArrayBuffer[Panel]()
  val en3: ArrayBuffer[Panel] = ArrayBuffer[Panel]();
  val en4: ArrayBuffer[Panel] = ArrayBuffer[Panel]()

  /** panels that form the board */
  val homePanel1 = new HomePanel(hc1, hn1, characters(0))
  val neutralPanel1 = new NeutralPanel(nc1, nn1)
  val bonusPanel1 = new BonusPanel(bc1, bn1)
  val dropPanel1 = new DropPanel(dc1, dn1)
  val encounterPanel1 = new EncounterPanel(ec1, en1)
  val homePanel2 = new HomePanel(hc2, hn2, characters(1))
  val neutralPanel2 = new NeutralPanel(nc2, nn2)
  val bonusPanel2 = new BonusPanel(bc2, bn2)
  val dropPanel2 = new DropPanel(dc2, dn2)
  val encounterPanel2 = new EncounterPanel(ec2, en2)
  val homePanel3 = new HomePanel(hc3, hn3, characters(2))
  val neutralPanel3 = new NeutralPanel(nc3, nn3)
  val bonusPanel3 = new BonusPanel(bc3, bn3)
  val dropPanel3 = new DropPanel(dc3, dn3)
  val encounterPanel3 = new EncounterPanel(ec3, en3)
  val homePanel4 = new HomePanel(hc4, hn4, characters(3))
  val neutralPanel4 = new NeutralPanel(nc4, nn4)
  val bonusPanel4 = new BonusPanel(bc4, bn4)
  val dropPanel4 = new DropPanel(dc4, dn4)
  val encounterPanel4 = new EncounterPanel(ec4, en4)

  // Llenar el área interna con NeutralPanel
  for (i <- 1 to 4; j <- 1 to 4) {
    board(i)(j) = defaultNeutralPanel
  }

  // paneles de las esquinas
  board(0)(0) = homePanel1
  board(0)(5) = homePanel2
  board(5)(5) = homePanel3
  board(5)(0) = homePanel4

  /* paneles exteriores */
  // Tablero arriba
  board(1)(0) = neutralPanel1
  board(2)(0) = bonusPanel1
  board(3)(0) = dropPanel1
  board(4)(0) = encounterPanel1
  // Tablero derecha
  board(5)(1) = neutralPanel2
  board(5)(2) = bonusPanel2
  board(5)(3) = dropPanel2
  board(5)(4) = encounterPanel2
  // Tablero abajo
  board(1)(5) = neutralPanel3
  board(2)(5) = bonusPanel3
  board(3)(5) = dropPanel3
  board(4)(5) = encounterPanel3
  // Tablero izquierda
  board(0)(1) = neutralPanel4
  board(0)(2) = bonusPanel4
  board(0)(3) = dropPanel4
  board(0)(4) = encounterPanel4


  // Conectar los paneles de las esquinas
  board(0)(0).nextPanels += encounterPanel4; board(0)(0).nextPanels += neutralPanel1
  board(0)(5).nextPanels += encounterPanel1; board(0)(5).nextPanels += neutralPanel2
  board(5)(5).nextPanels += encounterPanel2; board(5)(5).nextPanels += neutralPanel3
  board(5)(0).nextPanels += encounterPanel3; board(5)(0).nextPanels += neutralPanel4

  // conexion resto de paneles externos
  // Tablero arriba
  board(1)(0).nextPanels += homePanel1; board(1)(0).nextPanels += bonusPanel1
  board(2)(0).nextPanels += neutralPanel1; board(2)(0).nextPanels += dropPanel1
  board(3)(0).nextPanels += bonusPanel1; board(3)(0).nextPanels += encounterPanel1
  board(4)(0).nextPanels += dropPanel1; board(4)(0).nextPanels += homePanel2
  // Tablero derecha
  board(5)(1).nextPanels += homePanel2; board(5)(1).nextPanels += bonusPanel2
  board(5)(2).nextPanels += neutralPanel2; board(5)(2).nextPanels += dropPanel2
  board(5)(3).nextPanels += bonusPanel2; board(5)(3).nextPanels += encounterPanel2
  board(5)(4).nextPanels += dropPanel2; board(5)(4).nextPanels += homePanel3
  // Tablero abajo
  board(4)(5).nextPanels += homePanel3; board(4)(5).nextPanels += bonusPanel3
  board(3)(5).nextPanels += neutralPanel3; board(3)(5).nextPanels += dropPanel3
  board(2)(5).nextPanels += bonusPanel3; board(2)(5).nextPanels += encounterPanel3
  board(1)(5).nextPanels += dropPanel3; board(1)(5).nextPanels += homePanel4
  // Tablero izquierda
  board(0)(4).nextPanels += homePanel4; board(0)(4).nextPanels += bonusPanel4
  board(0)(3).nextPanels += neutralPanel4; board(0)(3).nextPanels += dropPanel4
  board(0)(2).nextPanels += bonusPanel4; board(0)(2).nextPanels += encounterPanel4
  board(0)(1).nextPanels += dropPanel4; board(0)(1).nextPanels += homePanel1

  def printBoard(board: Array[Array[Panel]]): Unit = {
    for (i <- 0 until 6) {
      for (j <- 0 until 6) {
        print(board(i)(j).getClass.getSimpleName.substring(0, 2) + " ")
      }
      println()
    }
  }

  printBoard(board)
}


