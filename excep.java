class excep{
	static void method2(){
			System.out.println("method 2 executed");
		
		try {
			System.out.println("try block executed");
			int result=15/0;
		}
		catch (ArithmeticException e){
			System.out.println("arithmetic exception happened");
		}
		finally {
			System.out.println("finally block executed");
		}}
		static void method1(){
			System.out.println("method 1 executed");
			excep.method2();
		}
	public static void main(String []args){
			System.out.println("main method executed");
		
		excep.method1();
		
		
	}
}