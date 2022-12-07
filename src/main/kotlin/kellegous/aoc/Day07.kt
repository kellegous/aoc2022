@file:OptIn(ExperimentalCli::class)

package kellegous.aoc

import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand
import java.nio.file.Path
import java.nio.file.Paths

class Day07 : Subcommand("day07", "Day 7") {
    private val input by Input.optionFor(this)

    companion object {
        private fun parseEntry(lines: ArrayDeque<String>) =
            sequence {
                while (lines.isNotEmpty() && !lines.first().startsWith("$")) {
                    val line = lines.removeFirst()
                    if (!line.startsWith("dir ")) {
                        yield(line)
                    }
                }
            }.sumOf { it.substringBefore(" ").toInt() }

        private fun parentsOf(p: Path) = sequence {
            var n = p
            while (n != p.root) {
                yield(n)
                n = n.parent
            }
            yield(n)
        }

        private fun parse(
            lines: ArrayDeque<String>,
            cwd: Path,
            fs: MutableMap<Path, Int>
        ): Map<Path, Int> {
            if (lines.isNotEmpty()) {
                val cmd = lines.removeFirst()
                if (cmd == "$ ls") {
                    val size = parseEntry(lines)
                    parentsOf(cwd).forEach { fs.merge(it, size, Int::plus) }
                    return parse(
                        lines,
                        cwd,
                        fs
                    )
                } else if (cmd.startsWith("$ cd ")) {
                    return parse(
                        lines,
                        cwd.resolve(cmd.substring(5)).normalize(),
                        fs
                    )
                }
            }
            return fs
        }

        fun solve(src: String): Pair<Int, Int> {
            val lines = ArrayDeque<String>().apply {
                Input.linesFrom(src).forEach { addLast(it) }
            }
            require(lines.removeFirst() == "$ cd /")
            val fs = parse(
                lines,
                Paths.get("/"),
                mutableMapOf<Path, Int>().withDefault { 0 }
            )

            val p1 = fs.values.asSequence().filter { it <= 100000 }.sum()

            val needed = fs[Paths.get("/")]!! - 40000000
            val p2 = fs.values.asSequence().filter { it >= needed }.min()
            return Pair(p1, p2)
        }
    }

    override fun execute() {
        val (p1, p2) = solve(input)
        println("part 1: $p1")
        println("part 2: $p2")
    }
}