import java.util.*;

class HelloWorld{

     public static void main(String []args){
        String input="5 1 2 2 3 1";
	String[] temp=input.split(" ");
	int size = Integer.parseInt(temp[0]);

	HashSet<String> numbers = new HashSet<String>();
	HashSet<String> numbers2 = new HashSet<String>();

	for (int x=1; x<temp.length; x++) {
      		if(numbers.contains(temp[x])){
			numbers.remove(temp[x]);
			numbers2.add(temp[x]);
			continue;
		}
		numbers.add(temp[x]);
		if(numbers2.contains(temp[x])){
			numbers2.remove(temp[x]);
		}
    	}
	
	int suma=0;
	for (String x : numbers2){
		System.out.println(x);
		suma+=Integer.parseInt(x);
	}
	System.out.println(suma);
     }
}
