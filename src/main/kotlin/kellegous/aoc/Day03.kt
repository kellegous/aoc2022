@file:OptIn(ExperimentalCli::class)

package kellegous.aoc

import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand
import java.io.BufferedReader

fun priority(c: Char) = when (c) {
    in 'a'..'z' -> c.code - 96
    else -> c.code - 38
}

class Day03 : Subcommand("day03", "Day 3") {
    private val input by Input.optionFor(this)

    override fun execute() {
        val sacks = BufferedReader(Input.readerFrom(input)).lineSequence().toList()

        val p1 = sacks.map {
            val n = it.length / 2
            it.substring(0, n).toSet().intersect(it.substring(n).toSet()).first()
        }.sumOf { priority(it) }
        println("part 1: $p1")

        val p2 = sacks.chunked(3).map {
            it[0].toSet().intersect(it[1].toSet()).intersect(it[2].toSet()).first()
        }.sumOf { priority(it) }
        println("part 2: $p2")
    }
}