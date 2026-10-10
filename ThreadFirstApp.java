class First extends Thread {
	public void run(){
	try{
		for(int i=1;i<=5;i++){
			System.out.println("first "+i);
			if(i==3){
				stop();
			}
			sleep(10000);
		}
	}
	catch (Exception ex ){
		System.out.println("arror is "+ex);
	}
	}
}
class Second extends Thread{
	public void run(){
		try{
			for(int i=1;i<=50;i++){
				System.out.println("second "+i);
				sleep(1000);
			}
		}
		catch (Exception ex ){
			System.out.println("error is "+ex);
		}
	}
}
public class ThreadFirstApp{
	public static void main(String []args)
	{
		First f=new First();
		f.start();
		Second s=new Second();
		s.start();
	}
}