package cl.uchile.dcc.citric
package exceptions

/** The `Require` object provides utilities for validating specific constraints or requirements.
 *
 * This object includes the `Stat` class, designed to validate a statistic's value against
 * constraints like a specified range or a minimum threshold.
 *
 * ### Adding New Requirements:
 *
 * 1. Within the `Require` object, create a case class representing the requirement.
 *    While the `final case` modifier is optional, it's recommended for better control over class
 *    hierarchy.
 * 2. Implement a validation method in the new case class. This method should either return the
 *    valid value or raise an exception for invalid cases.
 *
 * @author [[https://github.com/danielRamirezL/ Daniel Ramírez L.]]
 * @author [[https://github.com/joelriquelme/ Joel Riquelme P.]]
 * @author [[https://github.com/r8vnhill/ Ignacio Slater M.]]
 * @author [[https://github.com/Seivier/ Vicente González B.]]
 * @author [[https://github.com/~Your github account~/ ~Your Name~]]
 */
object Require {

  /**
   * Represents and validates a game statistic with an associated name.
   *
   * This class facilitates the validation of a statistic's value against specified constraints.
   * An [[InvalidStatException]] is thrown if the validation fails.
   *
   * Note:
   * The class is marked final and cannot be extended.
   *
   * @param value The numeric value of the statistic.
   * @param name The identifier or label for the statistic, primarily used in error messages.
   */
  final case class Stat(value: Int, name: String) {

    /**
     * Validates if the statistic's value lies within a provided range.
     *
     * @example
     * {{{
     *   val health = Require.Stat(100, "health") in (0 to 100)
     *   // => health: Int = 100
     * }}}
     * @example
     * {{{
     *   val health = Require.Stat(200, "health") in (0 to 100)
     *   // => throws InvalidStatException
     * }}}
     *
     * @param range The permissible range for the statistic's value.
     * @return The statistic's value if within the range.
     * @throws InvalidStatException for values outside the range.
     */
    def in(range: Range): Int =
      if (range contains value) value
      else throw new InvalidStatException(s"$name should be in $range but was $value")

    /**
     * Validates if the statistic's value is greater than or equal to a specified limit.
     *
     * @example
     * {{{
     *   val health = Require.Stat(100, "health") atLeast 0
     *   // => health: Int = 100
     * }}}
     * @example
     * {{{
     *   val health = Require.Stat(-10, "health") atLeast 0
     *   // => throws InvalidStatException
     * }}}
     *
     * @param lo The minimum accepted value for the statistic.
     * @return The statistic's value if it meets or exceeds the threshold.
     * @throws InvalidStatException for values below the threshold.
     */
    def atLeast(lo: Int): Int =
      if (value >= lo) value
      else throw new InvalidStatException(s"$name should be at least $lo but was $value")
  }
}
