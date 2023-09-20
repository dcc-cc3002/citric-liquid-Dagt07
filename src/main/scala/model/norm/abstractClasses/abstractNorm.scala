package cl.uchile.dcc.citric
package model.norm.abstractClasses

import model.norm.traits.normTrait

/* pensé en hacer una clase abstracta para las normas, pero no sé si es necesario,
por ello la dejo de forma momentanea creada */

class abstractNorm(val defaultNorm: Int = 1, var currentNorm: Int, var normObjective: String) extends normTrait {

}
