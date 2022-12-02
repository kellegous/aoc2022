use std::{
    cmp::Reverse,
    collections::BinaryHeap,
    error::Error,
    fs,
    io::{BufRead, BufReader},
    path::Path,
};

#[derive(clap::Args, Debug)]
pub struct Args {
    #[arg(long, default_value_t = String::from("data/day01/input.txt"))]
    input: String,
}

fn read_elves<P: AsRef<Path>>(src: P) -> Result<Vec<Vec<i32>>, Box<dyn Error>> {
    let mut elves = vec![Vec::new()];
    for line in BufReader::new(fs::File::open(src)?).lines() {
        let line = line?;
        if line.is_empty() {
            elves.push(Vec::new());
        } else {
            elves.last_mut().unwrap().push(line.parse::<i32>()?);
        }
    }

    Ok(elves)
}

fn top_k(iter: impl Iterator<Item = i32>, k: usize) -> Vec<i32> {
    // totally not needed but fun anyway. 😜
    let mut h = BinaryHeap::new();
    for item in iter {
        h.push(Reverse(item));
        if h.len() > k {
            h.pop();
        }
    }
    h.into_iter().map(|Reverse(x)| x).collect()
}

pub fn solve(args: &Args) -> Result<(), Box<dyn Error>> {
    let elves = read_elves(&args.input)?;
    println!(
        "part 1: {}",
        elves.iter().map(|e| e.iter().sum::<i32>()).max().unwrap()
    );
    println!(
        "part 2: {}",
        top_k(elves.iter().map(|e| e.iter().sum::<i32>()), 3)
            .iter()
            .sum::<i32>()
    );

    Ok(())
}
