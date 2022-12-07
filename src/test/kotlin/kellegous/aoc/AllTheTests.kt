package kellegous.aoc

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AllTheTests {

    @Test
    fun day01() {
        assertEquals(
            24000 to 45000,
            Day01.solve("day01/example.txt")
        )
    }
}