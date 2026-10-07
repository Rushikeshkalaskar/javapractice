class excep{
	
	public static void main(String []args){
			
		try {
			System.out.println("try block executed");
			int result=15/0;
		}
		
		finally {
			System.out.println("finally block executed");
		}
		
		
	}
}