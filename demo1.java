class demo{
	static void method2(){
		int result =10/0;
			System.out.println(result);
	}
	static void method1(){
		demo.method2();
	}
	public static void main(String []args){
		demo.method1();
	}
}