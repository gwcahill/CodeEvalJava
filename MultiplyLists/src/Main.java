import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 * Problem Location: https://www.codeeval.com/open_challenges/113/
 * 
 * Problem Description:
 * MULTIPLY LISTS CHALLENGE DESCRIPTION:
 * You have 2 lists of positive integers. Write a program which 
 * multiplies corresponding elements in these lists.
 * 
 * INPUT SAMPLE:
 * Your program should accept as its first argument a path to a 
 * filename. Input example is the following
 * 
 * 9 0 6 | 15 14 9
 * 5 | 8
 * 13 4 15 1 15 5 | 1 4 15 14 8 2
 * 
 * The lists are separated with a pipe char, numbers are separated with a space char. 
 * The number of elements in lists are in range [1, 10]. 
 * The number of elements is the same in both lists. 
 * Each element is a number in range [0, 99].
 * 
 * OUTPUT SAMPLE:
 * Print the result in the following way.
 * 
 * 135 0 54
 * 40
 * 13 16 225 14 120 10
 * 
 * @author Grant Cahill
 *
 */
public class Main {
	/**
	 * Multiply the two arrays and print the results. The 
	 * arrays are assumed to be of equal length.
	 * 
	 * @param one first array list
	 * @param two second array list
	 */
	public void multList(String[] one, String[] two) {
		String output = "";
		for(int x=0; x<one.length; x++) {
			if(x == (one.length-1)) {
				output = output.concat(
						String.valueOf(Integer.valueOf(one[x])*Integer.valueOf(two[x])));
			}
			else {
				output = output.concat(
						(Integer.valueOf(one[x])*Integer.valueOf(two[x])) + 
						" ");
			}
		}
		System.out.println(output);
	}

	public static void main(String[] args) {
		Main multLists = new Main();
		
		//Get file from input arg
		try {
			
			File file = new File(args[0]);
	        BufferedReader buffer = new BufferedReader(new FileReader(file));
	        String line;
	        String one;
	        String two;
	        
	        StringTokenizer st;

	        while ((line = buffer.readLine()) != null) {
	            line = line.trim();
	            st = new StringTokenizer(line, "|");
	            
	            //capture the two lists
	            one = st.nextToken();
	            one = one.trim();
	            two = st.nextToken();
	            two = two.trim();
	            
	            //put lists into arrays for processing
	            String[] numListOne = one.split(" ");
	            String[] numListTwo = two.split(" ");
	            
	            
	            multLists.multList(numListOne, numListTwo);
	        }
	        buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
