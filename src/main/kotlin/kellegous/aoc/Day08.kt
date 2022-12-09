@file:OptIn(ExperimentalCli::class)

package kellegous.aoc

import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand
import kotlin.math.sqrt

class Day08 : Subcommand("day08", "Day 8") {
    private val input by Input.optionFor(this)

    companion object {
        private data class Grid(val data: List<Int>) {
            val size = sqrt(data.size.toDouble()).toInt()

            fun at(i: Int, j: Int) = data[j * size + i]

            fun isVisible(i: Int, j: Int) = at(i, j).let { v ->
                (0 until i).all { at(it, j) < v }
                        || (i + 1 until size).all { at(it, j) < v }
                        || (0 until j).all { at(i, it) < v }
                        || (j + 1 until size).all { at(i, it) < v }
            }

            fun score(i: Int, j: Int) = at(i, j).let { v ->
                (((i + 1 until size).find { at(it, j) >= v } ?: (size - 1)) - i) *
                        (i - ((i - 1 downTo 0).find { at(it, j) >= v } ?: 0)) *
                        (((j + 1 until size).find { at(i, it) >= v } ?: (size - 1)) - j) *
                        (j - ((j - 1 downTo 0).find { at(i, it) >= v } ?: 0))
            }

            fun coordinates() = sequence {
                for (i in 0 until size) {
                    yieldAll((0 until size).map { i to it })
                }
            }
        }

        fun solve(src: String): Pair<Int, Int> {
            val grid = Grid(
                Input.linesFrom(src)
                    .flatMap { it.asSequence().map { it.code - 48 } }
                    .toList())
            return Pair(
                grid.coordinates().count { (i, j) -> grid.isVisible(i, j) },
                grid.coordinates().maxOf { (i, j) -> grid.score(i, j) }
            )
        }
    }

    override fun execute() {
        val (p1, p2) = solve(input)
        println("part 1: $p1")
        println("part 2: $p2")
    }
}