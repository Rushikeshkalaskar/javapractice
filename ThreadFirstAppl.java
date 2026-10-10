class First extends Thread{
	public void run(){
		try{
			for(int i=1;i<=5;i++){
				System.out.println("first " + i+isAlive());
				sleep(10000);
			}
		}
	catch (Exception ex){
		System.out.println("error is "+ex);
	}
	}
}
class Second extends Thread{
	public void run(){
		try {
			for(int i=1;i<=30;i++){
				System.out.println("second = "+i);
				sleep(1000); 
			}
		}
		catch (Exception ex){
			System.out.println("error is "+ex);
		}
	}
}

public class ThreadFirstAppl{
	public static void main(String []args){
		First f=new First();
		f.start();
		Second s=new Second();
		s.start();
		
	}
}