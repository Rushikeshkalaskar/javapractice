import java.io.FileReader;
import java.io.IOException;

class checkedExcep{
	public static void main(String []args){
		try {
			FileReader fr =new FileReader("data.txt");
		}
		catch(IOException e){
			System.out.println("io exception happened");
		}
	}

}