import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Problem Location: https://www.codeeval.com/open_challenges/156/
 * 
 * Problem Description: Roller Coaster
 * 
 * You are given a piece of text. Your job is to write 
 * a program that sets the case of text characters according 
 * to the following rules:
 * 
 * The first letter of the line should be in uppercase.
 * The next letter should be in lowercase.
 * The next letter should be in uppercase, and so on.
 * 
 * Any characters, except for the letters, are ignored during 
 * determination of letter case.
 * 
 * INPUT SAMPLE:
 * 
 * The first argument will be a path to a filename containing 
 * sentences, one per line. You can assume that all characters 
 * are from the English language.
 * 
 * For example:
 * 
 * To be, or not to be: that is the question.
 * Whether 'tis nobler in the mind to suffer.
 * The slings and arrows of outrageous fortune.
 * Or to take arms against a sea of troubles.
 * And by opposing end them, to die: to sleep.
 * 
 * OUTPUT SAMPLE:
 * 
 * Print to stdout the RoLlErCoAsTeR case version of 
 * the string.
 * 
 * For example:
 * 
 * To Be, Or NoT tO bE: tHaT iS tHe QuEsTiOn.
 * WhEtHeR 'tIs NoBlEr In ThE mInD tO sUfFeR.
 * ThE sLiNgS aNd ArRoWs Of OuTrAgEoUs FoRtUnE.
 * Or To TaKe ArMs AgAiNsT a SeA oF tRoUbLeS.
 * AnD bY oPpOsInG eNd ThEm, To DiE: tO sLeEp.
 * 
 * CONSTRAINTS:
 * 
 * The length of each piece of text does not exceed 1000 
 * characters.
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
			char c;
			int counter;
			
			while((line = bf.readLine()) != null) {
				StringBuffer strBuf = new StringBuffer();
				line = line.trim();
				line = line.toLowerCase();
				counter = 0;
				
				//Step through string and roller coaster the case
				for(int i=0; i<line.length(); i++) {
					if(Character.isLetter(line.charAt(i))) {
						if(counter == 0) {
							strBuf.append(
									Character.toUpperCase(line.charAt(i)));
						} else if(counter%2 == 0) {
							strBuf.append(
									Character.toUpperCase(line.charAt(i)));
						} else {
							strBuf.append(
									Character.toLowerCase(line.charAt(i)));
						}
						counter++;
					}
					else {
						strBuf.append(line.charAt(i));
					}
				}
				
				System.out.println(strBuf.toString());
			}
			
			bf.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
