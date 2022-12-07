@file:OptIn(ExperimentalCli::class)

package kellegous.aoc

import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand

class Day06 : Subcommand("day06", "Day 6") {
    private val input by Input.optionFor(this)

    companion object {
        fun distinctOffsetOf(s: String, size: Int) = s.windowedSequence(size)
            .indexOfFirst {
                it.toSet().size == size
            } + size
    }

    override fun execute() {
        val sig = Input.textFrom(input)
        println("part 1: ${distinctOffsetOf(sig, 4)}") // 1848
        println("part 2: ${distinctOffsetOf(sig, 14)}")
    }
}