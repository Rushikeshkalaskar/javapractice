import java.util.*;
import java.util.stream.*;
class ass2{
	public static void main(String[]args){
		List<Integer> list=new ArrayList<Integer>();
		Collections.addAll(list,10,55,60,25,80,45,90,30);
		List<Integer> list2=list.stream()
								.filter(i->i>50)
								.collect(Collectors.toList());
		for(Integer i:list2){
			System.out.println(i);
		}
	}
}