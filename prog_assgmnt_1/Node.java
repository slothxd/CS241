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

public class Node<V extends Comparable<V>> {

	protected V value;
	protected Node<V> parent;
	protected Node<V> leftChild;
	protected Node<V> rightChild;

	Node(V initValue) {
		value = initValue;
		parent = null;
		leftChild = null;
		rightChild = null;
	}
}