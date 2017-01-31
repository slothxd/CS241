
public class Set<V> {
	private V arrayElement[];
    int size =0;
    
    public Set(){

        this.arrayElement = null;

  }

  public Set(V[] element){

        arrayElement = element;
        size = arrayElement.length;

  }
  public void add(V value){
  if(!contains(value)){

      if(size == arrayElement.length){

            incrementArray();

      }

      arrayElement[size++] = value;

      }

}
/*  public void remove(V value){
	    for (int i = 0; i < size; i++) {
	      if (arrayElement[i] == value) {
	    	  arrayElement[i] = null;
	        size--;
	        //shift element after deleting
	        for (int j = i; j < size; j++) {
	        	arrayElement[j] = arrayElement[j + 1];
	        	}
	    }
	  }
  }
  */
  
  
  public boolean contains(V value){
 
      if (value == null) {
          for (int i = 0; i < size; i++)
            if (arrayElement[i]==null)
                return true;
      } else {

          for (int i = 0; i < size; i++)

            if (value.equals(arrayElement[i]))

                return true;

      }

      return false;

   

}
  
  public int size(){

      if(arrayElement != null){
            return arrayElement.length;
      }else{
            return 0;
      }

}
  
  
  public boolean isEmpty(){

      if(arrayElement == null || arrayElement.length == 0 )
      return true;

      else{
    	  return false;
      }

}
  
//helper function
private void incrementArray(){

    V[] tempArray = arrayElement;

    int tempSize=size+5;

     arrayElement =(V[]) new Object[tempSize];

     System.arraycopy(tempArray, 0, arrayElement, 0, size);
}


}
