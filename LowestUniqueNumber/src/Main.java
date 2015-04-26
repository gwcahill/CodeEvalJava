import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Problem Location: https://www.codeeval.com/open_challenges/103/
 * 
 * Problem Description: Lowest Unique Number
 * 
 * There is a game where each player picks a number from 1 to 9, 
 * writes it on a paper and gives to a guide. A player wins if 
 * his number is the lowest unique. We may have 10-20 players in 
 * our game.
 * 
 * INPUT SAMPLE:
 * 
 * Your program should accept as its first argument a path 
 * to a filename.
 * 
 * You're a guide and you're given a set of numbers from players 
 * for the round of game. E.g. 2 rounds of the game look this way:
 * 
 * 3 3 9 1 6 5 8 1 5 3
 * 9 2 9 9 1 8 8 8 2 1 1
 * 
 * OUTPUT SAMPLE:
 * 
 * Print a winner's position or 0 in case there is no winner. 
 * In the first line of input sample the lowest unique number 
 * is 6. So player 5 wins.
 * 
 * 5
 * 0
 * 
 * @author Grant Cahill
 *
 */
public class Main {
	/**
	 * Group the elements into a distribution map.
	 * 
	 * @param inputList holding the set of digits provided per line
	 * @return HashMap holding the distribution of integers
	 */
	public HashMap<Integer, Integer> findDistribution(
			ArrayList<Integer> inputList) {
		HashMap<Integer, Integer> distributionMap = 
				new HashMap<Integer, Integer>();
		int count = 0;
		
		//search through the input list
		for(int item : inputList) {
			//group the located items
			if(distributionMap.containsKey(item)) {
				count = distributionMap.get(item);
				count++;
				
				distributionMap.put(item, count);
			} else {
				distributionMap.put(item, 1);
			}
		}
		
		return distributionMap;
	}

	public static void main(String[] args) {
		Main lowestUniqueNumber = new Main();
		
		try {
			File file = new File(args[0]);
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line;
			StringTokenizer st;
			ArrayList<Integer> inputList = new ArrayList<Integer>();
			HashMap<Integer, Integer> distMap;
			
			while((line = bf.readLine()) != null) {
				st = new StringTokenizer(line, " ");
				while(st.hasMoreTokens()) {
					inputList.add(Integer.valueOf(st.nextToken()));
				}
				
				distMap = lowestUniqueNumber.findDistribution(inputList);
				
				//Loop through map to find smallest list of size 1
				Iterator it = distMap.entrySet().iterator();
				int smallest = 100000000;
				boolean found = false;
				while(it.hasNext()) {
					Map.Entry<Integer, Integer> pair = 
							(Map.Entry<Integer, Integer>)it.next();
					
					if(pair.getValue() == 1) {
						found = true;
						if(pair.getKey() < smallest) {
							smallest = pair.getKey();
						}
					}
			
				}
				
				//print results
				if(found) {
					for(int i=0; i<inputList.size(); i++) {
						if(smallest == inputList.get(i)) {
							System.out.println(i+1);
						}
					}
				} else {
					System.out.println(0);
				}
				
				inputList.clear();
			}
			
			bf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
