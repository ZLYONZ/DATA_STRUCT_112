package trie;

import java.util.ArrayList;

/**
 * This class implements a Trie. 
 * 
 * @author Sesh Venugopal
 *
 */
public class Trie {
	
	// prevent instantiation
	private Trie() { }
	
	/**
	 * Builds a trie by inserting all words in the input array, one at a time,
	 * in sequence FROM FIRST TO LAST. (The sequence is IMPORTANT!)
	 * The words in the input array are all lower case.
	 * 
	 * @param allWords Input array of words (lowercase) to be inserted.
	 * @return Root of trie with all words inserted from the input array
	 */
	
	// FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
    // MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATION
	public static TrieNode buildTrie(String[] allWords) {
		TrieNode root = new TrieNode(null, null, null);
		if(allWords.length == 0) {
			return root;
		}
		
		root.firstChild = new TrieNode(new Indexes(0, (short)(0), (short)
				(allWords[0].length() - 1)), null, null);

		TrieNode pointer = root.firstChild, lastSee = root.firstChild;
		int sut = -1, start = -1, end = -1, wordIndex = -1;


		for(int index = 1; index < allWords.length; index++) {
			String word = allWords[index];
			while(pointer != null) {
				start = pointer.substr.startIndex;
				end = pointer.substr.endIndex;
				wordIndex = pointer.substr.wordIndex;

				if(start > word.length()) {
					lastSee = pointer;
					pointer = pointer.sibling;
					continue;
				}

				sut = similarUpTo(allWords[wordIndex].substring(start, end+1), 
						word.substring(start)); 
				if(sut != -1)
					sut += start;

				if(sut == -1) { 
					lastSee = pointer;
					pointer = pointer.sibling;
				}
				else {
					if(sut == end) { 
						lastSee = pointer;
						pointer = pointer.firstChild;
					}
					else if (sut < end){ 
						lastSee = pointer;
						break;
					}
				}
			}
			if(pointer == null) {
				Indexes indexes = new Indexes(index, (short)start, (short)(word.length()-1));
				lastSee.sibling = new TrieNode(indexes, null, null);
			} 
			else {
				TrieNode currChild1 = lastSee.firstChild; 
				Indexes currIndexes = lastSee.substr; 

				Indexes currrentWordNewIndexes = new Indexes(currIndexes.wordIndex, 
						(short)(sut+1), currIndexes.endIndex); currIndexes.endIndex = (short)sut; 

				lastSee.firstChild = new TrieNode(currrentWordNewIndexes, null, null);
				lastSee.firstChild.firstChild = currChild1;
				lastSee.firstChild.sibling = new TrieNode(new Indexes((short)index, 
						(short)(sut+1), (short)(word.length()-1)), null, null);
			}
			pointer = lastSee = root.firstChild;
			sut = start = end = wordIndex = -1;
		}
		return root;
	}
	
	private static int similarUpTo(String inTrie, String insert) {

		int upTo = 0;
		while(upTo < inTrie.length() && upTo < insert.length()
				&& inTrie.charAt(upTo) == insert.charAt(upTo))
			upTo++;

		return (upTo-1);
	}
	
	/**
	 * Given a trie, returns the "completion list" for a prefix, i.e. all the leaf nodes in the 
	 * trie whose words start with this prefix. 
	 * For instance, if the trie had the words "bear", "bull", "stock", and "bell",
	 * the completion list for prefix "b" would be the leaf nodes that hold "bear", "bull", and "bell"; 
	 * for prefix "be", the completion would be the leaf nodes that hold "bear" and "bell", 
	 * and for prefix "bell", completion would be the leaf node that holds "bell". 
	 * (The last example shows that an input prefix can be an entire word.) 
	 * The order of returned leaf nodes DOES NOT MATTER. So, for prefix "be",
	 * the returned list of leaf nodes can be either hold [bear,bell] or [bell,bear].
	 *
	 * @param root Root of Trie that stores all words to search on for completion lists
	 * @param allWords Array of words that have been inserted into the trie
	 * @param prefix Prefix to be completed with words in trie
	 * @return List of all leaf nodes in trie that hold words that start with the prefix, 
	 * 			order of leaf nodes does not matter.
	 *         If there is no word in the tree that has this prefix, null is returned.
	 */
	
	// FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
	// MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATION
	public static ArrayList<TrieNode> completionList(TrieNode root,
										String[] allWords, String prefix) {
		if(root == null) {
			return null;
		}
		ArrayList<TrieNode> same = new ArrayList<>();
		TrieNode pointer = root;
		
		while(pointer != null) {
			if(pointer.substr == null) {
				pointer = pointer.firstChild;
			}
			String sub = allWords[pointer.substr.wordIndex];
			String alter = sub.substring(0, pointer.substr.endIndex+1);
			
			if(sub.startsWith(prefix) || prefix.startsWith(alter)) {
				if(pointer.firstChild != null) { 
					same.addAll(completionList(pointer.firstChild, allWords, prefix));
					pointer = pointer.sibling;
				} 
				else { 
					same.add(pointer);
					pointer = pointer.sibling;
				}
			} 
			else {
				pointer = pointer.sibling;
			}
		}

		return same;
	}
	
	
	
	public static void print(TrieNode root, String[] allWords) {
		System.out.println("\nTRIE\n");
		print(root, 1, allWords);
	}
	
	private static void print(TrieNode root, int indent, String[] words) {
		if (root == null) {
			return;
		}
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		
		if (root.substr != null) {
			String pre = words[root.substr.wordIndex]
							.substring(0, root.substr.endIndex+1);
			System.out.println("      " + pre);
		}
		
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		System.out.print(" ---");
		if (root.substr == null) {
			System.out.println("root");
		} else {
			System.out.println(root.substr);
		}
		
		for (TrieNode ptr=root.firstChild; ptr != null; ptr=ptr.sibling) {
			for (int i=0; i < indent-1; i++) {
				System.out.print("    ");
			}
			System.out.println("     |");
			print(ptr, indent+1, words);
		}
	}
 }
