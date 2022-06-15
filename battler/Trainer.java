package battler;
import java.util.ArrayList;
import java.util.Scanner;

public class Trainer {
	public ArrayList<Pokemon> team = new ArrayList<Pokemon>();
	public boolean won = false;
	public int currentPokemon;
	
	public Pokemon getCurrentMon() {
		return team.get(currentPokemon - 1);
	}
	
	public Move getMoveFromCurrentMon(int id) {
		return team.get(currentPokemon - 1).moves.get(id);
	}
	
	
	//Implement Switch Pokemon Function
	public void switchPokemon() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please select a pokemon to switch to.");
		for(int i = 0; i != team.size(); i++) {
			System.out.print(i + 1 + ": ");
			System.out.println(team.get(i));
		}
		int selectedMon = input.nextInt();
		if(team.get(selectedMon - 1).HP > 0) {
			currentPokemon = selectedMon;
		}
		else{
			System.out.println("Couldn't switch pkmn!");
		}
	}
	
	public boolean didLose() {
		int numberOfKOdPokemon = 0;
		for(int i = 0; i != team.size(); i++) {
			if(team.get(i).HP <= 0){
				numberOfKOdPokemon++;
			}
		}
		if(numberOfKOdPokemon == team.size()) {
			return true;
		}
		else {
			return false;
		}
	}
}
