import controller.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {

    // Path
    private static final String PATH = "D://string.txt";

    // Controller
    private static final Controller controller = new Controller();

    // Main program
    public static void main(String[] args) {
        try {
            // Intialize main BufferedReader for this program
            BufferedReader bf = new BufferedReader(new FileReader(PATH));

            // Read each line, refractor each and sout
            String line = bf.readLine();
            while (line != null) {
                if (!line.isEmpty()) {
                    String refLine = controller.refractor(line);
                    System.out.println(refLine);
                }
                line = bf.readLine();
            }
        } catch (IOException e) {
            System.out.println("File not found");
            System.exit(-1);
        }
    }
}
