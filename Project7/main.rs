extern crate rand;
use std::io;
use rand::Rng;
use std::string::String;


#[derive(Copy, Clone)]
	
	//Create enum array
	enum Options { Rock, 
				 Paper,
				 Scissors
	}
	
	struct PlayerStats {
		num_rocks: u32,
		num_scissors: u32,
		num_papers: u32
	}
	
	struct WinStats {
		num_wins: f32,
		num_loss: f32,
		num_ties: f32,
		total_rounds: f32
	}
	
	


fn main() {
	
	//Create instance of PlayerStats
	let mut ps: PlayerStats = PlayerStats { num_rocks: 0, num_papers: 0, num_scissors: 0};
	
	//Create instance of WinStats
	let mut ws: WinStats = WinStats {num_wins: 0.0, num_loss: 0.0, num_ties: 0.0, total_rounds: 0.0};
	
	//Create instance of Options
	let mut op: Options;
	let mut comp_choice: Options;
	
	
	
	loop{
	
	println!("Enter choice (r,p,s) or q to quit >");
	
	//need to create new string. Otherwise match function fails after the first iteration
	let mut player_choice = String::new();
	io::stdin().read_line(&mut player_choice).expect("Failed to read line");

	//Process player input
	match player_choice.trim() {
					"r" => {	op = Options::Rock;
								println!("Player chose: Rock");
								ps.num_rocks+=1;
							},
					"p" => {	op = Options::Paper;
								println!("Player chose: Paper");
								ps.num_papers+=1;
							},
					"s" => {	op = Options::Scissors;
								println!("Player chose: Scissors");
								ps.num_scissors+=1;
							},
					"q" =>	{	print_rps(&ps);
								break;
							},
					_ =>	{
								println!("Invalid Input");
								continue;
							}
					
				}//match
				
				comp_choice = gen_comp_choice();
				victor(comp_choice, op, &mut ws);
				
	}//while loop
	
	print_wl(&mut ws);
				
}//main

//Print the number of rocks, papers, and scissors chosen by the player
fn print_rps(ps: &PlayerStats) {

	println!("Choice Statistics:");
	println!("Rock: {}", ps.num_rocks);
	println!("Paper: {}", ps.num_papers);
	println!("Scissors: {}", ps.num_scissors);

}//print_rps



//Generate Computer Choice
fn gen_comp_choice() -> Options {
		
		let choice = rand::thread_rng().gen_range(0,3);
		match choice {
			0 => return Options::Rock,
			1 => return Options::Paper,
			_ => return Options::Scissors
		}


}//gen_comp_choice

//Determine Winner
fn victor(comp: Options, user: Options, ws: &mut WinStats) {

	match user {
		Options::Rock => {
			match comp {
				Options::Rock => {
					println!("Computer chose: Rock");
					println!("Tie!");
					ws.num_ties+=1.0;
								 },
				Options::Paper =>{
					println!("Computer chose: Paper");
					println!("You Lose!");
					ws.num_loss+=1.0;
								 },
				Options::Scissors =>{
					println!("Computer chose: Scissors");
					println!("You Win!");
					ws.num_wins+=1.0;
									},
			}		
					},
		Options::Paper => match comp {
						Options::Rock => {
							println!("Computer chose: Rock");
							println!("You Win!");
							ws.num_wins+=1.0;
						 },
						Options::Paper =>{
							println!("Computer chose: Paper");
							println!("Tie!");
							ws.num_ties+=1.0;
						 },
						Options::Scissors =>{
							println!("Computer chose: Scissors");
							println!("You Lose!");
							ws.num_loss+=1.0;
						 },
					},
		Options::Scissors => match comp {
							Options::Rock => {
								println!("Computer chose: Rock");
								println!("You Lose!");
								ws.num_loss+=1.0;
							 },
							Options::Paper =>{
								println!("Computer chose: Paper");
								println!("You Win!");
								ws.num_wins+=1.0;
							 },
							Options::Scissors =>{
								println!("Computer chose: Scissors");
								println!("Tie!");
								ws.num_ties+=1.0;
							 },

					},
	}
	
	//Increment number of total rounds
	ws.total_rounds+=1.0;

}//victor

//Print Win Loss Statistics
fn print_wl(ws: &mut WinStats) {
	
	if ws.total_rounds==0.0 {
		ws.total_rounds=1.0;
	}
	
	let win_per = (ws.num_wins / ws.total_rounds) *100.0;
	let loss_per = (ws.num_loss / ws.total_rounds) *100.0;
	let tie_per = (ws.num_ties / ws.total_rounds)*100.0;
	
	
	
	
	println!("Wins: {} ({:.2}%)",ws.num_wins,win_per);
	println!("Losses: {} ({:.2}%)",ws.num_loss,loss_per);
	println!("Ties: {} ({:.2}%)",ws.num_loss,tie_per);
	

}//print_wl
