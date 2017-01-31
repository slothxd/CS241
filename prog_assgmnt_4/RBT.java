package edu.csupomona.cs.cs241.prog_assgmnt_4;


public class RBT<K extends Comparable<K>, V> implements Tree<K, V> {
		/* RED BLACK TREE 5 COMMANDMENTS
		 * 1. EVERY NODE HAS A COLOR, EITHER RED OR BLACK
		 * 2. THE ROOT IS ALWAYS BLACK
		 * 3. THE LEAVES ARE ALWAYS BLACK AND EMPTY. NEVER USE LEAF TO START ANY VALUE
		 * 4. IF A NODE IS RED, THEN ITS CHILDREN MUST BE BLACK
		 * 5. ANY PATH AND EVERY PATH, STARTING FROM THE ROOT TO ANY/EVERY LEAF, 
		 * 	  MUST HAVE SAME NUMBER OF BLACK NODES
		 */

	public static enum Color { RED, BLACK }


	private Node<K, V> root;
	private int size;
	
	// Constructor 
	public RBT(){
		root = null;
		size = 0;
	}
	
	/**
	 * Node class
	 * @param <K>
	 * @param <V>
	 */
	private class Node<K, V> {
		
		private K key;
		private V value;
		private Node<K, V> parent;
		private Node<K, V> leftChild;
		private Node<K, V> rightChild;
		private Color color;
		
		//Constructor overload
		private Node(K key, V value, Node<K, V> parent, Node<K, V> leftChild, Node<K, V> rightChild, Color color){
			this.key = key;
			this.value = value;
			this.parent = parent;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			this.color = color;
		}
		
		//////////////////////////
		//Mutator & Accessor	//
		//////////////////////////
		void setKey(K key){
			this.key = key;
		}
		void setValue(V value){
			this.value = value;
		}
		void setParentLink(Node<K, V> parent){
			this.parent = parent;
		}
		void setLeft(Node<K, V> leftChild){
			this.leftChild = leftChild;
		}
		void setRight(Node<K, V> rightChild){
			this.rightChild = rightChild;
		}
		void setColor(Color color){
			this.color = color;
		}
		
		K getKey(){
			return key;
		}
		V getValue(){
			return value;
		}
		Node<K, V> getParent(){
			return parent;
		}
		Node<K, V> getLeft(){
			return leftChild;
		}
		Node<K, V> getRight(){
			return rightChild;
		}
		
		Color getColor(){
			return color;
		}
		
	}
	
	/**
	 * To find node with given key
	 * @param key
	 * @return node
	 */
	private Node<K, V> findNode(K key){
		
		Node<K, V> current = root;
		
		while(current.getLeft() != null && current.getRight() != null){
			if(current.getKey().compareTo(key) > 0){	
				current = current.getLeft();
			}else if(current.getKey().compareTo(key) < 0){	
				current = current.getRight();
			}else{	
				return current;
			}
		}
		return null;
	}
	
	/**
	 * Swaping nodes
	 * @param first node
	 * @param second node
	 */
	private void swap(Node<K, V> x, Node<K, V> y){
		K tempKey = x.getKey();
		V tempValue = x.getValue();
		x.setKey(y.getKey());
		y.setKey(tempKey);
		x.setValue(y.getValue());
		y.setValue(tempValue);
	}
	/**
	 * rotate left the subtree based on the node with given key
	 * @param key
	 */
	private void rotateLeft(K key){
		
		Node<K, V> preFixNode = findNode(key);	

		if(preFixNode == null)
			return;
		
		Node<K, V> postFixNode = preFixNode.getRight();	
		
		if(postFixNode.getKey() == null)
			return;
		
		Color oldColor = preFixNode.getColor();
		Color newColor = postFixNode.getColor();
		

		preFixNode.setRight(postFixNode.getLeft());
		if(preFixNode.getRight().getKey() != null)
			preFixNode.getRight().setColor(newColor);	
		
		if(postFixNode.getLeft() != null){
			postFixNode.getLeft().setParentLink(preFixNode);
		}

		
		if(preFixNode.getParent() != null){
			
			postFixNode.setParentLink(preFixNode.getParent());
			
			if(preFixNode.getParent().getLeft() == preFixNode){
				preFixNode.getParent().setLeft(postFixNode);
				preFixNode.getParent().getLeft().setColor(oldColor);
			}else{
				preFixNode.getParent().setRight(postFixNode);
				preFixNode.getParent().getRight().setColor(oldColor);
			}
		}else{
			return;
		}
		
		postFixNode.setLeft(preFixNode);
		preFixNode.setParentLink(postFixNode);
	}
	/**
	 * rotate right the subtree based on the node with given key
	 * @param key
	 */

	private void rotateRight(K key){
		
	
		
		Node<K, V> preFixNode = findNode(key);	
		
		if(preFixNode == null)
			return;
		
		Node<K, V> postFixNode = preFixNode.getLeft();	
		if(postFixNode.getKey() == null)
			return;
		
		Color oldColor = preFixNode.getColor();
		Color newColor = postFixNode.getColor();
		
		preFixNode.setLeft(postFixNode.getRight());
		if(preFixNode.getLeft().getKey() != null)
			preFixNode.getLeft().setColor(newColor);	
		
		if(postFixNode.getRight() != null){
			postFixNode.getRight().setParentLink(preFixNode);
		}
		
		if(preFixNode.getParent() != null){
			
			postFixNode.setParentLink(preFixNode.getParent());
			
			if(preFixNode.getParent().getRight() == preFixNode){
				preFixNode.getParent().setRight(postFixNode);
				preFixNode.getParent().getRight().setColor(oldColor);
			}else{
				preFixNode.getParent().setLeft(postFixNode);
				preFixNode.getParent().getLeft().setColor(oldColor);
			}
		}else{
			return;
		}
		
		postFixNode.setRight(preFixNode);
		preFixNode.setParentLink(postFixNode);
		
	}
	

	/**
	 * To add a key and a value
	 * @param key
	 * @param value
	 */
	@Override


	public void add(K key, V value) {
		
		//Base case: The tree is empty
		if(size == 0){
			root = new Node<>(key, value, null, null, null, Color.BLACK);
			root.setLeft(new Node<>(null, null, root, null, null, Color.BLACK));
			root.setRight(new Node<>(null, null, root, null, null, Color.BLACK));
			size += 3;
		}
		else{

			Node<K, V> current = root;	
			Node<K, V> vistedNode;		
			
			while(current.getLeft() != null && current.getRight() != null){
				if(current.getKey().compareTo(key) > 0){	
					current = current.getLeft();
				}else{	
					current = current.getRight();
				}
			}
			if(current.getKey() == null){
				current.setKey(key);
				current.setValue(value);
				current.setLeft(new Node<>(null, null, current, null, null, Color.BLACK));
				current.setRight(new Node<>(null, null, current, null, null, Color.BLACK));
				
				if(current.getParent() != null && current.getParent().getColor() == Color.BLACK){
					current.setColor(Color.RED);
				}else {
					current.setColor(Color.BLACK);
				}
			}
			
			
			vistedNode = current;
			
			//Case 1: The node's parent is red and its uncle is red 
			while(vistedNode.getParent() != null && vistedNode.getParent().getParent() != null && 
					vistedNode.getParent().getParent().getLeft().getColor() == Color.RED && 
					vistedNode.getParent().getParent().getRight().getColor() == Color.RED){
				
				//Color the node's parent and uncle black
				vistedNode.getParent().getParent().getLeft().setColor(Color.BLACK);
				vistedNode.getParent().getParent().getRight().setColor(Color.BLACK);
				
				//Color the node's grand-parent red
				if(vistedNode.getParent().getParent() != root){
					vistedNode.getParent().getParent().setColor(Color.RED);
				}else{
					if(vistedNode.getParent().getParent().getLeft() == vistedNode.getParent()){
						vistedNode.getParent().getParent().getLeft().setColor(Color.RED);
					}else if(vistedNode.getParent().getParent().getRight() == vistedNode.getParent()){
						vistedNode.setColor(Color.RED);
					}
				}
				
				vistedNode = vistedNode.getParent();
			}
			
			vistedNode = current;
			
			// Case 2: The node's parent is red and its uncle is black and the node is a right child
			
			while(vistedNode.getParent() != null && vistedNode.getParent().getParent() != null && 
					((vistedNode.getParent().getParent().getLeft().getColor() == Color.RED &&
					vistedNode.getParent().getParent().getRight().getColor() == Color.BLACK) ||
					(vistedNode.getParent().getParent().getRight().getColor() == Color.RED &&
					vistedNode.getParent().getParent().getLeft().getColor() == Color.BLACK)) &&
					vistedNode.getParent().getRight() == vistedNode){
				
				if(vistedNode.getParent() != root)
					rotateLeft(vistedNode.getParent().getKey());	
				
				vistedNode = vistedNode.getParent();
			}
			
			vistedNode = current;
			
			// Case 3:  parent is red and uncle is black and the node is a left child 
			
			while(vistedNode.getParent() != null && vistedNode.getParent().getParent() != null &&
					((vistedNode.getParent().getParent().getLeft().getColor() == Color.RED &&
					vistedNode.getParent().getParent().getRight().getColor() == Color.BLACK) ||
					(vistedNode.getParent().getParent().getRight().getColor() == Color.RED &&
					vistedNode.getParent().getParent().getLeft().getColor() == Color.BLACK))&&
					vistedNode.getParent().getLeft() == vistedNode){
				
				if(vistedNode.getParent().getParent() != root)
					rotateRight(vistedNode.getParent().getParent().getKey());	
				
				if(vistedNode.getParent().getParent() != root){
					vistedNode.getParent().setColor(Color.BLACK); 		
					vistedNode.getParent().getParent().setColor(Color.RED); 		
				}
				
				vistedNode = vistedNode.getParent();
			}
			
			size += 2;
		}
	}
	
	/**
	 * To remove a key
	 * @param key
	 * @return value of removed key
	 */

	public V remove(K key) {
		
		V value = null;
		
		if(size > 0){
			
			if(size == 3){
				if(root.getKey() == key){
					value = root.getValue();
					root.setLeft(null);
					root.setRight(null);
					root = null;
					size -= 3;
				}
			}
			else{
				Node<K, V> current;		
				Node<K, V> cursor;		
				Node<K, V> pointerNode;	
				Node<K, V> vistedNode;		
				Node<K, V> foundNode = findNode(key);
				
				if(foundNode == null)
					return null;
				
				value = foundNode.getValue();
				
				current = foundNode;
				
				if(current.getLeft().getKey() == null && current.getRight().getKey() == null){	
					
					pointerNode = current.getParent();
					
					if(pointerNode.getKey().compareTo(key) > 0){	
						pointerNode.setLeft(new Node<>(null, null, pointerNode, null, null, Color.BLACK));
					}else{	
						pointerNode.setRight(new Node<>(null, null, pointerNode, null, null, Color.BLACK));
					}
					
					size -= 2;
					
				}else if(current.getLeft().getKey() != null && current.getRight().getKey() == null){	
					
					pointerNode = current.getLeft();
					
					if(current.getParent() != null){
						
						pointerNode.setParentLink(current.getParent());
						
						if(current.getParent().getKey().compareTo(pointerNode.getKey()) > 0){
							current.getParent().setLeft(pointerNode);
						}else{
							current.getParent().setRight(pointerNode);
						}
						
					}else{
						
						cursor = current.getLeft();
						while(cursor.getRight().getKey() != null){
							cursor = cursor.getRight();
						}
						
						swap(cursor, current);
						
						pointerNode = cursor.getParent();
						

						if(cursor == pointerNode.getLeft()){
							pointerNode.setLeft(new Node<>(null, null, pointerNode, null, null, Color.BLACK));
						}else
							pointerNode.setRight(new Node<>(null, null, pointerNode, null, null, Color.BLACK));
					}
					
					size -= 2;
					
				}else if(current.getLeft().getKey() == null && current.getRight().getKey() != null){	
					
					if(current.getParent() != null){
						
						pointerNode = current.getRight();
						
						pointerNode.setParentLink(current.getParent());
					
						if(current.getParent() != null && current.getParent().getKey().compareTo(pointerNode.getKey()) > 0){
							current.getParent().setLeft(pointerNode);
						}else if(current.getParent() != null && current.getParent().getKey().compareTo(pointerNode.getKey()) <= 0){
							current.getParent().setRight(pointerNode);
						}
						
					}else{	
						
						cursor = current.getRight();
						while(cursor.getLeft().getKey() != null){
							cursor = cursor.getLeft();
						}
						
						swap(cursor, current);
						
						pointerNode = cursor.getParent();
						

						if(cursor == pointerNode.getLeft()){
							pointerNode.setLeft(new Node<>(null, null, pointerNode, null, null, Color.BLACK));
						}else
							pointerNode.setRight(new Node<>(null, null, pointerNode, null, null, Color.BLACK));
					}
					
					size -= 2;
					
				}else{
					
					cursor = current.getLeft();
					while(cursor.getRight().getKey() != null){
						cursor = cursor.getRight();
					}
					
					swap(cursor, current);
					
					pointerNode = cursor.getParent();
					

					if(cursor == pointerNode.getLeft()){
						pointerNode.setLeft(new Node<>(null, null, pointerNode, null, null, Color.BLACK));
					}else
						pointerNode.setRight(new Node<>(null, null, pointerNode, null, null, Color.BLACK));
					
					size -= 2;
				}
				

				if(findNode(key).getColor() == Color.RED){
					return value;
				}
				

				
				
				// Case 1: The node's sibling is red
				 
				if(pointerNode.getParent() != null && pointerNode.getParent().getLeft() == pointerNode){	
					while(pointerNode.getParent().getRight().getColor() == Color.RED){
						
				
						rotateLeft(pointerNode.getParent().getKey());
						pointerNode.getParent().getRight().setColor(Color.BLACK);
						pointerNode.getParent().setColor(Color.RED);
					}
					
				}else if(pointerNode.getParent() != null && pointerNode.getParent().getRight() == pointerNode){
					while(pointerNode.getParent().getLeft().getColor() == Color.RED){
						
				
						rotateRight(pointerNode.getParent().getKey());
						pointerNode.getParent().getLeft().setColor(Color.BLACK);
						pointerNode.getParent().setColor(Color.RED);
					}
				}
				
				
				 // Case 2: The node's sibling is black and it's sibling's children are both black
				 
				vistedNode = pointerNode;
				
				if(vistedNode.getParent() != null && vistedNode.getParent().getLeft() == vistedNode){	
					while(vistedNode.getParent().getRight().getColor() == Color.BLACK &&
							vistedNode.getParent().getRight().getLeft().getColor() == Color.BLACK &&
							vistedNode.getParent().getRight().getRight().getColor() == Color.BLACK){

								vistedNode.getParent().getRight().setColor(Color.RED);
								vistedNode = vistedNode.getParent();
						
					}
				}else if(vistedNode.getParent() != null && vistedNode.getParent().getRight() == vistedNode){	
					while(vistedNode.getParent().getLeft().getColor() == Color.BLACK &&
							vistedNode.getParent().getLeft().getLeft().getColor() == Color.BLACK &&
							vistedNode.getParent().getLeft().getRight().getColor() == Color.BLACK){

								vistedNode.getParent().getLeft().setColor(Color.RED);
								vistedNode = vistedNode.getParent();
					}
				}
				
				
				 //Case 3: The node's sibling is black, and it's sibling's internal child is red
				 
				 
				vistedNode = pointerNode;
				
				if(vistedNode.getParent() != null && vistedNode.getParent().getLeft() == vistedNode){	
					while(vistedNode.getParent().getRight().getColor() == Color.BLACK &&
							vistedNode.getParent().getRight().getLeft().getColor() == Color.RED &&
							vistedNode.getParent().getRight().getRight().getColor() == Color.BLACK){
						
								rotateRight(vistedNode.getParent().getRight().getKey());
								vistedNode.getParent().getRight().setColor(Color.BLACK); 		
								vistedNode.getParent().getRight().getRight().setColor(Color.RED);	
						
					}
				}else if(vistedNode.getParent() != null && vistedNode.getParent().getRight() == vistedNode){	
					while(vistedNode.getParent().getLeft().getColor() == Color.BLACK &&
							vistedNode.getParent().getLeft().getLeft().getColor() == Color.BLACK &&
							vistedNode.getParent().getLeft().getRight().getColor() == Color.RED){
						
								rotateLeft(vistedNode.getParent().getLeft().getKey());
								vistedNode.getParent().getLeft().setColor(Color.BLACK); 		
								vistedNode.getParent().getLeft().getLeft().setColor(Color.RED);	
						}
					}
				
				vistedNode = pointerNode;
			}
		}
		
		return value;
	}
	
	/**
	 * look up for the key
	 * @param key
	 * @return value of removed key
	 */
	@Override
	public V lookup(K key) {
		V value = null;
		value = findNode(key).getValue();
		return value;
	}

	
	/**
	 * Return a string of pyramid looking tree
	 * @return string of the values in the tree
	 */
	@Override
public String toPrettyString() {
		return null;
	}
}