import java.lang.*;

class threadApp implements Runnable{
	
	
	
	public void run(){
		String name = Thread.currentThread().getName();
		for (int i=1;i<=5;i++){
			System.out.println(name +" executed run():"+i);
		}
	}
	public static void main(String[]args){
		threadApp thp=new threadApp();
		Thread th1=new Thread(thp,"first thread");
		th1.start();
		
		Thread t1 =new Thread(thp,"Second thread");
		t1.start();
	}
	
		
} 
