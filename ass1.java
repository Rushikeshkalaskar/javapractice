import java.util.*;
import java.util.stream.*;
class ass1{
	public static void main(String []args){
		List<Integer> list=new ArrayList<Integer>(Arrays.asList(10,15,20,25,30,40,45));
		
		List<Integer>list2=list
			.stream()
			.filter(i->i%2==0)
			.collect(Collectors.toList());
			
			System.out.println(list2);
	}
}
