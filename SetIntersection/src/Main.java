import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Problem Location: https://www.codeeval.com/open_challenges/30/
 * 
 * Problem Description:
 * 
 *  You are given two sorted list of numbers (ascending order). 
 *  The lists themselves are comma delimited and the two lists are 
 *  semicolon delimited. Print out the intersection of these two sets.
 *  
 *  Input sample:
 *  	File containing two lists of ascending order sorted integers, 
 *  	comma delimited, one per line. 
 *  	E.g. 
 *  		1,2,3,4;4,5,6
 *  		20,21,22;45,46,47
 *  		7,8,9;8,9,10,11,12
 *  
 *  Output sample:
 *  	Print out the ascending order sorted intersection of the two 
 *  	lists, one per line. Print empty new line in case the lists 
 *  	have no intersection. 
 *  	E.g. 
 *  		4
 *  		
 *  		8,9
 * 
 * @author Grant Cahill
 *
 */
public class Main {
	
	public void findIntersection(String[] listOne, String[] listTwo) {
		ArrayList<String> intersectionList = new ArrayList<String>();
		String outputLine = "";
		
		// search through each list
		for(String itemOne : listOne) {
			for(String itemTwo : listTwo) {
				// intersection found
				if(Integer.valueOf(itemOne) == Integer.valueOf(itemTwo)) {
					intersectionList.add(itemOne);
				}
			}
		}
		
		Object[] outputArray = intersectionList.toArray();
		
		//format output according to requirements
		for(int i=0; i<outputArray.length; i++) {
			if(i == (outputArray.length - 1)) {
				outputLine = outputLine.concat(String.valueOf(outputArray[i]));
			} 
			else {
				outputLine = outputLine.concat(String.valueOf(outputArray[i]) + ",");
			}
		}
		
		System.out.println(outputLine);
	}

	public static void main(String[] args) {
		Main setIntersection = new Main();
		
		//Get file from input arg
		try {
			
			File file = new File(args[0]);
	        BufferedReader buffer = new BufferedReader(new FileReader(file));
	        String line;

	        while ((line = buffer.readLine()) != null) {
	            line = line.trim();
	            
	            //break up input line into two strings
	            String [] lineArray = line.split(";");
	            
	            //break up two lists into separate arrays
	            String [] listOne = lineArray[0].split(",");
	            String [] listTwo = lineArray[1].split(",");
	            
	            //determine intersection
	            setIntersection.findIntersection(listOne, listTwo);
	        }
	        buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
