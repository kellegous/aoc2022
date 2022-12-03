mod day01;

use clap::{Parser, Subcommand};
use std::error::Error;

#[derive(Parser, Debug)]
struct Args {
    #[command(subcommand)]
    command: Command,
}

#[derive(Subcommand, Debug)]
enum Command {
    Day01(day01::Args),
}

impl Command {
    fn solve(&self) -> Result<(), Box<dyn Error>> {
        match self {
            Command::Day01(args) => day01::solve(args),
        }
    }
}

fn main() -> Result<(), Box<dyn Error>> {
    let args = Args::parse();
    args.command.solve()?;
    Ok(())
}
