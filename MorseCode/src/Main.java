import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Problem Location: https://www.codeeval.com/open_challenges/116/
 * 
 * Problem Description: Morse Code
 * 
 * You have received a text encoded with Morse code and want to 
 * decode it.
 * 
 * INPUT SAMPLE:
 * 
 * Your program should accept as its first argument a path to a 
 * filename. Input example is the following:
 * 
 * .- ...- ..--- .-- .... .. . -.-. -..-  ....- .....
 * -... .... ...--
 * 
 * Each letter is separated by space char, each word is separated 
 * by 2 space chars.
 * 
 * OUTPUT SAMPLE:
 * 
 * Print out decoded words. E.g.
 * 
 * AV2WHIECX 45
 * BH3
 * 
 * Your program has to support letters and digits only.
 * 
 * @author Grant Cahill
 *
 */
public class Main {
	

	public static void main(String[] args) {
		//populate morse code dictionary
		HashMap<String, String> morseDict = new HashMap<String, String>();
		morseDict.put(".-", "A");
		morseDict.put("-...", "B");
		morseDict.put("-.-.", "C");
		morseDict.put("-..", "D");
		morseDict.put(".", "E");
		morseDict.put("..-.", "F");
		morseDict.put("--.", "G");
		morseDict.put("....", "H");
		morseDict.put("..", "I");
		morseDict.put(".---", "J");
		morseDict.put("-.-", "K");
		morseDict.put(".-..", "L");
		morseDict.put("--", "M");
		morseDict.put("-.", "N");
		morseDict.put("---", "O");
		morseDict.put(".--.", "P");
		morseDict.put("--.-", "Q");
		morseDict.put(".-.", "R");
		morseDict.put("...", "S");
		morseDict.put("-", "T");
		morseDict.put("..-", "U");
		morseDict.put("...-", "V");
		morseDict.put(".--", "W");
		morseDict.put("-..-", "X");
		morseDict.put("-.--", "Y");
		morseDict.put("--..", "Z");
		morseDict.put("-----", "0");
		morseDict.put(".----", "1");
		morseDict.put("..---", "2");
		morseDict.put("...--", "3");
		morseDict.put("....-", "4");
		morseDict.put(".....", "5");
		morseDict.put("-....", "6");
		morseDict.put("--...", "7");
		morseDict.put("---..", "8");
		morseDict.put("----.", "9");
		
		try {
			File file = new File(args[0]);
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line;
			StringTokenizer st;
			String[] words;
			StringBuffer strBuf;
			
			while((line = bf.readLine()) != null) {
				//split on double space
				words = line.split("\\s{2}");
				strBuf = new StringBuffer();
				
				//loop through words on line
				for(String word : words) {
					st = new StringTokenizer(word, " ");
					while(st.hasMoreTokens()) {
						strBuf.append(morseDict.get(st.nextToken()));
					}
					//add space
					strBuf.append(" ");
				}
				System.out.println(strBuf.toString());
			}
			
			bf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
