package com.demo.codesamples;

import com.demo.codesamples.challenge.FindLongestCompoundString;

/**
 * Find Longest compound word in a file made from existing word.. !
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		String filename = "wordsforproblem.txt"; //present in src/main/resources directory... 
		Long n_top = 2L; // num of top length numbers you want to print.. 
        
		FindLongestCompoundString f2 = new FindLongestCompoundString();
		f2.getOutput(filename,n_top);
    }
}
