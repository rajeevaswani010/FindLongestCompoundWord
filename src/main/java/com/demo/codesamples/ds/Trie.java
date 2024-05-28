package com.demo.codesamples.ds;

import java.util.HashMap;
import java.util.Map;

public class Trie {
	
	private TrieNode root;
	
	private class TrieNode {
		private Map<Character, TrieNode> children;
		private boolean isEndOfWord;
		
		public TrieNode() {
			this.children = new HashMap<>();
			this.isEndOfWord = false;
		}
	}
	
	public Trie() {
		this.root = new TrieNode();
	}
	
	public void insert(String word) {
		TrieNode current = root;
		for(char c : word.toCharArray()) {
			current = current.children.computeIfAbsent(c, k -> new TrieNode());
		}
		current.isEndOfWord = true;
	}
	
	public boolean search(String word) {
		TrieNode current = this.root;
		for(char c : word.toCharArray()) {
			current = current.children.get(c);
			if(current == null) {
				return false;
			}
		}
		return current.isEndOfWord;
	}
	
	public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            current = current.children.get(c);
            if (current == null) {
                return false;
            }
        }
        return true;		
	}

}
