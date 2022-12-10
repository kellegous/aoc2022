@file:OptIn(ExperimentalCli::class)

package kellegous.aoc

import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand
import kotlin.math.abs

class Day10 : Subcommand("day10", "Day 10") {
    private val input by Input.optionFor(this)

    companion object {
        private fun parseInstruction(s: String) = s.substringBefore(' ').let {
            when (it) {
                "noop" -> sequenceOf(0)
                "addx" -> sequenceOf(0, s.substring(it.length + 1).toInt())
                else -> throw IllegalArgumentException(s)
            }
        }

        private fun process(instructions: Sequence<Int>) = sequence {
            var x = 1
            yield(x)
            for (instruction in instructions) {
                x += instruction
                yield(x)
            }
        }

        fun solve(src: String, separatorSoYouCanReadTheShit: String = ""): Pair<Int, String> {
            val instructions = Input.linesFrom(src)
                .flatMap(::parseInstruction)
                .toList()

            val p1 = process(instructions.asSequence())
                .withIndex()
                .map { (i, v) -> Pair(i + 1, v) }
                .takeWhile { (i, v) -> i <= 220 }
                .filter { (i, v) -> (i - 20) % 40 == 0 }
                .sumOf { (i, v) -> i * v }

            val p2 = process(instructions.asSequence())
                .take(6 * 40).withIndex()
                .map { (i, v) ->
                    if (abs((i % 40) - v) < 2) {
                        "#"
                    } else {
                        "."
                    }
                }.chunked(40).map {
                    it.joinToString(separatorSoYouCanReadTheShit)
                }.joinToString("\n")
            return Pair(p1, p2)
        }
    }

    override fun execute() {
        val (p1, p2) = solve(input, " ")
        println("part 1: $p1")
        println("part 2:\n$p2")
    }
}