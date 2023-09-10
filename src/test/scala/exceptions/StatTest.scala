package cl.uchile.dcc.citric
package exceptions

class StatTest extends munit.FunSuite {
  private val statName = "testStat"
  private val testRange = 1 to 10
  private val statInRange = Require.Stat(5, statName)
  private val statOutOfRange = Require.Stat(11, statName)
  private val threshold = 5
  private val valueBelowThreshold = 4
  private val statBelowThreshold = Require.Stat(valueBelowThreshold, statName)
  private val valueAboveThreshold = 6
  private val statAboveThreshold = Require.Stat(valueAboveThreshold, statName)

  test(
    "An `in` requirement should assign the correct value if it's inside the range"
  ) {
    assertEquals(statInRange in testRange, 5)
  }

  test(
    "An `in` requirement should throw an exception if it's outside the range"
  ) {
    interceptMessage[InvalidStatException](
      s"An invalid stat was found -- ${statOutOfRange.name} should be in $testRange but was ${statOutOfRange.value}"
    ) {
      statOutOfRange in testRange
    }
  }

  test(
    "An `atLeast` requirement should assign the correct value if it's greater than or equal to the threshold"
  ) {
    assertEquals(statAboveThreshold atLeast threshold, valueAboveThreshold)
  }

  test(
    "An `atLeast` requirement should throw an exception if it's less than the threshold"
  ) {
    interceptMessage[InvalidStatException](
      s"An invalid stat was found -- ${statBelowThreshold.name} should be at least $threshold but was ${statBelowThreshold.value}"
    ) {
      statBelowThreshold atLeast threshold
    }
  }
}
