import java.util.ArrayList;



public class Circuit {


	//H equals hand to eye coordination
	//E equals endurance
	//P equals pizzazz
	private int cHandToEye;
	private int cEndurance;
	private int cPizzazz;
	private final  int circuitID;
	
	//	1 = free ; 0 = joined a team
	private int cAvailability = 1;
	
	/**
	 * This holds the Jugglers assigned to a circuit.
	 */
	public ArrayList<Juggler> team;
	
	/**
	 * This is the class the circuit objects are constructed from. Each instance of the circuit class is assigned an id. 
	 * @param id The identifier assigned to each circuit object. 
	 */
	public Circuit(int id){
			circuitID = id;
			team = new ArrayList<Juggler>();
		}
	/**Retrieve the availability status of a circuit.
	 * 
	 * @return The value signifying whether the circuits team has a vacancy.
	 * <p> 1 means there is a vacancy on the team.</p>
	 * <p> 0 means that there is no opening.</p>
	 * 
	 */
	public int getcAvailability() {
		
		return cAvailability;
	}
	/** Used to change the 
	 * 
	 */
	public void setcAvailability(int cAvailability) {
	
		this.cAvailability = cAvailability;
	}
	
	public int getId(){
		return circuitID;
	}

	public int getcHandToEye() {
		return cHandToEye;
	}
	public void setcHandToEye(int cHandToEye) {
		this.cHandToEye = cHandToEye;
	}
	public int getcEndurance() {
		return cEndurance;
	}
	public void setcEndurance(int cEndurance) {
		this.cEndurance = cEndurance;
	}
	public int getcPizzazz() {
		return cPizzazz;
	}
	public void setcPizzazz(int cPizzazz) {

		this.cPizzazz = cPizzazz;
	}
	
	public void sortTeam(){
		
		for(int i1 = 0; i1 < team.size()-1; i1++){
			if(((Juggler) team.get(i1)).calculateDotProduct(this) < ((Juggler) team.get(i1+1)).calculateDotProduct(this)){
				Juggler temp = team.get(i1);
				//since the current is less than the next
				//current is set to the next
				team.set(i1, team.get(i1+1));
				//next is set to the current
				team.set(i1+1, temp);
			}
		}
	}
	public int testIfOnTeam(Juggler j){
		for(int i = 0; i < team.size(); i++){
			if(team.get(i).getId() == j.getId()){
				return 1;
			}
		}
		return 0;
	}
	public void printTeam(){
		System.out.print("C" + circuitID);
		for(int i =0; i < team.size(); i++){
			
			team.get(i).printPreferences(team.size());
			
		}
	}
	public String toString(){
		return ("C" + circuitID  + " H:" + cHandToEye + " E:" + cEndurance + " P:" + cPizzazz);
	}
	
}
