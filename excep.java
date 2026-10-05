class excep{
	static void method2(){
			System.out.println("method 2 executed zalleli ahe");
		
		try {
			System.out.println("try block executed");
			int result=15/0;
		}
		
		finally {
			System.out.println("finally block executed");
		}}
		static void method1(){
			System.out.println("method 1 execute zali ");
			try{
				System.out.println("method madhe try block");
			excep.method2();}
			catch (ArithmeticException e){
			System.out.println("arithmetic exception happened");
		}
		}
	public static void main(String []args){
			System.out.println("main method executed");
		
		excep.method1();
		
		
	}
}