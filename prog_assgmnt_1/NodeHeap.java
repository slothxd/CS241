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

import java.util.LinkedList;
import java.util.Queue;

public class NodeHeap< V extends Comparable<V>> implements Heap<V> {

	private Queue<Node<V>> queue;
	private Node<V> root;
	private int counter = 0;
	
	public NodeHeap() {
		queue = new LinkedList<Node<V>>();
	}

		
	public void add(V value) { 
		
		boolean changed = false;
		counter++;
		
		if (root == null) {
			root = new Node<V>(value);
		} 
		else {
			queue.add(root);
			Node<V> newNode = new Node<V>(value);
			Node<V> temp; 
			
			while (!changed) {
				temp = queue.poll();
				if(temp.leftChild == null) {
					temp.leftChild = newNode;
					newNode.parent = temp;
					changed = true;
				} 
				else if(temp.rightChild == null) {
					temp.rightChild = newNode;
					newNode.parent = temp;
					changed = true;
				} 
				else if(temp.leftChild != null) {
					queue.add(temp.leftChild);
					if(temp.leftChild != null) {
						queue.add(temp.rightChild);
					}
				} 
			}
			
			queue.clear();
			siftUp(newNode);
		}
	}
	

	public V[] toArray(V[] array) {
		
		V[] result = (V[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), counter);
		Node<V> temp;
		
		if (root != null) {
			queue.add(root);
			
		for (int i = 0; i < counter; ++i) {
				temp = queue.poll();
				result[i] = temp.value;
				
		if (temp.leftChild != null) {
				queue.add(temp.leftChild);
					
		if (temp.rightChild != null) {
				queue.add(temp.rightChild);
			}
		}
		} 
		}
		queue.clear();
		return result;
	}
	
	
	public V remove() {
		
		counter --; 
		
		V removedValue = null;
		Node<V> currentNode;
		Node<V> temp = null;
		
		if (root != null) {

			removedValue = root.value;
			queue.add(root);
			root.value = null;
			
			while (root.value == null) { 			
				
			if (!queue.isEmpty()) {
				currentNode = queue.poll();
				
			if (currentNode.leftChild != null) {
				queue.add(currentNode.leftChild);
						
			if (currentNode.rightChild != null) {
				queue.add(currentNode.rightChild);
				temp = currentNode.rightChild;
			} 
			else {
				temp = currentNode.leftChild;
				}
			}
			} 
			else {
				root.value = temp.value;
				temp = null; 
			}
			} 
			queue.clear();
		}
		return removedValue;
	}
	
	


	public void fromArray(V[] array) { 
		
		for (int i = 0; i < array.length; ++i) {
			add(array[i]);
		}
	}

	public V[] getSortedContents(V[] array) {
		
		V[] result = null;
		
		HeapSort<V> h = new HeapSort<V>();
		result = h.heapSort(array, counter);
		
		return result;
	}
	
	public void siftUp(Node<V> node) { 

		boolean changed = false;
		
			while (!changed) {
				
			if (node.value.compareTo(node.parent.value) == 1) {
				V tempValue = node.parent.value;
				node.parent.value = node.value;
				node.value = tempValue;
				node = node.parent; 
					
			if (node.parent == null) {
				changed = true;
				}
			} 
			else 
			{
				changed = true;
			}
		}
	}
	
	public boolean isLeaf(Node<V> node) {
		return (node.leftChild == null) && (node.rightChild == null);
	}
	
	public boolean isEmpty() {
		if (root != null) {
			return false;
		}
		return true;
	}


}