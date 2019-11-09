package rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestProperties {
	public static void main(String[] args) throws FileNotFoundException {
	
		FileInputStream fis = new FileInputStream(System.getProperty("config.properties"));
		System.out.println(fis);
		
		
		
		
	}

	
}
