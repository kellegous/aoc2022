@file:OptIn(ExperimentalCli::class)

package kellegous.aoc

import kotlinx.cli.ArgParser
import kotlinx.cli.ExperimentalCli

fun main(args: Array<String>) {
    val parser = ArgParser(
        "aoc",
        strictSubcommandOptionsOrder = true,
        prefixStyle = ArgParser.OptionPrefixStyle.GNU
    )
    parser.subcommands(
        Day01(),
        Day02(),
        Day03(),
        Day04(),
        Day05(),
        Day06(),
        Day07(),
    )
    parser.parse(args)
}