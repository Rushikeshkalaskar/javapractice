import java.util.*;
class largestElement{
	public static void main(String []args){
		int arr[]=new int[]{-3,-1,-3,-52,-2,-1};
		int max=Integer.MIN_VALUE;
		int smax=Integer.MIN_VALUE;
		for(int i=0;i<arr.length;i++){
			if(arr[i]>max){
				
				
				smax=max;
				max=arr[i];
			}
			else if(smax<arr[i] &&max>arr[i]){
				
				smax=arr[i];
			}
		}
		System.out.println(smax);
		System.out.println(max);
		
	}
}