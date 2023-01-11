import java.io.File;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;

public class write {
	
	public static void main(String... args) {
		try {
			File out = new File("out.txt");
		
			FileOutputStream fos = new FileOutputStream(out, true);
		
			fos.write(65);
		} catch (IOException e) {
			;
		}
		
	}
	
}