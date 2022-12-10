@file:OptIn(ExperimentalCli::class)

package kellegous.aoc

import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sign

class Day09 : Subcommand("day09", "Day 9") {
    private val input by Input.optionFor(this)

    companion object {
        private class Particle(var x: Int = 0, var y: Int = 0) {
            private val observers = mutableListOf<(Particle) -> Unit>()

            fun observe(fn: (Particle) -> Unit) {
                observers.add(fn)
            }

            fun moveBy(dx: Int, dy: Int) {
                x += dx
                y += dy
                observers.forEach { it(this) }
            }

            fun distTo(b: Particle) = max(
                abs(b.x - x),
                abs(b.y - y)
            )

            fun follow(p: Particle) {
                p.observe {
                    if (distTo(p) > 1) {
                        moveBy((p.x - x).sign, (p.y - y).sign)
                    }
                }
            }

            fun asPair() = Pair(x, y)
        }

        fun solve(src: String): Pair<Int, Int> {
            val head = Particle()

            val p1 = mutableSetOf(Pair(0, 0))
            Particle().apply {
                follow(head)
                observe { p1.add(it.asPair()) }
            }

            val p2 = mutableSetOf(Pair(0, 0))
            (1..9).fold(head) { p, i ->
                Particle().apply { follow(p) }
            }.observe { p2.add(it.asPair()) }

            Input.linesFrom(src)
                .map { line ->
                    line.substringBefore(" ").let { Pair(it, line.substring(it.length + 1).toInt()) }
                }
                .forEach { (cmd, n) ->
                    when (cmd) {
                        "R" -> repeat(n) { head.moveBy(1, 0) }
                        "L" -> repeat(n) { head.moveBy(-1, 0) }
                        "U" -> repeat(n) { head.moveBy(0, 1) }
                        "D" -> repeat(n) { head.moveBy(0, -1) }
                    }
                }

            return Pair(p1.size, p2.size)
        }
    }

    override fun execute() {
        val (p1, p2) = solve(input)
        println("part 1: $p1")
        println("part 2: $p2")
    }
}