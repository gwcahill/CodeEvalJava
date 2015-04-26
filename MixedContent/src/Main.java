import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Problem Location: https://www.codeeval.com/open_challenges/115/
 * 
 * Problem Description: Mixed Content
 * 
 * You have a string of words and digits divided by comma. 
 * Write a program which separates words with digits. You 
 * shouldn't change the order elements.
 * 
 * INPUT SAMPLE:
 * Your program should accept as its first argument a path 
 * to a filename. Input example is the following
 * 
 * 8,33,21,0,16,50,37,0,melon,7,apricot,peach,pineapple,17,21
 * 24,13,14,43,41
 * 
 * OUTPUT SAMPLE:
 * 
 * melon,apricot,peach,pineapple|8,33,21,0,16,50,37,0,7,17,21
 * 24,13,14,43,41
 * 
 * As you can see you need to output the same input string if 
 * it has words only or digits only.
 * 
 * @author Grant Cahill
 *
 */
public class Main {
	/**
	 * Print the results of the check for Mixed types in the 
	 * input line.
	 * 
	 * @param results HashMap holding the results of the parse
	 */
	public void printResults(HashMap<String, ArrayList> results) {
		StringBuffer strBuf = new StringBuffer();
		StringBuffer intBuf = new StringBuffer();
		
		boolean intContent = false;
		boolean strContent = false;
		
		if(results.get("String").size() > 0) {
			strContent = true;
			for(Object item : results.get("String")) {
				strBuf.append(String.valueOf(item));
				strBuf.append(",");
			}
			strBuf.setLength(strBuf.length() - 1);
		}
		
		if(results.get("Int").size() > 0) {
			intContent = true;
			for(Object item : results.get("Int")) {
				intBuf.append(String.valueOf(item));
				intBuf.append(",");
			}
			intBuf.setLength(intBuf.length() - 1);
		}
		
		//organize results for final output
		String finalOutput = "";
		if(intContent && strContent) {
			strBuf.append("|");
			finalOutput = strBuf.toString() + intBuf.toString();
		} else if(intContent) {
			finalOutput = intBuf.toString();
		} else if(strContent) {
			finalOutput = strBuf.toString();
		}
		
		System.out.println(finalOutput);
		
	}
	
	/**
	 * Parse the contents of the input line checking for
	 * String and Integer objects. Return the parsed results.
	 * 
	 * @param input ArrayList line input object
	 * @return HashMap holding the parsed contents
	 */
	public HashMap<String, ArrayList> parseMixedContent(ArrayList<String> input) {
		HashMap<String, ArrayList> mixMap = new HashMap<String, ArrayList>();
		ArrayList<String> strList = new ArrayList<String>();
		ArrayList<Integer> intList = new ArrayList<Integer>();

		for(String item : input) {
			try {
				intList.add(Integer.valueOf(item));
			} catch (NumberFormatException ne) {
				strList.add(item);
			}
		}
		
		//add lists to the mixed map
		mixMap.put("String", strList);
		mixMap.put("Int", intList);
		
		return mixMap;
	}

	public static void main(String[] args) {
		Main mixedContent = new Main();
		
		try {
			File file = new File(args[0]);
			BufferedReader buf = new BufferedReader(new FileReader(file));
			String line;
			StringTokenizer st;
			ArrayList<String> lineList = new ArrayList<String>();
			
			//read the input content
			while((line = buf.readLine()) != null) {
				st = new StringTokenizer(line, ",");
				while(st.hasMoreTokens()) {
					lineList.add(st.nextToken());
				}
				
				//parse the content
				mixedContent.printResults(
						mixedContent.parseMixedContent(lineList));
				
				//empty list
				lineList.clear();
			}
			buf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
