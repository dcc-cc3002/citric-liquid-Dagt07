package cl.uchile.dcc.citric
package model.Norm.AbstractClasses

import model.Norm.Traits.normTrait

/* pensé en hacer una clase abstracta para las normas, pero no sé si es necesario,
por ello la dejo de forma momentanea creada */

class abstractNorm(val defaultNorm: Int = 1, var currentNorm: Int, var normObjective: String) extends normTrait {

}
