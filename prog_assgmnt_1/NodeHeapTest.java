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

public class NodeHeapTest {
	
	public static void main(String[] args) {
		NodeHeap<Integer> nh = new NodeHeap<Integer>();
		nh.add(37);
		nh.add(3);
		nh.add(13);
		nh.add(6);
		nh.add(7);
		nh.add(88);
		nh.add(69);
		nh.add(420);

		Integer[] array = new Integer[1];
		array = nh.toArray(array);

		System.out.println("Heap Sorting...");
		array = nh.getSortedContents(array);
		
		for (int i = 0; i < array.length; ++i) {
			System.out.print(array[i] + " ");
		}
	}
}
	