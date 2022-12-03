@file:OptIn(ExperimentalCli::class)

package kellegous.aoc

import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand

class Day02 : Subcommand("day02", "Day 2") {
    val input by Input.optionFor(this)

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

    override fun execute() {
        val lines = Input.linesFrom(input).toList()
        val p1Score = lines.sumOf { p1Scores.get(it)!! }
        println("part 1: $p1Score")
        val p2Score = lines.sumOf { p2Scores.get(it)!! }
        println("part 2: $p2Score")
    }
}