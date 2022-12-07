@file:OptIn(ExperimentalCli::class)

package kellegous.aoc

import kotlinx.cli.*
import java.util.*

class Day01 : Subcommand("day01", "Day 1") {
    val input by Input.optionFor(this)

    companion object {
        fun solve(src: String): Pair<Int, Int> {
            val elves = Input.linesFrom(src)
                .fold(mutableListOf(0)) { acc, line ->
                    if (line.isEmpty()) {
                        acc.add(0)
                    } else {
                        acc[acc.size - 1] += line.toInt()
                    }
                    acc
                }.toList()
            return elves.max() to topK(elves, 3).sum()
        }

        private fun topK(values: List<Int>, k: Int) = PriorityQueue<Int>().apply {
            for (value in values) {
                add(value)
                if (size > k) {
                    poll()
                }
                toList()
            }
        }
    }

    override fun execute() {
        val (p1, p2) = solve(input)
        println("part 1: $p1")
        println("part 2: $p2")
    }
}