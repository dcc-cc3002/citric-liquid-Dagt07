package cl.uchile.dcc.citric
package model.panels.classes

import model.panels.abstractClasses.AbstractPanel
import model.panels.traits.Panel
import model.units.classes.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

class EncounterPanel(characters: ArrayBuffer[PlayerCharacter], nextPanels: ArrayBuffer[Panel])
  extends AbstractPanel(characters, nextPanels){

  /* In the current version, this panel cant be implemented because the combat system is not implemented yet */
  }
