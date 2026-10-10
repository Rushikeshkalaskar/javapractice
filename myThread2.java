class myThread2 extends Thread{
		public void run(){
			//String name =Thread.currentThread().getName();
			for(int i=1;i<=5;i++){
				System.out.println(Thread.currentThread().getName()+"run() executed "+i);
				
			
			}
		}
		public static void main(String[]args){
			myThread2 mth=new myThread2();
			Thread th=new Thread(mth,"first thread ");
			th.start();
			Thread th1=new Thread(mth,"second thread");
			th1.start();
		}
}