class first extends Thread{
	public void run(){
		try{
		for(int i=1;i<=5;i++){
			System.out.println(" first "+i);
			sleep(10000);//10 second
		}}
		catch(Exception ex){
			System.out.println("error is "+ex);
		}
	}
	
}
class Second extends Thread{
	public void run(){
		try{
			for(int i=1;i<=5;i++){
				System.out.println("second "+i);
				sleep(5000);
				
			}
		}
		catch(Exception ex) {
			System.out.println("error is "+ex);
		}
	}
}
public class ThreadFirstApplication{
	public static void main(String []args){
	first f=new first();
	f.start();//call run internally and excute thread logics

	Second s=new Second();
	s.start();
}}