import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Trie {
	
	// inner class, only for the use of Trie
	private class TrieNode {
		@SuppressWarnings("unused")
		private char val;			
		private HashMap<Character, TrieNode> children;

		private boolean isWord;
		// constructor
		public TrieNode(char val) {
			this.val = val;
			children = new HashMap<Character, TrieNode>();
			isWord = false;
		}
		
		// add child to trienode
		public void addChild(char child) {
			children.put(child, new TrieNode(child));
		}
		
		// get child of trienode that has the same character as the give one
		public TrieNode getChild(char child) {
			if (!children.keySet().contains(child)) {
				return null;
			}
			
			return children.get(child);
		}
		
		// return true if child exists
		public boolean containsChild(char child) {
			return children.keySet().contains(child);
		}
	}
	
	private TrieNode root;
	private TrieNode curr;
	
	public Trie() {
		root = new TrieNode(' ');
		curr = root;
	}
	
	// insert a word to trie
	public void insert(String s) {
		char letter;
		curr = root;
		// update trie if a letter is not in the structure
		for (int i = 0; i < s.length(); i++) {
			letter = s.charAt(i);
			
			if (!curr.containsChild(letter)) {
				curr.addChild(letter);
			} 
			curr = curr.getChild(letter);
		}
		// mark last letter as the end of a word
		curr.isWord = true;
	}
	
	// return starting indices of all suffixes of a word
	public List<Integer> getSuffixesStartIndices(String s) {
		List<Integer> indices = new LinkedList<Integer>();
		char letter;
		curr = root;
		
		for (int i = 0; i < s.length(); i++) {
			letter = s.charAt(i);
			if (!curr.containsChild(letter))
				return indices;
			
			// move on to the child node
			curr = curr.getChild(letter);
			if (curr.isWord)
				indices.add(i + 1);
		}
		return indices;
	}
}