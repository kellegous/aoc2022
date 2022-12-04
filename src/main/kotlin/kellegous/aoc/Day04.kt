@file:OptIn(ExperimentalCli::class)

package kellegous.aoc

import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand

fun splitAt(s: String, d: Char) = s.substringBefore(d).let {
    it to s.substring(it.length + 1)
}

fun parseRange(s: String) = splitAt(s, '-').let { (a, b) ->
    IntRange(a.toInt(), b.toInt())
}

class Day04 : Subcommand("day04", "Day 4") {
    val input by Input.optionFor(this)

    override fun execute() {
        val pairs = Input.linesFrom(input).map {
            splitAt(it, ',').let { (a, b) ->
                parseRange(a) to parseRange(b)
            }
        }.toList()

        val p1 = pairs.count { (a, b) ->
            minOf(a.last, b.last) - maxOf(a.first, b.first) == minOf(a.last - a.first, b.last - b.first)
        }
        println("part 1: $p1")

        val p2 = pairs.count { (a, b) ->
            maxOf(a.first, b.first) <= minOf(a.last, b.last)
        }
        println("part 2: $p2")
    }
}