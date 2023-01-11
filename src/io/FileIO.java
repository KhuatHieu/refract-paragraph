package io;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
    // Read data from file
    public String readFile(final String IN) {
        String data = "";
        
        try {
            // Init main BufferedReader for this program
            BufferedReader br = new BufferedReader(new FileReader(IN));
            
            String line = br.readLine();
            while (line != null) {
                data = data + line + "\n";
                line = br.readLine();
            }
            
            br.close();
        } catch (IOException e) {
            System.out.println("File not found");
            System.exit(-1);
        }
        
        return data;
    }
    
    public void writeFile(final String OUT, final String fixedData) {
        try {
            // Init main BufferedReader for this program
            BufferedWriter bw = new BufferedWriter(new FileWriter(OUT));

            String[] fixedDataLine = fixedData.split("\n");
            for (String line : fixedDataLine) {
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("File not found");
            System.exit(-1);
        }
    }
        
}
