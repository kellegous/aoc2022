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

fn read_elves<P: AsRef<Path>>(src: P) -> Result<Vec<i32>, Box<dyn Error>> {
    let mut elves = vec![0];
    for line in BufReader::new(fs::File::open(src)?).lines() {
        let line = line?;
        if line.is_empty() {
            elves.push(0);
        } else {
            *elves.last_mut().unwrap() += line.parse::<i32>()?;
        }
    }
    Ok(elves)
}

fn top_k<T: Ord + Copy>(vals: &[T], k: usize) -> Vec<T> {
    let mut h = BinaryHeap::new();
    for i in vals {
        h.push(Reverse(*i));
        if h.len() > k {
            h.pop();
        }
    }
    h.into_iter().map(|Reverse(x)| x).collect()
}

pub fn solve(args: &Args) -> Result<(), Box<dyn Error>> {
    let elves = read_elves(&args.input)?;
    println!("part 1: {}", elves.iter().max().unwrap());
    println!("part 2: {}", top_k(&elves, 3).iter().sum::<i32>());
    Ok(())
}
