
//to find the longest compound word and the second longest compound words from "Input_01.txt" file.
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.LinkedList;


public class LongestCompoundWord_01 {

	public static void main(String[] args) throws FileNotFoundException {
		
		// here input the "txt" file name 
		File file = new File("Input_01.txt");

		// Trie
		Trie trie = new Trie();
		LinkedList<Pair<String>> queue = new LinkedList<Pair<String>>();
		
		// scan the file
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);

		String word;			
		List<Integer> sufIndex;	// indices of suffixes of a word
		
		// read words from the file
		// insert each word in trie
		while (sc.hasNext()) {
			word = sc.next();		
			sufIndex = trie.getSuffixesStartIndices(word);
			for (int i : sufIndex) {
				if (i >= word.length())		
					break;
				queue.add(new Pair<String>(word, word.substring(i)));
			}
			trie.insert(word);
		}
		
		Pair<String> p; 
		int maxLength = 0;
		String longest = "";
		String sec_longest = "";

		while (!queue.isEmpty()) {
			p = queue.removeFirst();
			word = p.second();
			
			sufIndex = trie.getSuffixesStartIndices(word);
			
			// if no suffixes found, which means no prefixes found
			// discard the pair and check the next pair
			if (sufIndex.isEmpty()) {
				continue;
			}
			
			for (int i : sufIndex) {
				if (i > word.length()) {
					break;
				}
				
				if (i == word.length()) { // no suffix, means it is a compound word
					// check if the compound word is the longest
					// if it is update both longest and second longest
					if (p.first().length() > maxLength) {
						sec_longest = longest;
						maxLength = p.first().length();
						longest = p.first();
					}
				} else {
					queue.add(new Pair<String>(p.first(), word.substring(i)));
				}
			}
		}
		// print the results
		System.out.println("Longest Compound Word: " + longest);
		System.out.println("Second Longest Compound Word: " + sec_longest);
	}
}