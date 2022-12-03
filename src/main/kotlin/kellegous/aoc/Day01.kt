@file:OptIn(ExperimentalCli::class)

package kellegous.aoc

import kotlinx.cli.*
import java.io.BufferedReader
import java.util.*

class Day01 : Subcommand("day01", "Day 1") {
    val input by Input.optionFor(this)

    private fun topK(vals: List<Int>, k: Int) = PriorityQueue<Int>().apply {
        for (item in vals) {
            add(item)
            if (size > k) {
                poll()
            }
            toList()
        }
    }

    override fun execute() {
        val elves = BufferedReader(Input.readerFrom(input)).lineSequence().fold(mutableListOf(0)) { acc, line ->
            if (line.isEmpty()) {
                acc.add(0)
            } else {
                acc[acc.size - 1] += line.toInt()
            }
            acc
        }.toList()
        println("part 1: ${elves.max()}")
        println("part 2: ${topK(elves, 3).sum()}")
    }
}