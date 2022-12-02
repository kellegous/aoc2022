use std::{cmp::Reverse, error::Error, fs};

#[derive(clap::Args, Debug)]
pub struct Args {
    #[arg(long, default_value_t = String::from("data/day01/input.txt"))]
    input: String,
}

pub fn solve(args: &Args) -> Result<(), Box<dyn Error>> {
    let data = fs::read_to_string(&args.input)?;
    let mut elves = data
        .split("\n\n")
        .map(|e| {
            e.split("\n").fold(Ok(0), |sum, l| {
                sum.and_then(|v| l.parse::<i32>().and_then(|x| Ok(x + v)))
            })
        })
        .collect::<Result<Vec<_>, _>>()?;

    elves.sort_by_key(|w| Reverse(*w));
    println!("part 1: {}", elves.first().unwrap());
    println!("part 2: {}", elves.iter().take(3).sum::<i32>());

    Ok(())
}
