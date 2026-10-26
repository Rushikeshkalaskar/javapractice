class printabc{
	public static void main(String []args){
		for(int i=65 ;i<=122;i++){
			if(i>=65 && i<=90 || i>96 && i<=122){
				System.out.print((char) i + " \t");
			}
		}
	}
}