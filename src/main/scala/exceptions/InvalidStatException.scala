package cl.uchile.dcc.citric
package exceptions

/**
 * Custom exception to signal an invalid game statistic encountered.
 *
 * This exception is designed to provide more specific feedback about
 * invalid game statistics. For instance, if a player's health points are
 * outside an allowable range, this exception could be thrown with a detailed
 * message indicating the nature of the problem.
 *
 * @example To throw the exception with specific details:
 * {{{
 * throw new InvalidStatException("Player's health points cannot be negative.")
 * // => InvalidStatException: An invalid stat was found -- Player's health points cannot be negative.
 * }}}
 *
 * @param details A descriptive message detailing the nature of the invalid stat.
 *
 * @author [[https://github.com/danielRamirezL/ Daniel Ramírez L.]]
 * @author [[https://github.com/joelriquelme/ Joel Riquelme P.]]
 * @author [[https://github.com/r8vnhill/ Ignacio Slater M.]]
 * @author [[https://github.com/Seivier/ Vicente González B.]]
 * @author [[https://github.com/~Your github account~/ ~Your Name~]]
 */
class InvalidStatException(details: String) extends Exception(s"An invalid stat was found -- $details")
