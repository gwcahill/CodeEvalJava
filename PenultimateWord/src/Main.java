import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem Location: https://www.codeeval.com/open_challenges/92/
 * 
 * Problem Description: Penultimate Word
 * 
 * Write a program which finds the next to last word in a string.
 * 
 * INPUT SAMPLE:
 * Your program should accept as its first argument a path to 
 * a filename. Input example is the following
 * 
 * some line with text
 * another line
 * 
 * Each line has more than one word.
 * 
 * OUTPUT SAMPLE:
 * 
 * Print the next to last word in the following way.
 * 
 * with
 * another
 * 
 * @author Grant Cahill
 *
 */
public class Main {

	public static void main(String[] args) {
		
		try{
			File file = new File(args[0]);
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line; 
			StringTokenizer st;
			ArrayList<String> inputList = new ArrayList<String>();
			
			while((line = bf.readLine()) != null) {
				st = new StringTokenizer(line, " ");
				
				while(st.hasMoreTokens()) {
					inputList.add(st.nextToken());
				}

				System.out.println(inputList.get(inputList.size()-2));
				
				inputList.clear();
			}
			
			bf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
