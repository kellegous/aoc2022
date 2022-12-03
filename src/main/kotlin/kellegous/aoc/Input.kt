@file:OptIn(ExperimentalCli::class)

package kellegous.aoc

import kotlinx.cli.ArgType
import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand
import kotlinx.cli.default
import java.io.IOException

object Input {
    private fun resourceOf(src: String) = this.javaClass.classLoader.getResource(src).let {
        if (it == null) {
            throw IOException("resource not found: $src")
        }
        it
    }

    fun readerFrom(src: String) = resourceOf(src).openStream().reader()

    fun textFrom(src: String) = resourceOf(src).readText()

    fun optionFor(cmd: Subcommand) = cmd.option(ArgType.String, "input", "i")
        .default("${cmd.name}/input.txt")
}