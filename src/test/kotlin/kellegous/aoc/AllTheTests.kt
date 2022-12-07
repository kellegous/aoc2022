package kellegous.aoc

import org.junit.jupiter.api.Test
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
}