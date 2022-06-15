package battler;

public class Move {
	public String name;
	public int power;
	public int accuracy;
	public int type;
	
	public Move() {}
	
	public Move(String name, int power, int accuracy, int moveType) {
		this.name = name;
		this.power = power;
		this.accuracy = accuracy;
		this.type = moveType;
	}
	
	public String toString() {
		return name + " Power: " + power + " Acccuracy: " + accuracy + " Type: " + mainGame.moveIntToString(type); 
	}
}
