package kellegous.aoc

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertEquals

class Day06Test {
    @TestFactory
    fun testExamples() = listOf(
        Triple("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 4, 7),
        Triple("bvwbjplbgvbhsrlpgdmjqwftvncz", 4, 5),
        Triple("nppdvjthqldpwncqszvftbrmjlhg", 4, 6),
        Triple("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 4, 10),
        Triple("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 4, 11),

        Triple("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 14, 19),
        Triple("bvwbjplbgvbhsrlpgdmjqwftvncz", 14, 23),
        Triple("nppdvjthqldpwncqszvftbrmjlhg", 14, 23),
        Triple("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 14, 29),
        Triple("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 14, 26),
    ).map { (s, size, expected) ->
        DynamicTest.dynamicTest(s) {
            assertEquals(expected, Day06.distinctOffsetOf(s, size))
        }
    }
}