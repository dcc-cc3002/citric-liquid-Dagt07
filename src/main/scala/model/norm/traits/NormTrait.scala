package cl.uchile.dcc.citric
package model.norm.traits

/** Trait that defines the common attributes of a norm.
 *
 * @param defaultNorm the default norm value.
 * @param currentNorm the current norm value.
 * @param normObjective sets the current objective for level up the norm.
 * @constructor create a new norm with a default norm value.
 * @author [[https://github.com/Dagt07 David Garcia T.]]
 */

trait NormTrait {
  val defaultNorm: Int
  var currentNorm: Int
  var normObjective: String
}
