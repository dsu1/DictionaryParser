import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DictionaryParser {
	static List<String> Verbs = new ArrayList(Arrays.asList("bc", "fg", "g", "hij", "bcd"));
	static List<String> Nouns = new ArrayList(Arrays.asList("abcd", "c", "def", "h", "ij", "cde"));
	static List<String> Articles = new ArrayList(Arrays.asList("a", "ac", "e"));

	public static void main(String[] args){
		System.out.println("Enter input:");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine().toLowerCase();

		if(input.isEmpty() || input == null){
			System.out.println("[]");
		} else {
			List<String> processing = new ArrayList();
			List<String> completing = new ArrayList();
			List<String> finished = new ArrayList();

			// Fill array list first time
			// Iterate for each verb, noun, and article with the first input character
			// If the characters match, check if the word is longer than one character and check the input against the word
			// If the input matches with the word, add it to the 'processing' string array list.
			// Format of processed strings: [word:position:number of nouns:number of verbs:number of articles]
			String processed;
			for(String v : Verbs){
				if(input.charAt(0) == v.charAt(0)){
					processed = scanWord(v, "", input, 0);
					if(!processed.isEmpty()){
						processing.add(processed+":0:1:0");
					}
				}
			}
			for(String n : Nouns){
				if(input.charAt(0) == n.charAt(0)){
					processed = scanWord(n, "", input, 0);
					if(!processed.isEmpty()){
						processing.add(processed+":1:0:0");
					}
				}
			}
			for(String a : Articles){
				if(input.charAt(0) == a.charAt(0)){
					processed = scanWord(a, "", input, 0);
					if(!processed.isEmpty()){
						processing.add(processed+":0:0:1");
					}
				}
			}

			if(processing.isEmpty()){
				System.out.println("[]");
			} else {
				while(!processing.isEmpty()){
					if(Integer.parseInt(processing.get(0).split(":")[1])==input.length()){
						completing.add(processing.get(0));
						processing.remove(0);
					} else {
						String[] current = processing.get(0).split(":");
						int pointer = Integer.parseInt(current[1]);
						for(String v : Verbs){
							if(input.trim().charAt(pointer) == v.charAt(0)){
								processed = scanWord(v, current[0], input, pointer);
								if(!processed.isEmpty()){							
									processed += ":"+current[2]+":"+(Integer.parseInt(current[3])+1)+":"+current[4];
									processing.add(processed);
								}
							}
						}
						for(String n : Nouns){	
							if(input.trim().charAt(pointer) == n.charAt(0)){
								processed = scanWord(n, current[0], input, pointer);
								if(!processed.isEmpty()){
									processed += ":"+(Integer.parseInt(current[2])+1)+":"+current[3]+":"+current[4];
									processing.add(processed);
								}
							}
						}
						for(String a : Articles){
							if(input.trim().charAt(pointer) == a.charAt(0)){
								processed = scanWord(a, current[0], input, pointer);
								if(!processed.isEmpty()){
									processed += ":"+current[2]+":"+current[3]+":"+(Integer.parseInt(current[4])+1);
									processing.add(processed);
								}
							}
						}
						processing.remove(0);
					}
				}

				String[] finish;
				for(String c : completing){
					finish = c.split(":");
					int verbs = Integer.parseInt(finish[3]);
					int nouns = Integer.parseInt(finish[2]);
					int articles = Integer.parseInt(finish[4]);
					if(verbs > 0 && (nouns > 0 || articles > 1)){
						finished.add(c);
					} 
				}

				if(finished.size() > 1){
					System.out.println("[");
					for(int f = 0; f < finished.size(); f++){
						if(f == finished.size()-1){
							System.out.println("\""+finished.get(f).split(":")[0].trim()+"\"");
						} else {
							System.out.println("\""+finished.get(f).split(":")[0].trim()+"\",");
						}
					}
					System.out.println("]");
				} else if(finished.size() == 1) {
					System.out.println("[\""+finished.get(0).split(":")[0].trim()+"\"]");
				} else {
					System.out.println("[]");
				}
			}
		}
	}

	// Checks if a word matches the input from an offset location
	// If a match is not found or the sum of the length of the word and the input's offset exceeds the input length,
	// the word is invalid and the function returns the empty string
	// If a match is found, the current word is appended to the end of its predecessor and the offset is adjusted
	public static String scanWord(String word, String pred, String in, int pos){
		if((pos+word.length())>in.length()){
			return "";
		}
		for(int i = 0; i < word.length(); i++){
			if(in.charAt(pos+i) != word.charAt(i)){
				return "";
			}
		}
		return pred + " " + word + ":" + (pos+word.length());
	}
}
