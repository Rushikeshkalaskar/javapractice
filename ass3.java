import java.util.*;
import java.util.stream.*;
class ass3{
	public static void main(String []args){
		Scanner sc=new Scanner(System.in);
	List<String> list=new ArrayList<>();
	Collections.addAll(list,"ram","sham","swanand","shiv","tukaram");
	boolean found=false;
	System.out.println("search any name");
	String name=sc.nextLine();
	found=list.stream()
			.anyMatch(str->str.equals(name));
			if(found){
	System.out.println("found ");
			}			
else {
	System.out.println("not found");
}			
	}
}
