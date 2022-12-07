@file:OptIn(ExperimentalCli::class)

package kellegous.aoc

import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand

private fun splitAt(s: String, d: Char) = s.substringBefore(d).let {
    it to s.substring(it.length + 1)
}

private fun parseRange(s: String) = splitAt(s, '-').let { (a, b) ->
    IntRange(a.toInt(), b.toInt())
}

class Day04 : Subcommand("day04", "Day 4") {
    private val input by Input.optionFor(this)

    companion object {
        fun solve(src: String): Pair<Int, Int> {
            val pairs = Input.linesFrom(src).map {
                splitAt(it, ',').let { (a, b) ->
                    parseRange(a) to parseRange(b)
                }
            }.toList()

            val p1 = pairs.count { (a, b) ->
                minOf(a.last, b.last) - maxOf(a.first, b.first) == minOf(a.last - a.first, b.last - b.first)
            }

            val p2 = pairs.count { (a, b) ->
                maxOf(a.first, b.first) <= minOf(a.last, b.last)
            }

            return Pair(p1, p2)
        }
    }

    override fun execute() {
        val (p1, p2) = solve(input)
        println("part 1: $p1")
        println("part 2: $p2")
    }
}