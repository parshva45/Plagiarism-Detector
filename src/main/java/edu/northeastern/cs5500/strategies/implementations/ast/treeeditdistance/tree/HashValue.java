/*
 * Created on Jun 22, 2005
 * Updated on Apr 6, 2018
 */
package edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance.tree;

/**
 * Simple class that stores a hash value.
 * 
 * @author augsten, namratabilurkar
 */
public class HashValue implements Comparable {

	
	/**
	 * Mask used to retrieve the value of the least significant byte of an integer number.  
	 */
	public final int LSByteMask = 255;
		
	private String hashvalue;
	
	/**
	 * Initialize the hash value with a string.
	 * 
	 * @param hashvalue hash value as a string
	 */
	public HashValue(String hashvalue) {
		this.hashvalue = hashvalue;
	}

	/**
	 * Initialize the hash value with an integer number.
	 * 
	 * @param h hash value as an integer 
	 * @param length length of the hash value in bytes
	 */
	public HashValue(long h, int length) {
		StringBuffer s = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			char c = (char)(h & LSByteMask);
			s.append(c);
			h = h >>> 8;
		}
		hashvalue = s.toString();
		
	}
	
	/**
	 * @return hash value as a string
	 */
	@Override
	public String toString() {
		if (hashvalue.charAt(hashvalue.length() -1) == ' ') {
			return hashvalue.substring(0, hashvalue.length() - 1) + (char)0;
		} else {
			return hashvalue;
		}
	}

	/**
	 * @param arg0 is the input
	 * @return 0 is input matches the object of the class, 1 is the input object is greater, else -1.
	 */
	public int compareTo(Object arg0) {
		return this.hashvalue.compareTo(((HashValue)arg0).toString());
	}

	/**
	 * Calculate the hash value
	 * @param length
	 * @return hash value of the string
	 */
	public static HashValue maxValue(int length) {
		String s = "";
		for (int i = 0; i < length; i++) {
			s += Character.MAX_VALUE; 
		}
		return new HashValue(s);
	}

	/**
	 * @param arg0 is the input object
	 * @return true, iff the input object matches the object of the class
	 */
	@Override
	public boolean equals(Object arg0) {
		return (this.toString()).equals(((HashValue)arg0).toString());
	}
	
	
	
}
