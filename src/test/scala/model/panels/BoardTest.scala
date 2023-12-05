package cl.uchile.dcc.citric
package model.panels

import model.panels.traits.Panel
import model.panels.classes._
import model.panels.Board

import model.units.traits.UnitTrait
import model.units.classes.PlayerCharacter
import model.units.classes.wilds._

import scala.collection.mutable.ArrayBuffer

class BoardTest extends munit.FunSuite{
  private var player1 : PlayerCharacter= _
  private var player2 : PlayerCharacter= _
  private var player3 : PlayerCharacter= _
  private var player4 : PlayerCharacter= _

  private var testBoard: Board = _

  override def beforeEach(context: BeforeEach): Unit = {
    player1 = new PlayerCharacter("Player1", 10, 1, 1, 1)
    player2 = new PlayerCharacter("Player2", 10, 1, 1, 1)
    player3 = new PlayerCharacter("Player3", 10, 1, 1, 1)
    player4 = new PlayerCharacter("Player4", 10, 1, 1, 1)

    val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer[PlayerCharacter](player1,player2,player3,player4)
    val nextPanels: ArrayBuffer[Panel] = ArrayBuffer[Panel]()

    //Init the board
    testBoard = new Board(characters, nextPanels) {
    }

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

    /** connections between panels */
    homePanel1.nextPanels += encounterPanel4;
    homePanel1.nextPanels += neutralPanel1
    neutralPanel1.nextPanels += bonusPanel1;
    neutralPanel1.nextPanels += bonusPanel1
    bonusPanel1.nextPanels += neutralPanel1;
    bonusPanel1.nextPanels += dropPanel1
    dropPanel1.nextPanels += bonusPanel1;
    dropPanel1.nextPanels += encounterPanel1
    encounterPanel1.nextPanels += dropPanel1;
    encounterPanel1.nextPanels += homePanel2 //hasta acá
    homePanel2.nextPanels += encounterPanel1;
    homePanel2.nextPanels += neutralPanel2
    neutralPanel2.nextPanels += bonusPanel2;
    neutralPanel2.nextPanels += bonusPanel2
    bonusPanel2.nextPanels += neutralPanel2;
    bonusPanel2.nextPanels += dropPanel2
    dropPanel2.nextPanels += bonusPanel2;
    dropPanel2.nextPanels += encounterPanel2
    encounterPanel2.nextPanels += dropPanel2;
    encounterPanel2.nextPanels += homePanel3
    homePanel3.nextPanels += encounterPanel2;
    homePanel3.nextPanels += neutralPanel3
    neutralPanel3.nextPanels += bonusPanel3;
    neutralPanel3.nextPanels += bonusPanel3
    bonusPanel3.nextPanels += neutralPanel3;
    bonusPanel3.nextPanels += dropPanel3
    dropPanel3.nextPanels += bonusPanel3;
    dropPanel3.nextPanels += encounterPanel3
    encounterPanel3.nextPanels += dropPanel3;
    encounterPanel3.nextPanels += homePanel4
    homePanel4.nextPanels += encounterPanel3;
    homePanel4.nextPanels += neutralPanel4
    neutralPanel4.nextPanels += bonusPanel4;
    neutralPanel4.nextPanels += bonusPanel4
    bonusPanel4.nextPanels += neutralPanel4;
    bonusPanel4.nextPanels += dropPanel4
    dropPanel4.nextPanels += bonusPanel4;
    dropPanel4.nextPanels += encounterPanel4
    encounterPanel4.nextPanels += dropPanel4;
    encounterPanel4.nextPanels += homePanel1
  }

  // 1. Test invariant properties
  test("A Board should have correctly set their attributes") {
    assertEquals(testBoard.characters, ArrayBuffer[PlayerCharacter](player1, player2, player3, player4))
    assertEquals(testBoard.nextPanels, ArrayBuffer[Panel]())
  }


  test("A board should have a home panel for each player") {
    assertEquals(testBoard.homePanel1.owner, player1)
    val homePanel1 = testBoard.getPanelAt(0,0)
    assertEquals(homePanel1, testBoard.homePanel1)
  }



}


