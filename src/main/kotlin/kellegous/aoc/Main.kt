@file:OptIn(ExperimentalCli::class)

package kellegous.aoc

import kotlinx.cli.ArgParser
import kotlinx.cli.ExperimentalCli

fun main(args: Array<String>) {
    val parser = ArgParser("aoc", strictSubcommandOptionsOrder = true)
    parser.subcommands(Day01(), Day02(), Day03())
    parser.parse(args)
}