package PokemonBattler;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class mainGame {
	
	public static ArrayList<Pokemon> allMons = new ArrayList<Pokemon>();
	public static ArrayList<Move> moves = new ArrayList<Move>();
	
	public static void main(String[] args) {
		//Create the two players.
		Trainer player1 = new Trainer();
		Trainer player2 = new Trainer();
		//Importing all moves from the moves.txt file in the Project Root. 
	    try {
	    	ArrayList<String> strings = new ArrayList<String>();
	        File myObj = new File("moves.txt");
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          String data = myReader.nextLine();
	          strings.add(data);
	        }
	        myReader.close();
	        
	        for(int i = 0; i != strings.size(); i++) {
	        	//Changing the move from a string into usable object.
	        	String[] moveStr = strings.get(i).split(";");
	        	Move move = new Move();
	        	move.name = capitalizeWord(moveStr[1].replace("-", " "));
	        	try {
	        		//Some moves don't have power or accuracy, those moves have been removed until I decide to add the functionality for them
		        	move.accuracy = Integer.parseInt(moveStr[2]);
		        	move.power = Integer.parseInt(moveStr[3]);
	        	}
	        	catch (NumberFormatException e) {
	        		move.accuracy = 0;
	        		move.power = 0;
	        	}
	        	move.type = TypeToNum(moveStr[4]);
	        	if(move.type != 0 && move.power != 0 && move.accuracy != 0) {
	        		moves.add(move);
	        		//System.out.println(move);
	        	}
	        }
	      } 
	    catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
	    
	    //Importing all pokemon
	    try {
	    	ArrayList<String> strings = new ArrayList<String>();
	        File myObj = new File("pokemon.txt");
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          String data = myReader.nextLine();
	          strings.add(data);
	        }
	        myReader.close();
	        
	        for(int i = 0; i != strings.size(); i++) {
	        	//Changing the move from a string into usable object.
	        	String[] pokemonStr = strings.get(i).split(";");
	        	Pokemon mon = new Pokemon();
	        	mon.id = Integer.parseInt(pokemonStr[0]);
	        	mon.name = pokemonStr[1];
	        	if(pokemonStr[2].equals(pokemonStr[3])) { //One type pokemon
	        		mon.type = Integer.toString(TypeToNum(pokemonStr[2]));
	        	}
	        	else {
	        		mon.type = Integer.toString(TypeToNum(pokemonStr[2]));
	        		mon.type += "," + Integer.toString(TypeToNum(pokemonStr[3]));
	        	}
	        	mon.HP = Integer.parseInt(pokemonStr[4]); 
	        	mon.attack = Integer.parseInt(pokemonStr[4]); 
	        	mon.defense = Integer.parseInt(pokemonStr[4]); 
	        	mon.spAttack = Integer.parseInt(pokemonStr[4]); 
	        	mon.spDefense = Integer.parseInt(pokemonStr[4]);
	        	mon.speed = Integer.parseInt(pokemonStr[4]);
	        	allMons.add(mon);
	        }
	      } 
	    catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
	    System.out.println(allMons.size() + " Pokemon Loaded!");
	    System.out.println(moves.size() + " Moves Loaded!");
	    Scanner input = new Scanner(System.in);
		//Team Builder
	    //Pokemon Selection goes here. 
	    System.out.println("Player 1. Please select your pokemon! Please enter the NATIONAL dex number of the pokemon.");
	    System.out.println("\nIf you dont know what the national dex is, go check out \nhttps://bulbapedia.bulbagarden.net/wiki/List_of_Pok%C3%A9mon_by_National_Pok%C3%A9dex_number\n and enter the ndex number\n");
	    for(int i = 0; i != 6; i++) {
	    	System.out.println("Please select a pokemon! (Enter the dex num)\n(You can enter 0 to finish selecting pokemon)\n(You can enter -1 to select a random pokemon!):");
	    	int selection = input.nextInt();
	    	if(selection == 0) {
	    		break;
	    	}
	    	if(selection == -1) {
	    		Random rand = new Random();
	    		selection = rand.nextInt(898);
	    	}
	    	player1.team.add(allMons.get(selection-1));
	    	System.out.println("You Selected: " + allMons.get(selection-1).name);
	    }
	    System.out.println("Player 2. Please select your pokemon! Please enter the NATIONAL dex number of the pokemon.");
	    for(int i = 0; i != 6; i++) {
	    	System.out.println("Please select a pokemon! (Enter the dex num) (You can enter 0 to finish selecting pokemon)\n(You can enter -1 to select a random pokemon!):");
	    	int selection = input.nextInt();
	    	if(selection == 0) {
	    		break;
	    	}
	    	if(selection == -1) {
	    		Random rand = new Random();
	    		selection = rand.nextInt(898);
	    	}
	    	player2.team.add(allMons.get(selection-1));
	    	System.out.println("You Selected: " + allMons.get(selection-1).name);
	    }
	    
	    //Pokemon Move Selection for player 1
		System.out.println("Player 1, You will now select your moves for your pokemon! Press enter to continue.");
		input.nextLine();
		for(int i = 0; i != moves.size(); i++) {
			System.out.print(i + 1 + ": ");
			System.out.println(moves.get(i));
		}
		System.out.println("Please refer back to the Move List above to enter the 4 moves for your pokemon.");
	    for(int mon = 0; mon != player1.team.size(); mon++) {
	    	System.out.println("Now selecting moves for your " + player1.team.get(mon).name);
	    	for(int slot = 0; slot != 4; slot++) {
	    		System.out.println("Please enter the move NUMBER you want to select");
	    		int selection = input.nextInt();
	    		if(selection == 0) {
	    			break;
	    		}
	    		player1.team.get(mon).moves.add(moves.get(selection - 1));
	    		System.out.println("You selected " + moves.get(selection - 1).name);
	    	}
	    }
		//Pokemon Move Selection for player 2
		System.out.println("Player 2, You will now select your moves for your pokemon! Press enter to continue.");
		input.nextLine();
		input.nextLine();
		for(int i = 0; i != moves.size(); i++) {
			System.out.print(i + 1 + ": ");
			System.out.println(moves.get(i));
		}
		System.out.println("Please refer back to the Move List above to enter the 4 moves for your pokemon.");
	    for(int mon = 0; mon != player2.team.size(); mon++) {
	    	System.out.println("Now selecting moves for your " + player2.team.get(mon).name);
	    	for(int slot = 0; slot != 4; slot++) {
	    		System.out.println("Please enter the move NUMBER you want to select");
	    		int selection = input.nextInt();
	    		if(selection == 0) {
	    			break;
	    		}
	    		player2.team.get(mon).moves.add(moves.get(selection - 1));
	    		System.out.println("You selected " + moves.get(selection - 1).name);
	    	}
	    }
		
		//TODO:
		//Import moves from the moves.txt file created by the python scripts - DONE
		//dupe the python script, and make a version for downloading all the pokemon. 
	    //Import all of those.
		//Add the Team Builder, to allow the player to add pokemon to their team. - Partially done
		//Test out (most) of the pokemon, make sure the game holds up, and fighting actually works - Fighting works
		

		
		
		System.out.println("PLEASE SELECT YOUR STARTING POKEMON!");
		System.out.println("Player 1:");
		for(int i = 0; i != player1.team.size(); i++) {
			System.out.print(i + 1 + ": ");
			System.out.println(player1.team.get(i));
		}
		
		int selectedMon = input.nextInt();
		if(selectedMon > player1.team.size()) {
			player1.currentPokemon = 0;
		}
		else {
			player1.currentPokemon = selectedMon;
		}
		System.out.println("Player 2:");
		for(int i = 0; i != player2.team.size(); i++) {
			System.out.print(i + 1 + ": ");
			System.out.println(player2.team.get(i));
		}
		selectedMon = input.nextInt();
		if(selectedMon > player2.team.size()) {
			player2.currentPokemon = 0;
		}
		else {
			player2.currentPokemon = selectedMon;
		}
		
		
		while(player1.won != true && player2.won != true) {
			//Player 1's turn
			//Ask the user for what move they want to use
			System.out.println("Player 1! Please select a move to use. Press 5 to change pokemon!");
			System.out.println("Moves:");
			for(int i = 0; i != player1.getCurrentMon().moves.size(); i++) {
				System.out.print(i + 1 + ": ");
				System.out.println(player1.getCurrentMon().moves.get(i));
			}
			
			int selection = input.nextInt();
			if(selection == 5) {
				player1.switchPokemon();
			}
			else {
				float damage = player1.getCurrentMon().calculateDamage(player1.getMoveFromCurrentMon(selection - 1), player2.getCurrentMon().defense, player2.getCurrentMon().spDefense,player2.getCurrentMon().type);
				player2.getCurrentMon().HP -= damage;
				System.out.println(player2.getCurrentMon().name + " took " + damage + " damage");
				stateOfGame(player1,player2);
			}
			
			//In-between turn phase. Checks for multiple things.
			if(player1.didLose() == true || player2.didLose() == true) {
				if(player1.didLose() == true) {
					System.out.println("Player 2 WON!!!");
				}
				else {
					System.out.println("Player 1 WON!!!");
				}
				break;
			}
			
			//Player 2's Turn
			System.out.println("Player 2! Please select a move to use");
			System.out.println("Moves:");
			for(int i = 0; i != player2.getCurrentMon().moves.size(); i++) {
				System.out.print(i + 1 + ": ");
				System.out.println(player2.getCurrentMon().moves.get(i));
			}
			selection = input.nextInt();
			float damage = player2.getCurrentMon().calculateDamage(player2.getMoveFromCurrentMon(selection - 1), player1.getCurrentMon().defense, player1.getCurrentMon().spDefense,player1.getCurrentMon().type);
			player1.getCurrentMon().HP -= damage;
			System.out.println(player1.getCurrentMon().name + " took " + damage + " damage");
			stateOfGame(player1,player2);
			
			//Another In Between Turn Phase
			if(player1.didLose() == true || player2.didLose() == true) {
				if(player1.didLose() == true) {
					System.out.println("Player 2 WON!!!");
				}
				else {
					System.out.println("Player 1 WON!!!");
				}
				break;
			}
		}
	}
	
	public static Move getMove(int id) {
		return moves.get(id);
	}
	
	public static void stateOfGame(Trainer player1, Trainer player2) {
		Pokemon p1 = player1.getCurrentMon();
		Pokemon p2 = player2.getCurrentMon();
		System.out.println("\n\nPlayer 1's Pokemon: " + p1.name + " HP: " + p1.HP);
		System.out.println("Player 2's Pokemon: " + p2.name + " HP: " + p2.HP + "\n\n");
		if(p1.HP <= 0) {
			player1.switchPokemon();
		}
		if(p2.HP <= 0) {
			player2.switchPokemon();
		}
	}
	
	public static String capitalizeWord(String str){  
	    String words[]=str.split("\\s");  
	    String capitalizeWord="";  
	    for(String w:words){  
	        String first=w.substring(0,1);  
	        String afterfirst=w.substring(1);  
	        capitalizeWord+=first.toUpperCase()+afterfirst+" ";  
	    }  
	    return capitalizeWord.trim();  
	}
	
	//Converts a string Pokemon Type (Flying, fighting, etc...) into the integer defined in the LOG
	public static int TypeToNum(String name) {
		switch(name) {
		case "normal":
			return 1;
		case "fighting":
			return 2;
		case "flying":
			return 3;
		case "poison":
			return 4;
		case "ground":
			return 5;
		case "rock":
			return 6;
		case "bug":
			return 7;
		case "ghost":
			return 8;
		case "steel":
			return 9;
		case "fire":
			return 10;
		case "water":
			return 11;
		case "grass":
			return 12;
		case "electric":
			return 13;
		case "psychic":
			return 14;
		case "ice":
			return 15;
		case "dragon":
			return 16;
		case "dark":
			return 17;
		case "fairy":
			return 18;
		}
		return 0;
	}
	//Converts an int Pokemon type, and converts it into the string. For players to read. 
	public static String moveIntToString(int move) {
		switch(move) {
		case 1:
			return "Normal";
		case 2:
			return "Fighting";
		case 3:
			return "Flying";
		case 4:
			return "Poison";
		case 5:
			return "Ground";
		case 6:
			return "Rock";
		case 7:
			return "Bug";
		case 8:
			return "Ghost";
		case 9:
			return "Steel";
		case 10:
			return "Fire";
		case 11:
			return "Water";
		case 12:
			return "Grass";
		case 13:
			return "Electric";
		case 14:
			return "Psychic";
		case 15:
			return "Ice";
		case 16:
			return "Dragon";
		case 17:
			return "Dark";
		case 18:
			return "Fairy";
		}
		return "MissingNO.";
	}

}
