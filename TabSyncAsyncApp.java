class Table{
	public synchronized void showTable(int x){
		try{
			for(int i=1;i<=10;i++){
				System.out.println(i + " X " + x + " = " + (i*x));
				Thread.sleep(1000);
		}}
			catch(Exception ex){
				System.out.println("error is "+ ex);
			}
		}
	}

class Two extends Thread{
	Table table;
	void setTable(Table table){
		this.table=table;
	}
	public void run(){
		table.showTable(2);
	}
}
class Three extends Thread{
	Table table;
	void setTable(Table table){
		this.table=table;
	}
	public void run(){
		table.showTable(3);
	}
}
public class TabSyncAsyncApp{
	public static void main(String []args){
		Table t=new Table();
		
		Three th=new Three();
		th.setTable(t);
		th.start();
		Two tw=new Two();
		tw.setTable(t);
		tw.start();
	}
}