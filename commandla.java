import java.util.*;
class commandla{
	
	public static void main(String[]args){
		
		int size=Integer.parseInt(args[0]);
		int arr[]=new int[size];
		for(int i=1;i<=size;i++){
			
			int k=Integer.parseInt(args[i]);
			if(k%2==0){
			arr[i-1]=k;
			}
			else if(k%2!=0){
				arr[i-1]=k*2;
			}
		}
		for(int i=0;i<size;i++){
			System.out.print(arr[i]+"\t");
		}
	}
}