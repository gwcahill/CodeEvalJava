import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Problem Location: https://www.codeeval.com/open_challenges/16/
 * 
 * Problem Description: Number of Ones
 *  Write a program which determines the number of 1 bits in the internal 
 *  representation of a given integer.
 *  
 *  Input sample:
 *  
 *  The first argument is a path to a file. The file contains integers, 
 *  one per line.
 *  
 *  For example: 
 *  
 *  10
 *  22
 *  56
 *  
 *  Output sample:
 *  
 *  Print to stdout the number of ones in the binary form of each number.
 *  
 *  For example: 
 *  
 *  2
 *  3
 *  3
 * 
 * @author Grant Cahill
 *
 */
public class Main {

	public static void main(String[] args) {
		try {
			File file = new File(args[0]);
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line;
			int count = 0;
			
			while((line = bf.readLine()) != null) {
				count = 0;
				line = line.trim();
				int value = Integer.valueOf(line);
				
				String binValue = Integer.toBinaryString(value);
				
				char[] binArray = binValue.toCharArray();
				for(int i=0; i<binArray.length; i++) {
					if(String.valueOf(binArray[i]).equals("1")) {
						count++;
					}
				}
				
				//print results
				System.out.println(count);
			}
			
			bf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
