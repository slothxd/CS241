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

public class Customer implements Comparable<Customer> {

	private String name;
	private int priority;

	public Customer(String n, int p) {
		this.name = n;
		this.priority = p;
	}

	public int compareTo(Customer c) {
		
		int result = 0;
		int x = this.getPriority();
		int y = c.getPriority();
		
		if (x > y) {
			result = 1;
		} else if (x == y) {
			result =  0;
		} else if (x < y) {
			result = -1;
		}
		
		return result;
	}

	public String getName() {
		return name;
	}

	public int getPriority() {
		return priority;
	}
	
	public String toString(Customer customer) {
		return ("Name: " + name + " -- " + "Priority: " + priority);
	}
}