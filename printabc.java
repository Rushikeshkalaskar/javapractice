class printabc{
	public static void main(String []args){
		for(int i=65 ;i<=122;i++){
			if(i==65){
			System.out.println("Capital letters ");}
			if(i>=65 && i<=90 ){
				System.out.print((char) i + " \t");
			}
			if(i==96){
			System.out.println("\nsmall letters");
			}
			if( i>96 && i<=122){
				System.out.print((char) i + " \t");
			}
		}
	}
}