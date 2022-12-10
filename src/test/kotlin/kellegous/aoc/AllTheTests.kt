package kellegous.aoc

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertEquals

class AllTheTests {

    @Test
    fun day01() {
        assertEquals(
            Pair(24000, 45000),
            Day01.solve("day01/example.txt")
        )
    }

    @Test
    fun day02() {
        assertEquals(
            Pair(15, 12),
            Day02.solve("day02/example.txt")
        )
    }

    @Test
    fun day03() {
        assertEquals(
            Pair(157, 70),
            Day03.solve("day03/example.txt")
        )
    }

    @Test
    fun day04() {
        assertEquals(
            Pair(2, 4),
            Day04.solve("day04/example.txt")
        )
    }

    @Test
    fun day05() {
        assertEquals(
            Pair("CMZ", "MCD"),
            Day05.solve("day05/example.txt")
        )
    }

    @TestFactory
    fun day06() = listOf(
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
        DynamicTest.dynamicTest("s = ${s}, size = ${size}") {
            assertEquals(expected, Day06.distinctOffsetOf(s, size))
        }
    }

    @Test
    fun day07() {
        assertEquals(
            Pair(95437, 24933642),
            Day07.solve("day07/example.txt")
        )
    }

    @Test
    fun day08() {
        assertEquals(
            Pair(21, 8),
            Day08.solve("day08/example.txt")
        )
    }

    @Test
    fun day09() {
        assertEquals(
            Pair(13, 1),
            Day09.solve("day09/example01.txt")
        )
        assertEquals(
            Pair(88, 36),
            Day09.solve("day09/example02.txt")
        )
    }
}