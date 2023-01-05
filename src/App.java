import controller.Controller;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class App {

    // Path
    private static final String IN = System.getProperty("user.dir") + "\\input.txt";
    private static final String OUT = System.getProperty("user.dir") + "\\output.txt";
    
    // Controller
    private static final Controller controller = new Controller();

    // Main program
    public static void main(String[] args) {
        try {
            // Init main BufferedReader and BufferedWriter for this program
            BufferedReader br = new BufferedReader(new FileReader(IN));
            BufferedWriter bw = new BufferedWriter(new FileWriter(OUT, true));
            
            // Read each line, repair each and sout
            String line = br.readLine();
            while (line != null) {
                if (!line.isEmpty()) {
                    String repLine = controller.repair(line);
                    bw.write(repLine);
                    System.out.println(repLine);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("File not found");
            System.exit(-1);
        }
    }
}
