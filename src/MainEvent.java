import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
/**
 * @author Kashif Hoyte
 * 
 * <p> The MainEvent class creates the Juggler/Circuit assignments by first 
 * requesting a text file via user dialog box to input the data then outputting a file with the
 * circuit team assignments. The process of assigning a Juggler to a circuit first goes through the preference matching algorithm
 * based on a revision of the stable-matching algorithm which for my algorithm makes use of the Juggler to Circuit dot product which
 * evaluates the Juggler/ Circuit match. The second step is to check for those Jugglers who have not been assigned to a circuit and 
 * assign them where there is a vacancy and no other Juggler that has a greater dot product. Lastly the assignments are output to a file 
 * </p>
 * 
 * 
 */
public class MainEvent {
	/**
	 * Array used to hold all the circuit objects for searching.
	 */
	private static ArrayList<Circuit> mCircuits;
	/**
	 * Array used to hold all the Juggler objects for searching.
	 */
	private static ArrayList<Juggler> mJugglers;
	
	private final static int numberOfJugglers = 12000;
	private final static int numberOfCircuits = 2000;
	
	public static void main(String[] args){
		
		mCircuits = new ArrayList<Circuit>();
		mJugglers = new ArrayList<Juggler>();
		
		lineParsing();
		
		while(checkJugglersAvailability() == 1){
				
				preferenceMatchingCheck();
				assignJugglersToCircuits();
				
		}
		printToFile();
	}
	
	/**
	 * 
	 */
	private static void printToFile(){
		
		try{
			/*
			 * Choose or name a file to save the Circuit Team listings
			 */
			File file = null;
		
				final JFileChooser fc = new JFileChooser();
				
				
				//In response to a button click:
				
				int returnVal = fc.showSaveDialog(fc);
				
				if(returnVal == JFileChooser.APPROVE_OPTION){
					file =  fc.getSelectedFile();
					if(file.getName().endsWith(".txt") == false){
						printToFile();
					}
					
				}else{
					System.out.println("You need to select a file.");
					
				}
			
			
			
			//create output manager to write the information to the file
			PrintWriter out = new PrintWriter(file);	
			
			for(Circuit c: mCircuits){
				out.print("C" + c.getId());
						
						for(int jugglerIndex = 0; jugglerIndex < c.team.size(); jugglerIndex++ ){
							Juggler j = c.team.get(jugglerIndex);
							out.print(" J" + j.getId());
							//print the identification number 
							//then print the dot product for that circuit
							for(int preferenceIndex = 0; preferenceIndex < j.sizeOfPreferenceList(); preferenceIndex++){
								
								out.print(" C" + j.getPreferenceFromList(preferenceIndex) + ":" + j.getDotProductFromPreferList(preferenceIndex));
							
							}
							if(jugglerIndex < c.team.size() - 1) out.print(",");
						}
						out.println();
					}
				out.close();
		}catch(NullPointerException e){
			System.exit(0);
		}catch(IOException e){
			System.out.println("Error: Cannot create that file");
			System.exit(0);
		}
		
		
		
	}
	private static int checkJugglersAvailability(){
		int jAvailibilityCheck = 0;
		
		//iterate through each Juggler object to see if they have found a team 
		//if they have not all found a team return 1
		//else return 0 stating that they all have
		
		for(Juggler j: mJugglers){
			jAvailibilityCheck += j.jAvailability; 
		}
		
		if(jAvailibilityCheck > 0) {
			System.out.println("Number of Available Jugglers " + jAvailibilityCheck + "\n");
			return 1;
		}
		//All of the jugglers have been assigned to a team
		return 0;
		
		
		
	
	}

	//Assign each Juggler to a Circuit based on their dot product ONLY
	private static void assignJugglersToCircuits(){
		
			//Iterate through the jugglers 
			for(Juggler j: mJugglers){
				//iterate through the circuits and attempt to add the jugglers to all the teams based on 
				//their dot product NOT their preference list
				if(j.jAvailability == 0)continue;
				for(Circuit c: mCircuits){
					if(c.team.size() < (numberOfJugglers/numberOfCircuits)){
							
							//join the team 
							c.team.add(j);
							//change the jugglers availability to taken
							j.jAvailability = 0;
							
							break;
					}else{ //Attempt to add the juggler to the team if it has a high enough dot product
						
							c.sortTeam();
							
							//if the dotProduct for the current Juggler 'j' 
							//and the Circuit 'c' is greater than the next one 
							//then the juggler can replace the last one in the last
							if(c.team.size() <  (numberOfJugglers/numberOfCircuits)){
								c.team.add(j);
								j.jAvailability = 0;
								break;
							}else if(j.calculateDotProduct(c) > c.team.get((c.team.size())-1).calculateDotProduct(c)){
								//set the juggler back to free
								c.team.get((c.team.size())-1).jAvailability = 1;
								//remove the juggler at the end of the team list
								c.team.remove((c.team.size())-1);
								//add new juggler to the team
								c.team.add(j);
								//change the availability of the new Juggler
								j.jAvailability = 0;
								break;
							}
							
						}//should not reach the end of the else loop for attempting to add the juggler to a Team
						 //if they have a higher dot product for that circuit
					
				}
			}
		}	
			
	
	private static void preferenceMatchingCheck(){
			//circulate through the circuit preferences each time this is run
			for(int i = 0 ; i < 10; i++){
			
				/*
				 * 	If the selected Juggler is free run the
				 *  process to find a team to place the juggler on
				 *  otherwise go to the next Juggler.
				 */

				for(Juggler j : mJugglers){
						
						//Free = 1; Joined a team = 0
						if(j.jAvailability == 0)continue;
						
						/*
						 * Locate the Preferred circuits given in the Preference List 
						 * and test to see if the Juggler has a high enough dot product
						 * qualifying the Juggler to join, otherwise remain free.
						 */
						for(Circuit c : mCircuits){
								
							if(c.getId() == j.getPreferenceFromList(i)){
									//Join if vacancy
									if(c.team.size() < (numberOfJugglers/numberOfCircuits)){
										//join the team 
										c.team.add(j);
										//change the jugglers availability to taken
										j.jAvailability = 0;
										
										break;
									
									}else{ 
									//Run the Dot Product Test
										c.sortTeam();
										if(j.calculateDotProduct(c) > c.team.get((c.team.size())-1).calculateDotProduct(c)){
											//set juggler in that position back to free
											c.team.get((c.team.size())-1).jAvailability = 1;
											//remove juggler with the smallest dot product on the team
											c.team.remove((c.team.size())-1);
											//add current juggler to the team
											c.team.add(j);
											
											//change the availability of the newly added juggler to taken
											j.jAvailability = 0;
										}
										break;
									}//end if-else statement that attempts to add the juggler to the selected circuit
							}//this point is reached if the jugglers preference does not match the circuit reached
						}//exit because you have reached the end of the circuits arrayList mCircuits
					}//Exit for loop because either reached the end of the preference list or the juggler has joined a team
			}
		}

	
	private static File askUserForTheFile(){
		
		final JFileChooser fc = new JFileChooser();
		//In response to a button click:
		int returnVal = fc.showOpenDialog(fc);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			return fc.getSelectedFile();
		}else{
			System.out.println("You need to select a file.");
			return null;
		}
	}
	private static void lineParsing(){
		//repeat if the file is not located
		while(true){ 
				//if the file exist continue 
				//if the file does not exist catch the FileNotFound Error
				try{
					Scanner input = new Scanner(askUserForTheFile());
					String incomingLine;
					HandleLine handle = new HandleLine();
					int inc = 0;//increment for the circuit index
					int inj = 0;//increment for the juggler index
					
					while(input.hasNext()){
						incomingLine = input.nextLine();
						
						//row holds the number used to identify the Circuit
						//column holds the value of the dot product for that Circuit 
						int [][] circuitsPreferred = new int[10][2];
						
						if(!incomingLine.isEmpty()){
							//does the line start with a C or J ???
							//based on the first character in a line I know what methods to run
							if(incomingLine.charAt(0) == 'C'){
								//create a Circuit object
								Circuit c = new Circuit(inc);
								
								//Parse the line for attribute values
								handle.parseLine(incomingLine);
								
								//grab the value for each of the three attributes for the circuit
									c.setcHandToEye(handle.takeNumber((handle.findH() + 2), 0));
									c.setcEndurance(handle.takeNumber((handle.findE() + 2), 0)); 
									c.setcPizzazz(handle.takeNumber((handle.findP() + 2), 0));
							
									//Add the element to the ArrayList
									//that is holding the Circuit objects
									mCircuits.add(c);
									inc++;
							}else if(incomingLine.charAt(0) == 'J'){
								//create a Juggler object
								Juggler j = new Juggler(inj);
								handle.parseLine(incomingLine);
								//grab the value for each of the three attributes for the circuit
								j.setjHandToEye( handle.takeNumber((handle.findH() + 2), 0));
								j.setjEndurance( handle.takeNumber((handle.findE() + 2), 0));
								j.setjPizzazz( handle.takeNumber((handle.findP() + 2), 0));
								
								//grab the value for the order of the preferred circuit selection
									int startFrom = 0;
				
									//Take the numeric value for the circuits preferred by each juggler
									for(int i = 0; i < circuitsPreferred.length; i++){
										//the first parameter tells the method where to start from
										//the second parameter tells the method to find a comma before taking the value
										circuitsPreferred[i][0] = handle.takeNumber((handle.findC(startFrom) + 1), 1);
		 								 
										for(Circuit c: mCircuits){
		 									if(circuitsPreferred[i][0] == c.getId()){
		 										//calculate dot product an assign the value 
		 										//to the second column in the array
		 										circuitsPreferred[i][1] = j.getjEndurance() * c.getcEndurance() 
		 												+ j.getjHandToEye() * c.getcHandToEye()
		 												+ j.getjPizzazz() * c.getcPizzazz();
		 									}
		 								} 
										//advance the startAt position
										startFrom = handle.findC(startFrom) + 1;
									}
									//set the Preferred list circuit identifiers
									
									j.setPreferList(circuitsPreferred);
									
									//Add the element to the ArrayList
									//that is holding the Juggler objects
									mJugglers.add(j);
									inj++;
							}
							
						
						}	
					}
					input.close();	
					break;
				}catch(NullPointerException e){
					System.exit(0);
				}catch(IOException e){
					System.out.println("Error: File Not Found!");
					
				}
		}
		
		
			
	}
}
