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

public class HeapSort<V extends Comparable<V>> {
	
	public V[] heapSort(V[] array, int count) {

		V[] result = null;
		int last = (count - 1);

		if (count > 0) {
			while (last > 0) {
				swap(array, 0, last);
				last--;
				siftDown(array, last);
			}
			result = array;
		}	
		return result;
	}
	
	private int left(int n) {
		return ((n * 2) + 1);
	}

	private int right(int n) {
		return ((n * 2) + 2);
	}

	private void swap(V[] array, int j, int k) {
		V tempArray = array[j];
		array[j] = array[k];
		array[k] = tempArray;
	}
	
	public void siftDown(V[] array, int last) {

		int bound = last + 1;
		int i = 0;
		int swap;

		while (left(i) < bound || right(i) < bound) {
			if (right(i) < bound) {
			if (array[left(i)].compareTo(array[right(i)]) >= 0) {
				swap = left(i);
			} else {
				swap = right(i);
				}
			} else {
				swap = left(i);
			}
			
			if (array[i].compareTo(array[swap]) == -1) {
				swap(array, i, swap);
				i = swap;
			} else {
				break;
			}
		}
	}	
	
}