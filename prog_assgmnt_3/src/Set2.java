import java.util.ArrayList;

public class Set2<V> {
  ArrayList<V> arrlist = new ArrayList<V>();
  public void add(V value) {
   if(!contains(value)){
   arrlist.add(value);}
   else 
    System.out.println("The Element is already in the System"); 
  }
  
  public void remove(V value) {
  if(arrlist.contains(value)){
   arrlist.remove(value);
   } 
  }
  
  public boolean contains(V Value) {
    if(arrlist.contains(Value))
     return true;
    else 
     return false;
  }

  public int size() {
   return arrlist.size();
  }
  public boolean isEmpty(){
   if(size() <= 0){
    return true;
   }
   return false;
   
  }
  1
  }