/**
 * @author Kashif Hoyte
 * <p>
 * This class parses a single line of data by placing it into a string and returning requested
 * data values for attributes and populating list of preferred circuits.
 *</p>
 */
public class HandleLine{
	
	
	
	private String line;
	private int stoppedAT;
	
	
	public void parseLine(String passedLine){
		line = passedLine;
	}
	/**
	 * 
	 * @param startAT The index position to start searching from. 
	 * @param select This determines if the method is returning the value after a comma or whitespace. 
	 * @return Outputs the value found.
	 */
	public int takeNumber(int startAT, int select){
		int endAT = 0;
		
		if(select == 0){
			endAT = findWhitespace(startAT);
		}else if(select == 1){
			endAT = findComma(startAT);
		}else System.out.println("Error with the select variable!!!");
		
		int tempNumber, getNumber;
		int number = 0;
		for(int i = startAT; i <= endAT; i ++){
			getNumber = Character.getNumericValue(line.charAt(i));
			tempNumber = (int) (getNumber * Math.pow(10, (endAT - i)  ));
			number = number + tempNumber; //add tempNumber to the current value of number 
										// and this will be the value of number
		}
		return number;
	}
	//return index value of H:
	public int findH(){
		return line.indexOf('H');
	}
	//return index value of E:
	public int findE(){
		return line.indexOf('E');
	}
	//return index value of the P:
	public int findP(){
		return line.indexOf('P');
	}
	//return index value of the P:
	public int findC(int fromIndex){	
		return line.indexOf('C', fromIndex);
	}
	//Locate the Comma and return its index value 
	private int findComma(int start){
		boolean containsComma = false;
		int tempI = 0;
		//
		for (int i = start; i < line.length() && !containsComma ; i++){
			tempI = i;
			if(line.charAt(i) == ','){
				containsComma = true;
				stoppedAT = i - 1;
			}
		}
		if(tempI == line.length() - 1) {
			return (line.length() - 1);
			}
		//return the value of the index where the Whitespace was found
		return stoppedAT;
	}
	//determine where the file comes across Whitespace in a line
	//used to determine whitespace for the Circuit and Jugglers Attributes
	private int findWhitespace(int start){
		//exit the for loop if we come across either the end of the line or a whitespace 
		//character
		boolean containsWhitespace = false;
		int tempI = 0;
		for (int i = start; i < line.length() && !containsWhitespace; i++){
			tempI = i;
			if(Character.isWhitespace(line.charAt(i))){
				containsWhitespace = true;
				stoppedAT = i - 1;
			}
		}
		if(tempI == line.length() - 1) {
			return (line.length() - 1);
			}
		//return the value of the index where the Whitespace was found
		return stoppedAT;
	}
	
}