import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {


 public static void main(String[] args) {
  // TODO Auto-generated method stub
  HashSet<Integer> h = new HashSet();
  //Set2<Integer> s = new Set2<Integer>();
  //Map<Integer,Integer> m = new Map<Integer,Integer>();
  //HashMap hm = new HashMap();
  
  long initTime = System.currentTimeMillis();
  for(int i = 0 ; i <1000000; ++i){
   //s.add(i);
   //hm.put(i, null);
   //m.add(i, i);
	  h.add(i);
  }
  long finalTime = System.currentTimeMillis();
  System.out.println(finalTime - initTime);
 }

}