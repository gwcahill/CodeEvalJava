import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Problem Location: https://www.codeeval.com/open_challenges/29/
 * 
 * Problem Description: 
 * 	You are given a sorted list of numbers with duplicates. Print 
 * 	out the sorted list with duplicates removed.
 * 
 * 	Input sample:
 * 		File containing a list of sorted integers, comma 
 * 		delimited, one per line. 
 * 
 * 		E.g. 
 * 			1,1,1,2,2,3,3,4,4
 * 			2,3,4,5,5
 * 
 * 	Output sample:
 * 		Print out the sorted list with duplicates removed, one per line.
 * 
 * 		E.g. 
 * 		1,2,3,4
 * 		2,3,4,5
 * 
 * @author Grant Cahill
 *
 */
public class Main {
	public void extractUnique(ArrayList<Integer> inputList) {
		SortedSet<Integer> outputSet = new TreeSet<Integer>();
		
		String resultString = "";
		
		for(int i : inputList) {
			outputSet.add(i);
		}
		
		//print results
		Object[] results = outputSet.toArray();
		int size = results.length;
		for(int x=0; x<size; x++) {
			if(x == (size-1)) {
				resultString = resultString.concat(String.valueOf(results[x]));
			}
			else {
				resultString = resultString.concat(String.valueOf(results[x]) + ",");
			}
		}
		
		System.out.println(resultString);
		
		resultString = "";
		outputSet.clear();
	}

	public static void main(String[] args) {
		Main uElem = new Main();
		
		//Get file from input arg
		try {
			
			File file = new File(args[0]);
	        BufferedReader buffer = new BufferedReader(new FileReader(file));
	        String line;
	        ArrayList<Integer> inputList = new ArrayList<Integer>();
	        while ((line = buffer.readLine()) != null) {
	            line = line.trim();
	            String [] lineArray = line.split(",");
                for(String item : lineArray) {
                	inputList.add(Integer.valueOf(item));
                }
                uElem.extractUnique(inputList);  
                inputList.clear();
	        }
	        buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
