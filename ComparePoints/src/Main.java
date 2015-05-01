import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.StringTokenizer;


/**
 * Problem Location: https://www.codeeval.com/open_challenges/192/
 * 
 * Problem Description: Compare Points
 * Bob's hiking club is lost in the mountains on the way to a scenic 
 * overlook. Fortunately, Bob has a GPS device, so that he can see the 
 * coordinates where the group is currently at. The GPS gives the current 
 * X/Y coordinates as O, P, and the scenic overlook is located at Q, R. 
 * Bob now just needs to tell the group which way to go so they can get 
 * to the overlook in time for s'mores.
 * 
 * INPUT SAMPLE:
 * 
 * The input is a file with each line representing a test case. 
 * Each test case consists of four integers O, P, Q, R on a line, 
 * separated by spaces.
 * 
 * 0 0 1 5
 * 12 13 12 13
 * 0 1 0 5
 * 
 * OUTPUT SAMPLE:
 * 
 * For each test case print a line containing one of the following: 
 * N, NE, E, SE, S, SW, W, NW, here if the coordinates Q, R are 
 * (respectively) north, northeast, east, southeast, south, southwest, 
 * west, northwest, or already at ("here") the coordinates O, P. 
 * Note that N, S, E and W mean directly North, South, East or West 
 * respectively, i.e. X or Y coordinates of two points are exactly the same. 
 * In all other cases your output should be one of the NW, NE, SW, SE or here.
 * 
 * NE
 * here
 * N
 * 
 * CONSTRAINTS:
 * 
 * All coordinates -10000 < |O,P,Q,R| < 10000
 * Number of test cases is 40
 * 
 * @author Grant Cahill
 *
 */
public class Main {
	
	/**
	 * Find the direction of the coordinates.
	 * 
	 * @param coordinateMap listing the coordinates passed in
	 * @return direction
	 */
	private String findDirection(HashMap<String, Integer> coordinateMap) {
		String direction = "";
		
		int oqComp = coordinateMap.get("O").compareTo(coordinateMap.get("Q"));
		int prComp = coordinateMap.get("P").compareTo(coordinateMap.get("R"));
		
		if(oqComp == 0) {
			if(prComp == 0) {
				direction = "here";
			}
			else if(prComp > 0) {
				direction = "S";
			}
			else if(prComp < 0) {
				direction = "N";
			}
		} 
		else if(oqComp < 0) {
			if(prComp == 0) {
				direction = "E";
			}
			else if(prComp > 0) {
				direction = "SE";
			}
			else if(prComp < 0) {
				direction = "NE";
			}
		}
		else if (oqComp > 0) {
			if(prComp == 0) {
				direction = "W";
			}
			else if(prComp > 0) {
				direction = "SW";
			}
			else if(prComp < 0) {
				direction = "NW";
			}
		}
		
		return direction;
	}

	public static void main(String[] args) {
		Main comparePoints = new Main();
		
		try {
			File file = new File(args[0]);
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line;
			int count;
			
			while((line = bf.readLine()) != null) {
				HashMap<String, Integer> pointMap = new HashMap<String, Integer>();
				StringTokenizer st = new StringTokenizer(line, " ");
				count = 0;
				
				while(st.hasMoreTokens()) {
					switch(count) {
					case 0: pointMap.put("O", Integer.valueOf(st.nextToken()));
							count++;
							break;
					case 1: pointMap.put("P", Integer.valueOf(st.nextToken()));
							count++;
							break;
					case 2: pointMap.put("Q", Integer.valueOf(st.nextToken()));
							count++;
							break;
					case 3: pointMap.put("R", Integer.valueOf(st.nextToken()));
							break;
					}
				}
				
				System.out.println(comparePoints.findDirection(pointMap));
			}
			bf.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
