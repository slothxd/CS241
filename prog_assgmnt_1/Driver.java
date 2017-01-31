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

import java.util.Scanner;

public class Driver {

	NodeHeap<Customer> pq = new NodeHeap();
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Driver d = new Driver();
		d.chooseMenu();
	}

	
	public void printMenu() {
		System.out.println("=================================");		
		System.out.println("What would you like to do?");
		System.out.println(" 1. Add Customer to queue");
		System.out.println(" 2. Seat Customer");
		System.out.println(" 3. Exit program");
		System.out.print("\nEnter a command (1~3): ");
	}
	
	public void chooseMenu() {
		int choice = 0;
		System.out.println();

		while (choice == 0) {
			printMenu();
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				addName();
				choice = 0;
				break;
			case 2:
				deQueue();
				choice = 0;
				break;
			case 3:
				System.out.println("Program will now exit.");
				System.exit(0);
			default:
				choice = 0;
				System.out.println("Invalid Input.");
				break;
			}
		}
	}
	

	
	public int choosePriority() {
		int choice = 0;
		int priority = 0;
		
		while (choice == 0) {

			System.out.println("1. VIP");
			System.out.println("2. Advance Call: customers who called in advance");
			System.out.println("3. Seniors");
			System.out.println("4. Veteran");
			System.out.println("5. Large Groups (more than 4)");
			System.out.println("6. Families with children");
			System.out.println("7. Everyone else");
			System.out.println();
			System.out.print("Enter Customer Priority: ");
			choice = sc.nextInt();
			sc.nextLine();
			
			switch (choice) {
			case 1:
				priority = 7;
				break;
			case 2:
				priority = 6;
				break;
			case 3:
				priority = 5;
				break;
			case 4:
				priority = 4;
				break;
			case 5:
				priority = 3;
				break;
			case 6:
				priority = 3;
				break;
			case 7:
				priority = 1;
				break;
			default:
				System.out.println("Invalid Input.");
				choice = 0;
				break;
			}
		}
		return priority;
	}
	
	public void addName() {
		System.out.print("Enter Customer Name: ");
		String name = sc.nextLine();
		System.out.println("=================================");
		int priority = choosePriority();
		Customer customer = new Customer(name, priority);
		pq.add(customer);
	}
	
	public void deQueue() {
		Customer customer = pq.remove();
		System.out.println(customer.getName() + " is on top of the queue");
		
	}
}