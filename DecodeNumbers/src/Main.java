import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem Location: https://www.codeeval.com/open_challenges/73/
 * 
 * Problem Description: Decode Numbers
 * 
 * You are given an encoded message containing only numbers. You 
 * are also provided with the following mapping:
 * 
 * A : 1
 * B : 2
 * C : 3
 * ...
 * Z : 26
 *  
 * Given an encoded message, count the number of ways it can be decoded.
 * Input sample:
 *   
 * Your program should accept as its first argument a path to a filename. 
 * Each line in this file is a test-case and contains an encoded 
 * message of numbers. 
 * E.g. 
 *   
 * 12
 * 123
 * 345
 * 11211
 * 1319
 * 3519
 * 1718
 * 5518
 * 6417
 * 5617
 * 8315
 *   
 * You may assume that the test cases contain only numbers.
 * Output sample:
 *    
 * Print out the different number of ways it can be decoded. 
 * E.g. 
 *    
 * 2
 * 3
 * 1
 * 8
 * 4
 * 2
 * 4
 * 2
 * 2
 * 2
 * 2
 *    
 * NOTE: 12 could be decoded as AB(1 2) or L(12). Hence the number 
 * of ways to decode 12 is 2. 
 * 
 * Sample Decoded Set:
 * 11211
 * - 1 1 2 1 1
 * - 11 2 1 1 
 * - 11 21 1
 * - 11 2 11
 * - 1 12 1 1
 * - 1 12 11
 * - 1 1 21 1
 * - 1 1 2 11
 * 
 * @author Grant Cahill
 *
 */
public class Main {
	
	/**
	 * Search string for decoded permutations. The algorithm will sequentially 
	 * step through the string looking for two digit numbers that are 26 or less 
	 * according to the parameters of the problem. When the algorithm finds a two
	 * digit number meeting the specifications, it will immediately grab the 
	 * remainder of the string and split them into singles and push that solution
	 * into a Set data structure (to ensure a single solution of that type). Then,
	 * the algorithm will keep stepping forward and looking for two digit qualified
	 * answers and if one is found then it will split the remainder of the string again
	 * into singles and push that solution into the Set (while keeping what was found 
	 * earlier in the string). This cycle will continue until the whole string
	 * has been scanned.
	 * 
	 * @param line of input from file
	 * @return Set<List<String>> solution set
	 */
	private void decodePermutations(Main decode, String line) {
		//Set holding the unique solutions
		Set<List<String>> solutionSet = new HashSet<List<String>>();
		
		//set up input data structure
		String[] inputArray = line.split("(?!^)");
		
		//add singles to solution set
		List<String> singlesList = new ArrayList<String>();
		for(int x=0; x<inputArray.length; x++) {
			try {
				Integer.valueOf(inputArray[x]);
				singlesList.add(inputArray[x]);
			} catch (Exception e) {
				
			}
			
		}
		solutionSet.add(singlesList);
		
		//collect unique solution sets
		ArrayList<Integer> twosList = new ArrayList<Integer>();
		decode.findSolutionSet(solutionSet, decode, inputArray, 0, twosList);
	}
	
	/**
	 * Recursive method that finds all solution sets in the field by
	 * checking for available two digit sets and then appending
	 * singles to the end of that list. The method cycles through the 
	 * input line until the whole input line has been searched.
	 * 
	 * @param solutionSet holding unique solutions
	 * @param inputArray holding the remaining input not analyzed
	 * @param decode class instance
	 * @return boolean indicating processing status
	 */
	private boolean findSolutionSet(Set<List<String>> solutionSet, 
			Main decode, String[] inputArray, int startPoint,
			ArrayList<Integer> twosList) {
		//populate array under analysis
		ArrayList<String> captured = new ArrayList<String>();
		ArrayList<String> toBeProcessed = new ArrayList<String>();
		
		
		if(startPoint > 0) {
			for(int t=0; t<startPoint; t++) {
				captured.add(inputArray[t]);
				
			}
			for(int t=startPoint; t<inputArray.length; t++) {
				toBeProcessed.add(inputArray[t]);
			}
		} else {
			for(String item : inputArray) {
				toBeProcessed.add(item);
			}
		}
		
		String[] nextToAnalyze = toBeProcessed.toArray(
				new String[toBeProcessed.size()]);
		
			
		//Locate solution sets by searching for two digit groupings
		int length = nextToAnalyze.length;
		int twoDigit;
			
		for(int i=0; i<length; i++) {
			//solution list
			List<String> solutionList = new ArrayList<String>();
			
			//find qualifying two digit pair in multiples approach
			if((i+2 < length)&&(twosList.size()>0)) {
				//search remainder of string for multiple pairs
				ArrayList<String> tempList = new ArrayList<String>();
				for(int item : twosList) {
					tempList.add(String.valueOf(item));
				}
				tempList.add(nextToAnalyze[i+1]);
				for(int x=i+2; x<length-1; x++) {
					twoDigit = Integer.valueOf(nextToAnalyze[x] + nextToAnalyze[x+1]);
					if(twoDigit <= 26) {
						tempList.add(String.valueOf(twoDigit));
					} else {
						tempList.add(nextToAnalyze[x]);
					}
					
				}
				if(tempList.size() > 0) {
					StringBuffer sb = new StringBuffer();
					for(String item : tempList) {
						sb.append(item);
					}
					if(sb.toString().length() == inputArray.length) {
						solutionSet.add(tempList);
					}
				}
				ArrayList<String> twoSolList = new ArrayList<String>();
				
				twoDigit = Integer.valueOf(nextToAnalyze[i+1] + nextToAnalyze[i+2]);
				int stop = i+2;
				if(twoDigit <= 26) {
					//populate solution with already analyzed items
					for(int item : twosList) {
						twoSolList.add(String.valueOf(item));
					}
					
					//add the newly discovered two digit item to the solution
					twoSolList.add(String.valueOf(twoDigit));
					
					//add the remaining singles to the solution set
					for(int z=stop+1; z<nextToAnalyze.length; z++) {
						twoSolList.add(nextToAnalyze[z]);
					}
					
					//add the two digit plus singles solution to the set
					solutionSet.add(twoSolList);
				}
			}
			
			//find qualifying two digit pair in singles approach
			if(i != length-1) {
				twoDigit = Integer.valueOf(nextToAnalyze[i] + nextToAnalyze[i+1]);
				int stop = i+1;
				
				/* Located two digit pair. Capture leading singles
				 * (if any) as well as two digit pair and remaining
				 * singles and push them to the solutions set.
				 */
				if(twoDigit <= 26) {
					
					//manage previous pairs located
					if(twosList.size() > 0) {
						twosList.clear();
						for(String item : captured) {
							twosList.add(Integer.valueOf(item));
						}
						twosList.add(twoDigit);
					}
					else {
						for(String item : captured) {
							twosList.add(Integer.valueOf(item));
						}
						twosList.add(twoDigit);
					}
					
					//populate solution with already analyzed items
					for(String item : captured) {
						solutionList.add(item);
					}
					
					//add the newly discovered two digit item to the solution
					solutionList.add(String.valueOf(twoDigit));
					
					//add the remaining singles to the solution set
					for(int z=stop+1; z<nextToAnalyze.length; z++) {
						solutionList.add(nextToAnalyze[z]);
					}
					
					//add the two digit plus singles solution to the set
					solutionSet.add(solutionList);
					
					//increment the start point for the next iteration
					startPoint++;
					
					//search for remaining two digit pairs and repeat
					if(!decode.findSolutionSet(solutionSet, decode, inputArray, 
							startPoint, twosList)) {
						return false;
					}
				}
			}
		}
		
		StringBuffer sbu = new StringBuffer();
		for(String b : inputArray) {
		    sbu.append(b);
		}
		
		String input = sbu.toString();
		
		Set<List<String>> finishSet = new HashSet<List<String>>();
		for(List<String> bob : solutionSet) {
			StringBuffer finish = new StringBuffer();
			for(String element : bob) {
				finish.append(element);
			}
			
			if(finish.toString().equals(input)) {
				finishSet.add(bob);
			}
			
		}
		
		//print solution
		System.out.println(finishSet.size());
		
		return false;
	}

	public static void main(String[] args) {
		Main decodeNumbers = new Main();
		
		try {
			File file = new File(args[0]);
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line;
			
			while((line = bf.readLine()) != null) {
				line = line.trim();
				decodeNumbers.decodePermutations(decodeNumbers, line);
			}
			
			bf.close();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}
