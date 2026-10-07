//import java

class NumForExcep{
	static int arr[];
	public static void main(String []args){
		try {
			String exx="123";
			int a=Integer.parseInt(exx);
			System.out.println("exception doesnt happened");
		}
		catch (NumberFormatException ex){
			System.out.println("exception doesnt happened");
					
			System.out.println("error is "+ex);
		}
	}
}