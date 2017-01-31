/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodríguez
 *
 * Programming Assignment #1
 *
 * The purpose of this assignment is helping you understand the nuances of the heap data structure studied in class.
 *
 * Christopher Kang
 *  
 */

package edu.cpp.cs.cs241.prog_assgmnt_1;

public interface Heap<V extends Comparable<V>> {
	
	public void add(V value);

	public V[] toArray(V[] array);

	public V remove();

	public void fromArray(V[] array);

	public V[] getSortedContents(V[] array);
	
}