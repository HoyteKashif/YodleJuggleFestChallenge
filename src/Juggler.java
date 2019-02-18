
//Set the ID separate so that it is not needed
//to instantiate an object

public class Juggler {
	/*Attributes*/
	//H equals hand to eye coordination
	//E equals endurance
	//P equals pizzazz
	private int jHandToEye;
	private int jEndurance;
	private int jPizzazz;
	
	//left parameter is the id of the Circuit
	//Right parameter is the dot product of the Juggler ad Circuit 
	private int[][] jPreferList;
	
	private int jID;
	
	//	1 = free ; 0 = joined a team
	public int jAvailability = 1;
	
	
	public Juggler(int id){
		jID = id;
	}
	public int getId(){
		return jID;
	}
	/**
	 * Use this to retrieve the value of a Jugglers hand-to-eye coordination attribute.
	 * @return Value of Hand-To-Eye coordination attribute.
	 */	
	public int getjHandToEye() {
		return jHandToEye;
	}
	
	public void setjHandToEye(int jHandToEye) {
		this.jHandToEye = jHandToEye;
	}
	/**
	 * Use this to retrieve the value of a Jugglers Endurance attribute. 
	 * @return Value of Endurance attribute.
	 */
	public int getjEndurance() {
		return jEndurance;
	}

	public void setjEndurance(int jEndurance) {
		this.jEndurance = jEndurance;
	}

	/**
	 * Use this to retrieve the value of a Jugglers Pizzazz attribute.
	 * @return Value of Pizzazz attribute.
	 */
	public int getjPizzazz() {
		return jPizzazz;
	}

	public void setjPizzazz(int jPizzazz) {
		this.jPizzazz = jPizzazz;
	}
	public void setPreferList(int[][] prefList){
		jPreferList = prefList;
	}
	public int getPreferenceFromList(int n){
		return jPreferList[n][0];
	}
	public int sizeOfPreferenceList(){
		return jPreferList.length;
	}
	public int getDotProductFromPreferList(int n){
		return jPreferList[n][1];
	}
	
	/**
	 * 
	 * @param teamSize This is a constant value of 6.
	 */
	public void printPreferences(int teamSize){
		System.out.print(" J" + jID);
		int stopComma = 0;
		for(int i = 0; i < jPreferList.length; i++ ){
			//print the identification number 
			//then print the dot product for that circuit
			System.out.print(" C" + jPreferList[i][0] + ":" + jPreferList[i][1]);
			stopComma =+ 1;
		}
		if(stopComma < teamSize - 1) System.out.print(",");
	}
	/**
	 * This method outputs the jugglers ID with its attributes.
	 */
	public String toString(){
		return (" J" + jID  + " H:" + jHandToEye + " E:" + jEndurance + " P:" + jPizzazz + " ;" );
		
	}
	/**
	 * 
	 * @param c A circuit read in for the juggler-circuit dot product evaluation.
	 * @return Outputs the dot product found for the input circuit.
	 */
	public int calculateDotProduct(Circuit c) {
		
		return jHandToEye * c.getcHandToEye() +
				jEndurance * c.getcEndurance() +
				jPizzazz * c.getcPizzazz();
	}
}
