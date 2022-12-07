@file:OptIn(ExperimentalCli::class)

package kellegous.aoc

import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand

class Day02 : Subcommand("day02", "Day 2") {
    private val input by Input.optionFor(this)

    companion object {
        val p1Scores = mapOf(
            "A X" to 3 + 1,
            "A Y" to 6 + 2,
            "A Z" to 0 + 3,
            "B X" to 0 + 1,
            "B Y" to 3 + 2,
            "B Z" to 6 + 3,
            "C X" to 6 + 1,
            "C Y" to 0 + 2,
            "C Z" to 3 + 3,
        )

        val p2Scores = mapOf(
            "A X" to 0 + 3,
            "A Y" to 3 + 1,
            "A Z" to 6 + 2,
            "B X" to 0 + 1,
            "B Y" to 3 + 2,
            "B Z" to 6 + 3,
            "C X" to 0 + 2,
            "C Y" to 3 + 3,
            "C Z" to 6 + 1,
        )

        fun solve(src: String) = Input.linesFrom(src).toList().let {
            Pair(
                it.sumOf { p1Scores.get(it)!! },
                it.sumOf { p2Scores.get(it)!! }
            )
        }
    }

    override fun execute() {
        val (p1, p2) = solve(input)
        println("part 1: $p1")
        println("part 2: $p2")
    }
}