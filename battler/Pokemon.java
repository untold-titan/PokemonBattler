package battler;

import java.util.ArrayList;

public class Pokemon {
	public int id;
	public String name;
	public String type;
	public int HP;
	public int attack;
	public int defense;
	public int spAttack;
	public int spDefense;
	public int speed;
	public ArrayList<Move> moves;
	
	public Pokemon() {
		moves = new ArrayList<Move>();
	}
	
	public Pokemon(int id, String name, String type, String weakTo, String resist, int HP,int attack, int defense, int spAttack, int spDefense, int speed) {
		this.attack = attack;
		this.id = id;
		this.HP = HP;
		this.defense = defense;
		this.spAttack = spAttack;
		this.spDefense = spDefense;
		this.speed = speed;
		this.name = name;
		this.type = type;
		this.moves = new ArrayList<Move>();
	}
	
	public String toString() {
		return name + "  HP: " + HP;
	}
	
	public int calculateDamage(int moveID,int defense, int spDefense) {
		Move move = mainGame.getMove(moveID);
		int effective = 1;
		System.out.println();
		float damage = (((42 * move.power * (attack/defense))/50) + 2) * effective * (float)(Math.random() * 0.15 + 0.85);
		return (int) damage;
		
	}
	
	public int calculateDamage(Move move ,int defense, int spDefense, String defenderType ) {
		System.out.println();
		int pkmnType1 = 0;
		int pkmnType2 = 0;
		try { //Try to parse opposing PKMN type
			pkmnType1 = Integer.parseInt(defenderType);
		}
		catch (NumberFormatException e){
			String[] types = defenderType.split(",");
			pkmnType1 = Integer.parseInt(types[0]);
			pkmnType2 = Integer.parseInt(types[1]);
		}
		float effective = effectiveCheck(move.type,pkmnType1);
		if(pkmnType2 != 0) {
			effective = (effective + effectiveCheck(move.type,pkmnType2)) / 2;
			if(effective == 0.25) { //0.5 + 0.5
				effective = 0;
			}
			if(effective == 0.75f) { //0.5 + 1
				effective = 0.5f;
			}
			if(effective == 1.25f) { //0.5 + 2
				effective = 1;
			}
			if(effective == 1.5f) { //1 + 2
				effective = 2;
			}
		}
		
		if(effective == 0.5f) {
			System.out.println("Its not very effectve.");
		}
		if(effective == 1) {
			System.out.println("It's effective");
		}
		if(effective == 2) {
			System.out.println("It's super effective!!");
		}
		if(effective == 0) {
			System.out.println("It has no effect...");
		}
		float damage = (((42 * move.power * (attack/defense))/50) + 2) * effective * (float)(Math.random() * 0.15 + 0.85);
		return (int) damage;
		
	}
	//attackerType = move type, defenderType = pokemon type. 
	//For dual typed pokemon, run this function twice, with each type put into defender type, and average, and round.
	public float effectiveCheck(int attackerType, int defenderType) {
		switch(defenderType) {
		case 1: //Normal
			if(attackerType == 2) { //Fighting - super effective
				return 2;
			}
			if(attackerType == 8) { //Ghost - no effect
				return 0;
			}
			break;
		case 2: //Fighting
			if(attackerType == 3 || attackerType == 14 || attackerType == 18) { //Flying, Psychic and Fairy
				return 2;
			}
			if(attackerType == 6 || attackerType == 7 || attackerType == 17) { //Rock,Bug and Dark
				return 0.5f;
			}
			break;
		case 3: //Flying
			if(attackerType == 6 || attackerType == 13 || attackerType == 15) { //Rock,Electric and Ice
				return 2;
			}
			if(attackerType == 2 || attackerType == 7 || attackerType == 12) { //Fighting, Bug and Grass
				return 0.5f;
			}
			if(attackerType == 5) { //Ground. The earth cannot hurt birds
				return 0;
			}
			break;
		case 4: //Poison
			if(attackerType == 5 || attackerType == 14) { //Ground and Psychic
				return 2;
			}
			if(attackerType == 2 || attackerType == 4 || attackerType == 12 || attackerType == 18) { //Fighting, Poison, Grass and Fairy.
				return 0.5f;
			}
			break;
		case 5: //Ground
			if(attackerType == 11 || attackerType == 12 || attackerType == 15 ) { //Water, Grass and Ice
				return 2;
			}
			if(attackerType == 4 || attackerType == 6) { //Poison and Rock
				return 0.5f;
			}
			if(attackerType == 13) {
				return 0;
			}
			break;
		case 6: //Rock
			if(attackerType == 11 || attackerType == 12 || attackerType == 2 || attackerType == 5 || attackerType == 9) { //Fighting, Ground, Water, Ice and Steel
				return 2;
			}
			if(attackerType == 1 || attackerType == 10 || attackerType == 3 || attackerType == 4) {//Normal, Flying, Poison and F I R E
				return 0.5f;
			}
			break;
		case 7: //Bug
			if(attackerType == 3 || attackerType == 10 || attackerType == 6) { // Fire,Flying and Rock. All things, that kill bugs.
				return 2;
			}
			if(attackerType == 2 || attackerType == 5 || attackerType == 12) { //Fighting (I'd have thought this would trash bugs), Ground and Grass
				return 0.5f;
			}
			break;
		case 8: //Ghost.
			if(attackerType == 1 || attackerType == 2) {//Normal and Fighting. normal people and fighters are scared of ghosts.
				return 0;
			}
			if(attackerType == 8 || attackerType == 9) { //Ghost and Steel. Fight fire with fire. Or just board it out with a steel door
				return 2;
			}
			if(attackerType == 7 || attackerType == 4) { //Bug and Poison. You can't poison something thats already dead.
				return 0.5f;
			}
			break;
		case 9: //STEEL(IX)
			if(attackerType == 10 || attackerType == 2 || attackerType == 5) { //F I R E, Fighting and Ground. 
				return 2;
			}
			if(attackerType == 11 || attackerType == 13 || attackerType == 8 || attackerType == 17) { //Ghost, Water, Electric and Dark. 
				return 1;
			}
			if(attackerType == 4) {//Poison. WHY WOULD YOU TRY THIS!?
				return 0;
			}
			return 0.5f; //EVERYTHING ELSE IS USELESS AGAINST STEEL. WHY IS THIS TYPE SO GOOD?!
		case 10: //F I R E, My favorite type. 
			if(attackerType == 11 || attackerType == 5 || attackerType == 6) {
				return 2;
			}
			if(attackerType == 10 || attackerType == 12 || attackerType == 15 || attackerType == 9 || attackerType == 18) { //Thats a lot.
				return 0.5f;
			}
			break;
		case 11: //Watur
			if(attackerType == 12 || attackerType == 13) {
				return 2;
			}
			if(attackerType == 11 || attackerType == 10 || attackerType == 15 || attackerType == 9) {
				return 0.5f;
			}
			break;
		case 12: //Grass
			if(attackerType == 10 || attackerType == 15 || attackerType == 4 || attackerType == 4 || attackerType == 7) {
				return 2;
			}
			if(attackerType == 11 || attackerType == 12 || attackerType == 13) {
				return 0.5f;
			}
			break;
		case 13: //Z A P (Electric)
			if(attackerType == 5) { //Gotta ground the electricity
				return 2;
			}
			if(attackerType == 13 || attackerType == 4 || attackerType == 9) {
				return 0.5f;
			}
			break;
		case 14: //Psychic
			if(attackerType == 7 || attackerType == 8 || attackerType == 17) {
				return 2;
			}
			if(attackerType == 14 || attackerType == 2) {
				return 0.5f;
			}
		case 15: //Ice
			if(attackerType == 2 || attackerType == 15 || attackerType == 6 || attackerType == 9) {
				return 2;
			}
			if(attackerType == 15) {
				return 0.5f;
			}
		case 16://DRAGON DEEZ NUTS
			if(attackerType == 15 || attackerType == 16 || attackerType == 18) {
				return 2;
			}
			if(attackerType == 10 || attackerType == 11 || attackerType == 12 || attackerType == 13) {
				return 0.5f;
			}
		case 17: //Dark
			if(attackerType == 2 || attackerType == 7 || attackerType == 18) {
				return 2;
			}
			if(attackerType == 8 || attackerType == 17) {
				return 0.5f;
			}
			if(attackerType == 14) {
				return 0;
			}
		case 18://Fairy. This type was MADE to counter Dragons. and thats it
			if(attackerType == 4 || attackerType == 9) {
				return 2;
			}
			if(attackerType == 2 || attackerType == 7 || attackerType == 17) {
				return 0.5f;
			}
			if(attackerType == 16) {
				return 0;
			}
		}
		
		
		return 1;
	}
}