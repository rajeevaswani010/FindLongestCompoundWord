package com.demo.codesamples.challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.demo.codesamples.ds.Trie;


public class FindLongestCompoundString {
	
	Trie wordStore;
	ArrayList<String> sortedlist; 	

	int minLength;
	
	public FindLongestCompoundString() {
		sortedlist = new ArrayList<String>();
		
		wordStore = new Trie();
	}
	
	public void getOutput(String filepath, Long n_top) {
		try {		
			System.out.println("file - "+filepath);
			System.out.println("----------------------------------------------");

			readFile(filepath);
		
			int counter = 0;
			
			while(this.sortedlist.size() > 0) {
				String str = this.sortedlist.remove(0);
				HashMap<String, Boolean> cache = new HashMap<String, Boolean>(); 
				
				if( this.can_construct(str, cache) == true ) {
					counter += 1;

					if(counter <= n_top) {
					if(counter == 1) 
						System.out.println("1st longest: "+str+" , len: "+str.length());
					else if(counter == 2)
						System.out.println("2nd longest: "+str+" , len: "+str.length());
					else if(counter == 3)
						System.out.println("3rd longest: "+str+" , len: "+str.length());
					else
						System.out.println(counter+"th longest: "+str+" , len: "+str.length());
					}
				}		
			}
			System.out.println();
			System.out.print("----------------------END------------------------");
			System.out.println();
			System.out.println("Total number of words: " +counter);
		
		} catch (IOException e) {
			System.out.println("Input/Output error - " + e.getMessage());
			e.printStackTrace();		
		}
		catch (Exception e) {
			System.out.println("internal error - " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	//read the strings from input file and put in an arraylist
	private void readFile(String filepath) throws Exception {	
		
		String str = null;
		BufferedReader reader = null;
		
		minLength = Integer.MAX_VALUE;
		InputStream inputStream = FindLongestCompoundString.class.getClassLoader().getResourceAsStream(filepath);
		reader = new BufferedReader(new InputStreamReader(inputStream));
		while (( str = reader.readLine()) != null){
			if(str.length() == 0) {
				continue;
			}
			if (str.length() < minLength)
				minLength = str.length();
			
			sortedlist.add(str);
			wordStore.insert(str);
		}
		
		if(minLength < 2) minLength = 2; //single characters are not considered.
		
		Collections.sort(this.sortedlist, (String o1, String o2) -> {
				return Integer.valueOf(o2.length()).compareTo(Integer.valueOf(o1.length()));
		});
		
		reader.close();
	}
	
	private boolean can_construct(String str, Map<String, Boolean> cache) {
//		System.out.println("inside can_construct");
//		System.out.println(str);
		
		if(cache.get(str) != null )
			return true;
			
		if(str.length() == 0) {
			return true;
		}
		
		if (str.length() < minLength) {
			return false;
		}

		for(int i=1;i < str.length(); i++) {
			String prefix = str.substring(0, i);
			String suffix = str.substring(i);
			if( (this.wordStore.search(prefix) == true) &&
				( (this.wordStore.search(suffix) == true) || can_construct(suffix, cache) )
			) {
				cache.put(str, true);
				return true;				
			}
		}
					
		cache.put(str, false);
		return false;
	}

}
