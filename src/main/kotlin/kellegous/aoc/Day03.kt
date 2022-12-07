@file:OptIn(ExperimentalCli::class)

package kellegous.aoc

import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand

private fun priority(c: Char) = when (c) {
    in 'a'..'z' -> c.code - 96
    else -> c.code - 38
}

class Day03 : Subcommand("day03", "Day 3") {
    private val input by Input.optionFor(this)

    companion object {
        fun solve(src: String): Pair<Int, Int> {
            val sacks = Input.linesFrom(src).toList()

            val p1 = sacks.map {
                val n = it.length / 2
                it.substring(0, n).toSet().intersect(it.substring(n).toSet()).first()
            }.sumOf { priority(it) }

            val p2 = sacks.chunked(3).map {
                it[0].toSet().intersect(it[1].toSet()).intersect(it[2].toSet()).first()
            }.sumOf { priority(it) }

            return Pair(p1, p2)
        }
    }

    override fun execute() {
        val (p1, p2) = solve(input)
        println("part 1: $p1")
        println("part 2: $p2")
    }
}