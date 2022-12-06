@file:OptIn(ExperimentalCli::class)

package kellegous.aoc

import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand

val pattern = """move (\d+) from (\d+) to (\d+)""".toRegex()

private fun parseStacks(lines: Iterator<String>): List<ArrayDeque<Char>> {
    val first = lines.next()
    val n = (first.length + 1) / 4
    val stacks = (0 until n).map { ArrayDeque<Char>() }.toList()
    for (line in iterator {
        yield(first)
        yieldAll(lines)
    }) {
        if (line.isEmpty()) {
            break
        }
        for (i in 0 until n) {
            val ix = i * 4
            if (line[ix] == '[') {
                stacks[i].addFirst(line[ix + 1])
            }
        }
    }
    return stacks
}

private data class Op(val count: Int, val from: Int, val to: Int) {
    companion object {
        fun parse(s: String): Op {
            val m = pattern.matchEntire(s) ?: throw RuntimeException("invalid input")
            return Op(
                m.groupValues[1].toInt(),
                m.groupValues[2].toInt() - 1,
                m.groupValues[3].toInt() - 1
            )
        }
    }
}

private fun process(
    src: String,
    fn: (stacks: List<ArrayDeque<Char>>, op: Op) -> Unit
): String {
    val lines = Input.linesFrom(src).iterator()
    val stacks = parseStacks(lines)
    lines.forEach { fn(stacks, Op.parse(it)) }
    return stacks.map { it.last() }.joinToString("")
}

class Day05 : Subcommand("day05", "Day 5") {
    private val input by Input.optionFor(this)

    override fun execute() {
        val p1 = process(input) { stacks, op ->
            op.apply {
                repeat(count) { stacks[to].addLast(stacks[from].removeLast()) }
            }
        }
        println("part 1: $p1")

        val p2 = process(input) { stacks, op ->
            op.apply {
                val stack = ArrayDeque<Char>()
                repeat(count) { stack.addLast(stacks[from].removeLast()) }
                repeat(count) { stacks[to].addLast(stack.removeLast()) }
            }
        }
        println("part 2: $p2")
    }
}